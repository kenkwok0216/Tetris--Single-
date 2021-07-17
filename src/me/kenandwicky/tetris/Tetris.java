package me.kenandwicky.tetris;

import me.kenandwicky.tetris.Broad.*;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Tetris extends JavaPlugin {
	public void onEnable() {
		Bukkit.getServer().getConsoleSender().sendMessage("Tetris is working");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
	    PlayerInventory pi = player.getInventory();
		if(cmd.getName().equalsIgnoreCase("givebanner")) {
			Banner banner = new Banner();
			for(char i = 'A'; i <= 'C'; i++) {
				pi.addItem(banner.Make(i));
			}
			return true;
		}
		return true;
	}
}
