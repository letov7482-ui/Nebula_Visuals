package com.nebula.visuals;

import com.nebula.visuals.gui.NebulaScreen;
import com.nebula.visuals.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NebulaClient implements ClientModInitializer {

    public static final String MOD_ID = "nebula";
    public static final String NAME = "Nebula Visuals";

    public static final Logger LOGGER =
            LoggerFactory.getLogger(NAME);

    private static KeyMapping openGuiKey;

    @Override
    public void onInitializeClient() {

        LOGGER.info("Nebula Visuals initializing...");

        // Инициализация системы модулей
        ModuleManager.init();

        // Регистрация клавиши открытия ClickGUI
        openGuiKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.nebula.open_gui",
                        GLFW.GLFW_KEY_RIGHT_SHIFT,
                        "category.nebula"
                )
        );

        // Обработка клавиши
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (openGuiKey.consumeClick()) {

                if (client.screen == null) {
                    client.setScreen(new NebulaScreen());
                }
            }
        });

        LOGGER.info("Nebula Visuals initialized successfully.");
    }
}
