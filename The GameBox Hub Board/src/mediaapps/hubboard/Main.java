package mediaapps.hubboard;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	public static String boardname = "";
	public static String url = "";
	public static String Host = "", Port = "", Database = "", User = "", Pass = "";
	public static final Logger log = Logger.getLogger("Minecraft");
	public static int taskID2,taskID;
	
	public static HashMap<String, String> rank = new HashMap<>();
	public static HashMap<String, String> credits = new HashMap<>();
	public static HashMap<String, String> tokens = new HashMap<>();
	
	public Connection c = null;
	static Main plugin;
	protected static int SecondsToCountDown = 1;
	protected static int counter = 1;
	@Override
	public void onEnable()
	{
		plugin = this;
		FileConfiguration config = getConfig();
		ConfigSet.setDefaults(config);
		config.options().copyDefaults(true);
		saveConfig();
		boardname = getConfig().getString("Board Name");
		url = getConfig().getString("URL");
		Host = getConfig().getString("Host");
		Port = getConfig().getString("Port");
		Database = getConfig().getString("Database");
		User = getConfig().getString("User");
		Pass = getConfig().getString("Pass");
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoin(),this);
		
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
		if(str.equalsIgnoreCase("version"))
		{
			p.sendMessage("Version: 0.1");
			return true;
		}
		return false;
	}
}
