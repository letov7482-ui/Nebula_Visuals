package com.nebula.visuals.animation;

public class Animation {

    private float value;
    private float target;

    private float speed = 12.0f;

    public Animation(float initialValue) {
        this.value = initialValue;
        this.target = initialValue;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public void setSpeed(float speed) {
        this.speed = Math.max(0.1f, speed);
    }

    public void update(float delta) {
        float difference = target - value;

        if (Math.abs(difference) < 0.001f) {
            value = target;
            return;
        }

        float progress = 1.0f - (float) Math.exp(-speed * delta);

        value += difference * progress;
    }

    public float get() {
        return value;
    }

    public float getTarget() {
        return target;
    }
}
