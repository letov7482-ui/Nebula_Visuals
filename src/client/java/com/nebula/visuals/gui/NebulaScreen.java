package com.nebula.visuals.gui;

import com.nebula.visuals.render.NebulaColors;
import com.nebula.visuals.render.RenderCore;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class NebulaScreen extends Screen {

    private float openAnimation = 0.0f;

    private static final int GUI_WIDTH = 760;
    private static final int GUI_HEIGHT = 450;

    private static final int SIDEBAR_WIDTH = 155;

    private static final String[] CATEGORIES = {
            "Combat",
            "Visuals",
            "HUD",
            "World",
            "Misc",
            "Config"
    };

    private int selectedCategory = 1;

    public NebulaScreen() {
        super(Component.literal("Nebula Visuals"));
    }

    @Override
    protected void init() {
        openAnimation = 0.0f;
    }

    @Override
    public void tick() {
        if (openAnimation < 1.0f) {
            openAnimation += 0.08f;

            if (openAnimation > 1.0f) {
                openAnimation = 1.0f;
            }
        }
    }

    @Override
    public void render(
            GuiGraphics graphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {
        int screenWidth = this.width;
        int screenHeight = this.height;

        float x = (screenWidth - GUI_WIDTH) / 2.0f;
        float y = (screenHeight - GUI_HEIGHT) / 2.0f;

        float scale = 0.94f + openAnimation * 0.06f;

        float centerX = screenWidth / 2.0f;
        float centerY = screenHeight / 2.0f;

        graphics.pose().pushPose();

        graphics.pose().translate(centerX, centerY, 0);
        graphics.pose().scale(scale, scale, 1.0f);
        graphics.pose().translate(-centerX, -centerY, 0);

        // Затемнение мира
        graphics.fill(
                0,
                0,
                screenWidth,
                screenHeight,
                0x70000000
        );

        int left = (int) x;
        int top = (int) y;

        // Основное окно
        RenderCore.roundedRect(
                graphics,
                left,
                top,
                GUI_WIDTH,
                GUI_HEIGHT,
                18,
                NebulaColors.BACKGROUND
        );

        // Sidebar
        RenderCore.roundedRect(
                graphics,
                left + 10,
                top + 10,
                SIDEBAR_WIDTH,
                GUI_HEIGHT - 20,
                14,
                NebulaColors.PANEL
        );

        // Логотип
        RenderCore.roundedRect(
                graphics,
                left + 23,
                top + 24,
                34,
                34,
                10,
                NebulaColors.ACCENT
        );

        graphics.drawString(
                font,
                "N",
                left + 34,
                top + 35,
                0xFF061018,
                false
        );

        // Название
        graphics.drawString(
                font,
                "NEBULA",
                left + 67,
                top + 26,
                NebulaColors.TEXT,
                false
        );

        graphics.drawString(
                font,
                "VISUALS",
                left + 67,
                top + 39,
                NebulaColors.TEXT_SECONDARY,
                false
        );

        // Категории
        int categoryY = top + 82;

        for (int i = 0; i < CATEGORIES.length; i++) {

            int itemY = categoryY + i * 48;

            boolean selected = selectedCategory == i;

            boolean hovered =
                    mouseX >= left + 20
                            && mouseX <= left + SIDEBAR_WIDTH - 15
                            && mouseY >= itemY
                            && mouseY <= itemY + 38;

            // Выбранная категория
            if (selected) {

                RenderCore.roundedRect(
                        graphics,
                        left + 20,
                        itemY,
                        SIDEBAR_WIDTH - 35,
                        38,
                        10,
                        NebulaColors.HOVER
                );

                // Акцентная полоска
                RenderCore.roundedRect(
                        graphics,
                        left + 20,
                        itemY + 7,
                        3,
                        24,
                        2,
                        NebulaColors.ACCENT
                );

            // Наведение мыши
            } else if (hovered) {

                RenderCore.roundedRect(
                        graphics,
                        left + 20,
                        itemY,
                        SIDEBAR_WIDTH - 35,
                        38,
                        10,
                        0x331FFFFF
                );
            }

            int textColor = selected
                    ? NebulaColors.TEXT
                    : NebulaColors.TEXT_SECONDARY;

            graphics.drawString(
                    font,
                    CATEGORIES[i],
                    left + 35,
                    itemY + 14,
                    textColor,
                    false
            );
        }

        // Заголовок выбранной категории
        int contentX = left + SIDEBAR_WIDTH + 30;

        graphics.drawString(
                font,
                CATEGORIES[selectedCategory],
                contentX,
                top + 28,
                NebulaColors.TEXT,
                false
        );

        graphics.drawString(
                font,
                "Nebula modules",
                contentX,
                top + 43,
                NebulaColors.TEXT_SECONDARY,
                false
        );

        // Разделительная линия
        graphics.fill(
                contentX,
                top + 66,
                left + GUI_WIDTH - 25,
                top + 67,
                0x331FFFFF
        );

        graphics.pose().popPose();

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(
            double mouseX,
            double mouseY,
            int button
    ) {
        if (button == 0) {

            int left = (this.width - GUI_WIDTH) / 2;
            int top = (this.height - GUI_HEIGHT) / 2;

            int categoryY = top + 82;

            for (int i = 0; i < CATEGORIES.length; i++) {

                int itemY = categoryY + i * 48;

                if (mouseX >= left + 20
                        && mouseX <= left + SIDEBAR_WIDTH - 15
                        && mouseY >= itemY
                        && mouseY <= itemY + 38) {

                    selectedCategory = i;
                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
            }
