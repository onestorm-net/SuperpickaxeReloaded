package me.bubba1234119.SuperpickaxeReloaded.command;

import me.bubba1234119.SuperpickaxeReloaded.SuperpickaxeReloaded;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpaCommand implements CommandExecutor {
    /**
     * Executes the given command, returning its success
     *
     * @param sender       Source of the command
     * @param cmd          Command which was executed
     * @param commandLabel Alias of the command which was used
     * @param args         Passed command arguments
     * @return true if a valid command, otherwise false
     */
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Error: Only in-game players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (commandLabel.equalsIgnoreCase("spa")) {
            if (player.hasPermission("spa.use")) {
                SuperpickaxeReloaded.getInstance().getSpaManager().toggleSpa(player, true);
                return true;
            }

            player.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. " +
                    "Please contact the server administrators if you believe that this is in error.");
            return true;
        }

        return false;
    }
}
