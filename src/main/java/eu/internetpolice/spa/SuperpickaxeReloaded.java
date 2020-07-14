package eu.internetpolice.spa;

import eu.internetpolice.spa.command.SpaCommand;
import eu.internetpolice.spa.config.Config;
import eu.internetpolice.spa.listener.BlockListener;
import eu.internetpolice.spa.listener.PlayerChangedWorldListener;
import eu.internetpolice.spa.listener.PlayerCommandPreprocessListener;
import eu.internetpolice.spa.manager.NoCheatManager;
import eu.internetpolice.spa.manager.SpaManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperpickaxeReloaded extends JavaPlugin {
    private Config runningConfig;
    private NoCheatManager noCheatManager;
    private SpaManager spaManager;

    @Override
    public void onEnable() {
        runningConfig = new Config(this);
        noCheatManager = new NoCheatManager();
        spaManager = new SpaManager(this);

        getServer().getPluginManager().registerEvents(new BlockListener(this), this);
        PluginCommand spaCommand = getCommand("superpickaxe");
        if (spaCommand != null) {
            SpaCommand spaExecutor = new SpaCommand(this);
            spaCommand.setExecutor(spaExecutor);
            spaCommand.setTabCompleter(spaExecutor);
        } else {
            getLogger().warning("Superpickaxe command returned null. The /superpickaxe command will not work.");
        }

        if (getRunningConfig().isMonitorWorldChanges()) {
            getServer().getPluginManager().registerEvents(new PlayerChangedWorldListener(this), this);
        }

        if (getRunningConfig().isOverrideWorldEdit()) {
            getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(this), this);
        }
    }

    @Override
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
}
