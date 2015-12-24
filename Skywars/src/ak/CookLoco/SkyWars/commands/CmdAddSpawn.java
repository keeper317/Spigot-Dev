package ak.CookLoco.SkyWars.commands;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.utils.LocationUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CmdAddSpawn
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
    if (paramArrayOfString.length < 0)
    {
      paramPlayer.sendMessage("§cFew arguments!");
      return true;
    }
    String str = paramPlayer.getWorld().getName();
    File localFile = new File(SkyWars.getPlugin().getDataFolder(), SkyWars.arenas + File.separator + str + ".yml");
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
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
    }
    ArrayList localArrayList = new ArrayList();
    if (!localYamlConfiguration.isSet("spawnpoints")) {
      localYamlConfiguration.set("spawnpoints", localArrayList);
    }
    for (Object localObject : localYamlConfiguration.getList("spawnpoints")) {
      localArrayList.add(localObject.toString());
    }
    localArrayList.add(LocationUtil.getString(paramPlayer.getLocation(), true));
    localYamlConfiguration.set("spawnpoints", localArrayList);
    try
    {
      localYamlConfiguration.save(localFile);
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
    }
    paramPlayer.sendMessage("§aSpawn added");
    return true;
  }
  
  public String help(Player paramPlayer)
  {
    String str = "&a/sw &eaddspawn";
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


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\CmdAddSpawn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */