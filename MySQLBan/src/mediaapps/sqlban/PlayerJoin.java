package mediaapps.sqlban;

import java.sql.SQLException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener 
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) throws SQLException 
	{
		if(SQLHandler.checkUser(e.getPlayer().getName().toString()))
		{
			e.getPlayer().sendMessage("&3&k|&1Welcome to the server&3&k|");
		}
		else
			e.getPlayer().kickPlayer("&4&l&nYou Are Not Permitted On This Server\nContact The Computer Club To Be Permitted!");
	}
	
}
