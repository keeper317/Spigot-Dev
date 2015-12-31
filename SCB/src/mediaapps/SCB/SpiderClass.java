 package mediaapps.SCB;
 
 import java.util.ArrayList;
import java.util.List;
 import java.util.Random;

 import org.bukkit.ChatColor;
 import org.bukkit.Color;
 import org.bukkit.GameMode;
 import org.bukkit.Material;
 import org.bukkit.block.Block;
 import org.bukkit.block.BlockFace;
 import org.bukkit.enchantments.Enchantment;
 import org.bukkit.entity.Entity;
 import org.bukkit.entity.Player;
 import org.bukkit.event.block.Action;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.potion.PotionEffect;
 import org.bukkit.potion.PotionEffectType;

 

import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.managers.ClassMakerManager;
import mediaapps.SCB.managers.PlayerManager; 
         
         
 public class SpiderClass
   implements ClassInterface
 {
   public String id()
   {
     return "spider";
   }
 
   public String DisplayName()
   {
     return "§7[§cSpider§7] ";
   }
 
   public boolean Movement(Player p)
   {
     if ((p.getGameMode() != GameMode.CREATIVE) && (PlayerManager.get().ingame(p)) && 
       (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
       p.setAllowFlight(true);
     }
 
     p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, 2));
     return true;
   }
 
   public boolean Jump(Player p)
   {
     return false;
   }
 
   public void Attack(Player attacker, Player attacke)
   {
     Random random = new Random();
     int num = random.nextInt(100) + 1;
     if (num <= 30)
       attacke.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1), true);
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
     p.getInventory().setHelmet(ClassMakerManager.get().getPlayerHead("MHF_Spider"));
     ClassMakerManager.get().setColorArmor(Color.RED, p);
     ItemStack i1 = new ItemStack(Material.SPIDER_EYE, 1);
     i1.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
     i1.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
     p.getInventory().addItem(new ItemStack[] { i1 });
   }
 
   public void Death(Player p)
   {
   }
 
   public void RightClick(Player p, Action a, Block b)
   {
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 3, 5));
   }
			public void LeftClick(Player p, Action a, Block b)
			{
				  
			}
 
   public ItemStack Icon()
   {
     ItemStack icon = new ItemStack(Material.SPIDER_EYE, 1);
     ItemMeta sm = icon.getItemMeta();
     sm.setDisplayName(ChatColor.RED + "§cSpider");
             List<String> lore = new ArrayList<String>();
             lore.add("§aTier: §bII");
             lore.add("");
             lore.add("§7The Spider class is great for");
             lore.add("§7slower, but high-damage kills.");
             lore.add("§a");
             lore.add("§aFeatures:");
             lore.add("§b- Spider Eye §7(Knockback 1, Sharpness 1, Poisoning)");
             sm.setLore(lore);
     icon.setItemMeta(sm);
     return icon;
   
   }
 
   public void ThrowPotion(Player attacker, Player attacke)
   {
   }
 }
