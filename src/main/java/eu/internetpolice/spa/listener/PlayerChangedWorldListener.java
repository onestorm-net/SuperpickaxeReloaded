package eu.internetpolice.spa.listener;

import eu.internetpolice.spa.SuperpickaxeReloaded;
import eu.internetpolice.spa.manager.SpaManager;
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
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        SpaManager spaManager = instance.getSpaManager();

        if (spaManager.isSpaEnabled(player) && spaManager.hasSpaUsePermission(player)) {
            spaManager.disableSpa(player, false);
        }
    }
}
