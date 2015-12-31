package mediaapps.SCB.managers;
 
import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.SCB;
 import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

public class PlayerManager
{
	HashMap<String, ClassInterface> plClass = new HashMap();
	HashMap<String, Boolean> ingame = new HashMap();
	HashMap<String, Location> pos1 = new HashMap();
	HashMap<String, Location> pos2 = new HashMap();
	HashMap<Player, Integer> live = new HashMap();
	HashMap<Player, ItemStack[]> inventory = new HashMap();
	HashMap<Player, ItemStack[]> armor = new HashMap();
 
	Plugin plugin = SCB.getInstance();
 
	String plyFile = this.plugin.getDataFolder() + File.separator + "players" + File.separator;
	String araFile = this.plugin.getDataFolder() + File.separator + "arenas" + File.separator;
 
	public static PlayerManager pmngr = new PlayerManager();
 
	public static PlayerManager get() 
	{
		return pmngr;
	}
	public boolean ingame(Player p) 
	{
		if (this.ingame.get(p.getName()) == null) 
			this.ingame.put(p.getName(), Boolean.valueOf(false));
		if (((Boolean)this.ingame.get(p.getName())).booleanValue())
			return true;
		return false;
	}
 
	public ClassInterface getClasse(Player p) 
	{
		if ((((Boolean)this.ingame.get(p.getName())).booleanValue()) && (this.plClass.get(p.getName()) != null))
			return (ClassInterface)this.plClass.get(p.getName());
		return null;
	}
	public void newPlayer(Player p) 
	{
		File f = new File(this.plyFile + p.getName() + ".yml");
		if (!f.exists()) 
		{
			File F = new File(this.plyFile + p.getName() + ".yml");
			FileConfiguration c = YamlConfiguration.loadConfiguration(F);
			c.set("Gems", "0");
			c.set("Wins", "0");
			c.set("Losses", "0");
			try 
			{
				c.save(F);
			}catch(IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
 
	public void setPos1(String name, Location loc) 
	{
		this.pos1.put(name, loc);
	}
 
	public void setPos2(String name, Location loc) 
	{
		this.pos2.put(name, loc);
	}
 
	public Location getPos1(String name) 
	{
		return (Location)this.pos1.get(name);
	}
 
	public Location getPos2(String name) 
	{
		return (Location)this.pos2.get(name);
	}
 
	public void prepPlayer(Player p)
	{
		ItemStack[] inv = p.getInventory().getContents();
		ItemStack[] arm = p.getInventory().getArmorContents();
		this.ingame.put(p.getName(), Boolean.valueOf(true));
		this.inventory.put(p, inv);
		this.armor.put(p, arm);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.updateInventory();
	}
 
	public void putBackInv(Player p)
	{
		ItemStack[] inv = (ItemStack[])this.inventory.get(p);
		ItemStack[] arm = (ItemStack[])this.armor.get(p);
		if ((arm != null) && (inv != null)) 
		{
			p.getInventory().setContents(inv);
			p.getInventory().setArmorContents(arm);
			p.updateInventory();
			this.armor.put(p, null);
			this.inventory.put(p, null);
		}
	}
	public FileConfiguration getPlayerConfig(Player p) 
	{
		String pn = p.getName();
		File f = new File(this.plyFile + pn + ".yml");
		return YamlConfiguration.loadConfiguration(f);
	}
 
	public void setClass(Player p, ClassInterface c) 
	{
		this.plClass.put(p.getName(), c);
	}
 
	public String getClassName(Player p, ClassInterface c) 
	{
		return c.id();
	}
 
	public void setIngame(Player p, boolean b) 
	{
		String name = p.getName();
		this.ingame.put(name, Boolean.valueOf(b));
	}
	public void setLives(Player p, Integer lives) 
	{
		this.live.put(p, new Integer(lives.intValue()));
	}
	public Integer getLives(Player p) 
	{
		return (Integer)this.live.get(p);
	}
}

