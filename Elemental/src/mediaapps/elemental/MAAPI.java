package mediaapps.elemental;

import org.bukkit.entity.Player;

public class MAAPI 
{
	public static void addPoints(Player p, int i)
	{
		Main.points.put(p.getName(), Main.points.get(p.getName()) + i);
	}
	public static void takePoints(Player p, int i)
	{
		Main.points.put(p.getName(), Main.points.get(p.getName()) - i);
	}
	public static void addKills(Player p, int i)
	{
		Main.points.put(p.getName(), Main.points.get(p.getName()) + i);
	}
	public static void addDeaths(Player p, int i)
	{
		Main.points.put(p.getName(), Main.points.get(p.getName()) + i);
	}
	public static int getTeam(Player p)
	{
		return Main.team.get(p.getName());
	}
}
