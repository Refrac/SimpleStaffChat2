package me.refracdevelopment.simplestaffchat.spigot.commands;

import me.refracdevelopment.simplestaffchat.shared.Permissions;
import me.refracdevelopment.simplestaffchat.spigot.SimpleStaffChat;
import me.refracdevelopment.simplestaffchat.spigot.config.Commands;
import me.refracdevelopment.simplestaffchat.spigot.manager.LocaleManager;
import me.refracdevelopment.simplestaffchat.spigot.utilities.Placeholders;
import me.refracdevelopment.simplestaffchat.spigot.utilities.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToggleCommand extends Command {

    private final SimpleStaffChat plugin;

    public static List<UUID> insc = new ArrayList<>();

    public ToggleCommand(SimpleStaffChat plugin) {
        super(Commands.TOGGLE_COMMAND, Permissions.STAFFCHAT_TOGGLE, Commands.TOGGLE_ALIAS);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!Commands.TOGGLE_COMMAND_ENABLED) return false;

        final LocaleManager locale = plugin.getManager(LocaleManager.class);

        if (!(sender instanceof Player)) {
            locale.sendMessage(sender, "no-console");
            return true;
        }

        Player player = (Player) sender;

        if (!sender.hasPermission(Permissions.STAFFCHAT_TOGGLE)) {
            locale.sendMessage(sender, "no-permission");
            return true;
        }

        if (insc.contains(player.getUniqueId())) {
            insc.remove(player.getUniqueId());
            locale.sendMessage(player, "toggle-off", Placeholders.setPlaceholders(player));
        } else {
            insc.add(player.getUniqueId());
            locale.sendMessage(player, "toggle-on", Placeholders.setPlaceholders(player));
        }
        return true;
    }

    @Override
    public int compareTo(@NotNull Command o) {
        return 0;
    }
}