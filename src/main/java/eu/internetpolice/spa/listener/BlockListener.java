package eu.internetpolice.spa.listener;

import eu.internetpolice.spa.SuperpickaxeReloaded;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class BlockListener implements Listener {
    private final SuperpickaxeReloaded instance;

    public BlockListener(SuperpickaxeReloaded instance) {
        this.instance = instance;
    }

    @EventHandler(ignoreCancelled = true)
    @SuppressWarnings("unused")
    public void breakBlock(final BlockDamageEvent event) {
        if (!instance.getSpaManager().isSpaEnabled(event.getPlayer())) return;
        if (!instance.getRunningConfig().isSpaTool(event.getItemInHand().getType())) return;

        if (!instance.getRunningConfig().isBlacklisted(event.getBlock().getType())
                || instance.getSpaManager().hasSpaMaterialPermission(event.getPlayer(), event.getBlock().getType())) {
            event.setInstaBreak(true);
        }
    }
}
