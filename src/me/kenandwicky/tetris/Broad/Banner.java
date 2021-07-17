package me.kenandwicky.tetris.Broad;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;

public class Banner {
	
	CreateBanner banner = new CreateBanner();
	
	public ItemStack Make(char c) {
		return banner.WordOfBanner(c);
	}
}

//delegate
class CreateBanner {
	
	public ItemStack WordOfBanner(char c) {
		if (c == 'A') {
			return ACreator.Create();
		} else if (c == 'B') {
			return BCreator.Create();
		} else if (c == 'C') {
			return CCreator.Create();
		}
		return new ItemStack(Material.WHITE_BANNER, 1);
	}	
}

//delegators
class ACreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		
		m.setPatterns(patterns);
		m.setDisplayName("Banner A");
		
		i.setItemMeta(m);
		
		return i;		
	}
}

class BCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.CURLY_BORDER));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		
		m.setPatterns(patterns);
		m.setDisplayName("Banner B");
		
		i.setItemMeta(m);
		
		return i;		
	}
}

class CCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner C");
		
		i.setItemMeta(m);
		
		return i;		
	}
}


