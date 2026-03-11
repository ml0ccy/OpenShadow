package com.example.command;

import com.example.modules.GlowModule;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

public class CommandHandler {

    // For answers client in chat
    private static final String PREFIX = "§8[§5client§8]§r ";

    public static void handle(String input) {
        String stripped = input.substring(1).trim();
        if (stripped.isEmpty()) {
            sendMessage("§7Введите команду. Доступные: §fglow");
            return;
        }

        String[] args = stripped.split("\\s+");
        String command = args[0].toLowerCase();

        switch (command) {
            case "glow" -> handleGlow(args);
            case "help" -> handleHelp();
            default -> sendMessage("§cНеизвестная команда: §f" + command
                    + "§7. Введите §f.help");
        }
    }

    private static void handleGlow(String[] args) {
        if (args.length == 1) {
            GlowModule.toggle();
            sendMessage("Glow: " + formatBool(GlowModule.isEnabled()));
            return;
        }

        String sub = args[1].toLowerCase();
        switch (sub) {
            case "on" -> {
                GlowModule.setEnabled(true);
                sendMessage("Glow: " + formatBool(true));
            }
            case "off" -> {
                GlowModule.setEnabled(false);
                sendMessage("Glow: " + formatBool(false));
            }
            case "status" -> {
                sendMessage("Glow: " + formatBool(GlowModule.isEnabled()));
            }
            default -> sendMessage("§cИспользование: §f.glow §7[on/off/status]");
        }
    }

    private static void handleHelp() {
        sendMessage("§7=== Доступные команды ===");
        sendMessage("§f.glow §7[on/off/status] §8— §7подсветка энтити");
        sendMessage("§f.help §8— §7список команд");
    }

    private static String formatBool(boolean value) {
        return value ? "§aON" : "§cOFF";
    }

    public static void sendMessage(String text) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.player.displayClientMessage(
                    Component.literal(PREFIX + text), false
            );
        }
    }
}