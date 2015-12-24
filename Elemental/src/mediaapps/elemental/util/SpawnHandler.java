package mediaapps.elemental.util;

import mediaapps.elemental.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SpawnHandler
{
	public static void teletportToArena()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(Main.team.get(p.getName()) == 0)
				p.teleport(Main.spawns[0]);
			else if(Main.team.get(p.getName()) == 1)
				p.teleport(Main.spawns[1]);
		}
	}
	public static void teleportToLobby()
	{
		for(Player p : Bukkit.getOnlinePlayers())
			p.teleport(Main.misc[1]);
	}
}
