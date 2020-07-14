package eu.internetpolice.spa.command;

import eu.internetpolice.spa.SuperpickaxeReloaded;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpaCommand implements CommandExecutor, TabCompleter {
    private final SuperpickaxeReloaded plugin;

    public SpaCommand(SuperpickaxeReloaded plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Error: Only in-game players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (commandLabel.equalsIgnoreCase("spa")) {
            if (player.hasPermission("spa.use")) {
                plugin.getSpaManager().toggleSpa(player, true);
                return true;
            }

            player.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. " +
                    "Please contact the server administrators if you believe that this is in error.");
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
