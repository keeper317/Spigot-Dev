package mediaapps.Pex2Bungee;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener
{
	public static String Host = null;
	public static String Port = null;
	public static String Database = null;
	public static String User = null;
	public static String Pass = null;
	public void onEnable()
	{
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
}
