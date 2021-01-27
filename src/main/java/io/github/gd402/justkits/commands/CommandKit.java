package io.github.gd402.justkits.commands;

import io.github.gd402.justkits.JustKits;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// This is the main command of the JustKits plug-in. It takes in one argument, the name of the kit, and runs it through multiple checks before either ending the function with an error (if the player or command doesn't meet the criteria specified in the config) or giving the items to the player

public class CommandKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandMain, String[] commandArgs) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender; // Cast to Player instance to use Player-specific methods

            if(commandArgs.length == 0) { commandSender.sendMessage(ChatColor.RED + "No kit defined. Try /kits to see available kits. Usage: /kit [kit name]"); return false; } // Check to see if kit name is not specified

            List<String> availableKits = new ArrayList<>(Objects.requireNonNull(JustKits.getConfigFile().getConfigurationSection("kits")).getKeys(false)); // Grabs all kits listed under 'kit' in config
            boolean isKitAvailable = false;

            for(String kit : availableKits) { // Checks if kit is in config
                if(kit.equals(commandArgs[0])) { isKitAvailable = true; break; }
            }

            if(!isKitAvailable) { commandSender.sendMessage(ChatColor.RED + "'" + commandArgs[0] + "' is not a valid kit."); return false; } // If kit isn't in config, say it isn't valid and break from function

            List<String> kitItems = new ArrayList<>(Objects.requireNonNull(JustKits.getConfigFile().getConfigurationSection(("kits." + commandArgs[0] + ".items"))).getKeys(false)); // Grabs all config items for the selected kit

            for(String item : kitItems) { // Grabs item material and item amount from each item under the selected kit, puts those two values into a new ItemStack, and submits that ItemStack as a parameter for addItem() which then adds the item to the player's inventory
                player.getInventory().addItem(new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(JustKits.getConfigFile().getString("kits." + commandArgs[0] + ".items." + item + ".type")))), JustKits.getConfigFile().getInt("kits." + commandArgs[0] + ".items." + item + ".amount"))); // Has null ptr checks, not that necessary but intellij wanted me to put those in
            }

            commandSender.sendMessage(ChatColor.GREEN + "'" + commandArgs[0] + "' kit has been added to your inventory.");

        } else { commandSender.sendMessage(ChatColor.RED + "Kit may only be used by a player."); } // Triggered if command sender is from server console; not a player
        return true;
    }
}
