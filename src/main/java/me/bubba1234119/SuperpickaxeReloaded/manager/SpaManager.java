package me.bubba1234119.SuperpickaxeReloaded.manager;

import me.bubba1234119.SuperpickaxeReloaded.SuperpickaxeReloaded;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class SpaManager {
    private final Set<UUID> spaEnabled = new HashSet<>();

    /**
     * Destructor method, called on plugin disable.
     */
    public void close() {
        clearSpaEnabled();
    }

    /**
     * Clears the spaEnabled list.
     */
    public void clearSpaEnabled() {
        spaEnabled.clear();
    }

    /**
     * Enable spa for the given player. Exempt from NoCheatPlus FASTBREAK if NCP is enabled.
     *
     * @param player      Player to enable SuperPickaxe for.
     * @param sendMessage True if the player should receive a message telling him that Spa is enabled.
     *                    False otherwise.
     */
    public void enableSpa(Player player, boolean sendMessage) {
        spaEnabled.add(player.getUniqueId());

        if (sendMessage) {
            player.sendMessage(ChatColor.BLACK + "[" + ChatColor.RED + "SuperPickaxe" + ChatColor.BLACK + "]" +
                    ChatColor.RED + " Enabled!");
        }

        if (Bukkit.getPluginManager().isPluginEnabled("NoCheatPlus")) {
            SuperpickaxeReloaded.getInstance().getNoCheatManager().exPlayer(player);
        }
    }

    /**
     * Disable spa for the given player. Unexempt from NoCheatPlus FASTBREAK if NCP is enabled.
     *
     * @param player      Player to disable SuperPickaxe for.
     * @param sendMessage True if the player should receive a message telling him that Spa is enabled.
     *                    False otherwise.
     */
    public void disableSpa(Player player, boolean sendMessage) {
        spaEnabled.remove(player.getUniqueId());

        if (sendMessage) {
            player.sendMessage(ChatColor.BLACK + "[" + ChatColor.RED + "SuperPickaxe" + ChatColor.BLACK + "]" +
                    ChatColor.RED + " Disabled!");
        }

        if (Bukkit.getPluginManager().isPluginEnabled("NoCheatPlus")) {
            SuperpickaxeReloaded.getInstance().getNoCheatManager().unExPlayer(player);
        }
    }

    /**
     * Checks if the given player has the permission to spa-break the given material.
     *
     * @param player   Player to check
     * @param material Material to check
     * @return True if the player has the permission, false otherwise.
     */
    public static boolean hasSpaMaterialPermission(Player player, Material material) {
        return player.hasPermission("spa." + material.name());
    }

    /**
     * Checks if the given player has the permission to toggle/use spa.
     *
     * @param player Player to check.
     * @return True if the player has the permission, false otherwise.
     */
    public static boolean hasSpaUsePermission(Player player) {
        return player.hasPermission("spa.use");
    }

    /**
     * Checks if the given player has spa enabled.
     *
     * @param player Player to check.
     * @return True if enabled, false otherwise.
     */
    public boolean isSpaEnabled(Player player) {
        return spaEnabled.contains(player.getUniqueId());
    }

    /**
     * Toggle spa for the given player.
     *
     * @param player      Player to toggle
     * @param sendMessage True if the player should receive a message telling him that Spa is enabled/disabled.
     *                    False otherwise.
     * @return True if spa is enabled after toggling, false if disabled.
     */
    public boolean toggleSpa(Player player, boolean sendMessage) {
        if (isSpaEnabled(player)) {
            disableSpa(player, sendMessage);
            return false;
        }

        enableSpa(player, sendMessage);
        return true;
    }
}
