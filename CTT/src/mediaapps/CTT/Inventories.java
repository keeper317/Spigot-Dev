package mediaapps.CTT;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class Inventories 
{
	public static Inventory Archers = Bukkit.getServer().createInventory(null, 27, "Archery"), 
					 potions = Bukkit.getServer().createInventory(null, 27, "Potions"), 
					 loot1 = Bukkit.getServer().createInventory(null, 27, "Loot1"),
					 loot2 = Bukkit.getServer().createInventory(null, 27, "Loot2"),
					 secret = Bukkit.getServer().createInventory(null, 27, "Secret");
	public static void setInvArcher()
	{
		ItemStack bow = new ItemStack(Material.BOW);
		ItemMeta bowmeta = bow.getItemMeta();
		List<String> bowlore = new ArrayList<String>();
		bowlore.add("�5Stop Reading and Shoot Someone!!");
		bowmeta.setLore(bowlore);
		bow.setItemMeta(bowmeta);
		for(int i = 0; i < 3; i++)
			Archers.setItem(i, bow);
		bowmeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		bowmeta.addEnchant(Enchantment.DURABILITY, 2, true);
		bow.setItemMeta(bowmeta);
		Archers.setItem(3, bow);
		for(int i = 4; i < 10; i++)
			Archers.setItem(i, new ItemStack(Material.ARROW, 64));
	}
	public static void setInvPotion()
	{
		ItemStack potion = new ItemStack(Material.POTION, 5);
		Potion pot = new Potion(1);
		pot.setType(PotionType.INSTANT_DAMAGE);
		pot.setSplash(true);
		pot.setLevel(2);
		pot.apply(potion);
		for(int i = 0; i < 5; i++)
			potions.setItem(i, potion);
	}
	public static void setInvLoot1()
	{
		ItemStack item = null;;
		Random rand = new Random();
		int ran = rand.nextInt(5) + 1;
		if(ran == 1)
			item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		else if(ran == 2)
			item = new ItemStack(Material.DIAMOND_AXE);
		else if(ran == 3)
			item = new ItemStack(Material.GOLDEN_APPLE);
		else if(ran == 4)
		{
			item = new ItemStack(Material.POTION, 5);
			ItemMeta meta = item.getItemMeta();
			List<String> lore = new ArrayList<String>();
			lore.add("Potion of Leaping");
			meta.setLore(lore);
			item.setItemMeta(meta);
			PotionMeta pm = (PotionMeta)item.getItemMeta();
			PotionEffect pe = new PotionEffect(PotionEffectType.JUMP, 30, (rand.nextInt(7) + 9));
			pm.addCustomEffect(pe, true );
			item.setItemMeta(pm);
		}
		else if(ran == 5)
			item = new ItemStack(Material.DIAMOND_SWORD);
		loot1.setItem(1, item);
	}
	public static void setInvLoot2()
	{
		ItemStack item = null;;
		Random rand = new Random();
		int ran = rand.nextInt(5) + 1;
		if(ran == 1)
			item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		else if(ran == 2)
			item = new ItemStack(Material.DIAMOND_AXE);
		else if(ran == 3)
			item = new ItemStack(Material.GOLDEN_APPLE);
		else if(ran == 4)
		{
			item = new ItemStack(Material.POTION, 5);
			ItemMeta meta = item.getItemMeta();
			List<String> lore = new ArrayList<String>();
			lore.add("Potion of Leaping");
			meta.setLore(lore);
			item.setItemMeta(meta);
			PotionMeta pm = (PotionMeta)item.getItemMeta();
			PotionEffect pe = new PotionEffect(PotionEffectType.JUMP, 30, 9);
			pm.addCustomEffect(pe, true );
			item.setItemMeta(pm);
		}
		else if(ran == 5)
			item = new ItemStack(Material.DIAMOND_SWORD);
		loot2.setItem(0, item);
	}
	public static void setInvSecret()
	{
		ItemStack item = null;
		Random rand = new Random();
		int ran = rand.nextInt(2);
		if(ran == 0)
		{
			item = new ItemStack(Material.IRON_CHESTPLATE);
			item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		}
		else if(ran == 1)
		{
			item = new ItemStack(Material.IRON_SWORD);
			item.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		}
		secret.setItem(0, item);
	}
}
