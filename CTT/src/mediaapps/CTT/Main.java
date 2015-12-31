package mediaapps.CTT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import mediaapps.CTT.events.BlockBreak;
import mediaapps.CTT.events.BlockPlace;
import mediaapps.CTT.events.Death;
import mediaapps.CTT.events.FallDamage;
import mediaapps.CTT.events.Respawn;

public class Main extends JavaPlugin implements Listener
{
	World Arena;
	public static Boolean inProg;
	public static Main plugin;
	public int maxPlayers = 8;
	public static String Host = "", Port = "", Database = "", User = "", Pass = "";
	
	public static Location spawns[] = new Location[3];
	public static Location field[] = new Location[3];
	public static Location monument[] = new Location[2];
	
	public static HashMap<String, Integer> points = new HashMap<>();
	public static HashMap<String, Integer> wins = new HashMap<>();
	public static HashMap<String, Integer> kills = new HashMap<>();
	public static HashMap<String, Integer> totalKills = new HashMap<>();
	public static HashMap<String, String> team = new HashMap<>();
	public static HashMap<String, Integer> brokenBlocks = new HashMap<>();
	
	public static ArrayList<Player> players = new ArrayList<>();
	public static int[] woolBlocks = new int[2];
	
	public static Economy economy = null;
	public static Economy econ = null;
	
	public static final Logger log = Logger.getLogger("Minecraft");
	@Override
	public void onEnable()
	{
		plugin = this;
		FileConfiguration config = getConfig();
		ConfigSet.setDefaults(config);
		config.options().copyDefaults(true);
		saveConfig();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new BlockPlace(),	this);
		pm.registerEvents(new FallDamage(), this);
		pm.registerEvents(new Death(), this);
		pm.registerEvents(new Respawn(), this);
		Arena = Bukkit.getWorld("Arena");
		spawns[0] = new Location(Arena, 1, 1 , 1);//red spawn
		spawns[1] = new Location(Arena, 1, 1 , 1);//blue spawn
		spawns[2] = new Location(Arena, 1, 1, 1);//lobby
		field[0] = new Location(Arena, 1, 1 , 1);//Corner red
		field[1] = new Location(Arena, 1, 1 , 1);//Corner blue
		field[2] = new Location(Arena, 1, 1, 1);//Center
		monument[0] = new Location(Arena, 1, 1, 1);//red monument
		monument[1] = new Location(Arena, 1, 1, 1);//blue monument
		inProg = false;
		Host = getConfig().getString("Host");
		Port = getConfig().getString("Port");
		Database = getConfig().getString("Database");
		User = getConfig().getString("User");
		Pass = getConfig().getString("Pass");
		if(getConfig().getString("rspawn") != null || getConfig().getString("bspawn") != null
		|| getConfig().getString("Lobby") != null || getConfig().getString("rcorner") != null 
		|| getConfig().getString("bcorner") != null || getConfig().getString("center") != null 
		|| getConfig().getString("rmonument") != null || getConfig().getString("bmonument") != null)
				{
					spawns[0] = Misc.setLocs(getConfig().getString("rspawn"));
					spawns[1] = Misc.setLocs(getConfig().getString("bspawn"));
					spawns[2] = Misc.setLocs(getConfig().getString("lobby"));
					field[0] = Misc.setLocs(getConfig().getString("rcorner"));
					field[1] = Misc.setLocs(getConfig().getString("bcorner"));
					field[2] = Misc.setLocs(getConfig().getString("center"));
					monument[0] = Misc.setLocs(getConfig().getString("rmonument"));
					monument[1] = Misc.setLocs(getConfig().getString("bmonument"));
					
				}
		if(!setupEconomy())
		{
			log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getVersion()));
		}
		for(Player p : Bukkit.getOnlinePlayers())
			ScoreBoardHandler.clearBoard(p);
	}
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args)
	{
		Player p = (Player) sender; 
		return GameCommands.Command(p, cmd, str, args);
	}
	public static void toConfig(String str, Location loc)
	{
		plugin.getConfig().addDefault(str, Misc.locToString(loc));
		plugin.getConfig().set(str, Misc.locToString(loc));
		plugin.saveConfig();
	}
	public static void toConfig(String str, String str1)
	{
		plugin.getConfig().addDefault(str, str1);
		plugin.saveConfig();
	}
	public static void toConfig(String name, int points2) 
	{
		plugin.getConfig().addDefault(name, points2);
		plugin.saveConfig();
	}
	private boolean setupEconomy() 
	{
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
