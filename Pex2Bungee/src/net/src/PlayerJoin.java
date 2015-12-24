package net.src;

import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) throws SQLException
	{
		Player p = e.getPlayer();
		
		SQLHandler.setRank(p);
	}
}
