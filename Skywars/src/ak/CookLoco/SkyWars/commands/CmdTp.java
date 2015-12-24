package ak.CookLoco.SkyWars.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class CmdTp
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
    if (paramArrayOfString.length != 1)
    {
      paramPlayer.sendMessage("§cUsage: /sw tp <world>");
      localObject = new StringBuilder();
      for (World localWorld : Bukkit.getWorlds()) {
        ((StringBuilder)localObject).append(", ").append(localWorld.getName());
      }
      paramPlayer.sendMessage(String.format("§cWorlds Loaded List: %s", new Object[] { ((StringBuilder)localObject).toString().replaceFirst(", ", "") }));
      return true;
    }
    Object localObject = Bukkit.getWorld(paramArrayOfString[0]);
    if (localObject == null)
    {
      paramPlayer.sendMessage("§cThat world does not exist!");
      return true;
    }
    paramPlayer.getPlayer().teleport(((World)localObject).getSpawnLocation());
    return true;
  }
  
  public String help(Player paramPlayer)
  {
    String str = "&a/sw tp &e<world> &a- &bTeleport to another world";
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


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\CmdTp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */