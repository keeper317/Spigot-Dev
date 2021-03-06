package mediaapps.CTT.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

import mediaapps.CTT.Main;

public class FallDamage implements Listener
{
	static int id;
	@EventHandler
	public void onFallDamage(EntityDamageEvent e)
	{
		Bukkit.broadcastMessage("Entity is: " + e.getEntityType().toString() + "\nEntity Damage: " + e.getCause().toString());
		if((e.getEntity() instanceof Player) && e.getCause() == DamageCause.FALL)
			e.setCancelled(true);
		else if(e.getEntity() instanceof Player && (e.getCause().equals(DamageCause.FIRE) || e.getCause() == DamageCause.FIRE_TICK ))
		{
			e.setCancelled(true);
		}
		
	}
	@EventHandler
	public void Items(ItemSpawnEvent e)
	{
		if(!(e.getEntity().getItemStack().getData().getItemType() == Material.WOOL))
		{
			e.setCancelled(true);
			return;
		}
		else if((e.getLocation().distance(Main.monument[0]) < 4 || e.getLocation().distance(Main.monument[1]) < 4) || e.getLocation().distance(Main.field[2]) < 2)
		{
			return;
		}
		else
		{
			String str = "null";
			if(e.getEntity().getItemStack().getData().getData() == 14)
				str = "Red";
			else if(e.getEntity().getItemStack().getData().getData() == 9)
				str = "Blue";
			spawnItem(Main.field[2], str);
			e.setCancelled(true);
		}
	}
	private void spawnItem(Location loc, String str) 
	{
		if(str.equalsIgnoreCase("red"))
			loc.getWorld().dropItem(loc, new ItemStack(Material.WOOL, 1, (short) 14));
		else if(str.equalsIgnoreCase("blue"))
			loc.getWorld().dropItem(loc, new ItemStack(Material.WOOL, 1, (short) 9));
		Bukkit.broadcastMessage(str + " wool spawned at center!");
	}
}
