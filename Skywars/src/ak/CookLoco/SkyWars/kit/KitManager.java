package ak.CookLoco.SkyWars.kit;

import ak.CookLoco.SkyWars.SkyWars;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;

public class KitManager
{
  public static HashMap<String, Kit> kits = new HashMap();
  
  public static void initKits()
  {
    File localFile1 = new File(SkyWars.getPlugin().getDataFolder(), SkyWars.kits + File.separator);
    if ((localFile1.exists()) && (localFile1.isDirectory()))
    {
      File[] arrayOfFile;
      int j = (arrayOfFile = localFile1.listFiles()).length;
      for (int i = 0; i < j; i++)
      {
        File localFile2 = arrayOfFile[i];
        String str = localFile2.getName().replace(".yml", "");
        new Kit(str);
      }
    }
  }
  
  public static Kit[] getKits()
  {
    return (Kit[])kits.values().toArray(new Kit[kits.values().size()]);
  }
  
  public static Kit getKit(String paramString)
  {
    return (Kit)kits.get(paramString);
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\kit\KitManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */