package me.kenandwicky.tetris.Board;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BannerMeta;

import me.kenandwicky.tetris.Utils;
import me.kenandwicky.tetris.Tetromino.Tetromino;
import me.kenandwicky.tetris.Tetromino.TetrominoType;
import me.kenandwicky.tetris.tools.RandomClass;

public class Board {
		
	private static SettingsManager settings;
	private static Player player;
	private static TetrominoType[] bag = new TetrominoType[4];
	private static int NextPositionX, NextPositionY, NextPositionZ;
	private static int HoldPositionX;
	private static int HoldPositionY;
	private static int HoldPositionZ;
	public static Tetromino currentpiece;
	public static Tetromino holdpiece;
	
	
	private RandomClass rnd = new RandomClass();
	private boolean isHold = false;
	public static TetrominoType[][] board = new TetrominoType[20][10];
	
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
			settings = SettingsManager.getInstance();
		}	
	}
	
	public void NextPiece() {
		if (bag[0] == null) {
			int y = rnd.TetrisRandom();
			TetrominoType starttype = TetrominoType.values()[y];
			Tetromino startpiece = new Tetromino(starttype);
			currentpiece = startpiece;
			for (int i = 1; i < 5; i++) {		
				int x = rnd.TetrisRandom();
				TetrominoType type = TetrominoType.values()[x];
				Tetromino piece = new Tetromino(type);
				bag[i - 1] = type;
				NextPositionX = settings.getData().getInt("NextPosition.X") - 1;
				NextPositionY = settings.getData().getInt("NextPosition.Y") + (4-i) * 5;
				NextPositionZ = settings.getData().getInt("NextPosition.Z") + 1;
				ClearPieceinBox(NextPositionX, NextPositionY, NextPositionZ);
				setPieceBlocks(NextPositionX, NextPositionY, NextPositionZ, piece, type);
			}
		} else {
			Tetromino nextpiece = new Tetromino(bag[0]);
			currentpiece = nextpiece;
			for (int i = 0; i < 3; i++) {
				bag[i] = bag[i+1];
			}
			int x = rnd.TetrisRandom();
			bag[3] = TetrominoType.values()[x];
			for (int i = 1; i < 5; i++) {
				Tetromino piece = new Tetromino(bag[i-1]);
				NextPositionX = settings.getData().getInt("NextPosition.X") - 1;
				NextPositionY = settings.getData().getInt("NextPosition.Y") + (4-i) * 5;
				NextPositionZ = settings.getData().getInt("NextPosition.Z") + 1;
				ClearPieceinBox(NextPositionX, NextPositionY, NextPositionZ);
				setPieceBlocks(NextPositionX, NextPositionY, NextPositionZ, piece, bag[i-1]);
			}
		}
				
	}
	
	public void ClearPieceinBox(int x, int y, int z) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            	Bukkit.getWorld("world").getBlockAt(x - i, y + j, z).setType(Material.AIR);
            }
        }
	}
	
	public void HoldBox() {
		if(isHold == false) {
			Tetromino piece = currentpiece;
			TetrominoType type = currentpiece.type;
			HoldPositionX = settings.getData().getInt("HoldPosition.X") - 1;
			HoldPositionY = settings.getData().getInt("HoldPosition.Y");
			HoldPositionZ = settings.getData().getInt("HoldPosition.Z") + 1;
			ClearPieceinBox(HoldPositionX, HoldPositionY, HoldPositionZ);
			ClearPieceinBox(HoldPositionX + 1, HoldPositionY, HoldPositionZ);
			setPieceBlocks(HoldPositionX, HoldPositionY, HoldPositionZ, piece, type);
			holdpiece = piece;
			NextPiece();
			isHold = true;
		} else {
			HoldPositionX = settings.getData().getInt("HoldPosition.X") - 1;
			HoldPositionY = settings.getData().getInt("HoldPosition.Y");
			HoldPositionZ = settings.getData().getInt("HoldPosition.Z") + 1;
			Tetromino tmppiece = currentpiece;
			currentpiece = holdpiece;
			holdpiece = tmppiece;
			ClearPieceinBox(HoldPositionX, HoldPositionY, HoldPositionZ);
			ClearPieceinBox(HoldPositionX + 1, HoldPositionY, HoldPositionZ);
			setPieceBlocks(HoldPositionX, HoldPositionY, HoldPositionZ, holdpiece, holdpiece.type);	
		}
	}
	
	
    public void setPieceBlocks(int coordX, int coordY, int coordZ, Tetromino piece, TetrominoType type) {	
    	for (int i = 0; i < 4; i++) {
            int x, y;   
            x = coordX + piece.coords[i][0];
            y = coordY + piece.coords[i][1];
            setBlock(x, y, coordZ, type);
        }
    }
    
    public void setBlock(int x, int y, int z, TetrominoType t) {
        Utils.placeTetromino("world", x, y, z, t);
    }
	
	private static void SavePosition(String string, int x, int y, int z) {
		if (settings != null) {
			settings.getData().set(string + ".X", x);
			settings.getData().set(string + ".Y", y);
			settings.getData().set(string + ".Z", z);
		} else {
			settings = SettingsManager.getInstance();
		}

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
	
	public static void NameUpdate(String s) {
		if(settings != null) {
			int x = settings.getData().getInt("NamePosition.X");
			int y = settings.getData().getInt("NamePosition.Y");
			int z = settings.getData().getInt("NamePosition.Z");
			String reverse = "";
			for(int i = s.length() - 1; i >= 0; i--) {
				if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
					reverse = reverse + (char) (s.charAt(i) - ('a' - 'A'));
				} else {
					reverse = reverse + s.charAt(i);
				}	
			}
			for(int i = 0; i < s.length(); i++) {
				BuildBanner(new Location(player.getWorld(), x + i, y, z), reverse.charAt(i));
			}
			for(int i = 0; i < 18 - s.length(); i++) {
				BuildBanner(new Location(player.getWorld(), x + i + s.length(), y, z), '?');
			}
		} else {
			settings = SettingsManager.getInstance();
		}
	}
	
	public static void initialize(Player player, SettingsManager settings) {
			Board.settings = settings;
			Board.player = player;
	}
	
	public void TetrisBoard(int boardX, int boardY, Tetromino piece, TetrominoType type) {
		
		int x = settings.getData().getInt("BoardPosition.X");
		int y = settings.getData().getInt("BoardPosition.Y");
		int z = settings.getData().getInt("BoardPosition.Z") + 1;
		setPieceBlocks(x - boardX, y + boardY, z, piece, type);	
		
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
