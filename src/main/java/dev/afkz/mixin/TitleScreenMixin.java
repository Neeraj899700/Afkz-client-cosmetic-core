package dev.afkz.mixin;

import dev.afkz.screen.CosmeticScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "init", at = @At("TAIL"))
    private void addCosmeticsButton(CallbackInfo ci) {
        TitleScreen screen = (TitleScreen) (Object) this;

        Button cosmeticsButton = Button.builder(
            Component.literal("§6§lCosmetics"),
            button -> {
                screen.minecraft.setScreen(new CosmeticScreen());
            }
        ).bounds(
            screen.width / 2 - 100,
            screen.height / 2 + 24,
            200,
            20
        ).build();

        screen.addRenderableWidget(cosmeticsButton);
    }
}
