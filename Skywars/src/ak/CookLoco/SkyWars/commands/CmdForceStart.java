package ak.CookLoco.SkyWars.commands;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import org.bukkit.entity.Player;

public class CmdForceStart
  implements BaseCommand
{
  public boolean onCommand(Player paramPlayer, String[] paramArrayOfString)
  {
    if (!(paramPlayer instanceof Player))
    {
      paramPlayer.sendMessage("You are not a player!");
      return true;
    }
    if (!paramPlayer.hasPermission(getPermission()))
    {
      paramPlayer.sendMessage("§cYou do not have permission!");
      return true;
    }
    if (paramArrayOfString.length < 1)
    {
      SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(paramPlayer);
      if (localSkyPlayer.isInArena())
      {
        Arena localArena = localSkyPlayer.getArena();
        localArena.setForceStart();
        localArena.broadcast("The game has been forced to start");
        return true;
      }
    }
    return true;
  }
  
  public String help(Player paramPlayer)
  {
    String str = "&a/sw &eforcestart &a- &bForce to start game";
    if (paramPlayer.hasPermission(getPermission())) {
      return str;
    }
    return "";
  }
  
  public String getPermission()
  {
    return "skywars.admin";
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\CmdForceStart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */