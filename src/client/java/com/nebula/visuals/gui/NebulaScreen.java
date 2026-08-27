package com.nebula.visuals.gui;

import com.nebula.visuals.render.NebulaColors;
import com.nebula.visuals.render.RenderCore;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class NebulaScreen extends Screen {

    private float openAnimation = 0.0f;

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
        int width = this.width;
        int height = this.height;

        int guiWidth = 720;
        int guiHeight = 430;

        float x = (width - guiWidth) / 2.0f;
        float y = (height - guiHeight) / 2.0f;

        float scale = 0.92f + (0.08f * openAnimation);

        float centerX = width / 2.0f;
        float centerY = height / 2.0f;

        graphics.pose().pushPose();

        graphics.pose().translate(centerX, centerY, 0);
        graphics.pose().scale(scale, scale, 1.0f);
        graphics.pose().translate(-centerX, -centerY, 0);

        // Затемнение мира
        graphics.fill(
                0,
                0,
                width,
                height,
                0x66000000
        );

        // Основная панель
        RenderCore.roundedRect(
                graphics,
                (int) x,
                (int) y,
                guiWidth,
                guiHeight,
                18,
                NebulaColors.BACKGROUND
        );

        // Верхняя панель
        RenderCore.roundedRect(
                graphics,
                (int) x + 12,
                (int) y + 12,
                guiWidth - 24,
                52,
                14,
                NebulaColors.PANEL
        );

        // Акцентная полоска
        RenderCore.roundedRect(
                graphics,
                (int) x + 12,
                (int) y + 12,
                4,
                52,
                2,
                NebulaColors.ACCENT
        );

        graphics.drawString(
                this.font,
                "Nebula Visuals",
                (int) x + 30,
                (int) y + 25,
                NebulaColors.TEXT,
                false
        );

        graphics.drawString(
                this.font,
                "VISUAL CLIENT",
                (int) x + 30,
                (int) y + 40,
                NebulaColors.TEXT_SECONDARY,
                false
        );

        graphics.pose().popPose();

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
