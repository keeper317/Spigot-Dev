package ak.CookLoco.SkyWars.box;

import ak.CookLoco.SkyWars.SkyWars;
import java.util.Collection;
import java.util.HashMap;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class BoxManager
{
  public static HashMap<String, Box> boxes = new HashMap();
  
  public static void initBoxes()
  {
    for (String str : SkyWars.boxes.getConfigurationSection("boxes").getKeys(false))
    {
      Box localBox = new Box(SkyWars.boxes.getString("boxes." + str + ".name"), SkyWars.boxes.getString("boxes." + str + ".desc"));
      localBox.setItem(SkyWars.boxes.getInt("boxes." + str + ".item"));
      localBox.setData(SkyWars.boxes.getInt("boxes." + str + ".data"));
      localBox.setSlot(SkyWars.boxes.getInt("boxes." + str + ".slot"));
      localBox.setSection(str);
      boxes.put(str, localBox);
    }
  }
  
  public static Box[] getBoxes()
  {
    return (Box[])boxes.values().toArray(new Box[boxes.values().size()]);
  }
  
  public static Box getBox(String paramString)
  {
    return (Box)boxes.get(paramString);
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\box\BoxManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */