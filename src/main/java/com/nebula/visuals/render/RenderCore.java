package com.nebula.visuals.render;

import net.minecraft.client.gui.DrawContext;

public final class RenderCore {

    private RenderCore() {
    }

    /**
     * Рисует прямоугольник со скруглёнными углами.
     *
     * Пока используется базовый Minecraft renderer.
     * Blur, glow и дополнительные эффекты подключим отдельным слоем.
     */
    public static void roundedRect(
            DrawContext context,
            float x,
            float y,
            float width,
            float height,
            float radius,
            int color
    ) {
        if (width <= 0 || height <= 0) {
            return;
        }

        radius = Math.max(0.0f, Math.min(radius, Math.min(width, height) / 2.0f));

        // Центральная часть
        context.fill(
                (int) (x + radius),
                (int) y,
                (int) (x + width - radius),
                (int) (y + height),
                color
        );

        // Левая и правая части
        context.fill(
                (int) x,
                (int) (y + radius),
                (int) (x + radius),
                (int) (y + height - radius),
                color
        );

        context.fill(
                (int) (x + width - radius),
                (int) (y + radius),
                (int) (x + width),
                (int) (y + height - radius),
                color
        );
    }

    /**
     * Возвращает цвет с заданной прозрачностью.
     *
     * alpha: 0.0 - полностью прозрачно
     * alpha: 1.0 - полностью непрозрачно
     */
    public static int withAlpha(int color, float alpha) {
        alpha = Math.max(0.0f, Math.min(1.0f, alpha));

        int a = (int) (alpha * 255.0f) & 0xFF;
        return (color & 0x00FFFFFF) | (a << 24);
    }

    /**
     * RGB -> Minecraft ARGB.
     */
    public static int rgb(int red, int green, int blue) {
        return 0xFF000000
                | ((red & 0xFF) << 16)
                | ((green & 0xFF) << 8)
                | (blue & 0xFF);
    }

    /**
     * RGBA -> Minecraft ARGB.
     */
    public static int rgba(int red, int green, int blue, int alpha) {
        return ((alpha & 0xFF) << 24)
                | ((red & 0xFF) << 16)
                | ((green & 0xFF) << 8)
                | (blue & 0xFF);
    }
}
