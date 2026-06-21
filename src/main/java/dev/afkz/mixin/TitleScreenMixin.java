package dev.afkz.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.afkz.AfkzClientCosmeticCore;
import dev.afkz.screen.CosmeticScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Method;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    private static final ResourceLocation AFKZ_ICON = ResourceLocation.fromNamespaceAndPath("afkz", "textures/cosmetic_icon.png");

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

            int accessX = 4;
            int accessY = self.height - 24;
            int btnW = 20;
            int btnH = 20;
            int cosmeticsX = accessX + btnW + 2;
            int cosmeticsY = accessY;

            AfkzClientCosmeticCore.LOGGER.info("[Afkz] Adding cosmetics icon at ({}, {})", cosmeticsX, cosmeticsY);

            Button btn = new Button(cosmeticsX, cosmeticsY, btnW, btnH,
                Component.literal("Cosmetics"),
                b -> {
                    AfkzClientCosmeticCore.LOGGER.info("[Afkz] Cosmetics icon clicked!");
                    mc.setScreen(new CosmeticScreen());
                }
            ) {
                @Override
                public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderTexture(0, AFKZ_ICON);
                    guiGraphics.blit(AFKZ_ICON, this.getX(), this.getY(), 0, 0, this.width, this.height, this.width, this.height);
                    if (this.isHoveredOrFocused()) {
                        guiGraphics.renderTooltip(guiGraphics, this.createTooltip(), mouseX, mouseY);
                    }
                }
            };

            if (addRenderableWidgetMethod != null) {
                addRenderableWidgetMethod.invoke(self, btn);
                AfkzClientCosmeticCore.LOGGER.info("[Afkz] Cosmetics icon added!");
            } else {
                AfkzClientCosmeticCore.LOGGER.error("[Afkz] Cannot add icon - method not found");
            }
        } catch (Exception e) {
            AfkzClientCosmeticCore.LOGGER.error("[Afkz] Failed to add icon!", e);
        }
    }
}
