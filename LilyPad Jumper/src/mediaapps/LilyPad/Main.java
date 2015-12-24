package mediaapps.LilyPad;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import mediaapps.LilyPad.commands.GameCommands;
import mediaapps.LilyPad.events.ClickEvents;
import mediaapps.LilyPad.events.Flying;
import mediaapps.LilyPad.events.Movements;
import mediaapps.LilyPad.events.PlayerJoin;
import mediaapps.LilyPad.events.PlayerLeave;
import mediaapps.LilyPad.util.Misc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin implements Listener
{
	World Arena, Lobby;
	public static Boolean inProg = false;
	public static Main plugin;
	public static String Host = "", Port = "", Database = "", User = "", Pass = "";
	public static final String version = "v1.1";
	public static int maxPlayers = 3;
	public static int SecondsToCountDown = 15;
	public static String winner = "";
	public static BukkitTask taskID2;
	
	public static String obmsg = "&cYou have lost this round.";
	public static Location spawns[] = new Location[3];
	public static Location field[] = new Location[2];
	
	public static ArrayList<Player> players = new ArrayList<>();
	public static ArrayList<Player> specs = new ArrayList<>();
	public static BukkitTask taskID;
	public static BukkitTask taskID3;
	public static final Logger log = Logger.getLogger("Minecraft");
	@Override
	public void onEnable()
	{
		FileConfiguration config = getConfig();
		ConfigSet.setDefaults(config);
		config.options().copyDefaults(true);
		saveConfig();
		ShopGUI.shopGui();
		plugin = this;
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Movements(), this);
		pm.registerEvents(new Flying(), this);
		pm.registerEvents(new ClickEvents(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerLeave(), this);
		Host = getConfig().getString("Host");
		Port = getConfig().getString("Port");
		Database = getConfig().getString("Database");
		User = getConfig().getString("User");
		Pass = getConfig().getString("Pass");
		
		Arena = Bukkit.getWorld("Arena");
		spawns[0] = new Location(Arena, 1, 1 , 1);//start of game
		spawns[1] = new Location(Arena, 1, 1 , 1);//end of game
		spawns[2] = new Location(Arena, 1, 1, 1);//lobby
		field[0] = new Location(Arena, 1, 1 , 1);//start of field
		field[1] = new Location(Arena, 1, 1 , 1);//end of field
		inProg = false;
		maxPlayers=getConfig().getInt("minPlayers");
		obmsg = getConfig().getString("ObMsg");
		if(getConfig().getString("Start") != null || getConfig().getString("End") != null
			|| getConfig().getString("Lobby") != null || getConfig().getString("Fields") != null 
			|| getConfig().getString("Fielde") != null)
			{
				spawns[0] = Misc.setLocs(getConfig().getString("Start"));
				spawns[1] = Misc.setLocs(getConfig().getString("End"));
				spawns[2] = Misc.setLocs(getConfig().getString("Lobby"));
				field[0] = Misc.setLocs(getConfig().getString("Fields"));
				field[1] = Misc.setLocs(getConfig().getString("Fielde"));
			}
	}
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args)
	{
		Player p = (Player) sender; 
		try {
			return GameCommands.Command(p, cmd, str, args);
		} catch (IllegalArgumentException | IllegalStateException
				| SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void toConfig(String str, Location loc)
	{
		plugin.getConfig().set(str, Misc.locToString(loc));
		plugin.saveConfig();
	}
	public static void toConfig(String str, String str1)
	{
		plugin.getConfig().set(str, str1);
		plugin.saveConfig();
	}
	public static void toConfig(String name, int points2) 
	{
		plugin.getConfig().set(name, points2);
		plugin.saveConfig();
	}
}
