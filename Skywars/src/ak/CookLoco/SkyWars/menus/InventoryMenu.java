package ak.CookLoco.SkyWars.menus;

import ak.CookLoco.SkyWars.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class InventoryMenu
{
  String name;
  int size;
  public Inventory inv;
  
  public abstract void onItemClick(Player paramPlayer, ItemStack paramItemStack);
  
  public abstract Inventory getInventory();
  
  public void addItem(ItemStack paramItemStack)
  {
    this.inv.addItem(new ItemStack[] { paramItemStack });
  }
  
  public void addItem(ItemBuilder paramItemBuilder)
  {
    addItem(paramItemBuilder.build());
  }
  
  public void setItem(int paramInt, ItemBuilder paramItemBuilder)
  {
    this.inv.setItem(paramInt, paramItemBuilder.build());
  }
  
  public void setItem(ItemStack paramItemStack, int paramInt1, int paramInt2)
  {
    this.inv.setItem(paramInt2 * 9 + paramInt1, paramItemStack);
  }
  
  public void clear()
  {
    this.inv.clear();
  }
  
  public void destroy()
  {
    this.inv.clear();
    this.inv = null;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\menus\InventoryMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */