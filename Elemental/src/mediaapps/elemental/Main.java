package mediaapps.elemental;

import java.util.ArrayList;
import java.util.HashMap;
import mediaapps.elemental.events.EntityDeath;
import mediaapps.elemental.events.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	World Arena, Lobby;
	public static Boolean inProg;
	
	public static Location spawns[] = new Location[2];
	public static Location misc[] = new Location[2];
	
	public static HashMap<String, Integer> points = new HashMap<>();
	public static HashMap<String, Integer> kills = new HashMap<>();
	public static HashMap<String, Integer> deaths = new HashMap<>();
	public static HashMap<String, Integer> team = new HashMap<>();
	
	public static ArrayList<String> players = new ArrayList<>();
	public static ArrayList<String> specs = new ArrayList<>();
	
	public void onEnable()
	{
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EntityDeath(), this);
		pm.registerEvents(new PlayerJoin(), this);
		
		Arena = Bukkit.getWorld("Arena");
		spawns[0] = new Location(Arena, 1, 1 , 1);//yin team spawn
		spawns[1] = new Location(Arena, 1, 1 , 1);//yang team spawn
		misc[1] = new Location(Arena, 1, 1 , 1);//Lobby
		misc[0] = new Location(Arena, 1, 1 , 1);//spectator spawn
		inProg = false;
	}
}
