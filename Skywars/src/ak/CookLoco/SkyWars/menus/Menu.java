package ak.CookLoco.SkyWars.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.ItemBuilder;

public abstract class Menu
  implements Listener
{
  private Inventory inv;
  
  public Menu(String paramString, int paramInt)
  {
    this.inv = Bukkit.createInventory(null, paramInt * 9 + 9, ChatColor.GOLD + ChatColor.BOLD + paramString);
    SkyWars.getPlugin().getServer().getPluginManager().registerEvents(this, SkyWars.getPlugin());
  }
  
  public Menu addItem(ItemStack paramItemStack)
  {
    this.inv.addItem(new ItemStack[] { paramItemStack });
    return this;
  }
  
  public Menu addItem(ItemBuilder paramItemBuilder)
  {
    return addItem(paramItemBuilder.build());
  }
  
  public Menu setItem(int paramInt, ItemBuilder paramItemBuilder)
  {
    this.inv.setItem(paramInt, paramItemBuilder.build());
    return this;
  }
  
  public Menu setItem(ItemStack paramItemStack, int paramInt1, int paramInt2)
  {
    this.inv.setItem(paramInt2 * 9 + paramInt1, paramItemStack);
    return this;
  }
  
  public Menu clear()
  {
    this.inv.clear();
    return this;
  }
  
  public void destroy()
  {
    HandlerList.unregisterAll(this);
    this.inv.clear();
    this.inv = null;
  }
  
  public Inventory getInventory()
  {
    return this.inv;
  }
  
  @EventHandler
  public void onInventoryClick(InventoryClickEvent paramInventoryClickEvent)
  {
    if ((paramInventoryClickEvent.getInventory().getTitle().equals(this.inv.getTitle())) && (paramInventoryClickEvent.getCurrentItem() != null))
    {
      paramInventoryClickEvent.setCancelled(true);
      paramInventoryClickEvent.getWhoClicked().closeInventory();
      onClick(SkyWars.getSkyPlayer((Player)paramInventoryClickEvent.getWhoClicked()), paramInventoryClickEvent.getCurrentItem());
    }
  }
  
  public abstract void onClick(SkyPlayer paramSkyPlayer, ItemStack paramItemStack);
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\menus\Menu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */