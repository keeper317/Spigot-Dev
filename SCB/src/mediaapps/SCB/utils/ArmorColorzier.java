 package mediaapps.SCB.utils;
 
 import org.bukkit.Color;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.LeatherArmorMeta;
 
 public class ArmorColorzier
 {
   public static ItemStack setColor(ItemStack item, int r, int g, int b)
   {
     	LeatherArmorMeta lam = (LeatherArmorMeta)item.getItemMeta();
    	lam.setColor(Color.fromRGB(r, g, b));
    	item.setItemMeta(lam);
    	return item;
   }
}