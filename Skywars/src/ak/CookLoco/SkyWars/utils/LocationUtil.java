package ak.CookLoco.SkyWars.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtil
{
  public static Location Center(Location paramLocation)
  {
    return new Location(paramLocation.getWorld(), getRelativeCoord(paramLocation.getBlockX()), getRelativeCoord(paramLocation.getBlockY()), getRelativeCoord(paramLocation.getBlockZ()));
  }
  
  private static double getRelativeCoord(int paramInt)
  {
    double d = paramInt;
    d = d < 0.0D ? d + 0.5D : d + 0.5D;
    return d;
  }
  
  public static String getString(Location paramLocation, boolean paramBoolean)
  {
    if (paramBoolean) {
      return paramLocation.getWorld().getName() + "," + Center(paramLocation).getX() + "," + paramLocation.getY() + "," + Center(paramLocation).getZ() + "," + 360 + "," + paramLocation.getYaw();
    }
    return paramLocation.getWorld().getName() + "," + paramLocation.getX() + "," + paramLocation.getY() + "," + paramLocation.getZ() + "," + paramLocation.getPitch() + "," + paramLocation.getYaw();
  }
  
  public static Location getLocation(String paramString)
  {
    String[] arrayOfString = paramString.split(",");
    Location localLocation = new Location(Bukkit.getWorld(arrayOfString[0]), Double.parseDouble(arrayOfString[1]), Double.parseDouble(arrayOfString[2]), Double.parseDouble(arrayOfString[3]));
    localLocation.setPitch(Float.parseFloat(arrayOfString[4]));
    localLocation.setYaw(Float.parseFloat(arrayOfString[5]));
    return localLocation;
  }
}