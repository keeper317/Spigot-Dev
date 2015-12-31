 package mediaapps.SCB;
 
 import java.util.ArrayList;
import java.util.List;

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
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.managers.ClassMakerManager;
 
 
 
 public class EndermanClass
   implements ClassInterface
 {
			Plugin plugin = SCB.getInstance();
	public ItemStack i = new ItemStack(Material.ENDER_STONE);
   public String id()
   {
     return "enderman";
   }
 
   public String DisplayName()
   {
     return "§7[§dEnderman§7] §7";
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
     ClassMakerManager.get().getPlayerHead("MHF_Enderman");
     ClassMakerManager.get().setColorArmor(Color.PURPLE, p);
     i.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
     i.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
			 ItemMeta meta = i.getItemMeta();
			 meta.setDisplayName("§6§lRIGHT_CLICK §e§l> §a§lSELECT §c§l| §6§lCLICK§e§l > §a§lTHROW");
			 i.setItemMeta(meta);
     p.getInventory().addItem(new ItemStack[] { i });
 
     ItemStack i2 = new ItemStack(Material.ENDER_PEARL, 64);
     p.getInventory().addItem(new ItemStack[] { i2 });
   }
 
   public void Death(Player p)
   {
   }
 
   public void RightClick(Player p, Action a, Block b)
   {
				if(p.getItemInHand().equals(i))
				{
					Material mat = b.getType();
					ItemStack is = new ItemStack(mat, 1, (short) b.getData());
					p.getInventory().setItem(4, is);
					p.updateInventory();
				}
   }
			public void LeftClick(Player p, Action a, Block b)
			{
				  if(p.getItemInHand().equals(i) && !p.getInventory().getItem(4).equals(null))
				  {
					  Item item = p.getWorld().dropItem(p.getEyeLocation().add(p.getLocation().getDirection()), p.getInventory().getItem(4));
					  item.setMetadata("Enderman", new FixedMetadataValue(this.plugin, "Ender"));
						 item.setVelocity(p.getLocation().getDirection().multiply(2));
						 item.setPickupDelay(2);
						 item.setTicksLived(5900);
					  p.getInventory().setItem(4, new ItemStack(Material.AIR, 1));
					  p.updateInventory();
				  }
			}
 
   public ItemStack Icon()
   {
     ItemStack icon = new ItemStack(Material.ENDER_PEARL, 1);
     ItemMeta sm = icon.getItemMeta();
     sm.setDisplayName("§dEnderman");
             List<String> lore = new ArrayList<String>();
             lore.add("§aTier: §bI");
             lore.add("");
             lore.add("§7The enderman class is the most");
             lore.add("§7basic class you can have.");
             lore.add("§a");
             lore.add("§aFeatures:");
             lore.add("§b- Ender Stone §7(Sharpness 2, Knockback 1)");
             lore.add("§6§lRIGHT CLICK§e§l >> §a§lSELECT");
             lore.add("§6§lLEFT CLICK §e§l >> §a§lTHROW");
             lore.add("§b- Ender Pearl §7(x64)");
             sm.setLore(lore);
     icon.setItemMeta(sm);
     return icon;
   }
 
   public void ThrowPotion(Player attacker, Player attacke)
   {
   }
 }

