package com.ivanech.vansjoinalert;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class VansJoinCommand implements CommandExecutor {

    private static final String PERMISSION_RELOAD = "vansjoinalert.reload";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String baseInfo = color("&8[&6VansJoinAlert&8] &eVansJoinAlert by &6ivanech &eversion &62.0.0-beta &8(beta)");

        if (args.length == 0) {
            sender.sendMessage(baseInfo);
            return true;
        }

        String sub = args[0].toLowerCase();
        switch (sub) {
            case "help":
                sendHelp(sender);
                return true;
            case "reload":
                if (!sender.hasPermission(PERMISSION_RELOAD)) {
                    sender.sendMessage(color("&8[&6VansJoinAlert&8] &eНедостаточно прав."));
                    return true;
                }
                VansJoinAlert.getInstance().reloadConfig();
                sender.sendMessage(color("&8[&6VansJoinAlert&8] &eКонфиг перезагружен."));
                return true;
            default:
                sender.sendMessage(baseInfo);
                return true;
        }
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(color("&8=====&6 VansJoinAlert &8====="));
        sender.sendMessage(color("&6/vj &8- &eИнформация о плагине"));
        sender.sendMessage(color("&6/vansjoinalert help &8- &eПоказать эту справку"));
        sender.sendMessage(color("&6/vansjoinalert reload &8- &eПерезагрузить конфиг &8(&7perm: vansjoinalert.reload&8)"));
        sender.sendMessage(color("&8Version: &62.0.0-beta"));
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}


