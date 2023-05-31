package me.refracdevelopment.simplestaffchat.bungee.commands;

import me.refracdevelopment.simplestaffchat.bungee.config.cache.Commands;
import me.refracdevelopment.simplestaffchat.bungee.config.cache.Config;
import me.refracdevelopment.simplestaffchat.bungee.utilities.chat.Color;
import me.refracdevelopment.simplestaffchat.shared.Permissions;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToggleCommand extends Command {

    public static List<UUID> insc = new ArrayList<>();

    public ToggleCommand() {
        super(Commands.TOGGLE_COMMAND, "", Commands.TOGGLE_ALIAS);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!Commands.TOGGLE_COMMAND_ENABLED) return;
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (!player.hasPermission(Permissions.STAFFCHAT_TOGGLE)) {
            Color.sendMessage(player, Config.NO_PERMISSION, true);
            return;
        }

        if (insc.contains(player.getUniqueId())) {
            insc.remove(player.getUniqueId());
            Color.sendMessage(player, Config.STAFFCHAT_TOGGLE_OFF, true);
        } else {
            insc.add(player.getUniqueId());
            Color.sendMessage(player, Config.STAFFCHAT_TOGGLE_ON, true);
        }
    }
}