package ak.CookLoco.SkyWars.menus;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class TrackerMenu
  extends InventoryMenu
{
  public TrackerMenu()
  {
    this.name = SkyWars.getMessage("tracker.menu.title");
    this.size = 27;
    this.inv = Bukkit.createInventory(null, this.size, this.name);
  }
  
  public void onItemClick(Player paramPlayer, ItemStack paramItemStack)
  {
    if (paramItemStack == null) {
      return;
    }
    if (paramItemStack.getType() == Material.SKULL_ITEM)
    {
      Player localPlayer = Bukkit.getPlayer(ChatColor.stripColor(paramItemStack.getItemMeta().getDisplayName()));
      paramPlayer.teleport(localPlayer.getLocation());
      return;
    }
  }
  
  public Inventory getInventory(Arena paramArena)
  {
    for (SkyPlayer localSkyPlayer : paramArena.getAlivePlayer())
    {
      ItemBuilder localItemBuilder = new ItemBuilder(Material.SKULL_ITEM, (short)3).setTitle("§a" + localSkyPlayer.getPlayer().getName());
      SkullMeta localSkullMeta = (SkullMeta)Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
      localSkullMeta.setOwner(localSkyPlayer.getPlayer().getName());
      localItemBuilder.build().setItemMeta(localSkullMeta);
      addItem(localItemBuilder);
    }
    return this.inv;
  }
  
  public Inventory getInventory()
  {
    if (SkyWars.isBungeeMode())
    {
      Arena localArena = ak.CookLoco.SkyWars.arena.ArenaManager.getGames()[0];
      for (SkyPlayer localSkyPlayer : localArena.getAlivePlayer())
      {
        ItemBuilder localItemBuilder = new ItemBuilder(Material.SKULL_ITEM, (short)3).setTitle("§a" + localSkyPlayer.getPlayer().getName());
        SkullMeta localSkullMeta = (SkullMeta)Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        localSkullMeta.setOwner(localSkyPlayer.getPlayer().getName());
        localItemBuilder.build().setItemMeta(localSkullMeta);
        addItem(localItemBuilder);
      }
    }
    return this.inv;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\menus\TrackerMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */