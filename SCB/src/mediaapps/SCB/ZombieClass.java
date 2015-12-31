 package mediaapps.SCB;
 
 import java.util.ArrayList;
import java.util.List;

 import org.bukkit.ChatColor;
 import org.bukkit.Color;
 import org.bukkit.Material;
 import org.bukkit.SkullType;
 import org.bukkit.block.Block;
 import org.bukkit.enchantments.Enchantment;
 import org.bukkit.entity.Entity;
 import org.bukkit.entity.Player;
 import org.bukkit.event.block.Action;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.managers.ClassMakerManager; 
         
 public class ZombieClass
   implements ClassInterface
 {
   Plugin plugin = SCB.getInstance();
 
   public String id() {
     return "zombie";
   }
 
   public String DisplayName()
   {
     return "§7[§2Zombie§7] ";
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
     ItemStack shovel = new ItemStack(Material.IRON_SPADE, 1);
     shovel.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
     shovel.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
     p.getInventory().addItem(new ItemStack[] { shovel });
 
     ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.ZOMBIE.ordinal());
     p.getInventory().setHelmet(i);
 
     ClassMakerManager.get().setColorArmor(Color.AQUA, p);
   }
 
   public void Death(Player p)
   {
   }
 
   public void RightClick(Player p, Action a, Block b)
   {
				List<Entity> e = p.getNearbyEntities(5.0, 5.0, 5.0);
				for(Entity temp : e)
				{
					if(temp instanceof Player)
					{
						Player v = ((Player) temp).getPlayer();
						v.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 5));
					}
				}
   }
			public void LeftClick(Player p, Action a, Block b)
			{
				  
			}
 
   public ItemStack Icon()
   {
     ItemStack icon = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.ZOMBIE.ordinal());
     ItemMeta im = icon.getItemMeta();
     im.setDisplayName(ChatColor.DARK_GREEN + "Zombie");
             List<String> lore = new ArrayList<String>();
             lore.add("§aTier: §bII");
             lore.add("");
             lore.add("§7The Zombie class is great for");
             lore.add("§7heavy melee attacks.");
             lore.add("§a");
             lore.add("§aFeatures:");
             lore.add("§b- Iron Shovels §7(Knockback 1, Sharpness 2)");
             im.setLore(lore);
     icon.setItemMeta(im);
     return icon;
   }
 
   public void ThrowPotion(Player attacker, Player attacke)
   {
   }
 }
