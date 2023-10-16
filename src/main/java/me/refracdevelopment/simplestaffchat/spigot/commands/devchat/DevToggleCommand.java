package me.refracdevelopment.simplestaffchat.spigot.commands.devchat;

import me.refracdevelopment.simplestaffchat.spigot.SimpleStaffChat;
import me.refracdevelopment.simplestaffchat.spigot.manager.LocaleManager;
import me.refracdevelopment.simplestaffchat.spigot.utilities.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DevToggleCommand extends Command {

    private final SimpleStaffChat plugin;

    public DevToggleCommand(SimpleStaffChat plugin) {
        super(plugin, plugin.getCommands().DEV_TOGGLE_COMMAND, "", plugin.getCommands().DEV_TOGGLE_COMMAND_ALIAS);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!plugin.getCommands().DEV_TOGGLE_COMMAND_ENABLED) return false;

        final LocaleManager locale = plugin.getManager(LocaleManager.class);

        if (!(sender instanceof Player)) {
            locale.sendMessage(sender, "no-console");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(plugin.getCommands().DEV_TOGGLE_COMMAND_PERMISSION)) {
            locale.sendMessage(player, "no-permission");
            return true;
        }

        plugin.getMethods().toggleDevChat(player);
        return true;
    }
}