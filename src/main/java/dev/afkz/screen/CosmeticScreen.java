package dev.afkz.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CosmeticScreen extends Screen {
    public CosmeticScreen() {
        super(Component.literal("Cosmetics"));
    }

    @Override
    protected void init() {
        this.addRenderableWidget(
            net.minecraft.client.gui.components.Button.builder(
                Component.literal("Back"),
                b -> this.minecraft.setScreen(null)
            ).bounds(this.width / 2 - 100, this.height / 2, 200, 20).build()
        );
    }
}
