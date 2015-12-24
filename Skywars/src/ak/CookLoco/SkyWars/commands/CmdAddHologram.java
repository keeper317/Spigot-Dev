package ak.CookLoco.SkyWars.commands;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.LocationUtil;
import ak.CookLoco.SkyWars.utils.SkyHologram;

public class CmdAddHologram
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
      ArrayList localArrayList = new ArrayList();
      localArrayList.addAll(SkyWars.score.getStringList("hologram.locations"));
      localArrayList.add(LocationUtil.getString(paramPlayer.getLocation(), true));
      SkyWars.score.set("hologram.locations", localArrayList);
      try
      {
        SkyWars.score.save(SkyWars.score_file);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      Object localObject;
      for (Iterator localIterator = SkyWars.score.getStringList("hologram.locations").iterator(); localIterator.hasNext();)
      {
        localObject = (String)localIterator.next();
        SkyWars.hologram.add(LocationUtil.getLocation((String)localObject));
      }
      for (localIterator = Bukkit.getOnlinePlayers().iterator(); localIterator.hasNext();)
      {
        localObject = (Player)localIterator.next();
        SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer((Player)localObject);
        if (paramPlayer.getWorld() == ((Player)localObject).getWorld()) {
          SkyHologram.createHologram(localSkyPlayer);
        }
      }
      paramPlayer.sendMessage("§aHologram added");
      return true;
    }
    return true;
  }
  
  public String help(Player paramPlayer)
  {
    String str = "&a/sw addhologram &a- &bAdd Lobby Hologram";
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


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\CmdAddHologram.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */