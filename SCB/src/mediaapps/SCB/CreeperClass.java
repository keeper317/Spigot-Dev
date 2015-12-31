 package mediaapps.SCB;
 
 import java.util.ArrayList;
import java.util.List;

 import org.bukkit.ChatColor;
 import org.bukkit.Color;
 import org.bukkit.Location;
 import org.bukkit.Material;
 import org.bukkit.SkullType;
 import org.bukkit.block.Block;
 import org.bukkit.enchantments.Enchantment;
 import org.bukkit.entity.Entity;
 import org.bukkit.entity.EntityType;
 import org.bukkit.entity.Player;
 import org.bukkit.event.block.Action;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.potion.PotionType;

import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.managers.ClassMakerManager;
 
 
 
 public class CreeperClass
   implements ClassInterface
 {
   public String id()
   {
     return "creeper";
   }
 
   public String DisplayName()
   {
     return "§7[§aCreeper§7] ";
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
 
   public void Spawn(Player p)
   {
     ItemStack gpow = new ItemStack(Material.SULPHUR, 1);
     gpow.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
     p.getInventory().addItem(new ItemStack[] { gpow });
     p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.TNT, 16) });
 
     ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.CREEPER.ordinal());
     p.getInventory().setHelmet(i);
 
     ClassMakerManager.get().givePotion(p, PotionType.INSTANT_DAMAGE, 1, 128, true);
     ClassMakerManager.get().setColorArmor(Color.AQUA, p);
     p.updateInventory();
   }
 
   public void Death(Player p)
   {
     Location loc = p.getLocation();
     loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 1.0F, false, false);
   }
 
   public void RightClick(Player p, Action a, Block b)
   {
     if ((a == Action.RIGHT_CLICK_BLOCK) && (p.getItemInHand().getType() == Material.TNT)) {
       Location Loc = new Location(b.getWorld(), b.getLocation().getX(), b.getLocation().getY() + 1.0D, b.getLocation().getZ());
       b.getWorld().spawnEntity(Loc, EntityType.PRIMED_TNT);
       p.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.TNT, 1) });
       p.updateInventory();
     }
   }
		  public void LeftClick(Player p, Action a, Block b)
		  {
			  
		  }
 
   public void ArrowHit(Player p, Entity e)
   {
   }
 
   public ItemStack Icon()
   {
     ItemStack icon = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.CREEPER.ordinal());
     ItemMeta im = icon.getItemMeta();
     im.setDisplayName(ChatColor.GREEN + "Creeper");
             List<String> lore = new ArrayList<String>();
             lore.add("§aTier: §bII");
             lore.add("");
             lore.add("§7The Creeper class is great for");
             lore.add("§7close combat, and explosive power.");
             lore.add("§a");
             lore.add("§aFeatures:");
             lore.add("§b- TNT §7(x16)");
             lore.add("§b- Instant Damage Potion §7(x128)");
             im.setLore(lore);
     icon.setItemMeta(im);
     return icon;
   }
 
   public void ThrowPotion(Player attacker, Player attacke)
   {
   }
 }
