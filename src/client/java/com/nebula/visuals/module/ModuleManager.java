package com.nebula.visuals.module;

import com.nebula.visuals.module.impl.HitEffects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ModuleManager {

    private static final List<Module> MODULES = new ArrayList<>();

    private ModuleManager() {
    }

    public static void init() {
        MODULES.clear();

        register(new HitEffects());
    }

    public static void register(Module module) {
        if (module == null) {
            return;
        }

        if (!MODULES.contains(module)) {
            MODULES.add(module);
        }
    }

    public static List<Module> getModules() {
        return Collections.unmodifiableList(MODULES);
    }

    public static List<Module> getModules(Category category) {
        return MODULES.stream()
                .filter(module -> module.getCategory() == category)
                .toList();
    }

    public static <T extends Module> T getModule(Class<T> type) {
        for (Module module : MODULES) {
            if (type.isInstance(module)) {
                return type.cast(module);
            }
        }

        return null;
    }

    public static Module getModule(String name) {
        for (Module module : MODULES) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }

        return null;
    }
}
