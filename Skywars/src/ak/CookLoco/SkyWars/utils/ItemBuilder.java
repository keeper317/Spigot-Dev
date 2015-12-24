package ak.CookLoco.SkyWars.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.Potion;

public class ItemBuilder
{
  private Material mat;
  private int amount;
  private final short data;
  private String title = null;
  private final List<String> lore = new ArrayList();
  private final HashMap<Enchantment, Integer> enchants = new HashMap();
  private Color color;
  private Potion potion;
  
  public ItemBuilder(Material paramMaterial)
  {
    this(paramMaterial, 1);
  }
  
  public ItemBuilder(Material paramMaterial, int paramInt)
  {
    this(paramMaterial, paramInt, (short)0);
  }
  
  public ItemBuilder(Material paramMaterial, short paramShort)
  {
    this(paramMaterial, 1, paramShort);
  }
  
  public ItemBuilder(Material paramMaterial, int paramInt, short paramShort)
  {
    this.mat = paramMaterial;
    this.amount = paramInt;
    this.data = paramShort;
  }
  
  public ItemBuilder setType(Material paramMaterial)
  {
    this.mat = paramMaterial;
    return this;
  }
  
  public ItemBuilder setTitle(String paramString)
  {
    this.title = paramString;
    return this;
  }
  
  public ItemBuilder addLore(String paramString)
  {
    this.lore.add(paramString);
    return this;
  }
  
  public ItemBuilder setLore(List<String> paramList)
  {
    this.lore.clear();
    for (String str : paramList) {
      this.lore.add(ChatColor.translateAlternateColorCodes('&', str));
    }
    return this;
  }
  
  public ItemBuilder addEnchantment(Enchantment paramEnchantment, int paramInt)
  {
    if (this.enchants.containsKey(paramEnchantment)) {
      this.enchants.remove(paramEnchantment);
    }
    this.enchants.put(paramEnchantment, Integer.valueOf(paramInt));
    return this;
  }
  
  public ItemBuilder setColor(Color paramColor)
  {
    if (this.mat.name().contains("LEATHER_")) {
      this.color = paramColor;
    }
    return this;
  }
  
  public ItemBuilder setPotion(Potion paramPotion)
  {
    if (this.mat != Material.POTION) {
      this.mat = Material.POTION;
    }
    this.potion = paramPotion;
    return this;
  }
  
  public ItemBuilder setAmount(int paramInt)
  {
    this.amount = paramInt;
    return this;
  }
  
  public ItemStack build()
  {
    Material localMaterial = this.mat;
    if (localMaterial == null)
    {
      localMaterial = Material.AIR;
      Bukkit.getLogger().warning("Null material!");
    }
    ItemStack localItemStack = new ItemStack(this.mat, this.amount, this.data);
    ItemMeta localItemMeta = localItemStack.getItemMeta();
    if (this.title != null) {
      localItemMeta.setDisplayName(this.title);
    }
    if (!this.lore.isEmpty()) {
      localItemMeta.setLore(this.lore);
    }
    if ((localItemMeta instanceof LeatherArmorMeta)) {
      ((LeatherArmorMeta)localItemMeta).setColor(this.color);
    }
    localItemStack.setItemMeta(localItemMeta);
    localItemStack.addUnsafeEnchantments(this.enchants);
    if (this.potion != null) {
      this.potion.apply(localItemStack);
    }
    return localItemStack;
  }
  
  public ItemBuilder clone()
  {
    ItemBuilder localItemBuilder = new ItemBuilder(this.mat);
    
    localItemBuilder.setTitle(this.title);
    Object localObject;
    for (Iterator localIterator = this.lore.iterator(); localIterator.hasNext();)
    {
      localObject = (String)localIterator.next();
      localItemBuilder.addLore((String)localObject);
    }
    for (localIterator = this.enchants.entrySet().iterator(); localIterator.hasNext();)
    {
      localObject = (Map.Entry)localIterator.next();
      localItemBuilder.addEnchantment((Enchantment)((Map.Entry)localObject).getKey(), ((Integer)((Map.Entry)localObject).getValue()).intValue());
    }
    localItemBuilder.setColor(this.color);
    localItemBuilder.potion = this.potion;
    
    return localItemBuilder;
  }
  
  public Material getType()
  {
    return this.mat;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public List<String> getLore()
  {
    return this.lore;
  }
  
  public Color getColor()
  {
    return this.color;
  }
  
  public boolean hasEnchantment(Enchantment paramEnchantment)
  {
    return this.enchants.containsKey(paramEnchantment);
  }
  
  public int getEnchantmentLevel(Enchantment paramEnchantment)
  {
    return ((Integer)this.enchants.get(paramEnchantment)).intValue();
  }
  
  public HashMap<Enchantment, Integer> getAllEnchantments()
  {
    return this.enchants;
  }
  
  public boolean isItem(ItemStack paramItemStack)
  {
    if (paramItemStack == null) {
      return false;
    }
    ItemMeta localItemMeta = paramItemStack.getItemMeta();
    if (paramItemStack.getType() != getType()) {
      return false;
    }
    if ((!localItemMeta.hasDisplayName()) && (getTitle() != null)) {
      return false;
    }
    if (!localItemMeta.getDisplayName().equals(getTitle())) {
      return false;
    }
    if ((!localItemMeta.hasLore()) && (!getLore().isEmpty())) {
      return false;
    }
    Object localObject;
    if (localItemMeta.hasLore()) {
      for (localIterator = localItemMeta.getLore().iterator(); localIterator.hasNext();)
      {
        localObject = (String)localIterator.next();
        if (!getLore().contains(localObject)) {
          return false;
        }
      }
    }
    for (Iterator localIterator = paramItemStack.getEnchantments().keySet().iterator(); localIterator.hasNext();)
    {
      localObject = (Enchantment)localIterator.next();
      if (!hasEnchantment((Enchantment)localObject)) {
        return false;
      }
    }
    return true;
  }
}