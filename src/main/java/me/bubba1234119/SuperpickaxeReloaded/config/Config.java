package me.bubba1234119.SuperpickaxeReloaded.config;

import me.bubba1234119.SuperpickaxeReloaded.SuperpickaxeReloaded;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public class Config {
    private final Set<Material> blacklistedBlocks = new HashSet<>();
    private final Set<Material> spaTools = new HashSet<>();

    private final boolean monitorWorldChanges;
    private final boolean overrideWorldEdit;

    public Config() {
        SuperpickaxeReloaded instance = SuperpickaxeReloaded.getInstance();

        instance.saveDefaultConfig();
        instance.reloadConfig();

        // Load blacklisted blocks:
        for (String materialName : instance.getConfig().getStringList("blacklisted")) {
            if (Material.matchMaterial(materialName) == null) {
                instance.getLogger().warning("Ignoring invalid material in blacklist: " + materialName);
            } else {
                blacklistedBlocks.add(Material.matchMaterial(materialName));
            }
        }

        // Load allowed spa tools:
        for (String toolName : instance.getConfig().getStringList("tools")) {
            if (Material.matchMaterial(toolName) == null) {
                instance.getLogger().warning("Ignoring invalid material in allowed tools: " + toolName);
            } else {
                spaTools.add(Material.matchMaterial(toolName));
            }
        }

        // Monitor world changes:
        if (!instance.getConfig().isSet("monitorWorldChanges")) {
            instance.getConfig().set("monitorWorldChanges", true);
        }
        monitorWorldChanges = instance.getConfig().getBoolean("monitorWorldChanges", true);

        // Override WorldEdit commands:
        if (!instance.getConfig().isSet("overrideWorldEdit")) {
            instance.getConfig().set("overrideWorldEdit", false);
        }
        overrideWorldEdit = instance.getConfig().getBoolean("overrideWorldEdit", false);

        instance.saveConfig();
    }

    /**
     * Checks if the given material is blacklisted in plugin config.
     *
     * @param material Material to check.
     * @return true if blacklisted, false otherwise.
     */
    public boolean isBlacklisted(Material material) {
        return blacklistedBlocks.contains(material);
    }

    /**
     * Should player world changes be monitored to check if the spa permission is still present after a world change
     * and disable spa accordingly.
     *
     * @return true if world changes should be monitored, false otherwise.
     */
    public boolean isMonitorWorldChanges() {
        return monitorWorldChanges;
    }

    /**
     * Checks if the WorldEdit superpickaxe commands should be overrided by this plugin.
     *
     * @return true if commands should be overrided, false otherwise.
     */
    public boolean isOverrideWorldEdit() {
        return overrideWorldEdit;
    }

    /**
     * Checks if the given material is a spa enabled tool in plugin config.
     *
     * @param material Material to check.
     * @return true if valid, false otherwise.
     */
    public boolean isSpaTool(Material material) {
        return spaTools.contains(material);
    }
}
