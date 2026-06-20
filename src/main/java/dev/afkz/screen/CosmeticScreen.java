package dev.afkz.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CosmeticScreen extends Screen {
    public CosmeticScreen() {
        super(Component.literal("Cosmetics"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addRenderableWidget(Button.builder(
            Component.literal("§cClose"),
            button -> this.minecraft.setScreen(null)
        ).bounds(centerX - 100, centerY + 30, 200, 20).build());
    }
}
