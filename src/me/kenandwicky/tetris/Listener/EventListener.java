package me.kenandwicky.tetris.Listener;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.kenandwicky.tetris.Board.Board;

public class EventListener implements Listener {
	
    @EventHandler
    public void onBuilderBreak(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
        	Board.setup();
        }
    }
	
}
