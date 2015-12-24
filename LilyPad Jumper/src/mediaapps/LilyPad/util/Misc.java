package mediaapps.LilyPad.util;

import java.sql.SQLException;

import mediaapps.LilyPad.Main;
import mediaapps.LilyPad.Manager;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Misc 
{
	public static void setBlock(int x, int z, World world)
	{
		Block block = world.getBlockAt(x, (int) Main.field[0].getY(), z);
		if(block.getType() == Material.AIR)
			block.setType(Material.WATER_LILY);
	}
	public static void setBlockAir(int x, int z, World world)
	{
		Block block = world.getBlockAt(x, (int) Main.field[0].getY(), z);
		if(block.getType() == Material.WATER_LILY)
			block.setType(Material.AIR);
	}
	public static void setBlockAir(Block block)
	{
		if(block.getType() == Material.WATER_LILY)
			block.setType(Material.AIR);
	}
	public static void addSpecs(Player p) throws IllegalArgumentException, IllegalStateException, SQLException
	{
		Main.players.remove(p);
		Main.specs.add(p);
		p.setAllowFlight(true);
		p.setCanPickupItems(false);
		p.setFlying(true);
		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 10, true), true);
		p.sendMessage("§c§L§N" + Main.obmsg);
		p.teleport(Main.spawns[0]);
		if(Main.players.size() < 1)
			Manager.endGame();
	}
	public static void addPlayers(Player p)
	{
		p.setAllowFlight(true);
		p.setFlying(false);
		p.setGameMode(GameMode.ADVENTURE);
		Main.players.add(p);
		Main.specs.remove(p);
		p.setCanPickupItems(false);
		p.setFlying(false);
		p.removePotionEffect(PotionEffectType.INVISIBILITY);
	}
	public static void gameEnded(Player p) throws IllegalArgumentException, IllegalStateException, SQLException
	{
		p.setFlying(false);
		Main.players.remove(p);
		Main.specs.remove(p);
		p.setCanPickupItems(true);
		p.setAllowFlight(false);
		p.setFlying(false);
		p.removePotionEffect(PotionEffectType.INVISIBILITY);
		ScoreBoardHandler.statBoard(p);
	}
	public static String locToString(Location loc)
	{
		String str = loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ();
		return str;
	}
	public static Location setLocs(String str)
	{
		 	String str2loc[]=str.split(":");
		 
		    Location loc = new Location(Bukkit.getWorld(str2loc[0]),0,0,0);
		 
		    loc.setX(Double.parseDouble(str2loc[1]));
		 
		    loc.setY(Double.parseDouble(str2loc[2]));
		 
		    loc.setZ(Double.parseDouble(str2loc[3]));
		 
		    return loc;
	}
}
