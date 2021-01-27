package io.github.gd402.justkits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandMain, String[] commandArgs) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender; // Cast to Player instance to use Player-specific methods

            ItemStack item1 = new ItemStack(Material.ARROW, 64);
            ItemStack item2 = new ItemStack(Material.BOW);

            player.getInventory().addItem(item1, item2);
        } else { commandSender.sendMessage(ChatColor.RED + "Kit may only be used by a player."); }
        return true;
    }
}
