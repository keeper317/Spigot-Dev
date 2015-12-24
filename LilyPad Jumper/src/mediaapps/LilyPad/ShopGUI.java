package mediaapps.LilyPad;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopGUI 
{
	public static Inventory shop;
	
	public static void shopGui()
	{
		shop = Bukkit.getServer().createInventory(null, 9, "LilyPad Shop");
		ItemStack stack = new ItemStack(Material.WATER_LILY);
		ItemMeta meta = stack.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("§7Be able to jump longer");
		lore.add("§7and higer. (1 Use)");
		lore.add("§7 ");
		lore.add("§7Price: §6100");
		lore.add("§7 ");
		lore.add("§aClick to Purchase");
		meta.setDisplayName("§aDouble Jump I");
		meta.setLore(lore);
		stack.setItemMeta(meta);
		shop.setItem(2, stack);
		
		ItemStack stack1 = new ItemStack(Material.WATER_LILY);
		ItemMeta meta1 = stack1.getItemMeta();
		List<String> lore1 = new ArrayList<String>();
		lore1.add("§7Be able to jump longer");
		lore1.add("§7and higer. (5 Uses)");
		lore1.add("§7 ");
		lore1.add("§7Price: §6350");
		lore1.add("§7 ");
		lore1.add("§aClick to Purchase");
		meta1.setDisplayName("§aDouble Jump V");
		meta1.setLore(lore1);
		stack1.setItemMeta(meta1);
		shop.setItem(4, stack1);
		
		ItemStack stack2 = new ItemStack(Material.WATER_LILY);
		ItemMeta meta2 = stack2.getItemMeta();
		List<String> lore2 = new ArrayList<String>();
		lore2.add("§7Be able to jump longer");
		lore2.add("§7and higer. (10 Use)");
		lore2.add("§7 ");
		lore2.add("§7Price: §6600");
		lore2.add("§7 ");
		lore2.add("§aClick to Purchase");
		meta2.setDisplayName("§aDouble Jump X");
		meta2.setLore(lore2);
		stack2.setItemMeta(meta2);
		shop.setItem(6, stack2);
	}
}
