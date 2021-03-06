package mediaapps.CTT;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameManagement 
{
	public static void gameStart()
	{
		Main.woolBlocks[0] = 2;//red team
		Main.woolBlocks[1] = 2;//blue team
		Inventories.setInvArcher();
		Inventories.setInvPotion();
		Inventories.setInvLoot1();
		Inventories.setInvLoot2();
		Inventories.setInvSecret();
		FieldManagement.onGameStart();
		for(Player p : Main.players)
		{
			equipPlayer(p);
			ScoreBoardHandler.gameBoard(p);
			Misc.setSpawn(p);
			tele2Spawns(p);
		}
		Main.inProg = true;
	}
	public static void gameEnd()
	{
		for(int i = 0; i < Main.players.size(); i++)
		{
			
			Player p = Main.players.get(i);
			endMsg(p);
			ScoreBoardHandler.clearBoard(p);
			Main.players.remove(p);
			Misc.setTotalKills(p);
			Main.toConfig(p.getName(), Misc.getTotalKills(p));
		}
		Main.inProg = false;
	}
	public static void endMsg(Player p)
	{
		p.sendMessage("�5The Game Has Ended!");
		if(Main.woolBlocks[0] == 4)
			p.sendMessage("�5The �l�cRED �5Team Has Won The Game!!");
		else if(Main.woolBlocks[1] == 4)
			p.sendMessage("�5The �l�3BLUE �5Team Has Won The Game!!");
		
	}
	private static void tele2Spawns(Player p)
	{
		if(Main.team.get(p.getName()).equalsIgnoreCase("red"))
			p.teleport(Main.spawns[0]);
		else if(Main.team.get(p.getName()).equalsIgnoreCase("blue"))
			p.teleport(Main.spawns[1]);
		
	}
	public static void equipPlayer(Player p) 
	{
		p.getInventory().clear();
		ItemStack helm = null;
		if(Main.team.get(p.getName()).equalsIgnoreCase("Red"))
			helm = new ItemStack(Material.REDSTONE_BLOCK, 1);
		else if(Main.team.get(p.getName()).equalsIgnoreCase("Blue"))
			helm = new ItemStack(Material.LAPIS_BLOCK);
		p.getInventory().setHelmet(helm);
		p.getInventory().setItem(0, new ItemStack(Material.SHEARS));
		p.getInventory().setItem(1, new ItemStack(Material.IRON_SWORD));
		p.getInventory().setItem(2, new ItemStack(Material.ENDER_PEARL, 1));
	}
	public static void checker()
	{
		if(Main.players.size() >= 4)
			countDown();
	}
	public static void countDown()
	{
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
		{
			@Override
			public void run() 
			{
				gameStart();
			}
		}, 600L);
	}
	public static void addWins(String team)
	{
		for(Player p : Main.players)
		{
			if(Main.team.get(p.getName()).equalsIgnoreCase(team));
			{
				if(Main.wins.get(p.getName()) != null)
					Main.wins.put(p.getName(), Main.wins.get(p.getName()) + 1);
				else
					Main.wins.put(p.getName(), 1);
			}
		}
	}
}
