package mediaapps.elemental;

import mediaapps.elemental.util.SpawnHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Manager 
{
	public static void startGame()
	{
		Main.inProg = true;
		SpawnHandler.teletportToArena();
		
		for(Player p : Bukkit.getOnlinePlayers())
			Main.players.add(p.getName());
		Bukkit.broadcastMessage("§cThe game has started! First to 5 kills wins!");
	}
	
	public static void endGame()
	{
		String str = "";
		Main.inProg = false;
		
		for(int i = 0; Main.players.size() > i; i++)
			str = Main.players.get(i) + " ";
		Bukkit.broadcastMessage("§cThe game has ended! Winner is: " + str);
		SpawnHandler.teleportToLobby();
	}
	public static void gameChecker()
	{
		if(Main.players.size() <= 1)
			endGame();
	}
}
