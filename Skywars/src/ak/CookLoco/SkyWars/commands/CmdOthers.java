package ak.CookLoco.SkyWars.commands;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.BungeeUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CmdOthers
  implements CommandExecutor
{
  public CmdOthers(SkyWars paramSkyWars) {}
  
  public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
  {
    Player localPlayer = null;
    if ((paramCommandSender instanceof Player)) {
      localPlayer = (Player)paramCommandSender;
    }
    if ((paramString.equalsIgnoreCase("leave")) || (paramString.equalsIgnoreCase("salir")))
    {
      if (paramArrayOfString.length == 0)
      {
        SkyPlayer localSkyPlayer;
        Arena localArena;
        if (SkyWars.isBungeeMode())
        {
          localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
          localArena = localSkyPlayer.getArena();
          if (localSkyPlayer.isInArena())
          {
            localSkyPlayer.resetVotes();
            localArena.removePlayer(localSkyPlayer, true);
          }
          BungeeUtils.teleToServer(localPlayer, SkyWars.getMessage("player.teleport.lobby"), SkyWars.getPlugin().getConfig().getString("lobby_server"));
        }
        else
        {
          localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
          localArena = localSkyPlayer.getArena();
          if (localSkyPlayer.isInArena())
          {
            localSkyPlayer.resetVotes();
            localArena.removePlayer(localSkyPlayer, true);
            SkyWars.goToSpawn(localSkyPlayer);
            localSkyPlayer.sendMessage(SkyWars.getMessage("player.teleport.lobby"));
            localSkyPlayer.updateScoreboard();
          }
        }
      }
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\CmdOthers.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */