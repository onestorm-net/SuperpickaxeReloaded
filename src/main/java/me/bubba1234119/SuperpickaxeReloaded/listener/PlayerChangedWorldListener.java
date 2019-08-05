package me.bubba1234119.SuperpickaxeReloaded.listener;

import me.bubba1234119.SuperpickaxeReloaded.SuperpickaxeReloaded;
import me.bubba1234119.SuperpickaxeReloaded.manager.SpaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangedWorldListener implements Listener {
    private final SuperpickaxeReloaded instance;

    public PlayerChangedWorldListener(SuperpickaxeReloaded instance) {
        this.instance = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    @SuppressWarnings("unused")
    public void onPlayerChangedWorld(final PlayerChangedWorldEvent event) {
        final Player player = event.getPlayer();
        final SpaManager spaManager = instance.getSpaManager();

        if (spaManager.isSpaEnabled(player) && !SpaManager.hasSpaUsePermission(player)) {
            spaManager.disableSpa(player, false);
        }
    }
}
