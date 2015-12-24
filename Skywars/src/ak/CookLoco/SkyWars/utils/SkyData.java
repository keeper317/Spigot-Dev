package ak.CookLoco.SkyWars.utils;

import java.util.HashMap;
import org.bukkit.Location;

public class SkyData
{
  private HashMap<String, Object> data = new HashMap();
  
  public void addData(String paramString, Object paramObject)
  {
    if (this.data.containsKey(paramString)) {
      this.data.remove(paramString);
    }
    this.data.put(paramString, paramObject);
  }
  
  public void removeData(String paramString)
  {
    if (this.data.containsKey(paramString)) {
      this.data.remove(paramString);
    }
  }
  
  public boolean hasData(String paramString)
  {
    return this.data.containsKey(paramString);
  }
  
  public Object get(String paramString)
  {
    return this.data.get(paramString);
  }
  
  public String getString(String paramString)
  {
    return (String)this.data.get(paramString);
  }
  
  public int getInt(String paramString)
  {
    return ((Integer)this.data.get(paramString)).intValue();
  }
  
  public Location getLocation(String paramString)
  {
    return (Location)this.data.get(paramString);
  }
  
  public boolean getBoolean(String paramString)
  {
    return ((Boolean)this.data.get(paramString)).booleanValue();
  }
  
  public float getFloat(String paramString)
  {
    return ((Float)this.data.get(paramString)).floatValue();
  }
  
  public long getLong(String paramString)
  {
    return ((Long)this.data.get(paramString)).longValue();
  }
  
  public void clearData()
  {
    this.data.clear();
  }
}