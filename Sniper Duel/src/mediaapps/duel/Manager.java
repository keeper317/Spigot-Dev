package mediaapps.duel;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Manager {

	public static void startGame()
	{
		Main.plugin.getServer().getScheduler().cancelTask(Main.taskID2);
		Main.SecondsToCountDown = 5;
		Main.taskID2 = Main.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			  public void run() {
				  Main.SecondsToCountDown--;
				  if (Main.SecondsToCountDown!=0) {
					  Bukkit.broadcastMessage("§bGame Starts in " + Main.SecondsToCountDown + " second(s)!");
				  }
				  if (Main.SecondsToCountDown==0) {
				      Main.plugin.getServer().getScheduler().cancelTask(Main.taskID2);
				      Main.SecondsToCountDown=30;
				      Bukkit.broadcastMessage("§bSniper Duel has Started!");
				  }
				  SpawnHandler.teletportToArena();
			  }
			}, 20L, 20L); // once each second
		for(int i = 0; i < Main.players.size(); i++)
		{
			Player p = Main.players.get(i);
			Misc.equipPlayer(p);
		}
	}
	public static void endGame() 
	{
		SpawnHandler.teleportToLobby();
		Bukkit.broadcastMessage("§cThe game has ended");
	}
	public static void countDown() 
	{
		Main.SecondsToCountDown = 30;
		Main.taskID2 = Main.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			  public void run() {
				  Main.SecondsToCountDown--;
				  if (Main.SecondsToCountDown!=0) {
					  Bukkit.broadcastMessage("§bGame Starts in " + Main.SecondsToCountDown + " second(s)!");
				  }
				  if (Main.SecondsToCountDown==0) {
				      Main.plugin.getServer().getScheduler().cancelTask(Main.taskID2);
				      Main.SecondsToCountDown=5;
				      startGame();
				  }
			  }
			}, 20L, 20L); // once each second
	}

}
