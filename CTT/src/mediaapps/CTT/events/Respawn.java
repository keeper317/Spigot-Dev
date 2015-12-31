package mediaapps.CTT.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import mediaapps.CTT.GameManagement;
import mediaapps.CTT.Main;

public class Respawn implements Listener
{
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e)
	{
		int i = -1;
		if(Main.team.get(e.getPlayer().getName()).equalsIgnoreCase("Red"))
			i = 0;
		else if(Main.team.get(e.getPlayer().getName()).equalsIgnoreCase("Blue"))
			i = 1;
		e.setRespawnLocation(Main.spawns[i]);
		GameManagement.equipPlayer(e.getPlayer());
	}
	
}
