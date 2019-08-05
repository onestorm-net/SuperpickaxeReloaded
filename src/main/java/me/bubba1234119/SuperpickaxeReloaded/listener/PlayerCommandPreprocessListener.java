package me.bubba1234119.SuperpickaxeReloaded.listener;

import me.bubba1234119.SuperpickaxeReloaded.SuperpickaxeReloaded;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener {
    private final SuperpickaxeReloaded instance;

    public PlayerCommandPreprocessListener(SuperpickaxeReloaded instance) {
        this.instance = instance;
    }

    @EventHandler(ignoreCancelled = true)
    @SuppressWarnings("unused")
    private void playerCommandPreprocessEvent(final PlayerCommandPreprocessEvent event) {
        final String lowerCase = event.getMessage().toLowerCase();
        if (lowerCase.equals("//") || lowerCase.equals("/,") || lowerCase.equals("/sp")) {
            event.setCancelled(true);
            instance.getServer().dispatchCommand(event.getPlayer(), "spa");
        }
    }
}
