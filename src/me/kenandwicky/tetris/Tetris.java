package me.kenandwicky.tetris;

import me.kenandwicky.tetris.Board.*;

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
			BannerClass banner = new BannerClass();
			pi.addItem(banner.Make('0'));
			pi.addItem(banner.Make('1'));
			pi.addItem(banner.Make('2'));
			pi.addItem(banner.Make('3'));
			pi.addItem(banner.Make('4'));
			pi.addItem(banner.Make('5'));
			pi.addItem(banner.Make('6'));
			pi.addItem(banner.Make('7'));
			pi.addItem(banner.Make('8'));
			pi.addItem(banner.Make('9'));
			pi.addItem(banner.Make('_'));
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("settetris")) {
			Board board = new Board();
			board.building(player);		
			
		}
		
		
		
		return true;
	}
	
	
}
