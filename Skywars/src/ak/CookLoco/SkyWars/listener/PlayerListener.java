package ak.CookLoco.SkyWars.listener;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener
  implements Listener
{
  @EventHandler
  public void onBlockBreak(BlockBreakEvent paramBlockBreakEvent)
  {
    Player localPlayer = paramBlockBreakEvent.getPlayer();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    if (localSkyPlayer.isInArena())
    {
      Arena localArena = localSkyPlayer.getArena();
      if (localSkyPlayer.isSpectating()) {
        paramBlockBreakEvent.setCancelled(true);
      }
      if ((localArena.isWaiting()) || (localArena.isStarting()) || (localArena.isEnding())) {
        paramBlockBreakEvent.setCancelled(true);
      }
    }
  }
  
  @EventHandler
  public void onPlaceBlock(BlockPlaceEvent paramBlockPlaceEvent)
  {
    Player localPlayer = paramBlockPlaceEvent.getPlayer();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    if ((localSkyPlayer.isInArena()) && 
      (localSkyPlayer.isSpectating())) {
      paramBlockPlaceEvent.setCancelled(true);
    }
  }
  
  @EventHandler
  public void onHunger(FoodLevelChangeEvent paramFoodLevelChangeEvent)
  {
    Player localPlayer = (Player)paramFoodLevelChangeEvent.getEntity();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    if (localSkyPlayer.isInArena())
    {
      Arena localArena = localSkyPlayer.getArena();
      if ((localArena.isWaiting()) || (localArena.isStarting()) || (localArena.isEnding())) {
        paramFoodLevelChangeEvent.setCancelled(true);
      }
      if (localSkyPlayer.isSpectating()) {
        paramFoodLevelChangeEvent.setCancelled(true);
      }
    }
  }
  
  @EventHandler
  public void onVoid(PlayerMoveEvent paramPlayerMoveEvent)
  {
    Player localPlayer = paramPlayerMoveEvent.getPlayer();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    if (localSkyPlayer.isInArena())
    {
      Arena localArena = localSkyPlayer.getArena();
      if (localSkyPlayer.getPlayer().getWorld() == Bukkit.getWorlds().get(0)) {
        localPlayer.teleport(localArena.getSpawn());
      }
      if ((localPlayer.getPlayer().getLocation().getWorld().equals(localArena.getWorld())) && (localPlayer.getPlayer().getLocation().distanceSquared(localSkyPlayer.getArenaSpawn()) >= 2.0D) && ((localArena.isWaiting()) || (localArena.isStarting()))) {
        localPlayer.getPlayer().teleport(localSkyPlayer.getArenaSpawn());
      }
      if (localArena.isInGame())
      {
        if (localSkyPlayer.getPlayer().getWorld() == Bukkit.getWorlds().get(0)) {
          localPlayer.teleport(localSkyPlayer.getArenaSpawn());
        }
        if ((localPlayer.getLocation().getY() <= 0.0D) && (localSkyPlayer.isSpectating())) {
          localPlayer.teleport(localArena.getSpawn());
        }
        if ((localPlayer.getLocation().getY() <= 0.0D) && (!localPlayer.isDead()))
        {
          localPlayer.setHealth(0.0D);
          localPlayer.teleport(localArena.getSpawn());
        }
      }
    }
  }
  
  @EventHandler
  public void onDrop(PlayerDropItemEvent paramPlayerDropItemEvent)
  {
    Player localPlayer = paramPlayerDropItemEvent.getPlayer();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    if (localSkyPlayer.isInArena())
    {
      Arena localArena = localSkyPlayer.getArena();
      if ((localArena.isWaiting()) || (localArena.isStarting())) {
        paramPlayerDropItemEvent.setCancelled(true);
      }
      if (localSkyPlayer.isSpectating()) {
        paramPlayerDropItemEvent.setCancelled(true);
      }
    }
  }
  
  @EventHandler
  public void onAnimation(BlockDamageEvent paramBlockDamageEvent)
  {
    Player localPlayer = paramBlockDamageEvent.getPlayer();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    if (localSkyPlayer.isInArena())
    {
      Arena localArena = localSkyPlayer.getArena();
      if ((localArena.isWaiting()) || (localArena.isStarting())) {
        paramBlockDamageEvent.setCancelled(true);
      }
    }
  }
  
  @EventHandler
  public void onChat(AsyncPlayerChatEvent paramAsyncPlayerChatEvent)
  {
    Player localPlayer = paramAsyncPlayerChatEvent.getPlayer();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    if (localSkyPlayer.isInArena())
    {
      Arena localArena = localSkyPlayer.getArena();
      if ((localArena.isWaiting()) || (localArena.isStarting()))
      {
        paramAsyncPlayerChatEvent.setCancelled(true);
        localSkyPlayer.sendMessage(SkyWars.getMessage("game.player.talk"));
      }
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\listener\PlayerListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */