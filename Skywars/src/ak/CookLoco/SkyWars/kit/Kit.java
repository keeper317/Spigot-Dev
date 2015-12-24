package ak.CookLoco.SkyWars.kit;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.utils.ItemBuilder;

public class Kit
{
  private String name;
  private int price;
  private List<ItemStack> items = new ArrayList();
  private int slot;
  private List<String> item_lore = new ArrayList();
  private List<String> contents = new ArrayList();
  private boolean free = false;
  private ItemBuilder item;
  File config_file = null;
  FileConfiguration config = null;
  
  public Kit(String paramString)
  {
    this.name = paramString;
    this.config_file = new File(SkyWars.getPlugin().getDataFolder(), SkyWars.kits + File.separator + paramString + ".yml");
    this.config = YamlConfiguration.loadConfiguration(this.config_file);
    
    this.price = this.config.getInt("price");
    if (this.price <= 0) {
      this.free = true;
    }
    this.slot = this.config.getInt("icon.slot");
    
    this.items.clear();
    for (Iterator localIterator = this.config.getList("items").iterator(); localIterator.hasNext();)
    {
      localObject = localIterator.next();
      this.items.add(read(localObject.toString()));
    }
    for (localIterator = this.config.getList("contents").iterator(); localIterator.hasNext();)
    {
      localObject = localIterator.next();
      this.contents.add(localObject.toString());
    }
    this.item_lore.add(SkyWars.getMessage("kit.contents"));
    for (localIterator = this.contents.iterator(); localIterator.hasNext();)
    {
      localObject = (String)localIterator.next();
      this.item_lore.add(String.format(ChatColor.translateAlternateColorCodes('&', SkyWars.getMessage("kit.contents.format")), new Object[] { localObject }));
    }
    Object localObject = this.config.getString("icon.item").split(",");
    int i = Integer.parseInt(localObject[0]);
    int j = Integer.parseInt(localObject[1]);
    this.item = new ItemBuilder(Material.getMaterial(i), (short)j)
      .setTitle(isFree() ? String.format(SkyWars.getMessage("kit.name.free"), new Object[] { paramString }) : String.format(SkyWars.getMessage("kit.name.notpurchased") + " ", new Object[] { paramString }))
      .setLore(this.item_lore);
    if (!isFree()) {
      this.item.addLore(String.format(SkyWars.getMessage("kit.cost"), new Object[] { Integer.valueOf(getPrice()) }));
    }
    KitManager.kits.put(paramString, this);
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getPrice()
  {
    return this.price;
  }
  
  public List<ItemStack> getItems()
  {
    return this.items;
  }
  
  public int getSlot()
  {
    return this.slot;
  }
  
  public ItemBuilder getIcon()
  {
    return this.item;
  }
  
  public boolean isFree()
  {
    return this.free;
  }
  
  public static ItemStack read(String paramString)
  {
    String[] arrayOfString = paramString.split(",");
    ArrayList localArrayList = new ArrayList();
    ItemStack localItemStack = new ItemStack(Integer.parseInt(arrayOfString[0]), Integer.parseInt(arrayOfString[1]), Short.parseShort(arrayOfString[2]));
    for (int i = 1; i < arrayOfString.length; i++)
    {
      ItemMeta localItemMeta;
      if (arrayOfString[i].startsWith("lore:"))
      {
        localItemMeta = localItemStack.getItemMeta();
        String str1 = arrayOfString[i].replace("lore:", "");
        String str3 = ChatColor.translateAlternateColorCodes('&', str1);
        localArrayList.add(str3);localItemMeta.setLore(localArrayList);localItemStack.setItemMeta(localItemMeta);
      }
      Enchantment[] arrayOfEnchantment;
      int k = (arrayOfEnchantment = Enchantment.values()).length;
      for (int j = 0; j < k; j++)
      {
        localItemMeta = arrayOfEnchantment[j];
        if (arrayOfString[i].toUpperCase().startsWith(localItemMeta.getName().toUpperCase()))
        {
          String str4 = arrayOfString[i].replace(localItemMeta.getName().toUpperCase() + ":", "");
          localItemStack.addUnsafeEnchantment(localItemMeta, Integer.parseInt(str4));
        }
      }
      if (arrayOfString[i].startsWith("name:"))
      {
        localItemMeta = localItemStack.getItemMeta();
        String str2 = arrayOfString[i].replace("name:", "");
        localItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', str2));
        localItemStack.setItemMeta(localItemMeta);
      }
    }
    return localItemStack;
  }
  
  public List<String> getContents()
  {
    return this.item_lore;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\kit\Kit.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */