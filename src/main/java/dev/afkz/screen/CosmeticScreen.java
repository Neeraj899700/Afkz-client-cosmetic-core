package dev.afkz.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CosmeticScreen extends Screen {
    public CosmeticScreen() {
        super(Component.literal("Cosmetics"));
    }

    @Override
    protected void init() {
        // Placeholder - will add cosmetic options later
    }

    @Override
    public void extractRenderState(net.minecraft.client.gui.GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        graphics.text(this.font, "§l§6Cosmetics Menu", this.width / 2 - 50, 20, 0xFFFFFFFF, true);
        graphics.text(this.font, "Coming soon...", this.width / 2 - 30, this.height / 2 - 10, 0xFFAAAAAA, true);
    }

    @Override
    public boolean shouldPause() {
        return true;
    }
}
