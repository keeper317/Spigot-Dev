package mediaapps.CTT;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class GameCommands 
{

	public static boolean Command(Player p, Command cmd, String str, String[] args) 
	{
		if(str.equalsIgnoreCase("CTT"))
		{
			if(args[0].equalsIgnoreCase("set"))
			{
				if(args[1].equalsIgnoreCase("rspawn"))
				{
					Main.spawns[0] = p.getLocation();
					Main.toConfig("rspawn", p.getLocation());
				}
				if(args[1].equalsIgnoreCase("bspawn"))
				{
					Main.spawns[1] = p.getLocation();
					Main.toConfig("bspawn", p.getLocation());
				}
				if(args[1].equalsIgnoreCase("Lobby"))
				{
					Main.spawns[2] = p.getLocation();
					Main.toConfig("lobby", p.getLocation());
				}
				if(args[1].equalsIgnoreCase("rmonument"))
				{
					Main.monument[0] = p.getLocation();
					Main.toConfig("rmonument", p.getLocation());
				}
				if(args[1].equalsIgnoreCase("bmonument"))
				{
					Main.monument[1] = p.getLocation();
					Main.toConfig("bmonument", p.getLocation());
				}
				if(args[1].equalsIgnoreCase("rcorner"))
				{
					Main.field[0] = p.getLocation();
					Main.toConfig("rcorner", p.getLocation());
				}
				if(args[1].equalsIgnoreCase("bcorner"))
				{
					Main.field[1] = p.getLocation();
					Main.toConfig("bcorner", p.getLocation());
				}
				if(args[1].equalsIgnoreCase("center"))
				{
					Main.field[2] = p.getLocation();
					Main.toConfig("center", p.getLocation());
				}
				p.sendMessage("Location set to: " + p.getLocation());
				return true;
			}
		}
		else if(str.equalsIgnoreCase("join"))
		{
			if(args[0].equalsIgnoreCase("red"))
				Misc.joinTeam("red", p);
			else if(args[0].equalsIgnoreCase("blue"))
				Misc.joinTeam("blue", p);
			else
				p.sendMessage("�cPick either blue or red!");
			p.sendMessage("You have joined the " + args[0] + " team");
			Main.players.add(p);
			GameManagement.checker();
			return true;
		}
		else if(str.equalsIgnoreCase("ForceStart"))
		{
			GameManagement.gameStart();
			return true;
		}
		else if(str.equalsIgnoreCase(("forceend")))
		{
			GameManagement.gameEnd();
			return true;
		}
		return false;
	}

}
