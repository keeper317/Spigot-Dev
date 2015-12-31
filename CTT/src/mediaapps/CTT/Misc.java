package mediaapps.CTT;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Misc {

	public static String locToString(Location loc)
	{
		String str = loc.getWorld().getName() + ":" + (int) loc.getX() + ":" + (int) loc.getY() + ":" + (int) loc.getZ();
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
	public static void setSpawn(Player p)
	{
		//if(Main.team.get(p.getName()).equalsIgnoreCase("Blue"))
		//	p.set
		//else if(Main.team.get(p.getName()).equalsIgnoreCase("Red"))
			//p.setBedSpawnLocation(Main.spawns[0]);
	}
	public static void setBlock(Location loc, Inventory inv)
	{
		Location broken = loc;
		Block block = broken.getWorld().getBlockAt(broken.getBlockX(), broken.getBlockY(), broken.getBlockZ());
		block.setType(Material.CHEST);
		Chest c = (Chest) block.getState();
		c.getInventory().setContents(inv.getContents());
		c.getBlockInventory().setContents(inv.getContents());
	}
	public static void joinTeam(String string, Player p) 
	{
		Main.team.put(p.getName(), string);
	}
	public static int getKills(Player p) 
	{
		if(Main.kills.get(p.getName()) == null)
			return 0;
		else
			return Main.kills.get(p.getName());
	}
	public static void setTotalKills(Player p)
	{
		if(Main.totalKills.get(p.getName()) != null)
			Main.totalKills.put(p.getName(), Main.totalKills.get(p.getName()) + Misc.getKills(p));
		else
			Main.totalKills.put(p.getName(), Misc.getKills(p));
	}
	public static int getTotalKills(Player p) 
	{
		if(Main.totalKills.get(p.getName()) == null)
			return 0;
		else
			return Main.totalKills.get(p.getName());
	}
	public static int getWins(Player p) 
	{
		if(Main.wins.get(p.getName()) == null)
			return 0;
		else
			return Main.wins.get(p.getName());
	}
}
