package mediaapps.duel.events;

import mediaapps.duel.MAAPI;
import mediaapps.duel.Misc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DamageEvents implements Listener 
{
	@EventHandler
	public void onDamage(EntityDeathEvent e)
	{
		if(e.getEntity() instanceof Player)
		{
			Player vic = (Player)  e.getEntity();
			Misc.addSpecs(vic);
			if(vic.getKiller() instanceof Player)
			{
				MAAPI.addPoints(vic.getKiller(), 10);
				MAAPI.addKill(vic.getKiller());
			}			
			vic.sendMessage("You have died!");
		}						
	}
	
}
