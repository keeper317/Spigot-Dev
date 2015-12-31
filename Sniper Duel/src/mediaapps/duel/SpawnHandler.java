package mediaapps.duel;

import org.bukkit.entity.Player;

public class SpawnHandler
{
	public static void teletportToArena()
	{
		for(int i = 0; i < Main.players.size(); i++)
		{
			Player p = Main.players.get(i);
			p.teleport(Main.spawns[i + 1]);
		}
	}
	public static void teleportToLobby()
	{
		for(int i = 0; i < Main.players.size(); i++)
		{
			Player p = Main.players.get(i);
			p.teleport(Main.spawns[0]);
			p.getInventory().clear();
			Main.players.remove(p);
		}
		for(int i = 0; i < Main.specs.size(); i++)
		{
			Player p = Main.specs.get(i);
			p.teleport(Main.spawns[0]);
			ScoreBoardHandler.statBoard(p);
			Misc.gameEnded(p);
			p.getInventory().clear();
		}
	}
}
