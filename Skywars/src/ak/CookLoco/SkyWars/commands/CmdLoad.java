package ak.CookLoco.SkyWars.commands;

import java.io.File;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.ArenaManager;

public class CmdLoad
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
    Iterator localIterator;
    if (paramArrayOfString.length != 1)
    {
      paramPlayer.sendMessage("§cUsage: /sw load <world>");
      localObject1 = new StringBuilder();
      for (localIterator = Bukkit.getWorlds().iterator(); localIterator.hasNext();)
      {
        localObject2 = (World)localIterator.next();
        ((StringBuilder)localObject1).append(", ").append(((World)localObject2).getName());
      }
      paramPlayer.sendMessage(String.format("§cWorlds Loaded List: %s", new Object[] { ((StringBuilder)localObject1).toString().replaceFirst(", ", "") }));
      return true;
    }
    Object localObject1 = new File(SkyWars.maps);
    Object localObject2 = paramArrayOfString[0];
    if ((((File)localObject1).exists()) && (((File)localObject1).isDirectory()))
    {
      File[] arrayOfFile;
      int j = (arrayOfFile = ((File)localObject1).listFiles()).length;
      for (int i = 0; i < j; i++)
      {
        localIterator = arrayOfFile[i];
        if ((localIterator.getName().contains((CharSequence)localObject2)) && 
          (localIterator.isDirectory())) {
          try
          {
            ArenaManager.delete(new File(localIterator.getName()));
            ArenaManager.copyFolder(localIterator, new File(localIterator.getName()));
            
            WorldCreator localWorldCreator = new WorldCreator((String)localObject2);
            localWorldCreator.generateStructures(false);
            World localWorld = localWorldCreator.createWorld();
            localWorld.setAutoSave(false);
            localWorld.setKeepSpawnInMemory(false);
            localWorld.setGameRuleValue("doMobSpawning", "false");
            localWorld.setGameRuleValue("doDaylightCycle", "false");
            localWorld.setGameRuleValue("mobGriefing", "false");
            localWorld.setGameRuleValue("commandBlockOutput", "false");
            localWorld.setTime(0L);
            paramPlayer.sendMessage("§a" + (String)localObject2 + " loaded");
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      }
    }
    return true;
  }
  
  public String help(Player paramPlayer)
  {
    String str = "&a/sw load &e<world> &a- &bLoad world";
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


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\CmdLoad.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */