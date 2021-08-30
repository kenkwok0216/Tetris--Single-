package me.kenandwicky.tetris.GameLoop;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.kenandwicky.tetris.Tetris;

public class Execute implements CommandExecutor {

	public static int gameLoopID = -1; //gameLoopId return -1 when failed
	public static Loop loop;
	public static boolean isGameGoing = false;
	@Override
	public boolean onCommand(CommandSender sender, Command commnd, String label, String[] args) {
		if (gameLoopID != -1) {
			Bukkit.getScheduler().cancelTask(gameLoopID);
		}
		
		loop = new Loop();
		Tetris.isStart = true;
		Tetris.game = new Game(Tetris.boardclass);
		gameLoopID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Tetris.plugin,
							loop,
							20, 20);	//Measure in takes (20 takes in second)
							// first number is delay 
							//e.g. 60 means "wait for 60 takes before it start"
							//the second number is intervel
							//e.g. 20 means for every 20 takes. it will run "loop"
		return true;
	}
	
	
	
	
}