package mediaapps.sqlban;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class GameCommands {

	public static boolean Command(Player p, Command cmd, String str, String[] args) throws SQLException 
	{
		if(str.equalsIgnoreCase("msql"))
		{
			if(args[0].equalsIgnoreCase("adduser"))
			{
				SQLHandler.insertUser(args[1], args[2]);
				p.sendMessage(args[1] + " Has been added to allow list.");
			}
			else if(args[0].equalsIgnoreCase("banuser"))
			{
				SQLHandler.banUser(args[1]);
				p.sendMessage(args[1] + " is now banned from the server.");
				Player un = Bukkit.getPlayer(args[1]);
				un.setBanned(true);
			}
			else if(args[0].equalsIgnoreCase("pardonuser"))
			{
				SQLHandler.pardonUser(args[1]);
				p.sendMessage(args[1] + " is now pardoned from their ban.");
				Player un = Bukkit.getPlayer(args[1]);
				un.setBanned(false);
			}
			return true;
		}
		return false;
	}

}
