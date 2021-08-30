package me.kenandwicky.tetris;

import me.kenandwicky.tetris.Board.*;
import me.kenandwicky.tetris.GameLoop.*;
import me.kenandwicky.tetris.Listener.EventListener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Tetris extends JavaPlugin {
	
	SettingsManager settings = SettingsManager.getInstance();
	public static Board boardclass = new Board();
	public static Plugin plugin;
	public static Game game = null;
	public static boolean isStart = false;
	
	
	
	public void onEnable() {
		Bukkit.getServer().getConsoleSender().sendMessage("Tetris is working");
		Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), this);
		settings.setup(this);
		plugin = this;
		this.getCommand("gamestart").setExecutor(new Execute());
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
			boardclass.building(player, settings);	
		}
		
		if(cmd.getName().equalsIgnoreCase("next")) {
			boardclass.NextPiece();
		}
		
		if(cmd.getName().equalsIgnoreCase("hold")) {
			boardclass.HoldBox();
		}
		
		if(cmd.getName().equalsIgnoreCase("start")) {
			game = new Game(boardclass);
			isStart = true;	
		}
		
		
		if(cmd.getName().equalsIgnoreCase("gamenext")) {
			game.Next();
		}
		
		
		if(cmd.getName().equalsIgnoreCase("getposition")) {
			boardclass.get(Integer.parseInt(args[0],10), Integer.parseInt(args[1],10));
		}
		
		
		
		
		return true;
	}
	
	
}
