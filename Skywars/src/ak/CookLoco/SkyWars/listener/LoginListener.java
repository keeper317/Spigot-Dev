package ak.CookLoco.SkyWars.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.arena.ArenaManager;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.SkyHologram;

public class LoginListener
  implements Listener
{
  @EventHandler
  public void onPlayerLogin(PlayerLoginEvent paramPlayerLoginEvent)
  {
    Player localPlayer = paramPlayerLoginEvent.getPlayer();
    Object localObject;
    if (SkyWars.isBungeeMode())
    {
      Arena[] arrayOfArena;
      int j = (arrayOfArena = ArenaManager.getGames()).length;
      for (int i = 0; i < j; i++)
      {
        localObject = arrayOfArena[i];
        if (localObject == null)
        {
          paramPlayerLoginEvent.allow();
        }
        else
        {
          if (((Arena)localObject).isInGame())
          {
            paramPlayerLoginEvent.setKickMessage(SkyWars.getMessage("game.ingame.message"));
            paramPlayerLoginEvent.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            return;
          }
          if (((Arena)localObject).getAlivePlayers() >= ((Arena)localObject).getMaxPlayers())
          {
            paramPlayerLoginEvent.setKickMessage(SkyWars.getMessage("game.full.message"));
            paramPlayerLoginEvent.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            return;
          }
          if (((Arena)localObject).isLoading())
          {
            paramPlayerLoginEvent.setKickMessage(SkyWars.getMessage("game.loading"));
            paramPlayerLoginEvent.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            return;
          }
        }
      }
    }
    if (!SkyWars.added.contains(localPlayer.getName()))
    {
      localObject = new SkyPlayer(localPlayer);
      SkyWars.added.add(localPlayer.getName());
      SkyWars.skyplayer.put(localPlayer.getName(), localObject);
    }
    else
    {
      localObject = SkyWars.getSkyPlayer(localPlayer);
      ((SkyPlayer)localObject).setPlayer(localPlayer);
    }
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent paramPlayerJoinEvent)
  {
    paramPlayerJoinEvent.setJoinMessage(null);
    Player localPlayer = paramPlayerJoinEvent.getPlayer();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    localSkyPlayer.reset();
    if (SkyWars.isBungeeMode())
    {
      Arena[] arrayOfArena;
      int j = (arrayOfArena = ArenaManager.getGames()).length;
      for (int i = 0; i < j; i++)
      {
        Arena localArena = arrayOfArena[i];
        if (localArena == null) {
          SkyWars.goToSpawn(localSkyPlayer);
        } else {
          localArena.addPlayer(localSkyPlayer, true);
        }
      }
    }
    else
    {
      SkyWars.goToSpawn(localSkyPlayer);
      localSkyPlayer.updateScoreboard();
    }
    if ((SkyWars.checkUpdate()) && (localPlayer.hasPermission("skywars.admin"))) {
      localPlayer.sendMessage("§8[§7SkyWars§8] §aIt found a new update!");
    }
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerLeave(PlayerQuitEvent paramPlayerQuitEvent)
  {
    paramPlayerQuitEvent.setQuitMessage(null);
    Player localPlayer = paramPlayerQuitEvent.getPlayer();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    localSkyPlayer.resetVotes();
    if (localSkyPlayer.isInArena())
    {
      Arena localArena = localSkyPlayer.getArena();
      localSkyPlayer.setSpectating(false);
      localSkyPlayer.resetVotes();
      localArena.removePlayer(localSkyPlayer, true);
      localSkyPlayer.unload();
    }
    SkyHologram.removeHologram(localSkyPlayer);
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerKick(PlayerKickEvent paramPlayerKickEvent)
  {
    paramPlayerKickEvent.setLeaveMessage(null);
    Player localPlayer = paramPlayerKickEvent.getPlayer();
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
    localSkyPlayer.resetVotes();
    if (localSkyPlayer.isInArena())
    {
      Arena localArena = localSkyPlayer.getArena();
      localSkyPlayer.setSpectating(false);
      localSkyPlayer.resetVotes();
      localArena.removePlayer(localSkyPlayer, true);
      localSkyPlayer.unload();
    }
    SkyHologram.removeHologram(localSkyPlayer);
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\listener\LoginListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */