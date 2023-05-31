package me.refracdevelopment.simplestaffchat.bungee.utilities.chat;

import me.refracdevelopment.simplestaffchat.bungee.config.cache.Config;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

public class Color {

    public static String translate(CommandSender sender, String source) {
        source = Placeholders.setPlaceholders(sender, source);

        return translate(source);
    }

    public static String translate(String source) {
        return ChatColor.translateAlternateColorCodes('&', source);
    }

    public static void sendMessage(CommandSender sender, String source, boolean color) {
        if (source.equalsIgnoreCase("%empty%") || source.contains("%empty%")) return;

        source = Placeholders.setPlaceholders(sender, source);

        if (color) source = translate(source);

        sender.sendMessage(new TextComponent(source));
    }

    public static void log(String message) {
        sendMessage(ProxyServer.getInstance().getConsole(), Config.PREFIX + " " + message, true);
    }

    public static void log2(String message) {
        sendMessage(ProxyServer.getInstance().getConsole(), message, true);
    }
}