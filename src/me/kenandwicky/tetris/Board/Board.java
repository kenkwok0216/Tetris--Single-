package me.kenandwicky.tetris.Board;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BannerMeta;

public class Board {
	
	public void building(Player player, SettingsManager settings) {
		if (PlayerDirection(player) == "North") {
			Location blockloc = player.getLocation();
			settings.getData().set("PlayerPosition.X", (int) blockloc.getX());
			settings.getData().set("PlayerPosition.Y", (int) blockloc.getY());
			settings.getData().set("PlayerPosition.Z", (int) blockloc.getZ());
			settings.saveData();
		
		} else {
			player.sendMessage("You must facing to East but you are now facing to " + PlayerDirection(player));
		
		}
		
			
	}


	private void BuildBanner(Location blockloc, char c) {
		BannerClass banners = new BannerClass();
		BannerMeta meta = (BannerMeta) banners.Make(c).getItemMeta();
		blockloc.getBlock().setType(banners.Make(c).getType());		
		if (blockloc.getBlock().getType() == banners.Make(c).getType()) {
			Banner banner = (Banner) blockloc.getBlock().getState();
			banner.setPatterns(meta.getPatterns());
			banner.update();		
		}
	}
	
	//The East and West 
	private String PlayerDirection(Player player) {
		float degrees = player.getLocation().getYaw();
		player.sendMessage(degrees + "i am here");
        if (degrees < 22.5) { 
        	return "South";
        } else if (degrees < 67.5) {
        	return "Southwest";
        } else if (degrees < 112.5) {
        	return "West";
        } else if (degrees < 157.5) {
        	return "Northwest";
        } else if (degrees < 202.5) {
        	return "North";
        } else if (degrees < 247.5) {
        	return "Northeast";
        } else if (degrees < 292.5) {
        	return "East";
        } else if (degrees < 337.5) {
        	return "Southeast";
        } else {
        	return "South";
        }
		
	}
}
