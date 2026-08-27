package com.nebula.visuals;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NebulaClient implements ClientModInitializer {

    public static final String MOD_ID = "nebula";
    public static final String NAME = "Nebula Visuals";

    public static final Logger LOGGER =
            LoggerFactory.getLogger(NAME);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Nebula Visuals initialized.");
    }
}
