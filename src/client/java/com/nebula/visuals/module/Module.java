package com.nebula.visuals.module;

public abstract class Module {

    private final String name;
    private final String description;
    private final Category category;

    private boolean enabled;

    protected Module(
            String name,
            String description,
            Category category
    ) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final Category getCategory() {
        return category;
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final void toggle() {
        setEnabled(!enabled);
    }

    public final void setEnabled(boolean enabled) {
        if (this.enabled == enabled) {
            return;
        }

        this.enabled = enabled;

        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    protected void onEnable() {
    }

    protected void onDisable() {
    }
}
