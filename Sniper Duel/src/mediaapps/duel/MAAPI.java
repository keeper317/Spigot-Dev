package mediaapps.duel;

import org.bukkit.entity.Player;

public class MAAPI 
{
	public static void addPoints(Player p, int i)
	{
		if(Main.points.get(p.getName()) == null)
			Main.points.put(p.getName(), Main.plugin.getConfig().getInt(p.getName()));
		Main.points.put(p.getName(), Main.points.get(p.getName()) + i);
		Main.toConfig(p.getName(), getPoints(p));
	}
	public static void takePoints(Player p, int i)
	{
		Main.points.put(p.getName(), Main.points.get(p.getName()) - i);
		Main.toConfig(p.getName(), getPoints(p));
	}
	public static void setPoints(Player p, int i)
	{
		if(Main.points.get(p.getName()) == null)
			Main.points.put(p.getName(), Main.plugin.getConfig().getInt(p.getName()));
		Main.points.put(p.getName(),  i);
		Main.toConfig(p.getName(), getPoints(p));
	}
	public static int getPoints(Player p)
	{
		if(Main.points.get(p.getName()) == null)
		{
			if(Main.plugin.getConfig().getInt(p.getName()) <= 0)
				return Main.plugin.getConfig().getInt(p.getName());
			else
				return 0;
		}
		else
			return Main.points.get(p.getName());
	}
	public static void addWin(Player p)
	{
		if(Main.wins.get(p.getName()) == null)
			Main.wins.put(p.getName(), Main.plugin.getConfig().getInt(p.getName() + " wins"));
		Main.wins.put(p.getName(), Main.wins.get(p.getName()) + 1);
		Main.toConfig(p.getName() + " wins", getWins(p));
	}
	public static int getWins(Player p)
	{
		if(Main.wins.get(p.getName()) == null)
			return 0;
		else
			return Main.wins.get(p.getName());
	}
	public static void addKill(Player p) 
	{
		if(Main.kills.get(p.getName()) == null)
			Main.kills.put(p.getName(), Main.plugin.getConfig().getInt(p.getName() + " kills"));
		Main.kills.put(p.getName(), Main.kills.get(p.getName()) + 1);
		Main.toConfig(p.getName() + " kills", getKills(p));
	}
	public static int getKills(Player p)
	{
		if(Main.kills.get(p.getName()) == null)
			return 0;
		else
			return Main.kills.get(p.getName());
	}
}

