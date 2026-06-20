package dev.afkz.mixin;

import dev.afkz.screen.CosmeticScreen;
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
    private void addCosmeticsButton(CallbackInfo ci) {
        Screen self = (Screen) (Object) this;
        int centerX = self.width / 2;
        int centerY = self.height / 2;

        Button cosmeticsButton = Button.builder(
            Component.literal("Cosmetics"),
            button -> self.minecraft.setScreen(new CosmeticScreen())
        ).bounds(centerX - 100, centerY + 24, 200, 20).build();

        self.addRenderableWidget(cosmeticsButton);
    }
}
