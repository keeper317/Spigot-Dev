package mediaapps.LilyPad.commands;

import java.sql.SQLException;

import mediaapps.LilyPad.Main;
import mediaapps.LilyPad.Manager;
import mediaapps.LilyPad.ShopGUI;
import mediaapps.LilyPad.events.PlayerJoin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class GameCommands
{
	public static boolean Command(Player p, Command cmd, String str, String[] args) throws IllegalArgumentException, IllegalStateException, SQLException, InterruptedException
	{
		Location loc = p.getLocation();
		if(str.equalsIgnoreCase("gstart"))
		{
			if(!Main.inProg)
				Manager.startGame();
			return true;
		}
		if(str.equalsIgnoreCase("gfinish"))
		{
			Manager.endGame();
			return true;
		}
		if(str.equalsIgnoreCase("setStart"))
		{
			Main.spawns[0] = new Location(p.getWorld(), loc.getX(), (loc.getY()), loc.getZ());
			p.sendMessage("Game Start set to " + Main.spawns[0].toString());
			Main.toConfig("Start", Main.spawns[0]);
			return true;
		}
		if(str.equalsIgnoreCase("setEnd"))
		{
			Main.spawns[1] = new Location(p.getWorld(), loc.getX(), (loc.getY()), loc.getZ());
			p.sendMessage("Game End set to " + Main.spawns[1].toString());
			Main.toConfig("End", Main.spawns[1]);
			return true;
		}
		if(str.equalsIgnoreCase("setLobby"))
		{
			Main.spawns[2] = p.getLocation();
			p.sendMessage("Lobby set to " + Main.spawns[2].toString());
			Main.toConfig("Lobby", Main.spawns[2]);
			return true;
		}
		if(str.equalsIgnoreCase("setFieldstart"))
		{
			Main.field[0] = new Location(p.getWorld(), loc.getX(), (loc.getY() - 1), loc.getZ());
			p.sendMessage("Field Corner set to " + Main.field[0].toString());
			Main.toConfig("Fields", Main.field[0]);
			return true;
		}
		 if(str.equalsIgnoreCase("setFieldend"))
		 {
			Main.field[1] = new Location(p.getWorld(), loc.getX(), Main.field[0].getY(), loc.getZ());
		 	p.sendMessage("Field Corner set to " + Main.field[1].toString());
		 	Main.toConfig("Fielde", Main.field[1]);
			return true;
		 }
		if(str.equalsIgnoreCase("Lilyshop"))
		{
			p.openInventory(ShopGUI.shop);
			return true;
		}
		if(str.equalsIgnoreCase("setobmsg"))
		{
			Main.obmsg = "";
			for(int i = 0; i < args.length; i++)
				Main.obmsg += (args[i] + " ");
			p.sendMessage("Message set to " + Main.obmsg);
			Bukkit.broadcastMessage(p.getName() + " Has set Out of Bounds Message to " + Main.obmsg);
		}
		if(str.equalsIgnoreCase("version"))
		{
			p.sendMessage(Main.version);
			return true;
		}
		return false;
	}
}
