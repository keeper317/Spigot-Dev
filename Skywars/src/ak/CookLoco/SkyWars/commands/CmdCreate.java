package ak.CookLoco.SkyWars.commands;

import ak.CookLoco.SkyWars.SkyWars;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CmdCreate
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
      paramPlayer.sendMessage("§cUsage: /sw create <arena_name>");
      return true;
    }
    String str = paramArrayOfString[0];
    File localFile = new File(SkyWars.getPlugin().getDataFolder(), SkyWars.arenas + File.separator + str + ".yml");
    if (localFile.exists())
    {
      paramPlayer.sendMessage("§cThis arena already exists!");
      return false;
    }
    YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
    if (!localFile.exists()) {
      try
      {
        localFile.createNewFile();
        localYamlConfiguration.set("name", str);
        localYamlConfiguration.set("min_players", Integer.valueOf(2));
        localYamlConfiguration.set("max_players", Integer.valueOf(6));
        localYamlConfiguration.save(localFile);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    paramPlayer.sendMessage("&a" + str + " has been created");
    return true;
  }
  
  public String help(Player paramPlayer)
  {
    String str = "&a/sw create &e<arena_name> &a- &bCreate new arena";
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


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\CmdCreate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */