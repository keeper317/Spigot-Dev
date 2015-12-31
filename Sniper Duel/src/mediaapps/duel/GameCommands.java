package mediaapps.duel;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class GameCommands {

	public static boolean Command(Player p, Command cmd, String str, String[] args) 
	{
		if(str.equalsIgnoreCase("SD"))
		{
			if(args[0].equalsIgnoreCase("set"))
			{
				if(args[1].equalsIgnoreCase("lobby"))
				{
					Main.spawns[0] = p.getLocation();
					Main.toConfig("Lobby", p.getLocation());
				}
				if(args[1].equalsIgnoreCase("iron"))
				{
					Main.spawns[1] = p.getLocation();
					Main.toConfig("Iron", p.getLocation());
				}
				if(args[1].equalsIgnoreCase("diamond"))
				{
					Main.spawns[2] = p.getLocation();
					Main.toConfig("Diamond", p.getLocation());
				}
				if(args[1].equalsIgnoreCase("gold"))
				{
					Main.spawns[3] = p.getLocation();
					Main.toConfig("Gold", p.getLocation());
				}
				if(args[1].equalsIgnoreCase("emerald"))
				{
					Main.spawns[4] = p.getLocation();
					Main.toConfig("Emerald", p.getLocation());
				}
				p.sendMessage("Location set to: " + Misc.locToString(p.getLocation()));
				return true;
			}
			else if(args[0].equalsIgnoreCase("Leave"))
			{
				if(Main.players.contains(p))
				{
					Main.players.remove(p);
					p.sendMessage("You have left the lobby!");
				}
				else
					p.sendMessage("You are not in the game!");
			}
			else if(args[0].equalsIgnoreCase("start"))
				Manager.startGame();
			else if(args[0].equalsIgnoreCase("end"))
				Manager.endGame();
		}
		return false;
	}
}
