package mediaapps.LilyPad.util;

import mediaapps.LilyPad.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SpawnHandler
{
	public static void teletportToArena()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.teleport(Main.spawns[0]);
		}
	}
}
