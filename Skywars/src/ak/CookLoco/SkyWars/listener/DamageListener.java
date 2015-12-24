package ak.CookLoco.SkyWars.listener;

import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.player.SkyPlayer;

public class DamageListener
  implements Listener
{
  @EventHandler
  public void onPlayerDamage(EntityDamageEvent paramEntityDamageEvent)
  {
    if ((paramEntityDamageEvent.getEntity() instanceof Player))
    {
      Player localPlayer = (Player)paramEntityDamageEvent.getEntity();
      SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
      if (localSkyPlayer.isInArena())
      {
        Arena localArena = localSkyPlayer.getArena();
        if ((localArena.isWaiting()) || (localArena.isStarting()) || (localArena.isEnding())) {
          paramEntityDamageEvent.setCancelled(true);
        }
        if ((paramEntityDamageEvent.getCause() == EntityDamageEvent.DamageCause.FALL) && 
          (!localArena.isFallDamage()) && (localArena.isInGame())) {
          paramEntityDamageEvent.setCancelled(true);
        }
        if (localSkyPlayer.isSpectating())
        {
          localSkyPlayer.getPlayer().setFireTicks(0);
          paramEntityDamageEvent.setCancelled(true);
        }
      }
    }
  }
  
  @EventHandler
  public void onPlayerDamageByPlayer(EntityDamageByEntityEvent paramEntityDamageByEntityEvent)
  {
    if (((paramEntityDamageByEntityEvent.getEntity() instanceof Player)) && ((paramEntityDamageByEntityEvent.getDamager() instanceof Player)))
    {
      Player localPlayer1 = (Player)paramEntityDamageByEntityEvent.getEntity();
      Player localPlayer2 = (Player)paramEntityDamageByEntityEvent.getDamager();
      SkyPlayer localSkyPlayer1 = SkyWars.getSkyPlayer(localPlayer1);
      SkyPlayer localSkyPlayer2 = SkyWars.getSkyPlayer(localPlayer2);
      if (localSkyPlayer1.isInArena())
      {
        Arena localArena = localSkyPlayer1.getArena();
        if (localSkyPlayer2.isSpectating()) {
          paramEntityDamageByEntityEvent.setCancelled(true);
        }
        if ((localArena.isWaiting()) || (localArena.isStarting()) || (localArena.isEnding())) {
          paramEntityDamageByEntityEvent.setCancelled(true);
        }
      }
      else
      {
        paramEntityDamageByEntityEvent.setCancelled(true);
      }
    }
  }
  
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent paramPlayerDeathEvent)
  {
    final Player localPlayer = paramPlayerDeathEvent.getEntity();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    if (localSkyPlayer.isInArena())
    {
      Arena localArena = localSkyPlayer.getArena();
      paramPlayerDeathEvent.setDeathMessage(null);
      localSkyPlayer.setSpectating(true);
      localSkyPlayer.addDeaths(1);
      localSkyPlayer.getPlayer().setMetadata("upload_stats", new FixedMetadataValue(SkyWars.getPlugin(), Boolean.valueOf(true)));
      Object localObject1;
      if ((paramPlayerDeathEvent.getEntity().getKiller() instanceof Player))
      {
        localObject1 = paramPlayerDeathEvent.getEntity().getKiller();
        localObject2 = SkyWars.getSkyPlayer((Player)localObject1);
        ((SkyPlayer)localObject2).addKills(1);
        ((SkyPlayer)localObject2).getPlayer().setMetadata("upload_stats", new FixedMetadataValue(SkyWars.getPlugin(), Boolean.valueOf(true)));
        localArena.broadcast(String.format(SkyWars.getMessage("game.player.death.player"), new Object[] { localPlayer.getName(), ((Player)localObject1).getName() }));
        ((SkyPlayer)localObject2).addCoins(SkyWars.getPlugin().getConfig().getInt("reward.kill"));
      }
      else
      {
        localArena.broadcast(String.format(SkyWars.getMessage("game.player.death.other"), new Object[] { localPlayer.getName() }));
      }
      localSkyPlayer.sendMessage(SkyWars.getMessage("player.death"));
      localArena.broadcast(String.format(SkyWars.getMessage("game.players.remain"), new Object[] { Integer.valueOf(localArena.getAlivePlayers()) }));
      for (Object localObject2 = localArena.getAlivePlayer().iterator(); ((Iterator)localObject2).hasNext();)
      {
        localObject1 = (SkyPlayer)((Iterator)localObject2).next();
        ((SkyPlayer)localObject1).addCoins(SkyWars.getPlugin().getConfig().getInt("reward.death"));
      }
      new BukkitRunnable()
      {
        public void run()
        {
          localPlayer.spigot().respawn();
        }
      }.runTaskLater(SkyWars.getPlugin(), 10L);
    }
  }
  
  @EventHandler
  public void onPlayerRespawn(PlayerRespawnEvent paramPlayerRespawnEvent)
  {
    Player localPlayer = paramPlayerRespawnEvent.getPlayer();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    if (localSkyPlayer.isInArena())
    {
      Arena localArena = localSkyPlayer.getArena();
      paramPlayerRespawnEvent.setRespawnLocation(localArena.getSpawn());
    }
    else
    {
      paramPlayerRespawnEvent.setRespawnLocation(SkyWars.getSpawn());
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\listener\DamageListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */