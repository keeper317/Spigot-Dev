package mediaapps.duel;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Misc 
{
	public static void addSpecs(Player p)
	{
		Main.players.remove(p);
		Main.specs.add(p);
		p.setAllowFlight(true);
		p.setCanPickupItems(false);
		p.setFlying(true);
		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 10, true), true);
		p.teleport(Main.spawns[0]);
		if(Main.players.size() < 2)
			Manager.endGame();
	}
	public static void addPlayers(Player p)
	{
		p.setFlying(false);
		p.setAllowFlight(false);
		p.setGameMode(GameMode.SURVIVAL);
		Main.players.add(p);
		Main.specs.remove(p);
		p.setCanPickupItems(false);
		p.setFlying(false);
		p.removePotionEffect(PotionEffectType.INVISIBILITY);
	}
	public static void equipPlayer(Player p) 
	{
		p.getInventory().clear();
		ItemStack bow = new ItemStack(Material.BOW);
		ItemMeta bowmeta = bow.getItemMeta();
		bowmeta.addEnchant(Enchantment.DURABILITY, 3, true);
		bowmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		bow.setItemMeta(bowmeta);
		p.getInventory().setItem(2, bow);
		p.getInventory().setItem(1, new ItemStack(Material.ARROW));
	}
	public static void gameEnded(Player p)
	{
		p.setFlying(false);
		Main.players.remove(p);
		Main.specs.remove(p);
		p.setCanPickupItems(true);
		p.setAllowFlight(false);
		p.setFlying(false);
		p.removePotionEffect(PotionEffectType.INVISIBILITY);
		ScoreBoardHandler.statBoard(p);
	}
	public static String locToString(Location loc)
	{
		String str = loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ();
		return str;
	}
	public static Location setLocs(String str)
	{
		 	String str2loc[]=str.split(":");
		 
		    Location loc = new Location(Bukkit.getWorld(str2loc[0]),0,0,0);
		 
		    loc.setX(Double.parseDouble(str2loc[1]));
		 
		    loc.setY(Double.parseDouble(str2loc[2]));
		 
		    loc.setZ(Double.parseDouble(str2loc[3]));
		 
		    return loc;
	}
	public static void Joined(Player p) 
	{
		if(!Main.players.contains(p))
		{
			if(Main.inProg)
			{
				Misc.addSpecs(p);
				p.sendMessage("§cYou have joined an in-progress game.\nThe game will end soon...");
			}
			else if(!Main.inProg)
			{
				Misc.addPlayers(p);
				p.sendMessage("§a§m-------------------------------");
				p.sendMessage("§1  §aYou have joined the game");
				p.sendMessage("§a§m-------------------------------");
				if(Main.players.size() >= Main.maxPlayers / 2)
					Manager.countDown();
			}
			Bukkit.broadcastMessage(p.getName() + " §ahas joined the game!");
		}
		else if(Main.players.contains(p))
			p.sendMessage("§cYou are already in the game!");		
	}
	
}

