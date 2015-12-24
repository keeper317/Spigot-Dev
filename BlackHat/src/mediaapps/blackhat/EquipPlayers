package mediaapps.blackhat;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EquipPlayers 
{
	public void equipMe(Player p)
	{
		p.getInventory().setItem(1, Decoder.getWeapon(p));
		p.getInventory().setHelmet(Decoder.getHelm(p, Main.team.get(p.getName())));
		p.getInventory().setChestplate(Decoder.getChest(p));
		p.getInventory().setLeggings(Decoder.getLeg(p));
		p.getInventory().setBoots(Decoder.getBoot(p));
		getUtils(p);
		getExtras(p);
		getWeapons(p);
	}
	public void getUtils(Player p)
	{
		for(ItemStack item : Decoder.getUtils(p))
			p.getInventory().addItem(item);
			
	}
	public void getExtras(Player p)
	{
		for(ItemStack item : Decoder.getExtras(p))
			p.getInventory().addItem(item);
	}
	public void getWeapons(Player p)
	{
		for(ItemStack item : Decoder.getWeapons(p))
			p.getInventory().addItem(item);
	}
	
}
