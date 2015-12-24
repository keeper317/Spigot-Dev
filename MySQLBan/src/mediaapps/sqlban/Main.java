package mediaapps.sqlban;

import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	public static String Database = "", User = "", Pass = "",
			url = "", Host = "", Port = "";
	static Main plugin;
	@Override
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
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoin(), this);
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
		try{
		Player p = (Player) sender; 
		return GameCommands.Command(p, cmd, str, args);
		}catch(Exception e)
		{
			Log.error(e);
			Bukkit.broadcastMessage("This is the error!! " + e.getMessage());
			Bukkit.broadcastMessage("&c" + e);
			Log.error("Error is here!!!\n" + e.getMessage());
			return false;
		}
	}
}