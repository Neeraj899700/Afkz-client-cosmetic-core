package dev.afkz.mixin;

import dev.afkz.AfkzClientCosmeticCore;
import dev.afkz.screen.CosmeticScreen;
import net.minecraft.client.Minecraft;
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
        AfkzClientCosmeticCore.LOGGER.info("[Afkz] TitleScreen mixin fired!");
        try {
            TitleScreen self = (TitleScreen) (Object) this;
            ScreenAccessor accessor = (ScreenAccessor) self;
            Minecraft mc = Minecraft.getInstance();
            int x = self.width / 2 - 100;
            int y = self.height / 2 + 24;
            AfkzClientCosmeticCore.LOGGER.info("[Afkz] Adding button at ({}, {})", x, y);

            Button btn = Button.builder(
                Component.literal("Cosmetics"),
                b -> {
                    AfkzClientCosmeticCore.LOGGER.info("[Afkz] Cosmetics button clicked!");
                    mc.setScreen(new CosmeticScreen());
                }
            ).bounds(x, y, 200, 20).build();
            accessor.callAddRenderableWidget(btn);

            AfkzClientCosmeticCore.LOGGER.info("[Afkz] Button added!");
        } catch (Exception e) {
            AfkzClientCosmeticCore.LOGGER.error("[Afkz] Failed to add button!", e);
        }
    }
}
