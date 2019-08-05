package me.bubba1234119.SuperpickaxeReloaded;

import me.bubba1234119.SuperpickaxeReloaded.command.SpaCommand;
import me.bubba1234119.SuperpickaxeReloaded.config.Config;
import me.bubba1234119.SuperpickaxeReloaded.listener.BlockListener;
import me.bubba1234119.SuperpickaxeReloaded.listener.PlayerChangedWorldListener;
import me.bubba1234119.SuperpickaxeReloaded.listener.PlayerCommandPreprocessListener;
import me.bubba1234119.SuperpickaxeReloaded.manager.NoCheatManager;
import me.bubba1234119.SuperpickaxeReloaded.manager.SpaManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperpickaxeReloaded extends JavaPlugin {
    private Config runningConfig;
    private NoCheatManager noCheatManager;
    private SpaManager spaManager;

    public void onEnable() {
        runningConfig = new Config();
        noCheatManager = new NoCheatManager();
        spaManager = new SpaManager();

        Bukkit.getServer().getPluginManager().registerEvents(new BlockListener(this), this);
        getCommand("spa").setExecutor(new SpaCommand());

        if (getRunningConfig().isMonitorWorldChanges()) {
            getServer().getPluginManager().registerEvents(new PlayerChangedWorldListener(this), this);
        }

        if (getRunningConfig().isOverrideWorldEdit()) {
            getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(this), this);
        }
    }

    public void onDisable() {
        spaManager.close();

        noCheatManager = null;
        runningConfig = null;
        spaManager = null;
    }

    public NoCheatManager getNoCheatManager() {
        return noCheatManager;
    }

    public Config getRunningConfig() {
        return runningConfig;
    }

    public SpaManager getSpaManager() {
        return spaManager;
    }

    public static SuperpickaxeReloaded getInstance() {
        return SuperpickaxeReloaded.getPlugin(SuperpickaxeReloaded.class);
    }
}
