package mediaapps.elemental.events;

import mediaapps.elemental.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffectType;

public class EntityDeath implements Listener
{
	@EventHandler
	public void onDeath(EntityDeathEvent e)
	{
		if(e.getEntity() instanceof Player)
		{
			Player v = (Player) e.getEntity();
			MAAPI.addDeaths(v, 1);
			if(Main.deaths.get(v.getName()) >= 5)
			{
				Main.specs.add(v.getName());
				Main.players.remove(v.getName());
				v.setAllowFlight(true);
				v.setFlying(true);
				v.teleport(Main.misc[0]);
				v.hasPotionEffect(PotionEffectType.INVISIBILITY);
			}
			else
			{
				v.teleport(Main.misc[MAAPI.getTeam(v)]);
			}
			if(v.getKiller() instanceof Player)
			{
				Player k = (Player) v.getKiller();
				MAAPI.addKills(k, 1);
				MAAPI.addPoints(k, 10);
			}
			Manager.gameChecker();
		}	
		
	}
}
