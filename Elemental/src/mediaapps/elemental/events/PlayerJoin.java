package mediaapps.elemental.events;

import mediaapps.elemental.Main;
import mediaapps.elemental.Manager;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{	if(Main.inProg == false)
			if(Bukkit.getOnlinePlayers().length >= 2)
			{
				Manager.startGame();
			}
		if(Main.inProg == true)
			Main.specs.add(e.getPlayer().getName());
	}
}
