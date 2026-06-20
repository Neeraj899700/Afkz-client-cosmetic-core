package dev.afkz.mixin;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "init", at = @At("TAIL"))
    private void afkz_addCosmeticsButton(CallbackInfo ci) {
        Screen screen = (Screen) (Object) this;
        Button btn = Button.builder(
            Component.literal("Cosmetics"),
            b -> screen.minecraft.setScreen(new dev.afkz.screen.CosmeticScreen())
        ).bounds(screen.width / 2 - 100, screen.height / 2 + 24, 200, 20).build();
        screen.addRenderableWidget(btn);
    }
}
