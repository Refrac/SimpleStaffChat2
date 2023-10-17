package me.refracdevelopment.simplestaffchat.bungee.commands.adminchat;

import me.refracdevelopment.simplestaffchat.bungee.BungeeStaffChat;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AdminHideCommand extends Command {

    private final BungeeStaffChat plugin;
    
    public AdminHideCommand(BungeeStaffChat plugin) {
        super(plugin.getCommands().ADMIN_HIDE_COMMAND_ALIASES.get(0), "", plugin.getCommands().ADMIN_HIDE_COMMAND_ALIASES.toArray(new String[0]));
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!plugin.getCommands().ADMIN_HIDE_COMMAND_ENABLED) return;
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (!player.hasPermission(plugin.getCommands().ADMIN_HIDE_COMMAND_PERMISSION)) {
            plugin.getColor().sendMessage(player, plugin.getConfig().NO_PERMISSION);
            return;
        }

        plugin.getMethods().hideAdminChat(player);
    }
}