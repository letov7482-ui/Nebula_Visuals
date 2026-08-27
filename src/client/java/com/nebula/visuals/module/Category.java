package com.nebula.visuals.module;

public enum Category {

    COMBAT("Combat"),
    VISUALS("Visuals"),
    HUD("HUD"),
    WORLD("World"),
    MISC("Misc"),
    CONFIG("Config");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
