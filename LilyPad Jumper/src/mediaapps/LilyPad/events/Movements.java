package mediaapps.LilyPad.events;

import java.sql.SQLException;

import mediaapps.LilyPad.Main;
import mediaapps.LilyPad.Manager;
import mediaapps.LilyPad.SQLHandler;
import mediaapps.LilyPad.util.Misc;
import mediaapps.LilyPad.util.ScoreBoardHandler;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

public class Movements implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerMoveEvent(PlayerMoveEvent e) throws IllegalArgumentException, IllegalStateException, SQLException
	{
		final Player p = e.getPlayer();
		Location l = p.getLocation();
		
		final Location below = p.getLocation();
		if(Main.inProg && Main.players.contains(p))
		{
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					Misc.setBlockAir(below.getBlockX(), below.getBlockZ(), p.getWorld());
				}
			}.runTaskLater(Main.plugin, 20);
			if(l.distance(Main.spawns[1]) < 1)
			{
				SQLHandler.addWins(p);
				Main.winner = p.getName();
				Manager.endGame();
			}
			if(l.getY() < Main.field[0].getY())
			{
				Misc.addSpecs(p);
				Bukkit.broadcastMessage("§7[§cLilypad Jumper§7] §c" + p.getName() + " §7took a dive."); 
			}
		}
		if(Main.players.size() < 1 && Main.inProg)
			Manager.endGame();
	}
}