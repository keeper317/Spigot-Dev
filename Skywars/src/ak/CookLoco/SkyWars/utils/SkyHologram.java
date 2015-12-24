package ak.CookLoco.SkyWars.utils;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.player.SkyPlayer;

public class SkyHologram
{
  public static HashMap<SkyPlayer, Hologram> holos = new HashMap();
  
  public static void createHologram(SkyPlayer paramSkyPlayer)
  {
    if ((SkyWars.holo) && 
      (!SkyWars.getHoloLocations().isEmpty()))
    {
      Player localPlayer = paramSkyPlayer.getPlayer();
      for (Location localLocation : SkyWars.getHoloLocations())
      {
        Hologram localHologram = HologramsAPI.createHologram(SkyWars.getPlugin(), localLocation);
        holos.put(paramSkyPlayer, localHologram);
        for (Iterator localIterator2 = SkyWars.score.getStringList("hologram.lines").iterator(); localIterator2.hasNext();)
        {
          localObject = (String)localIterator2.next();
          localHologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', SkyWars.getLobbyVariables(paramSkyPlayer, (String)localObject)));
        }
        Object localObject = localHologram.getVisibilityManager();
        ((VisibilityManager)localObject).showTo(localPlayer);
        ((VisibilityManager)localObject).setVisibleByDefault(false);
      }
    }
  }
  
  public static void removeHologram(SkyPlayer paramSkyPlayer)
  {
    if (SkyWars.holo) {
      for (Hologram localHologram : HologramsAPI.getHolograms(SkyWars.getPlugin()))
      {
        VisibilityManager localVisibilityManager = localHologram.getVisibilityManager();
        if (localVisibilityManager.isVisibleTo(paramSkyPlayer.getPlayer())) {
          localHologram.delete();
        }
      }
    }
  }
}