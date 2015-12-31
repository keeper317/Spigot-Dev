package mediaapps.SCB.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaced implements Listener
{
	@EventHandler
	public void onBlockPlaced(BlockPlaceEvent e)
	{
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE)
			e.setCancelled(true);
	}
}
