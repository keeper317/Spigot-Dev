package mediaapps.LilyPad.events;

import java.sql.SQLException;

import mediaapps.LilyPad.Main;
import mediaapps.LilyPad.SQLHandler;
import mediaapps.LilyPad.util.ScoreBoardHandler;

import org.bukkit.util.Vector;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class Flying implements Listener 
{
	@EventHandler
	public void onFlightAttempt(PlayerToggleFlightEvent e) throws SQLException 
	{
	    if(e.isFlying() && e.getPlayer().getGameMode() != GameMode.CREATIVE && Main.inProg) 
	    {
	    	if(SQLHandler.getJumps(e.getPlayer()) > 0)
	    	{
	    		e.getPlayer().setVelocity(e.getPlayer().getVelocity().add(new Vector(0,.85,0)));
	    		SQLHandler.updateJumps(e.getPlayer(), -1);
	    		ScoreBoardHandler.statBoard(e.getPlayer());
	    	}
	    	else
	    	{
	    		e.getPlayer().sendMessage("§7[§cLilypad Jumper§7] §cYou do not have any more Double Jumps");
	    	}
	        e.setCancelled(true);
	        e.getPlayer().setFlying(false);
	    }
	}
}
