package mediaapps.blackhat;

import java.sql.SQLException;

import org.bukkit.entity.Player;

public class Upgrades 
{
	public static void upWeapons(Player p, int cost) throws NumberFormatException, SQLException
	{
		int amount = Integer.parseInt(SQLHandler.getTokens(p));
		if(amount >= cost)
			SQLHandler.setWeapon(p, SQLHandler.getWeapon(p) + 1);
		else
			p.sendMessage("Too few tokens!!");
	}
	public static void upArmor(Player p, int cost, int slot) throws NumberFormatException, SQLException
	{
		int amount = Integer.parseInt(SQLHandler.getTokens(p));
		if(amount >= cost)
		{
			if(slot == 0)
				SQLHandler.setHelm(p, SQLHandler.getHelm(p) + 1);
			else if(slot == 1)
				SQLHandler.setChest(p, SQLHandler.getChest(p) + 1);
			else if(slot == 2)
				SQLHandler.setLegs(p, SQLHandler.getLegs(p) + 1);
			else if(slot == 3)
				SQLHandler.setBoots(p, SQLHandler.getBoots(p) + 1);
		}
	}
}
