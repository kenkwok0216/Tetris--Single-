package me.kenandwicky.tetris.Board;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BannerMeta;

public class Board {
		
	private static SettingsManager settings;
	private static Player player;
	
	public void building(Player player, SettingsManager settings) {
		Board.settings = settings;
		Board.player = player;
		if (PlayerDirection(player) == "South") {
			Location blockloc = player.getLocation();
			settings.getData().set("PlayerPosition.X", (int) blockloc.getX() - 1);
			settings.getData().set("PlayerPosition.Y", (int) blockloc.getY());
			settings.getData().set("PlayerPosition.Z", (int) blockloc.getZ());
			settings.saveData();
			blockloc = new Location(player.getWorld(),(int) blockloc.getX() - 15,(int) blockloc.getY() - 17,(int) blockloc.getZ() - 7);
			blockloc.getBlock().setType(Material.STRUCTURE_BLOCK);		
		} else {
			player.sendMessage("You must facing to South but you are now facing to " + PlayerDirection(player));
		}		
	}

	public static void setup() {
		if (settings != null) {
			String worldname = player.getWorld().getName().toString();
			int x = settings.getData().getInt("PlayerPosition.X");
			int y = settings.getData().getInt("PlayerPosition.Y");
			int z = settings.getData().getInt("PlayerPosition.Z");		
			Bukkit.getWorld(worldname).getBlockAt(x, y, z).setType(Material.AIR);
			x -= 14; y -= 17; z -= 7;
			Bukkit.getWorld(worldname).getBlockAt(x, y, z).setType(Material.AIR);
			x = settings.getData().getInt("PlayerPosition.X") + 16;
			y = settings.getData().getInt("PlayerPosition.Y") - 17;
			z = settings.getData().getInt("PlayerPosition.Z") + 32;
			for (int i = 0; i <= 31; i++) {
				for (int j = 0; j <= 25; j++) {
					if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.RED_CONCRETE) {
						SavePosition("HoldPosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.ORANGE_CONCRETE) {
						SavePosition("ScorePosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.YELLOW_CONCRETE) {
						SavePosition("LinePosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.GREEN_CONCRETE) {
						SavePosition("LevelPosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.BLUE_CONCRETE) {
						SavePosition("BoardPosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.LIGHT_BLUE_CONCRETE) {
						SavePosition("NextPosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.PURPLE_CONCRETE) {
						SavePosition("NamePosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						BuildBanner(new Location(player.getWorld(), x - i, y + j, z), '?');
					}
				}
			}			
		} else {
			return;
		}	
	}
	
	private static void SavePosition(String string, int x, int y, int z) {
		settings.getData().set(string + ".X", x);
		settings.getData().set(string + ".Y", y);
		settings.getData().set(string + ".Z", z);
	}

	private static void BuildBanner(Location blockloc, char c) {
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
