package mediaapps.elemental.commands;

import mediaapps.elemental.Main;
import mediaapps.elemental.Manager;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args)
	{
		Player p = (Player) sender;
		
		if(str.equalsIgnoreCase("Yin"))
			Main.team.put(p.getName(), 1);
		
		else if(str.equalsIgnoreCase("Yang"))
			Main.team.put(p.getName(), 0);
		
		else if(str.equalsIgnoreCase("setspec"))
		{
			Location loc = p.getLocation();
			Main.misc[0] = new Location(p.getWorld(), loc.getX(), loc.getY(), loc.getZ());
			p.sendMessage("Location set to " + p.getLocation().toString());
		}
		else if(str.equalsIgnoreCase("setlobby"))
		{
			Location loc = p.getLocation();
			Main.misc[1] = new Location(p.getWorld(), loc.getX(), loc.getY(), loc.getZ());
			p.sendMessage("Location set to " + p.getLocation().toString());
		}
		else if(str.equalsIgnoreCase("setyin"))
		{
			Location loc = p.getLocation();
			Main.spawns[0] = new Location(p.getWorld(), loc.getX(), loc.getY(), loc.getZ());
			p.sendMessage("Location set to " + p.getLocation().toString());
		}
		else if(str.equalsIgnoreCase("setyang"))
		{
			Location loc = p.getLocation();
			Main.spawns[1] = new Location(p.getWorld(), loc.getX(), loc.getY(), loc.getZ());
			p.sendMessage("Location set to " + p.getLocation().toString());
		}
		else if(str.equalsIgnoreCase("startGame"))
			Manager.startGame();
		else if(str.equalsIgnoreCase("endGame"))
			Manager.endGame();
		return false;
	}
}
