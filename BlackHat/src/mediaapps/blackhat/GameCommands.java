package mediaapps.blackhat;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class GameCommands {

	public static boolean Command(Player p, Command cmd, String str, String[] args) throws SQLException 
	{
		if(str.equalsIgnoreCase("bh"))
		{
			if(args[0].equalsIgnoreCase("shop"))
			{
				GUIHandler.mainGui(p);
				p.openInventory(GUIHandler.main);
				return true;
			}
			if(args[0].equalsIgnoreCase("join"))
			{
				if(args[1].equalsIgnoreCase("white") && !Main.team.get(p.getName()).equalsIgnoreCase("white"))
				{
					Main.team.put(p.getName(), "white");
					Main.totalPlayers++;
					return true;
				}
				else if(args[1].equalsIgnoreCase("black") && !Main.team.get(p.getName()).equalsIgnoreCase("black"))
				{
					Main.team.put(p.getName(), "black");
					Main.totalPlayers++;
					return true;
				}
				else if(Main.team.containsKey(p.getName()))
				{
					p.sendMessage("You are already on that team.");
				}
				else
					p.sendMessage("�cError: You need to pick either �0�lWHITE or �f�lBLACK");
			}
		}
		return false;
	}

}
