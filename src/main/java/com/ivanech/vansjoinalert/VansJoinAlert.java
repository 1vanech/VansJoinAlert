package com.ivanech.vansjoinalert;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class VansJoinAlert extends JavaPlugin {

    private static VansJoinAlert instance;

    public static VansJoinAlert getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new listeners.PlayerJoinListener(), this);

        VansJoinCommand commandExecutor = new VansJoinCommand();
        PluginCommand vj = getCommand("vj");
        if (vj != null) {
            vj.setExecutor(commandExecutor);
        }
        PluginCommand vja = getCommand("vansjoinalert");
        if (vja != null) {
            vja.setExecutor(commandExecutor);
        }

        getLogger().info("VansJoinAlert v" + getDescription().getVersion() + " enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("VansJoinAlert disabled");
    }
}


