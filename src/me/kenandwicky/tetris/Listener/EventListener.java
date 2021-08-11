package me.kenandwicky.tetris.Listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import me.kenandwicky.tetris.GameLoop.Game;
import me.kenandwicky.tetris.Tetris;
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
    
    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e) {
    	if(Tetris.isStart == false) {
    		return;
    	}
    	
    	if(e.getAction().equals(Action.LEFT_CLICK_AIR)) {
    		Game.rotatedLeft();
    		e.getPlayer().sendMessage("Rotate left");
    		return;
    	} else if(e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
    		Game.rotatedRight();
    		e.getPlayer().sendMessage("Rotate Right");
    		return;
    	}
    	
    }
    
    
    
    
    @EventHandler
    public static void onPlayerMovement(PlayerMoveEvent e) {
    	if(Tetris.isStart == false) {
    		return;
    	}
    	
    	Location fromLocation = e.getFrom();
    	Location toLocation = e.getTo();
    	
    	double xDiff = Math.abs(toLocation.getX() - fromLocation.getX());
    	double yDiff = Math.abs(toLocation.getY() - fromLocation.getY());
    	double zDiff = Math.abs(toLocation.getZ() - fromLocation.getZ());
    	
    	if (xDiff > 0 || yDiff > 0 || zDiff > 0) {
    		e.getPlayer().teleport(fromLocation.setDirection(toLocation.getDirection()));
    	}
    	
    	if (yDiff > 0) {
    		Game.holdPiece();
    	}
    	
    	if (zDiff > xDiff) {
    		if (toLocation.getZ() - fromLocation.getZ() < 0) {
    			Game.moveDown();
    			e.getPlayer().sendMessage("Down");
    		} else {
    			return;
    		}
    		return;
    	}
    	
    	if (xDiff > zDiff) {
    		if (toLocation.getX() - fromLocation.getX() > 0) {
    			Game.moveLeft();
    			e.getPlayer().sendMessage("Left");    			
    		} else {
    			Game.moveRight();
    			e.getPlayer().sendMessage("Right");
    		}
    		
    		return;	
    	}   	    	
    }
    
	
}
