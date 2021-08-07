package me.kenandwicky.tetris.Listener;

import org.bukkit.GameMode;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.kenandwicky.tetris.Board.Board;
import me.kenandwicky.tetris.Board.SettingsManager;

public class EventListener implements Listener {
	SettingsManager settings = SettingsManager.getInstance();
	
    @EventHandler
    public void onBuilderBreak(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
        	Board.setup();
        }
    }
    
    @EventHandler
    public void onPlayerJoing(PlayerJoinEvent e) {
    	if (e.getPlayer().getGameMode() == GameMode.ADVENTURE) {
    		Board.NameUpdate(e.getPlayer().getName());
    	}
    	Board.initialize(e.getPlayer(), settings);
    }
	
}
