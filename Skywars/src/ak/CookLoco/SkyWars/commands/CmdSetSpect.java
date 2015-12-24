package ak.CookLoco.SkyWars.commands;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.utils.LocationUtil;
import java.io.File;
import java.io.IOException;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CmdSetSpect
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
    if (paramArrayOfString.length < 0)
    {
      paramPlayer.sendMessage("§cFew arguments!");
      return true;
    }
    String str = paramPlayer.getWorld().getName();
    File localFile = new File(SkyWars.getPlugin().getDataFolder(), SkyWars.arenas + File.separator + str + ".yml");
    YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
    localYamlConfiguration.set("spectator_spawn", LocationUtil.getString(paramPlayer.getLocation(), true));
    try
    {
      localYamlConfiguration.save(localFile);
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    paramPlayer.sendMessage("§aSpectator spawn set");
    return true;
  }
  
  public String help(Player paramPlayer)
  {
    String str = "&a/sw &esetspect &a- To set Spectator spawn";
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


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\CmdSetSpect.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */