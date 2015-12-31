package mediaapps.blackhat;

import java.sql.SQLException;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickEvents implements Listener
{
	@EventHandler
	public void clickedOn(InventoryClickEvent e) throws SQLException{ if(e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) return; else//WIP
	{
		Player p = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getInventory().getName().equalsIgnoreCase("Black Hat Shop"))
			blackHat(p,e.getSlot());
		else if(e.getInventory().getName().equalsIgnoreCase("Weapons Shop"))
			weapons(p,e.getSlot());
		else if(e.getInventory().getName().equalsIgnoreCase("Armor Shop"))
			armor(p,e.getSlot());
		else if(e.getInventory().getName().equalsIgnoreCase("Agent's Shop"))
			agents(p,e.getSlot());
		else if(e.getInventory().getName().equalsIgnoreCase("Hacker's Shop"))
			hackers(p,e.getSlot());

		p.sendMessage(e.getSlot() + " opened!");
	}}
	public void blackHat(Player p, int slot) throws SQLException//DONE!!
	{
		switch(slot)
		{
			case 0: GUIHandler.agentsGui(p);p.openInventory(GUIHandler.agents);break;
			case 2: GUIHandler.weaponsGui(p);p.openInventory(GUIHandler.weapons);break;
			case 6: GUIHandler.armorGui(p);p.openInventory(GUIHandler.armor);break;
			case 8: GUIHandler.hackerGui(p);p.openInventory(GUIHandler.hacker);break;
		}
	}
	public void weapons(Player p, int slot) throws SQLException//WIP
	{
		switch(slot)
		{//Empty cases for upgrading weapon.
			case 3: Upgrades.upWeapons(p, 100); break;
			case 4: Upgrades.upWeapons(p, 200); break;
			case 5: Upgrades.upWeapons(p, 300); break;
			case 6: Upgrades.upWeapons(p, 400); break;
			case 7: Upgrades.upWeapons(p, 500); break;
			case 8: Upgrades.upWeapons(p, 600); break;
			case 12: GUIHandler.mainGui(p);p.openInventory(GUIHandler.main);break;
		}
	}
	public void armor(Player p, int slot) throws SQLException//WIP
	{
		switch(slot)
		{//Empty cases for upgrading armor
			case 3://Start Helm
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:break;//End Helm
			case 12://Start Chest
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:break;//End Chest
			case 21://Start Legs
			case 22:
			case 23:
			case 24:
			case 25:
			case 26:break;//End Legs
			case 30://Start Boots
			case 31:
			case 32:
			case 33:
			case 34:
			case 35:break;//End Boots
			case 39:  GUIHandler.mainGui(p);p.openInventory(GUIHandler.main);break;
		}
	}
	public void agents(Player p, int slot) throws SQLException//WIP
	{
		switch(slot)
		{//Empty cases for farther guis
			case 2: 
			case 4: 
			case 6:break;
			case 12:  GUIHandler.mainGui(p);p.openInventory(GUIHandler.main);break;
		}
	}
	public void hackers(Player p, int slot) throws SQLException//WIP
	{
		switch(slot)
		{//Empty cases for farther guis
			case 2: 
			case 4: 
			case 6:break;
			case 12:  GUIHandler.mainGui(p);p.openInventory(GUIHandler.main);break;
		}
	}
}
