package com.example.modules;

public class SprintModule {
    private static boolean enabled = false;

    public static void toggle() {
        enabled = !enabled;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean state) {
        enabled = state;
    }
}