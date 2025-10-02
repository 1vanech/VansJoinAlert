package com.ivanech.vansjoinalert.listeners;

import com.ivanech.vansjoinalert.VansJoinAlert;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public final class PlayerJoinListener implements Listener {

    private static final String PERMISSION_ALERT = "vansjoin.alert";

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission(PERMISSION_ALERT)) {
            return;
        }

        String template = VansJoinAlert.getInstance().getConfig().getString("join-message", "&6&lVansJoin &8>> &6{player} &eзашёл на сервер!");

        String prefixValue = "";
        Plugin papi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        if (papi != null && papi.isEnabled()) {
            try {
                prefixValue = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
                if (prefixValue == null) {
                    prefixValue = "";
                }
            } catch (Throwable ignored) {
                prefixValue = "";
            }
        }

        String message = template
                .replace("{player}", player.getName())
                .replace("{prefix}", prefixValue);
        message = ChatColor.translateAlternateColorCodes('&', message);

        // Send to all players and play sound
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.sendMessage(message);
            online.playSound(online.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1.0f, 1.0f);
        }

        // Send to console
        Bukkit.getConsoleSender().sendMessage(message);

        // Send to the player as well (already included above, but ensure direct)
        player.sendMessage(message);
    }
}


