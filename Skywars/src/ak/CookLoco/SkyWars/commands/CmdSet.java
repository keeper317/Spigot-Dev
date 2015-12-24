package ak.CookLoco.SkyWars.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import ak.CookLoco.SkyWars.SkyWars;

public class CmdSet
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
      paramPlayer.sendMessage("§cYou do not have permission to use that command!");
      return true;
    }
    if (paramArrayOfString.length < 1)
    {
      paramPlayer.sendMessage("§cFew arguments!");
      return true;
    }
    String str1 = paramPlayer.getWorld().getName();
    File localFile = new File(SkyWars.getPlugin().getDataFolder(), SkyWars.arenas + File.separator + str1 + ".yml");
    YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
    String str2;
    switch ((str2 = paramArrayOfString[0].toLowerCase()).hashCode())
    {
    case 107876: 
      if (str2.equals("max")) {
        break;
      }
      break;
    case 108114: 
      int i;
      if (!str2.equals("min"))
      {
        break label339;
        if (paramArrayOfString.length == 2)
        {
          i = Integer.parseInt(paramArrayOfString[1]);
          localYamlConfiguration.set("max_players", Integer.valueOf(i));
          try
          {
            localYamlConfiguration.save(localFile);
          }
          catch (IOException localIOException1)
          {
            localIOException1.printStackTrace();
          }
          paramPlayer.sendMessage("§aMax players set to " + i + " in " + str1);
        }
      }
      else if (paramArrayOfString.length == 2)
      {
        i = Integer.parseInt(paramArrayOfString[1]);
        localYamlConfiguration.set("min_players", Integer.valueOf(i));
        try
        {
          localYamlConfiguration.save(localFile);
        }
        catch (IOException localIOException2)
        {
          localIOException2.printStackTrace();
        }
        paramPlayer.sendMessage("§aMin players set to " + i + " in " + str1);
      }
      break;
    }
    label339:
    return true;
  }
  
  public String help(Player paramPlayer)
  {
    String str = "&a/sw &aset <max/min> <number> - To set Min/Max players on the map where you are";
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


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\CmdSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */