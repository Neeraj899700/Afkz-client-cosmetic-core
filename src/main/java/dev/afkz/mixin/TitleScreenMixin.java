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

import java.lang.reflect.Method;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    private static Method addRenderableWidgetMethod;

    static {
        try {
            for (Method m : Screen.class.getDeclaredMethods()) {
                if (m.getName().equals("addRenderableWidget") && m.getParameterCount() == 1) {
                    addRenderableWidgetMethod = m;
                    addRenderableWidgetMethod.setAccessible(true);
                    AfkzClientCosmeticCore.LOGGER.info("[Afkz] Found addRenderableWidget via reflection");
                    break;
                }
            }
        } catch (Exception e) {
            AfkzClientCosmeticCore.LOGGER.error("[Afkz] Reflection setup failed", e);
        }
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void afkz_addCosmeticsButton(CallbackInfo ci) {
        AfkzClientCosmeticCore.LOGGER.info("[Afkz] TitleScreen mixin fired!");
        try {
            TitleScreen self = (TitleScreen) (Object) this;
            Minecraft mc = Minecraft.getInstance();

            int btnW = 20;
            int btnH = 20;
            int accessX = 4;
            int accessY = self.height - 24;
            int cosmeticsX = accessX + btnW + 2;
            int cosmeticsY = accessY;

            AfkzClientCosmeticCore.LOGGER.info("[Afkz] Adding cosmetics button at ({}, {})", cosmeticsX, cosmeticsY);

            Button btn = Button.builder(
                Component.literal("\u2728"),
                b -> {
                    AfkzClientCosmeticCore.LOGGER.info("[Afkz] Cosmetics button clicked!");
                    mc.setScreen(new CosmeticScreen());
                }
            ).bounds(cosmeticsX, cosmeticsY, btnW, btnH).build();

            if (addRenderableWidgetMethod != null) {
                addRenderableWidgetMethod.invoke(self, btn);
                AfkzClientCosmeticCore.LOGGER.info("[Afkz] Cosmetics button added!");
            } else {
                AfkzClientCosmeticCore.LOGGER.error("[Afkz] Cannot add button - method not found");
            }
        } catch (Exception e) {
            AfkzClientCosmeticCore.LOGGER.error("[Afkz] Failed to add button!", e);
        }
    }
}
