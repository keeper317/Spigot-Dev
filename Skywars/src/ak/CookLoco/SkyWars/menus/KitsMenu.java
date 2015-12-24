package ak.CookLoco.SkyWars.menus;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.kit.Kit;
import ak.CookLoco.SkyWars.kit.KitManager;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class KitsMenu
  extends InventoryMenu
{
  public KitsMenu()
  {
    int i = SkyWars.getPlugin().getConfig().getInt("kitmenu_rows");
    this.name = SkyWars.getMessage("kits.menu.title");
    this.size = (i * 9);
    this.inv = Bukkit.createInventory(null, this.size, this.name);
  }
  
  public void onItemClick(Player paramPlayer, ItemStack paramItemStack)
  {
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(paramPlayer);
    if ((paramItemStack == null) || (paramItemStack.getType() == Material.AIR)) {
      return;
    }
    String str = ChatColor.stripColor(paramItemStack.getItemMeta().getDisplayName().split(" ")[0]);
    if (paramItemStack.getItemMeta().getDisplayName().contains(str))
    {
      Kit localKit = KitManager.getKit(str);
      if (localKit == null)
      {
        SkyWars.log("KitsMenu.onClick - Kit selected is NULL");
        return;
      }
      if (localKit.isFree())
      {
        if ((SkyWars.getPlugin().getConfig().getBoolean("kit_permission")) && 
          (!localSkyPlayer.hasPermissions("skywars.kit." + localKit.getName().toLowerCase())))
        {
          localSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.kit"));
          return;
        }
        localSkyPlayer.setKit(localKit);
        localSkyPlayer.sendMessage(String.format(SkyWars.getMessage("player.select.kit"), new Object[] { localKit.getName().toLowerCase() }));
        return;
      }
      if (!localSkyPlayer.hasKit(localKit))
      {
        if ((SkyWars.getPlugin().getConfig().getBoolean("kit_permission")) && 
          (!localSkyPlayer.hasPermissions("skywars.kit." + localKit.getName().toLowerCase())))
        {
          localSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.kit"));
          return;
        }
        if (localSkyPlayer.getCoins() > localKit.getPrice())
        {
          localSkyPlayer.addKit(localKit);
          localSkyPlayer.removeCoins(localKit.getPrice());
          localSkyPlayer.setKit(localKit);
          localSkyPlayer.sendMessage(String.format(SkyWars.getMessage("player.purchase.kit"), new Object[] { localKit.getName().toLowerCase() }));
          localSkyPlayer.getPlayer().setMetadata("upload_me", new FixedMetadataValue(SkyWars.getPlugin(), Boolean.valueOf(true)));
          return;
        }
        localSkyPlayer.sendMessage(SkyWars.getMessage("player.needmoney.kit"));
        return;
      }
      localSkyPlayer.setKit(localKit);
      localSkyPlayer.sendMessage(String.format(SkyWars.getMessage("player.select.kit"), new Object[] { localKit.getName().toLowerCase() }));
      return;
    }
  }
  
  public Inventory getInventory(SkyPlayer paramSkyPlayer)
  {
    Kit[] arrayOfKit;
    int j = (arrayOfKit = KitManager.getKits()).length;
    for (int i = 0; i < j; i++)
    {
      Kit localKit = arrayOfKit[i];
      ItemBuilder localItemBuilder = localKit.getIcon();
      if (paramSkyPlayer.hasKit(localKit)) {
        localItemBuilder = new ItemBuilder(localKit.getIcon().getType()).setTitle(String.format(SkyWars.getMessage("kit.name.purchased"), new Object[] { localKit.getName() + " " })).setLore(localKit.getContents());
      }
      setItem(localKit.getSlot(), localItemBuilder);
    }
    return this.inv;
  }
  
  public Inventory getInventory()
  {
    Kit[] arrayOfKit;
    int j = (arrayOfKit = KitManager.getKits()).length;
    for (int i = 0; i < j; i++)
    {
      Kit localKit = arrayOfKit[i];
      ItemBuilder localItemBuilder = localKit.getIcon();
      setItem(localKit.getSlot(), localItemBuilder);
    }
    return this.inv;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\menus\KitsMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */