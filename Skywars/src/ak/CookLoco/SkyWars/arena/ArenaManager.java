package ak.CookLoco.SkyWars.arena;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.World;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.sign.SignManager;

public class ArenaManager
{
  public static HashMap<String, Arena> games = new HashMap();
  
  public static void initGames()
  {
    File localFile1 = new File(SkyWars.getPlugin().getDataFolder(), SkyWars.arenas);
    if ((localFile1.exists()) && 
      (localFile1.listFiles().length > 0))
    {
      File localFile2;
      Object localObject2;
      Object localObject4;
      Object localObject3;
      if (SkyWars.isBungeeMode())
      {
        localFile2 = null;
        if (SkyWars.isRandomMap())
        {
          int i = new Random().nextInt(localFile1.listFiles().length);
          localObject1 = localFile1.listFiles();
          localFile2 = localObject1[i];
        }
        else
        {
          localFile2 = new File(SkyWars.getPlugin().getDataFolder(), SkyWars.arenas + File.separator + SkyWars.getMapSet() + ".yml");
          if (!localFile2.exists())
          {
            SkyWars.log("ArenaManager.initGames - The map set not exists");
            return;
          }
        }
        String str = localFile2.getName().replace(".yml", "");
        
        Object localObject1 = new File(SkyWars.maps + File.separator + str);
        if (((File)localObject1).isDirectory())
        {
          try
          {
            delete(new File(str));
            copyFolder((File)localObject1, new File(str));
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
          }
          localObject2 = null;
          for (localObject4 = Bukkit.getWorlds().iterator(); ((Iterator)localObject4).hasNext();)
          {
            localObject3 = (World)((Iterator)localObject4).next();
            if (((World)localObject3).getName().equalsIgnoreCase(str)) {
              localObject2 = Bukkit.getWorld(str);
            }
          }
          if (localObject2 != null) {
            Bukkit.unloadWorld((World)localObject2, false);
          }
          new Arena(str);
          SkyWars.log("ArenaManager.initGames - " + str + " has been set as arena in BungeeMode");
        }
      }
      else if (SkyWars.isMultiArenaMode())
      {
        int k = (localObject2 = localFile1.listFiles()).length;
        for (int j = 0; j < k; j++)
        {
          localFile2 = localObject2[j];
          localObject3 = localFile2.getName().replace(".yml", "");
          
          localObject4 = new File(SkyWars.maps + File.separator + (String)localObject3);
          if (((File)localObject4).isDirectory())
          {
            try
            {
              delete(new File((String)localObject3));
              copyFolder((File)localObject4, new File((String)localObject3));
            }
            catch (Exception localException2)
            {
              localException2.printStackTrace();
            }
            World localWorld1 = null;
            for (World localWorld2 : Bukkit.getWorlds()) {
              if (localWorld2.getName().equalsIgnoreCase((String)localObject3)) {
                localWorld1 = Bukkit.getWorld((String)localObject3);
              }
            }
            if (localWorld1 != null) {
              Bukkit.unloadWorld(localWorld1, false);
            }
            new Arena((String)localObject3);
            SkyWars.log("ArenaManager.initGames - " + (String)localObject3 + " has been added as arena in NormalMode");
          }
        }
        SignManager.initSigns();
      }
    }
  }
  
  public static Arena[] getGames()
  {
    return (Arena[])games.values().toArray(new Arena[games.values().size()]);
  }
  
  public static Arena getGame(String paramString)
  {
    return (Arena)games.get(paramString);
  }
  
  public static void addGame(String paramString)
  {
    File localFile = new File(SkyWars.getPlugin().getDataFolder(), SkyWars.arenas + File.separator + paramString + ".yml");
    if (!localFile.exists())
    {
      SkyWars.log("ArenaManager.addGame - The arena not exists");
      return;
    }
    new Arena(paramString);
  }
  
  public static void copyFolder(File paramFile1, File paramFile2)
  {
    Object localObject1;
    int j;
    Object localObject2;
    if (paramFile1.isDirectory())
    {
      if (!paramFile2.exists())
      {
        paramFile2.mkdir();
        SkyWars.log("Directory copied from " + paramFile1 + "  to " + paramFile2);
      }
      localObject1 = paramFile1.list();
      Object localObject3;
      j = (localObject3 = localObject1).length;
      for (int i = 0; i < j; i++)
      {
        localObject2 = localObject3[i];
        
        File localFile1 = new File(paramFile1, (String)localObject2);
        File localFile2 = new File(paramFile2, (String)localObject2);
        
        copyFolder(localFile1, localFile2);
      }
    }
    else
    {
      localObject1 = new FileInputStream(paramFile1);
      localObject2 = new FileOutputStream(paramFile2);
      
      byte[] arrayOfByte = new byte['Ѐ'];
      while ((j = ((InputStream)localObject1).read(arrayOfByte)) > 0) {
        ((OutputStream)localObject2).write(arrayOfByte, 0, j);
      }
      ((InputStream)localObject1).close();
      ((OutputStream)localObject2).close();
      SkyWars.log("File copied from " + paramFile1 + " to " + paramFile2);
    }
  }
  
  public static void delete(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      if (paramFile.list().length == 0)
      {
        paramFile.delete();
        SkyWars.log("Directory is deleted : " + paramFile.getAbsolutePath());
      }
      else
      {
        String[] arrayOfString1 = paramFile.list();
        String[] arrayOfString2;
        int j = (arrayOfString2 = arrayOfString1).length;
        for (int i = 0; i < j; i++)
        {
          String str = arrayOfString2[i];
          
          File localFile = new File(paramFile, str);
          
          delete(localFile);
        }
        if (paramFile.list().length == 0)
        {
          paramFile.delete();
          SkyWars.log("Directory is deleted : " + paramFile.getAbsolutePath());
        }
      }
    }
    else
    {
      paramFile.delete();
      SkyWars.log("File is deleted : " + paramFile.getAbsolutePath());
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\arena\ArenaManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */