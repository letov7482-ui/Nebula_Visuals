package com.nebula.visuals.render;

import net.minecraft.client.gui.DrawContext;

public final class RenderCore {

    private RenderCore() {
    }

    public static void roundedRect(
            DrawContext context,
            float x,
            float y,
            float width,
            float height,
            float radius,
            int color
    ) {
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }

        radius = Math.max(
                0.0f,
                Math.min(radius, Math.min(width, height) / 2.0f)
        );

        int left = (int) x;
        int top = (int) y;
        int right = (int) (x + width);
        int bottom = (int) (y + height);
        int r = (int) radius;

        context.fill(
                left + r,
                top,
                right - r,
                bottom,
                color
        );

        context.fill(
                left,
                top + r,
                left + r,
                bottom - r,
                color
        );

        context.fill(
                right - r,
                top + r,
                right,
                bottom - r,
                color
        );
    }

    public static int withAlpha(int color, float alpha) {
        alpha = Math.max(0.0f, Math.min(1.0f, alpha));

        int a = (int) (alpha * 255.0f);

        return (color & 0x00FFFFFF) | (a << 24);
    }

    public static int rgb(int red, int green, int blue) {
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
