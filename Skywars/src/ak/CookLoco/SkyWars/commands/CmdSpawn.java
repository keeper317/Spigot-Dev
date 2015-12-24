package ak.CookLoco.SkyWars.commands;

import org.bukkit.entity.Player;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.utils.LocationUtil;

public class CmdSpawn
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
    if (paramArrayOfString.length == 0)
    {
      SkyWars.getPlugin().getConfig().set("spawn", LocationUtil.getString(paramPlayer.getLocation(), true));
      SkyWars.getPlugin().saveConfig();
      SkyWars.spawn = paramPlayer.getLocation();
      paramPlayer.sendMessage("§aSpawn set");
      return true;
    }
    return true;
  }
  
  public String help(Player paramPlayer)
  {
    String str = "&a/sw spawn &a- &bSet lobby spawn";
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


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\CmdSpawn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */