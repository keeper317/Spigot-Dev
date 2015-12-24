package mediaapps.hubboard;

import java.sql.SQLException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener 
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) 
	{
		try {
			ScoreBoardHandler.statBoard(e.getPlayer());
		} catch (IllegalArgumentException | IllegalStateException
				| ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
