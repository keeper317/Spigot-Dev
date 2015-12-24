package mediaapps.LilyPad;

import java.sql.SQLException;

import mediaapps.LilyPad.util.FireWorks;
import mediaapps.LilyPad.util.LilyPadSpawns;
import mediaapps.LilyPad.util.Misc;
import mediaapps.LilyPad.util.SpawnHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class Manager 
{
	public static void startGame()
	{
		Runnable taskGameStart = new Runnable()
		{
			@Override
			public void run()
			{
				if(Main.SecondsToCountDown >= 6)
				{
					Bukkit.broadcastMessage("§7[§cLilypad Jumper§7] §7Lobby ending in §c" + (Main.SecondsToCountDown - 5) + " §7seconds.");
				}
				else if(Main.SecondsToCountDown == 5)
				{
					Bukkit.broadcastMessage("§a§m-----------------------------------------------------");
					Bukkit.broadcastMessage("");
		            Bukkit.broadcastMessage("                         §6§lLILYPAD JUMPER\n");
		            Bukkit.broadcastMessage("");
		            Bukkit.broadcastMessage("                    §eLeap from lilypad to lilypad\n");
		            Bukkit.broadcastMessage("                    §euntil you reach the beacon.\n\n");
		            Bukkit.broadcastMessage("");
		            Bukkit.broadcastMessage("        §c§lWARNING:§c Do not stay on one lilypad for too long.\n");
					Bukkit.broadcastMessage("§a§m-----------------------------------------------------");
					Bukkit.broadcastMessage("§7[§cLilypad Jumper§7] §7Game starting in §c" + Main.SecondsToCountDown + " §7seconds.");
					SpawnHandler.teletportToArena();
				}
				else if(Main.SecondsToCountDown > 0 && Main.SecondsToCountDown < 5)
				{
					Bukkit.broadcastMessage("§7[§cLilypad Jumper§7] §7Game starting in §c" + Main.SecondsToCountDown + " §7seconds.");
					SpawnHandler.teletportToArena();
				}
				else if(Main.SecondsToCountDown <= 0)
				{
					Bukkit.broadcastMessage("§7[§cLilypad Jumper§7] §aGame Started!");
					Main.taskID2.cancel();
					Main.SecondsToCountDown = 6;
				}
				Main.SecondsToCountDown--;
			}
		};
		if(!Main.inProg)
		{
			Main.inProg = true;
			
			LilyPadSpawns.placeLily();
			LilyPadSpawns.placeLily();
			
			Main.taskID2 = Main.plugin.getServer().getScheduler().runTaskTimer(Main.plugin, taskGameStart, 0L, 20L);
		}
	}
	public static void endGame() throws IllegalArgumentException, IllegalStateException, SQLException
	{
		Runnable taskServerStops = new Runnable()
		{
			@Override
			public void run() 
			{
				if(Main.SecondsToCountDown > 0)
				{
					Bukkit.broadcastMessage("§7[§cLilypad Jumper§7] §7Server restarting in §c" + Main.SecondsToCountDown + " §7seconds.");
				}
				else if(Main.SecondsToCountDown <= 0)
				{
					Main.taskID3.cancel();
					Bukkit.shutdown();
				}
				Main.SecondsToCountDown--;
			}
		};
		Main.inProg = false;
		LilyPadSpawns.breakLily();
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.teleport(Main.spawns[2]);
			if(Main.winner.equalsIgnoreCase(p.getName()))
			{
				MSG(p, 100);
				Bukkit.broadcastMessage("§7[§cLilypad Jumper§7] §a" + p.getName() + " §awon the game!");
				SQLHandler.updateTokens(p, 100.0);
			}
			else
			{
				MSG(p, 5);
				SQLHandler.updateTokens(p, 5.0);
			}
			Misc.gameEnded(p);
			Main.winner = "";
			p.removePotionEffect(PotionEffectType.INVISIBILITY);
			FireWorks.FWMaker(p);
		}
		Main.taskID3 = Main.plugin.getServer().getScheduler().runTaskTimer(Main.plugin, taskServerStops, 0L, 20L);
	}
	public static void MSG(Player p, int i)
	{
		p.sendMessage("§a§m-----------------------------------------------------");
		p.sendMessage("");
		p.sendMessage("                         §e§lREWARD SUMMARY");
		p.sendMessage("");
		p.sendMessage("                             §6" + i + " Tokens");
		p.sendMessage("");
		p.sendMessage("§a§m-----------------------------------------------------");
	}
}