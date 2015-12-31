 package mediaapps.SCB.managers;
 import org.bukkit.Color;
 import org.bukkit.Material;
 import org.bukkit.entity.Player;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.PlayerInventory;
 import org.bukkit.inventory.meta.LeatherArmorMeta;
 import org.bukkit.inventory.meta.SkullMeta;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.potion.Potion;
 import org.bukkit.potion.PotionType;

import mediaapps.SCB.SCB;
 
 public class ClassMakerManager
 {
   Plugin plugin = SCB.getInstance();
   public static ClassMakerManager cmmanger = new ClassMakerManager();
 
   public static ClassMakerManager get() { return cmmanger; }
 
   public void setColorArmor(Color c, Player p)
   {
     ItemStack cChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
     LeatherArmorMeta clam = (LeatherArmorMeta)cChest.getItemMeta();
     clam.setColor(c);
     cChest.setItemMeta(clam);
     p.getInventory().setChestplate(cChest);
     ItemStack cLeg = new ItemStack(Material.LEATHER_LEGGINGS, 1);
     LeatherArmorMeta llam = (LeatherArmorMeta)cLeg.getItemMeta();
     llam.setColor(c);
     cLeg.setItemMeta(llam);
     p.getInventory().setLeggings(cLeg);
     ItemStack cBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
     LeatherArmorMeta blam = (LeatherArmorMeta)cBoots.getItemMeta();
     blam.setColor(c);
     cBoots.setItemMeta(blam);
     p.getInventory().setBoots(cBoots);
     p.updateInventory();
   }
 
   public void givePotion(Player p, PotionType pt, int lvl, int amount, boolean splash) {
     Potion po = new Potion(pt, lvl);
     po.setSplash(splash);
     ItemStack item = new ItemStack(po.toItemStack(amount));
     p.getInventory().addItem(new ItemStack[] { item });
     p.updateInventory();
   }
   public ItemStack getPlayerHead(String pn) {
     ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
     SkullMeta sm = (SkullMeta)is.getItemMeta();
     sm.setOwner(pn);
     is.setItemMeta(sm);
     return is;
   }
 }