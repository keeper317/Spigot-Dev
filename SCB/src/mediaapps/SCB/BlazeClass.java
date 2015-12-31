 package mediaapps.SCB;
import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.managers.ClassMakerManager;

import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.Color;
 import org.bukkit.Material;
 import org.bukkit.block.Block;
 import org.bukkit.enchantments.Enchantment;
 import org.bukkit.entity.Entity;
 import org.bukkit.entity.Fireball;
 import org.bukkit.entity.Player;
 import org.bukkit.event.block.Action;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.plugin.Plugin;
import java.util.ArrayList;
import java.util.List;
         
 public class BlazeClass
   implements ClassInterface
 {
		   public boolean canUse = true;
		   protected  ItemStack i3 = new ItemStack(Material.BLAZE_ROD, 1);
   Plugin plugin = SCB.getInstance();
 
   public String id() {
     return "blaze";
   }
 
   public String DisplayName()
   {
     return "§7[§6Blaze§7] ";
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
 
   public boolean ShootArrow(final Player p)
   {
     return false;
   }
 
   public void ArrowHit(Player p, Entity e)
   {
   }
 
   public void Spawn(Player p)
   {
     p.getInventory().setHelmet(ClassMakerManager.get().getPlayerHead("MHF_Blaze"));
     ClassMakerManager.get().setColorArmor(Color.YELLOW, p);
 
			 ItemMeta meta = i3.getItemMeta();
			 meta.setDisplayName("§e§lRIGHT CLICK §6>> §a§lFIREBALL");
			 i3.setItemMeta(meta);
     i3.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
     i3.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
 
     p.getInventory().addItem(new ItemStack[] { i3 });
   }
 
   public void Death(Player p)
   {
   }
 
   public void RightClick(Player p, Action a, Block b)
   {
			final Player fp = p;
			 if(p.getItemInHand().equals(i3) && (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)))
			 {
				 if(this.canUse)
				 {
					 Fireball ball = (Fireball) p.launchProjectile(Fireball.class);
					 ball.setIsIncendiary(false);
					 ball.setYield(0.01F);
					 ball.setVelocity(p.getEyeLocation().getDirection().multiply(2));
					 this.canUse = false;
					 
					 Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
				     {
						 public void run() 
						 {
								BlazeClass.this.canUse = true;
								fp.sendMessage("§cYou can use fireball again!");
								BlazeClass.this.canUse = true;
					     }
				     }, 20L);
				 }
				 else
					 p.sendMessage("§cYou can not use fireball yet!");
			 }
			}
			public void LeftClick(Player p, Action a, Block b)
			{
				  
			}
 
   public ItemStack Icon()
   {
     ItemStack icon = new ItemStack(Material.BLAZE_ROD, 1);
     ItemMeta im = icon.getItemMeta();
     im.setDisplayName(ChatColor.GOLD + "Blaze");
             List<String> lore = new ArrayList<String>();
             lore.add("§aTier: §bIII");
             lore.add("");
             lore.add("§7The Blaze class is great for");
             lore.add("§7rapid-fire and low-damage shots.");
             lore.add("§a");
             lore.add("§aFeatures:");
             lore.add("§b- Bow §7(Shoots Fireballs)");
             lore.add("§b- Blaze Rod §7(Knockback 1, FireAspect 1)");
             im.setLore(lore);
     icon.setItemMeta(im);
     return icon;
   }
 
   public void ThrowPotion(Player attacker, Player attacke)
   {
   }
 }

