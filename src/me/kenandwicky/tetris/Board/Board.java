package me.kenandwicky.tetris.Board;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BannerMeta;

public class Board {
	
	public void building(Player player) {
		BannerClass banners = new BannerClass();
		Location blockloc = player.getLocation();
		BannerMeta meta = (BannerMeta) banners.Make('C').getItemMeta();
		blockloc.getBlock().setType(banners.Make('C').getType());		
		if(blockloc.getBlock().getType() == banners.Make('C').getType()) {
			Banner banner = (Banner) blockloc.getBlock().getState();
			banner.setPatterns(meta.getPatterns());
			banner.update();
		}
		
	}
	
}
