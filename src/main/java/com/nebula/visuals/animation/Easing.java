package com.nebula.visuals.animation;

public final class Easing {

    private Easing() {
    }

    public static float linear(float t) {
        return clamp(t);
    }

    public static float easeOutCubic(float t) {
        t = clamp(t);
        return 1.0f - (float) Math.pow(1.0f - t, 3.0);
    }

    public static float easeOutQuart(float t) {
        t = clamp(t);
        return 1.0f - (float) Math.pow(1.0f - t, 4.0);
    }

    public static float easeInOutCubic(float t) {
        t = clamp(t);

        if (t < 0.5f) {
            return 4.0f * t * t * t;
        }

        return 1.0f - (float) Math.pow(-2.0f * t + 2.0f, 3.0) / 2.0f;
    }

    private static float clamp(float value) {
        return Math.max(0.0f, Math.min(1.0f, value));
    }
}
