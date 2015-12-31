 package mediaapps.SCB;
 
 import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.Color;
 import org.bukkit.Material;
 import org.bukkit.SkullType;
 import org.bukkit.block.Block;
 import org.bukkit.enchantments.Enchantment;
 import org.bukkit.entity.Entity;
 import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
 import org.bukkit.event.block.Action;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
 import org.bukkit.potion.PotionEffect;
 import org.bukkit.potion.PotionEffectType;


import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.managers.ClassMakerManager; 
         
 public class WitherClass
   implements ClassInterface
 {
	protected boolean canUse = true;
	protected ItemStack i = new ItemStack(Material.NETHER_STAR, 1);
	Plugin plugin = SCB.getInstance();
   public String id()
   {
     return "Wither";
   }
 
   public String DisplayName()
   {
     return "§7[§5Wither§7] ";
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
			 Random random = new Random();
     int num = random.nextInt(100) + 1;
     if (num <= 75)
       attacke.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 50, 1), true);
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
     ItemStack helm = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
     p.getInventory().setHelmet(helm);
 
     ClassMakerManager.get().setColorArmor(Color.BLACK, p);
 
			 ItemMeta meta = i.getItemMeta();
			 meta.setDisplayName("§e§lRight Click §6>> §a§lWither Blast".toUpperCase());
			 i.setItemMeta(meta);
     i.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
     i.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
			 p.getInventory().addItem(i);
   }
 
   public void Death(Player p)
   {
   }
 
   public void RightClick(final Player p, Action a, Block b)
   {
				if(p.getItemInHand().equals(i) && (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)))
				{
					 if(this.canUse)
					 {
						 WitherSkull skull = (WitherSkull) p.launchProjectile(WitherSkull.class);
						 skull.setVelocity(p.getEyeLocation().getDirection().multiply(2));
						 this.canUse = false;
						 
						 Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
					     {
							 public void run() 
							 {
									WitherClass.this.canUse = true;
									p.sendMessage("§7You can use §cSkull §7again!");
									WitherClass.this.canUse = true;
						     }
					     }, 20L);
					 }
					 else
						 p.sendMessage("§7You can not use §cSkull §7yet!");
				}
   }
			public void LeftClick(Player p, Action a, Block b)
			{
				  
			}
 
   public ItemStack Icon()
   {
     ItemStack icon = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
     ItemMeta sm = icon.getItemMeta();
     sm.setDisplayName(ChatColor.DARK_PURPLE + "Wither");
             List<String> lore = new ArrayList<String>();
             lore.add("§aTier: §bIII");
             lore.add("");
             lore.add("§7The Wither class is great for");
             lore.add("§7low damage shots, but high-damage melee.");
             lore.add("§a");
             lore.add("§aFeatures:");
             lore.add("§b- Nether Warts §7(Knockback 1, Sharpness 1, Wither Damage)");
             lore.add("§b- Bow");
             sm.setLore(lore);
     icon.setItemMeta(sm);
     return icon;
   }
 
   public void ThrowPotion(Player attacker, Player attacke)
   {
     attacke.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 2));
   }
 }