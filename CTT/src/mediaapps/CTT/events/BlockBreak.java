package mediaapps.CTT.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import mediaapps.CTT.Main;
import mediaapps.CTT.ScoreBoardHandler;

public class BlockBreak implements Listener 
{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE && e.getBlock().getType() == Material.WOOL)
		{
			if(e.getBlock().getType() == Material.WOOL)
			{
				Main.brokenBlocks.put(e.getPlayer().getName(), 1);
				Location pL = e.getBlock().getLocation();
				if(pL.getBlockX() == Main.monument[0].getBlockX() && pL.getBlockZ() == Main.monument[0].getBlockZ() && pL.getBlockY() >= Main.monument[0].getBlockY())
					Main.woolBlocks[0]--;
				else if(pL.getBlockX() == Main.monument[1].getBlockX() && pL.getBlockZ() == Main.monument[1].getBlockZ() && pL.getBlockY() >= Main.monument[1].getBlockY())
					Main.woolBlocks[1]--;
				
				ScoreBoardHandler.updateBoard();
			}
			else
			{
				e.getPlayer().sendMessage("§CBreak wool and place it on your tower");
				e.setCancelled(true);
			}
		}
		else if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
		{
			
		}
		else
		{
			e.getPlayer().sendMessage("Break Wool and Only Wool!!");
			e.setCancelled(true);
		}
		
	}
}
