package mediaapps.CTT.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.PlayerInventory;

import mediaapps.CTT.Main;
import mediaapps.CTT.Misc;
import mediaapps.CTT.ScoreBoardHandler;

public class Death implements Listener
{
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
			Player p =  e.getEntity().getPlayer();
			
			Main.kills.put(p.getKiller().getName(), Misc.getKills(p) + 1);
			ScoreBoardHandler.updateBoard();
			int dropsize = p.getInventory().getSize();
			PlayerInventory stack = p.getInventory();
			for(int i = 0; i < dropsize; i++)
			{
				if(stack.getItem(i) != null && stack.getItem(i).getType() == Material.WOOL)
					Main.field[2].getWorld().dropItem(Main.field[2], stack.getItem(i));
			}
			p.getInventory().clear();
	}
}
