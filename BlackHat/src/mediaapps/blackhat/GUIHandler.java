package mediaapps.blackhat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIHandler 
{
	public static Inventory main, weapons, agents, hacker, armor;
	public static String[] weaponDisName = {""};
	public static String[][] weaponLore = {{""},{""}};
	public static String[][] armorDisName = {{},{""}};
	public static String[][] armorLore = {{""},{""}};
	public static void mainGui(Player p) throws SQLException
	{
		main = Bukkit.getServer().createInventory(null, 9, "Black Hat Shop");
		main.setItem(2, ItemMaker(Material.DIAMOND_SWORD, "Weapons Shop", new String[] {"�5Click me to enter the Weapons Shop!"}));
		main.setItem(0, ItemMaker(Material.LADDER, "Agent's Shop", new String[] {"�5Clcik me to enter the Agent's Shop!"}));
		main.setItem(8, ItemMaker(Material.IRON_DOOR, "Hacker's Shop", new String[] {"�5Click me to enter the Hacker's Shop!"}));
		main.setItem(6, ItemMaker(Material.DIAMOND_CHESTPLATE, "Armor Shop", new String[] {"�5Click me to enter the Armor Shop!"}));
		main.setItem(4, ItemMaker(Material.EMERALD, "Bank:", getLore(p)));
	}
	public void setBank(Player p, String str) throws SQLException
	{
		int i = 8;
		p.getInventory().setItem(i, ItemMaker(Material.EMERALD, "Bank:", getLore(p)));
	}
	private static String[] getLore(Player p) throws SQLException 
	{
		String[] str = new String[10];
		str[0] = p.getName() + ":";
		str[1] = "";
		str[2] = "&7Tokens: " + SQLHandler.getTokens(p);
		str[3] = "&5Wins: "+ SQLHandler.getWins(p);
		str[4] = "&6Kills: " + SQLHandler.getKills(p);
		return str;
	}
	public static void weaponsGui(Player p) throws SQLException
	{
		weapons = Bukkit.getServer().createInventory(null, 18, "Weapons Shop");
		weapons.setItem(0, ItemMaker(Material.WOOD_SWORD, "Current Weapon", new String[] {"�5Current Weapon"}));
		weapons.setItem(2, ItemMaker(Material.STAINED_GLASS_PANE, (short) 13, weaponDisName[0], weaponLore[0]));
		weapons.setItem(12, ItemMaker(Material.ARROW, "Go Back", new String[] {"�5Click me to go back to the Black Hat Shop!"}));
		weapons.setItem(14, ItemMaker(Material.EMERALD, "Bank:", getLore(p)));
	}
	public static void armorGui(Player p) throws SQLException
	{
		armor = Bukkit.getServer().createInventory(null, 54, "Armor Shop");
		
		armor.setItem(48, ItemMaker(Material.ARROW, "Go Back", new String[] {"�5Click me to go back to the Black Hat Shop!"}));
		weapons.setItem(14, ItemMaker(Material.EMERALD, "Bank:", getLore(p)));
	}
	public static void agentsGui(Player p) throws SQLException
	{
		agents = Bukkit.getServer().createInventory(null, 18, "Agent's Shop");
		agents.setItem(2, ItemMaker(Material.DISPENSER, "Utilities", new String[] {"�5Click me to upgrade Agent Utilities!"}));
		agents.setItem(4, ItemMaker(Material.BOOK, "Extras", new String[] {"�5Click me to buy Agent Extras!"}));
		agents.setItem(6, ItemMaker(Material.BOW, "Weapons", new String[] {"�5Click me to upgrade Agent Weapons!"}));
		agents.setItem(12, ItemMaker(Material.ARROW, "Go Back", new String[] {"�5Click me to go back to the Black Hat Shop"}));
		weapons.setItem(14, ItemMaker(Material.EMERALD, "Bank:", getLore(p)));
	}
	public static void hackerGui(Player p) throws SQLException
	{
		hacker = Bukkit.getServer().createInventory(null, 18, "Hacker's Shop");
		hacker.setItem(2, ItemMaker(Material.DIAMOND_PICKAXE, "Utilities", new String[] {"�5Click me to upgrade Hacker Utilities!"}));
		hacker.setItem(4, ItemMaker(Material.BOOK, "Extras", new String[] {"�5Click me to buy Hacker Extras!"}));
		hacker.setItem(6, ItemMaker(Material.FLINT_AND_STEEL, "Extras", new String[] {"�5Click me to upgrde Hacker Weapons!"}));
		hacker.setItem(12, ItemMaker(Material.ARROW, "Go Back", new String[] {"�5Click me to go back to the Black Hat Shop!"}));	
		weapons.setItem(14, ItemMaker(Material.EMERALD, "Bank:", getLore(p)));
	}
	public static ItemStack ItemMaker(Material mat, String disname, String[] list)
	{
		ItemStack stack = new ItemStack(mat);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(disname);
		List<String> lore = new ArrayList<String>();
		for(int i = 0; i < list.length; i++)
			lore.add(list[i]);
		meta.setLore(lore);
		stack.setItemMeta(meta);
		return stack;
	}
	private static ItemStack ItemMaker(Material mat, short s, String disname, String[] list) 
	{
		ItemStack stack = new ItemStack(mat, 1, s);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(disname);
		List<String> lore = new ArrayList<String>();
		for(int i = 0; i < list.length; i++)
			lore.add(list[i]);
		meta.setLore(lore);
		stack.setItemMeta(meta);
		return stack;
	}
}
