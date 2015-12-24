package mediaapps.LilyPad.events;

import java.sql.SQLException;

import mediaapps.LilyPad.SQLHandler;
import mediaapps.LilyPad.ShopGUI;
import mediaapps.LilyPad.util.Misc;
import mediaapps.LilyPad.util.ScoreBoardHandler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickEvents implements Listener
{
	@EventHandler
	public void clickedOn(InventoryClickEvent e) throws SQLException
	{
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equals(ShopGUI.shop.getName()))
		{
				e.setCancelled(true);
				if(e.getSlot() == 2 && SQLHandler.getTokens(p) >= 100)
				{
					p.sendMessage("§a§m-------------------------");
					p.sendMessage("§a     §aPurchase Successful");
					p.sendMessage("§a§m-------------------------");
				
					SQLHandler.updateJumps(p, 1);
					SQLHandler.updateTokens(p, -100);
				}
				else if(e.getSlot() == 4 && SQLHandler.getTokens(p) >= 350)
				{
					p.sendMessage("§a§m-------------------------");
					p.sendMessage("§a     §aPurchase Successful");
					p.sendMessage("§a§m-------------------------");

					SQLHandler.updateJumps(p, 5);
					SQLHandler.updateTokens(p, -350);
				}
				else if(e.getSlot() == 6 && SQLHandler.getTokens(p) >= 600)
				{
					p.sendMessage("§a§m-------------------------");
					p.sendMessage("§a     §aPurchase Successful");
					p.sendMessage("§a§m-------------------------");
					
					SQLHandler.updateJumps(p, 10);
					SQLHandler.updateTokens(p, -600);
				}
				else
					p.sendMessage("§7[§cLilypad Jumper§7] §cYou do not have sufficient funds. Please visit §6Store.TheGameBoxMC.com§c to purchase more!");
				ScoreBoardHandler.statBoard(p);
		}
		
	}
}
