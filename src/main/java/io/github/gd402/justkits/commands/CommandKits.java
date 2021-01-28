package io.github.gd402.justkits.commands;

import io.github.gd402.justkits.JustKits;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// This command outputs all the kits made available in the config

public class CommandKits implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String commandMain, String[] commandArgs) {

        if(commandArgs.length > 0) {
            commandSender.sendMessage(ChatColor.RED + "This command does not use any arguments.");
            return true;
        }

        List<String> availableKits = new ArrayList<>(Objects.requireNonNull(JustKits.getConfigFile().getConfigurationSection("kits")).getKeys(false)); // Grabs each item under 'kit' in the config file, which will be the names of each kit

        if(availableKits.isEmpty()) {
            commandSender.sendMessage(ChatColor.YELLOW + "No kits are currently available. Please check the JustKits config file."); // Checks if any kits are found, notifies sender if there are none found in the config
        } else {
            StringBuilder result = new StringBuilder(ChatColor.BOLD + "Available Kits: " + ChatColor.RESET); // Base string to be used for output of available kits
            for(String kit : availableKits) { // Adds each kit name to 'result' string
                result.append(kit).append(" ");
            }
            commandSender.sendMessage(ChatColor.YELLOW + result.toString()); // Sends 'result' string to sender; a list of all kits
        }

        return true;
    }
}
