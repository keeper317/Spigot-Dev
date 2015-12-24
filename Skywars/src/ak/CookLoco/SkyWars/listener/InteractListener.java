package ak.CookLoco.SkyWars.listener;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.kit.Kit;
import ak.CookLoco.SkyWars.kit.KitManager;
import ak.CookLoco.SkyWars.menus.InventoryMenu;
import ak.CookLoco.SkyWars.menus.InventoryMenus;
import ak.CookLoco.SkyWars.menus.KitsMenu;
import ak.CookLoco.SkyWars.menus.SettingsMenu;
import ak.CookLoco.SkyWars.menus.TrackerMenu;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.BungeeUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InteractListener
  implements Listener
{
  @EventHandler
  public void onInteract(PlayerInteractEvent paramPlayerInteractEvent)
  {
    Player localPlayer = paramPlayerInteractEvent.getPlayer();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    Object localObject;
    if (((paramPlayerInteractEvent.getAction() == Action.RIGHT_CLICK_AIR) || (paramPlayerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (localSkyPlayer.isInArena()))
    {
      localObject = localSkyPlayer.getArena();
      Material localMaterial = localPlayer.getItemInHand().getType();
      if (((((Arena)localObject).isWaiting()) || (((Arena)localObject).isStarting())) && (!localSkyPlayer.isSpectating()))
      {
        if (localMaterial == Material.EMPTY_MAP)
        {
          localPlayer.openInventory(((Arena)localObject).getVoteInventory());
          paramPlayerInteractEvent.setCancelled(true);
        }
        if (localMaterial == Material.PAPER)
        {
          int i = 0;
          Kit[] arrayOfKit;
          int k = (arrayOfKit = KitManager.getKits()).length;
          for (int j = 0; j < k; j++)
          {
            Kit localKit = arrayOfKit[j];
            i++;
          }
          if (i <= 0)
          {
            localPlayer.sendMessage(SkyWars.getMessage("kits.none"));
            return;
          }
          localPlayer.openInventory(new KitsMenu().getInventory(localSkyPlayer));
          paramPlayerInteractEvent.setCancelled(true);
        }
        if (localMaterial == Material.DIAMOND)
        {
          localPlayer.openInventory(SkyWars.settings_menu.getInventory());
          paramPlayerInteractEvent.setCancelled(true);
        }
      }
      if (localSkyPlayer.isSpectating())
      {
        if (localMaterial == Material.COMPASS) {
          localPlayer.openInventory(new TrackerMenu().getInventory((Arena)localObject));
        }
        if (localMaterial == Material.NETHER_STAR) {
          if (SkyWars.isBungeeMode())
          {
            if (localSkyPlayer.isInArena())
            {
              localSkyPlayer.resetVotes();
              ((Arena)localObject).removePlayer(localSkyPlayer, true);
            }
            BungeeUtils.teleToServer(localPlayer, SkyWars.getMessage("player.teleport.lobby"), SkyWars.getPlugin().getConfig().getString("lobby_server"));
          }
          else if (localSkyPlayer.isInArena())
          {
            localSkyPlayer.resetVotes();
            ((Arena)localObject).removePlayer(localSkyPlayer, true);
            SkyWars.goToSpawn(localSkyPlayer);
            localSkyPlayer.sendMessage(SkyWars.getMessage("player.teleport.lobby"));
            localSkyPlayer.updateScoreboard();
          }
        }
        paramPlayerInteractEvent.setCancelled(true);
      }
    }
    if ((paramPlayerInteractEvent.getAction() == Action.PHYSICAL) && 
      (localSkyPlayer.isInArena()) && 
      (localSkyPlayer.isSpectating()))
    {
      localObject = paramPlayerInteractEvent.getClickedBlock().getType();
      if ((localObject == Material.SOIL) || (localObject == Material.WOOD_PLATE) || (localObject == Material.STONE_PLATE) || (localObject == Material.IRON_PLATE) || (localObject == Material.GOLD_PLATE)) {
        paramPlayerInteractEvent.setCancelled(true);
      }
    }
  }
  
  @EventHandler
  public void onInventoryClick(InventoryClickEvent paramInventoryClickEvent)
  {
    Player localPlayer = (Player)paramInventoryClickEvent.getWhoClicked();
    String str = paramInventoryClickEvent.getInventory().getTitle();
    InventoryMenus[] arrayOfInventoryMenus;
    int j = (arrayOfInventoryMenus = InventoryMenus.values()).length;
    for (int i = 0; i < j; i++)
    {
      InventoryMenus localInventoryMenus = arrayOfInventoryMenus[i];
      if ((localInventoryMenus.getInventoryMenu().getInventory().getName().equalsIgnoreCase(str)) && (paramInventoryClickEvent.getCurrentItem() != null))
      {
        paramInventoryClickEvent.setCancelled(true);
        paramInventoryClickEvent.getWhoClicked().closeInventory();
        localInventoryMenus.getInventoryMenu().onItemClick(localPlayer, paramInventoryClickEvent.getCurrentItem());
      }
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\listener\InteractListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */