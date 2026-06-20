package dev.afkz;

import dev.afkz.screen.CosmeticScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AfkzClientCosmeticCore implements ClientModInitializer {
    public static final String MOD_ID = "afkz-client-cosmetic-core";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("[Afkz Client Cosmetic Core] Initializing...");

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            String screenTitle = screen.getTitle().getString();
            if (screenTitle.isEmpty() || screen.getClass().getSimpleName().equals("TitleScreen")) {
                Button cosmeticsButton = Button.builder(
                    Component.literal("Cosmetics"),
                    button -> client.setScreen(new CosmeticScreen())
                ).bounds(
                    screen.width / 2 - 100,
                    screen.height / 2 + 24,
                    200,
                    20
                ).build();

                screen.addRenderableWidget(cosmeticsButton);
            }
        });

        LOGGER.info("[Afkz Client Cosmetic Core] Loaded successfully!");
    }
}
