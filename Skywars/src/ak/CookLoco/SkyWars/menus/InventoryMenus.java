package ak.CookLoco.SkyWars.menus;

import org.bukkit.inventory.Inventory;

public enum InventoryMenus
{
  KIT_SHOP(new KitsMenu()),  TRACKER(new TrackerMenu()),  VOTE_CHEST(new VoteChestMenu()),  VOTE_TIME(new VoteTimeMenu());
  
  InventoryMenu im = null;
  Inventory inv = null;
  
  private InventoryMenus(InventoryMenu paramInventoryMenu)
  {
    this.im = paramInventoryMenu;
    this.inv = paramInventoryMenu.getInventory();
  }
  
  public InventoryMenu getInventoryMenu()
  {
    return this.im;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\menus\InventoryMenus.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */