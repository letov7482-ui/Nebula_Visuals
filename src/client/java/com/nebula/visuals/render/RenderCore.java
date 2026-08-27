package com.nebula.visuals.render;

import net.minecraft.client.gui.GuiGraphics;

public final class RenderCore {

    private RenderCore() {
    }

    public static void rect(
            GuiGraphics graphics,
            int x,
            int y,
            int width,
            int height,
            int color
    ) {
        if (width <= 0 || height <= 0) {
            return;
        }

        graphics.fill(
                x,
                y,
                x + width,
                y + height,
                color
        );
    }

    public static void roundedRect(
            GuiGraphics graphics,
            int x,
            int y,
            int width,
            int height,
            int radius,
            int color
    ) {
        if (width <= 0 || height <= 0) {
            return;
        }

        radius = Math.max(
                0,
                Math.min(radius, Math.min(width, height) / 2)
        );

        // Центральная область
        graphics.fill(
                x + radius,
                y,
                x + width - radius,
                y + height,
                color
        );

        // Левая область
        graphics.fill(
                x,
                y + radius,
                x + radius,
                y + height - radius,
                color
        );

        // Правая область
        graphics.fill(
                x + width - radius,
                y + radius,
                x + width,
                y + height - radius,
                color
        );
    }

    public static int withAlpha(
            int color,
            float alpha
    ) {
        alpha = Math.max(
                0.0f,
                Math.min(1.0f, alpha)
        );

        int a = (int) (alpha * 255.0f);

        return (color & 0x00FFFFFF)
                | (a << 24);
    }

    public static int rgb(
            int red,
            int green,
            int blue
    ) {
        return 0xFF000000
                | ((red & 0xFF) << 16)
                | ((green & 0xFF) << 8)
                | (blue & 0xFF);
    }

    public static int rgba(
            int red,
            int green,
            int blue,
            int alpha
    ) {
        return ((alpha & 0xFF) << 24)
                | ((red & 0xFF) << 16)
                | ((green & 0xFF) << 8)
                | (blue & 0xFF);
    }
}
