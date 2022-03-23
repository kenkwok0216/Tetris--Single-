package me.kenandwicky.tetris.GameLoop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.kenandwicky.tetris.Tetris;
import me.kenandwicky.tetris.Board.Board;
import me.kenandwicky.tetris.Board.SettingsManager;
import me.kenandwicky.tetris.tools.DataEncryption;

public class Execute implements CommandExecutor {

	public static int gameLoopID = -1; //gameLoopId return -1 when failed
	public static Loop loop;
	public static Speed speed;
	public static boolean isGameGoing = false;
	SettingsManager settings = SettingsManager.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command commnd, String label, String[] args) {
				
		//if sender do not have the permission of builder, it cannot run this command
		if(!sender.hasPermission("SingleTetris.Builder")) {
			sender.sendMessage(ChatColor.RED + "You don't have the permission");
			return true;
		}
		
		int hashcode = 0;

		hashcode += settings.getData().getInt("HoldPosition.X");  
		hashcode += settings.getData().getInt("HoldPosition.Y");	
		hashcode += settings.getData().getInt("HoldPosition.Z");
		hashcode += settings.getData().getInt("BoardPosition.X");
		hashcode += settings.getData().getInt("BoardPosition.Y");
		hashcode += settings.getData().getInt("BoardPosition.Z");
		hashcode += settings.getData().getInt("ScorePosition.X");
		hashcode += settings.getData().getInt("ScorePosition.Y");
		hashcode += settings.getData().getInt("ScorePosition.Z");
		hashcode += settings.getData().getInt("LinePosition.X");
		hashcode += settings.getData().getInt("LinePosition.Y");
		hashcode += settings.getData().getInt("LinePosition.Z"); 
		hashcode += settings.getData().getInt("LevelPosition.X");
		hashcode += settings.getData().getInt("LevelPosition.Y");
		hashcode += settings.getData().getInt("LevelPosition.Z");
		hashcode += settings.getData().getInt("NamePosition.X");
		hashcode += settings.getData().getInt("NamePosition.Y");
		hashcode += settings.getData().getInt("NamePosition.Z"); 
		hashcode += settings.getData().getInt("PlayerPosition.X");
		hashcode += settings.getData().getInt("PlayerPosition.Y");
		hashcode += settings.getData().getInt("PlayerPosition.Z"); 
		hashcode += settings.getData().getInt("NextPosition.X");
		hashcode += settings.getData().getInt("NextPosition.Y");
		hashcode += settings.getData().getInt("NextPosition.Z");
		hashcode = (Integer.toString(hashcode)).hashCode();
		
		if(hashcode ==  settings.getData().getInt("hashcode")) {
			//nothing happen
		} else {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Tetris have Error");
			return true;
		}
		
		
		if (gameLoopID != -1) {
			Bukkit.getScheduler().cancelTask(gameLoopID);
		}
		
		loop = new Loop();
		speed = new Speed();
		Tetris.isStart = true;
		Tetris.game = new Game(Tetris.boardclass);
		gameLoopID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Tetris.plugin,
							loop,
							20, speed.ReturnSpeed(Board.getPlayerlevel()));	//Measure in takes (20 takes in second)
							// first number is delay 
							//e.g. 60 means "wait for 60 takes before it start"
							//the second number is interval
							//e.g. 20 means for every 20 takes. it will run "loop"
		return true;
	}	
}
