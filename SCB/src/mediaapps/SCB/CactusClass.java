 package mediaapps.SCB;
 
 import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.Color;
 import org.bukkit.Material;
 import org.bukkit.block.Block;
 import org.bukkit.enchantments.Enchantment;
 import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
 import org.bukkit.entity.Player;
 import org.bukkit.event.block.Action;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;


import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.managers.ClassMakerManager; 
         
 public class CactusClass
   implements ClassInterface
 {
				public boolean canUse = true;
	   Plugin plugin = SCB.getInstance();

 protected ItemStack i1 = new ItemStack(Material.WOOD_SWORD, 1);
   public String id()
   {
     return "cactus";
   }
 
   public String DisplayName()
   {
     return "§7[§aCactus§7] ";
   }
 
   public boolean Movement(Player p)
   {
     return false;
   }
 
   public boolean Jump(Player p)
   {
     return false;
   }
 
   public void Attack(Player attacker, Player attacke)
   {
   }
 
   public boolean ShootArrow(Player p)
   {
     return false;
   }
 
   public void ArrowHit(Player p, Entity e)
   {
   }
 
   public void Spawn(Player p)
   {
     ItemStack hel = ClassMakerManager.get().getPlayerHead("MHF_Cactus");
     p.getInventory().setHelmet(hel);
 
     ItemStack cChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
     LeatherArmorMeta clam = (LeatherArmorMeta)cChest.getItemMeta();
     clam.setColor(Color.GREEN);
     cChest.setItemMeta(clam);
     cChest.addUnsafeEnchantment(Enchantment.THORNS, 4);
     cChest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
     p.getInventory().setChestplate(cChest);
     ItemStack cLeg = new ItemStack(Material.LEATHER_LEGGINGS, 1);
     LeatherArmorMeta llam = (LeatherArmorMeta)cLeg.getItemMeta();
     llam.setColor(Color.GREEN);
     cLeg.setItemMeta(llam);
     p.getInventory().setLeggings(cLeg);
     ItemStack cBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
     LeatherArmorMeta blam = (LeatherArmorMeta)cBoots.getItemMeta();
     blam.setColor(Color.GREEN);
     cBoots.setItemMeta(blam);
     cBoots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
     p.getInventory().setBoots(cBoots);
 
     i1.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
			  ItemMeta meta = i1.getItemMeta();
			  meta.setDisplayName("§6§lRIGHT_CLICK §e§l> §a§lSELECT §c§l| §6§lCLICK§e§l > §a§lTHROW");
			  i1.setItemMeta(meta);
     p.getInventory().addItem(new ItemStack[] { i1 });
   }
 
   public void Death(Player p)
   {
   }
 
   public void RightClick(final Player p, Action a, Block b)
   {
				if(p.getItemInHand().equals(i1))
				{
					 if(this.canUse)
					 {
						this.canUse = false;
						Item item = p.getWorld().dropItem(p.getEyeLocation().add(p.getLocation().getDirection()), new ItemStack(Material.CACTUS, 1));
						item.setMetadata("Cactus", new FixedMetadataValue(this.plugin, "Cac"));
						item.setVelocity(p.getLocation().getDirection().multiply(2));
						item.setPickupDelay(2);
						item.setTicksLived(5900);
						Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
					     {
							 public void run() 
							 {
									CactusClass.this.canUse = true;
									p.sendMessage("§7You can now use §cCactus Arm §7again!");
						     }
					     }, 20L);
						 }
						else
							p.sendMessage("§7You cannot use §cCactus Arm §7yet!");
				}
   }
			public void LeftClick(Player p, Action a, Block b){}
 
   public ItemStack Icon()
   {
     ItemStack icon = new ItemStack(Material.CACTUS, 1);
     ItemMeta sm = icon.getItemMeta();
     sm.setDisplayName(ChatColor.GREEN + "§aCactus §7[§aEmerald§7]");
             List<String> lore = new ArrayList<String>();
             lore.add("§aTier: §bIII");
             lore.add("");
             lore.add("§7The Cactus class is great for");
             lore.add("§7melee combat, and defense.");
             lore.add("§a");
             lore.add("§aFeatures:");
             lore.add("§b- Wooden Sword §7(Sharpness 1)");
             lore.add("§6§lRIGHT CLICK§e§l >> §a§lSELECT");
             lore.add("§6§lLEFT CLICK §e§l >> §a§lTHROW");
             lore.add("§b- Leather Chestplate §7(Thorns 4)");
             sm.setLore(lore);
     icon.setItemMeta(sm);
     return icon;
   }
 
   public void ThrowPotion(Player attacker, Player attacke)
   {
   }
 }
