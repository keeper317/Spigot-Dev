package mediaapps.LilyPad.events;

import java.sql.SQLException;

import mediaapps.LilyPad.Main;
import mediaapps.LilyPad.Manager;
import mediaapps.LilyPad.SQLHandler;
import mediaapps.LilyPad.util.Misc;
import mediaapps.LilyPad.util.ScoreBoardHandler;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerJoin implements Listener
{
	@EventHandler
	public static void Joined(PlayerJoinEvent e) throws IllegalArgumentException, IllegalStateException, SQLException, InterruptedException
	{
		Player p = e.getPlayer();
		if(!SQLHandler.checkPlayer(p))
			SQLHandler.addPlayer(p);

		ScoreBoardHandler.statBoard(p);
		p.removePotionEffect(PotionEffectType.INVISIBILITY);
			if(Main.inProg)
			{
				Misc.addSpecs(p);
				p.sendMessage("§cYou have joined an in-progress game.\nThe game will end soon...");
				p.teleport(Main.spawns[0]);
			}
			else if(!Main.inProg)
			{
				p.teleport(Main.spawns[2]);
				Misc.addPlayers(p);
				p.sendMessage("§a§m-------------------------------");
				p.sendMessage("§1  §aYou have joined the game");
				p.sendMessage("§a§m-------------------------------");
				Log.error(Main.players.size());
				if(Main.players.size() >= Main.maxPlayers && !Main.players.contains(p))
					Manager.startGame();
			}
			Log.error(p.getName() + " joined");
			Bukkit.broadcastMessage("§7[§cLilypad Jumper§7] §c" + p.getName() + " §7joined the game.");
	}
}
