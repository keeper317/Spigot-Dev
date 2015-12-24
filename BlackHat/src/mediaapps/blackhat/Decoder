package mediaapps.blackhat;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Decoder 
{
	public static ItemStack getWeapon(Player p)
	{
		Material mat = null;
		int i = getWeaponLevel(p);
		ItemStack stack = null;
		switch(i)
		{
			case 1: mat = Material.WOOD_SWORD; break;
			case 2: mat = Material.STONE_SWORD; break;
			case 3:
			case 4: mat = Material.IRON_SWORD; break;
			case 5: 
			case 6:
			case 7: mat = Material.DIAMOND_SWORD; break;
			
		}
		stack = new ItemStack(mat,1);
		if (i == 4 || i == 6 || i == 7)
			stack.addEnchantment(Enchantment.DAMAGE_ALL, (i / 7) + 1);
		return stack;
	}
	public static int getWeaponLevel(Player p)
	{
		int i = 0;
		return i;
	}
	public static int getHelmLevel(Player p)
	{
		int i = 0;
		return i;
	}
	public static ItemStack getHelm(Player p, String team)
	{
		int i = getChestLevel(p);
		ItemStack stack = new ItemStack(Material.LEATHER_HELMET,1);
		switch(i)
		{
			case 1: stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, i); break;
			case 2: stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, i); break;
			case 3: stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, i); break;
			case 4: stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, i); break;
			case 5: stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, i); break;
			case 6: stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, i); break;
			case 7: stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, i); break;
			
		}
		LeatherArmorMeta tag = (LeatherArmorMeta) stack.getItemMeta();
		if(team.equalsIgnoreCase("Hackers"))
			tag.setColor(Color.BLACK);
		if(team.equalsIgnoreCase("Agents"))
			tag.setColor(Color.WHITE);
		return stack;
	}
	public static int getChestLevel(Player p)
	{
		int i = 0;
		return i;
	}
	public static ItemStack getChest(Player p)
	{
		Material mat = null;
		int i = getChestLevel(p);
		ItemStack stack = null;
		switch(i)
		{
			case 1: mat = Material.LEATHER_CHESTPLATE; break;
			case 2: mat = Material.GOLD_CHESTPLATE; break;
			case 3: mat = Material.CHAINMAIL_CHESTPLATE; break;
			case 4:
			case 5: mat = Material.IRON_CHESTPLATE; break;
			case 6:
			case 7: mat = Material.DIAMOND_CHESTPLATE; break;
			
		}
		stack = new ItemStack(mat,1);
		if (i == 7 || i == 5)
			stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		return stack;
	}
	public static ItemStack getLeg(Player p)
	{
		Material mat = null;
		ItemStack stack = null;
		int i = getLegLevel(p);
		switch(i)
		{
			case 1: mat = Material.LEATHER_LEGGINGS; break;
			case 2: mat = Material.GOLD_LEGGINGS; break;
			case 3: mat = Material.CHAINMAIL_LEGGINGS; break;
			case 4:
			case 5: mat = Material.IRON_LEGGINGS; break;
			case 6:
			case 7: mat = Material.DIAMOND_LEGGINGS; break;
			
		}
		stack = new ItemStack(mat,1);
		if (i == 7 || i == 5)
			stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		return stack;
	}
	public static int getLegLevel(Player p)
	{
		int i = 0;
		return i;
	}
	public static ItemStack getBoot(Player p)
	{
		Material mat = null;
		int i = getBootLevel(p);
		ItemStack stack = null;
		switch(i)
		{
			case 1: mat = Material.LEATHER_BOOTS; break;
			case 2: mat = Material.GOLD_BOOTS; break;
			case 3: mat = Material.CHAINMAIL_BOOTS; break;
			case 4:
			case 5: mat = Material.IRON_BOOTS; break;
			case 6:
			case 7: mat = Material.DIAMOND_BOOTS; break;
			
		}
		stack = new ItemStack(mat,1);
		if (i == 7 || i == 5)
			stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		return stack;
	}
	public static int getBootLevel(Player p)
	{
		int i = 0;
		return i;
	}
	public static ItemStack[] getUtils(Player p) 
	{
		ItemStack[] stack = new ItemStack[2];
		
		return stack;
	}
	public static ItemStack[] getWeapons(Player p) 
	{
		ItemStack[] stack = new ItemStack[3];
		
		return stack;
	}
	public static ItemStack[] getExtras(Player p) 
	{
		ItemStack[] stack = new ItemStack[3];
		
		return stack;
	}
}
