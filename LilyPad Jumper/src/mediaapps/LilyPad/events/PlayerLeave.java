package mediaapps.LilyPad.events;

import mediaapps.LilyPad.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener
{
	@EventHandler
	public void onLeave(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		Main.players.remove(p);
	}
}
