 package mediaapps.SCB;
 
 import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
 import org.bukkit.Color;
 import org.bukkit.Material;
 import org.bukkit.SkullType;
 import org.bukkit.block.Block;
 import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
 import org.bukkit.entity.Entity;
 import org.bukkit.entity.Player;
 import org.bukkit.event.block.Action;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.managers.ClassMakerManager;
import mediaapps.SCB.managers.PlayerManager;
         
         
 public class SkeletonClass
   implements ClassInterface
 {
			protected ItemStack i2 = new ItemStack(Material.BOW, 1);
   Plugin plugin = SCB.getInstance();
			public int extraArrows = 0;
			public boolean canUse = true;
   public String id()
   {
     return "skeleton";
   }
 
   public String DisplayName()
   {
     return "§7[§bSkeleton§7] ";
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
			 if(this.extraArrows > 0)
			 {
				 
				 Arrow arrow = (Arrow) p.launchProjectile(Arrow.class);
				 Vector v = new Vector(1, 1, 1);
				 arrow.setVelocity(p.getEyeLocation().getDirection().add(v).multiply(2));
     	return true;
			 }
			 return false;
   }
 
   public void ArrowHit(Player p, Entity e){}
 
   public void Spawn(Player p)
   {
     ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.SKELETON.ordinal());
     p.getInventory().setHelmet(i);
 
     i2.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
     i2.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
			 i2.addUnsafeEnchantment(Enchantment.DURABILITY, 255);
     ItemStack i3 = new ItemStack(Material.ARROW, 1);
     ItemStack i4 = new ItemStack(Material.BONE, 1);
     i4.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
 
     p.getInventory().addItem(new ItemStack[] { i4 });
     p.getInventory().addItem(new ItemStack[] { i2 });
     p.getInventory().addItem(new ItemStack[] { i3 });
 
     ClassMakerManager.get().setColorArmor(Color.GRAY, p);
   }
 
   public void Death(Player p)
   {
   }
 
   public void RightClick(Player p, Action a, Block b)
   {
				
   }
			public void LeftClick(final Player p, Action a, Block b)
			{
				if(p.getItemInHand().equals(i2))
				{
					if(this.canUse)
					 {
						this.canUse = false;
					Arrow arrow = (Arrow) p.launchProjectile(Arrow.class);
					arrow.setMetadata("Explosions", new FixedMetadataValue(this.plugin, "Boom"));
					arrow.setVelocity(p.getEyeLocation().getDirection().multiply(2));
					Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
				     {
						 public void run() 
						 {
								SkeletonClass.this.canUse = true;
								p.sendMessage("§7You can now use §cExplosive Arrow §7again!");
					     }
				     }, 20L);
					 }
					else
						p.sendMessage("§7You cannot use §cExplosive Arrow §7yet!");
				}  
			}
 
   public ItemStack Icon()
   {
     ItemStack icon = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.SKELETON.ordinal());
     ItemMeta im = icon.getItemMeta();
     im.setDisplayName("§bSkeleton");
             List<String> lore = new ArrayList<String>();
             lore.add("§aTier: §bII");
             lore.add("");
             lore.add("§7The Skeleton class is perfect for");
             lore.add("§7the skilled archer, and high-damage shots.");
             lore.add("§a");
             lore.add("§aFeatures:");
             lore.add("§b- Bow §7(Punch 2)");
             lore.add("§b- Bone §7(Knockback 1)");
             im.setLore(lore);
     icon.setItemMeta(im);
     return icon;
   }
 
   public void ThrowPotion(Player attacker, Player attacke)
   {
   }
 }
