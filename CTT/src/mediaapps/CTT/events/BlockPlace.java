package mediaapps.CTT.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import mediaapps.CTT.GameManagement;
import mediaapps.CTT.Main;
import mediaapps.CTT.ScoreBoardHandler;

public class BlockPlace implements Listener 
{
	@EventHandler
	public void blockPlacement(BlockPlaceEvent e)
	{
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE && e.getBlock().getType() == Material.WOOL)
		{			
			Location pL = e.getBlockPlaced().getLocation();
			if(pL.getBlockX() == Main.monument[0].getBlockX() && pL.getBlockZ() == Main.monument[0].getBlockZ() && pL.getBlockY() >= Main.monument[0].getBlockY())
				Main.woolBlocks[0]++;
			else if(pL.getBlockX() == Main.monument[1].getBlockX() && pL.getBlockZ() == Main.monument[1].getBlockZ() && pL.getBlockY() >= Main.monument[1].getBlockY())
				Main.woolBlocks[1]++;
			if(Main.woolBlocks[0] == 4 || Main.woolBlocks[1] == 4)
			{
				if(Main.woolBlocks[0] == 4)
					GameManagement.addWins("red");
				else if(Main.woolBlocks[1] == 4)
					GameManagement.addWins("blue");
				GameManagement.gameEnd();
			}	
			ScoreBoardHandler.updateBoard(); 
		}
	}
}
