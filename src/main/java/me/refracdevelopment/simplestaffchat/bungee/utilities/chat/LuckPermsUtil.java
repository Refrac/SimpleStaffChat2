package me.refracdevelopment.simplestaffchat.bungee.utilities.chat;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@UtilityClass
public class LuckPermsUtil {

    @Getter
    @Setter
    private LuckPerms luckPerms;

    public String getPrefix(ProxiedPlayer player) {
        User lpUser = getLuckPerms().getUserManager().getUser(player.getUniqueId());
        if (lpUser.getCachedData().getMetaData().getPrefix() == null) return "N/A";
        return lpUser.getCachedData().getMetaData().getPrefix();
    }

    public String getSuffix(ProxiedPlayer player) {
        User lpUser = getLuckPerms().getUserManager().getUser(player.getUniqueId());
        if (lpUser.getCachedData().getMetaData().getSuffix() == null) return "N/A";
        return lpUser.getCachedData().getMetaData().getSuffix();
    }
}