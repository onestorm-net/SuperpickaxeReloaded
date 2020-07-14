package eu.internetpolice.spa.manager;

import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
import org.bukkit.entity.Player;

public class NoCheatManager {
    /**
     * Exempt the given player from NoCheatPlus FASTBREAK checks.
     *
     * @param player Player to exempt.
     */
    void exPlayer(Player player) {
        if (!NCPExemptionManager.isExempted(player, CheckType.BLOCKBREAK_FASTBREAK)) {
            NCPExemptionManager.exemptPermanently(player, CheckType.BLOCKBREAK_FASTBREAK);
        }
    }

    /**
     * Unexempt the given player from NoCheatPlus FASTBREAK checks.
     *
     * @param player Player to unexempt.
     */
    void unExPlayer(Player player) {
        if (NCPExemptionManager.isExempted(player, CheckType.BLOCKBREAK_FASTBREAK)) {
            NCPExemptionManager.unexempt(player, CheckType.BLOCKBREAK_FASTBREAK);
        }
    }
}
