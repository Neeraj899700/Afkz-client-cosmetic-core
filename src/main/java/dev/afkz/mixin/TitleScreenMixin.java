package dev.afkz.mixin;

import dev.afkz.screen.CosmeticScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    @Shadow protected Minecraft minecraft;

    protected TitleScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void addCosmeticsButton(CallbackInfo ci) {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        Button cosmeticsButton = Button.builder(
            Component.literal("§6§lCosmetics"),
            button -> {
                this.minecraft.setScreen(new CosmeticScreen());
            }
        ).bounds(centerX - 100, centerY + 24, 200, 20).build();

        this.addRenderableWidget(cosmeticsButton);
    }
}
