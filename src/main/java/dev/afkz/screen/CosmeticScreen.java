package dev.afkz.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CosmeticScreen extends Screen {
    public CosmeticScreen() {
        super(Component.literal("Cosmetics"));
    }

    @Override
    protected void init() {
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
        graphics.drawCenteredString(this.font, "§l§6Cosmetics Menu", this.width / 2, 20, 0xFFFFFFFF);
        graphics.drawCenteredString(this.font, "Coming soon...", this.width / 2, this.height / 2, 0xFFAAAAAA);
    }

    @Override
    public boolean shouldPause() {
        return true;
    }
}
