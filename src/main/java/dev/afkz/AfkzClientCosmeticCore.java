package dev.afkz;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AfkzClientCosmeticCore implements ClientModInitializer {
    public static final String MOD_ID = "afkz-client-cosmetic-core";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("[Afkz] Client Cosmetic Core loaded");
    }
}
