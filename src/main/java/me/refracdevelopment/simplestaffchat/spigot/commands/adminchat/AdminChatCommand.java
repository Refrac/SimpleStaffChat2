package me.refracdevelopment.simplestaffchat.spigot.commands.adminchat;

import com.google.common.base.Joiner;
import me.refracdevelopment.simplestaffchat.spigot.SimpleStaffChat;
import me.refracdevelopment.simplestaffchat.spigot.manager.LocaleManager;
import me.refracdevelopment.simplestaffchat.spigot.utilities.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminChatCommand extends Command {

    private final SimpleStaffChat plugin;
    
    public AdminChatCommand(SimpleStaffChat plugin) {
        super(plugin, plugin.getCommands().ADMINCHAT_COMMAND, "", plugin.getCommands().ADMINCHAT_COMMAND_ALIAS);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!plugin.getCommands().ADMINCHAT_COMMAND_ENABLED) return false;

        final LocaleManager locale = plugin.getManager(LocaleManager.class);

        String message = Joiner.on(" ").join(args);

        if (!sender.hasPermission(plugin.getCommands().ADMINCHAT_COMMAND_PERMISSION)) {
            locale.sendMessage(sender, "no-permission");
            return true;
        }

        if (args.length >= 1) {
            String format = (sender instanceof Player) ? plugin.getSettings().ADMINCHAT_FORMAT.replace("%message%", message) :
                    plugin.getSettings().CONSOLE_ADMINCHAT_FORMAT.replace("%message%", message);

            plugin.getMethods().sendAdminChat(sender, format);
        }
        return true;
    }
}