package mediaapps.duel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import mediaapps.duel.events.DamageEvents;
import mediaapps.duel.events.Interaction;

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

public class Main extends JavaPlugin implements Listener
{
	World Arena, Lobby;
	public static Boolean inProg;
	public static Main plugin;
	public static int maxPlayers = 4;
	public static int SecondsToCountDown=30;
	public static int taskID2;
	public static Location spawns[] = new Location[5];
	
	public static HashMap<String, Integer> points = new HashMap<>();
	public static HashMap<String, Integer> kills = new HashMap<>();
	public static HashMap<String, Integer> wins = new HashMap<>();
	
	public static ArrayList<Player> players = new ArrayList<>();
	public static ArrayList<Player> specs = new ArrayList<>();
	public static final Logger log = Logger.getLogger("Minecraft");
	@Override
	public void onEnable()
	{
		FileConfiguration config = getConfig();
		ConfigSet.setDefaults(config);
		config.options().copyDefaults(true);
		saveConfig();
		plugin = this;
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new DamageEvents(), this);
		pm.registerEvents(new Interaction(), this);
		
		Arena = Bukkit.getWorld("Arena");
		spawns[0] = new Location(Arena, 1, 1 , 1);//Lobby
		spawns[1] = new Location(Arena, 1, 1 , 1);//iron
		spawns[2] = new Location(Arena, 1, 1, 1);//diamond
		spawns[3] = new Location(Arena, 1, 1, 1);//gold
		spawns[4] = new Location(Arena, 1, 1, 1);//emerald
		inProg = false;
		if(getConfig().getString("Lobby") != null || getConfig().getString("Iron") != null
			|| getConfig().getString("Diamond") != null || getConfig().getString("Gold") != null 
			|| getConfig().getString("Emerald") != null)
			{
				spawns[0] = Misc.setLocs(getConfig().getString("Lobby"));
				spawns[1] = Misc.setLocs(getConfig().getString("Iron"));
				spawns[2] = Misc.setLocs(getConfig().getString("Diamond"));
				spawns[3] = Misc.setLocs(getConfig().getString("Gold"));
				spawns[4] = Misc.setLocs(getConfig().getString("Emerald"));
			}
	}
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args)
	{
		Player p = (Player) sender; 
		return GameCommands.Command(p, cmd, str, args);
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
