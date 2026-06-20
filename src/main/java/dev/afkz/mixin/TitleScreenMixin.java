package dev.afkz.mixin;

import dev.afkz.AfkzClientCosmeticCore;
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
    private void afkz_addCosmeticsButton(CallbackInfo ci) {
        AfkzClientCosmeticCore.LOGGER.info("[Afkz] TitleScreen init detected! Adding Cosmetics button...");
        Button btn = Button.builder(
            Component.literal("Cosmetics"),
            b -> this.minecraft.setScreen(new CosmeticScreen())
        ).bounds(this.width / 2 - 100, this.height / 2 - 100, 200, 20).build();
        this.addRenderableWidget(btn);
        AfkzClientCosmeticCore.LOGGER.info("[Afkz] Cosmetics button added at y={}", this.height / 2 - 100);
    }
}
