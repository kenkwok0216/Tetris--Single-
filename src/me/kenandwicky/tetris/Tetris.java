package me.kenandwicky.tetris;

import me.kenandwicky.tetris.Board.*;
import me.kenandwicky.tetris.Listener.EventListener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Tetris extends JavaPlugin {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public void onEnable() {
		Bukkit.getServer().getConsoleSender().sendMessage("Tetris is working");
		Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), this);
		settings.setup(this);	
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("initializetetris")) {
			Board.initialize(player, settings);	
		}
		
		if (cmd.getName().equalsIgnoreCase("re")) {
			Board.NameUpdate(args[0]);	
		} 
		
		if(cmd.getName().equalsIgnoreCase("settetris")) {
			Board board = new Board();
			board.building(player, settings);	
		}
		
		
		
		return true;
	}
	
	
}
