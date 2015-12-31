package mediaapps.redeem;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	public static String Database = "", User = "", Pass = "",
			url = "", Host = "", Port = "";
	static Main plugin;
	public void onEnable()
	{
		plugin = this;
		
		FileConfiguration config = getConfig();
		ConfigSet.setDefaults(config);
		config.options().copyDefaults(true);
		saveConfig();
		
		url = getConfig().getString("URL");
		Host = getConfig().getString("Host");
		Port = getConfig().getString("Port");
		Database = getConfig().getString("Database");
		User = getConfig().getString("User");
		Pass = getConfig().getString("Pass");		
	}
	public void onDisable()
	{
		try {
			SQLHandler.isDisabled();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args)
	{
		Player p = (Player) sender;
		if(str.equalsIgnoreCase("redeem"))
		{
			try {
				if(SQLHandler.getPlayer(p).equalsIgnoreCase("n"))
				{
					SQLHandler.updatePlayer(p);
					SQLHandler.updateTokens(p, SQLHandler.getTokens(p) + 2500);
					return true;
				}
				else
					p.sendMessage("You have already used redeem and cannot use it again.");
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else
			p.sendMessage("Please enter a valid command");
		return false;
	}
}
