package ak.CookLoco.SkyWars.utils.economy;

import ak.CookLoco.SkyWars.SkyWars;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class PlayerPointsUtils
{
  public static PlayerPoints playerPoints;
  
  public static boolean hookPlayerPoints()
  {
    Plugin localPlugin = SkyWars.getPlugin().getServer().getPluginManager().getPlugin("PlayerPoints");
    playerPoints = (PlayerPoints)PlayerPoints.class.cast(localPlugin);
    return playerPoints != null;
  }
  
  public PlayerPoints getPCoins()
  {
    return playerPoints;
  }
  
  public static int getCoins(Player paramPlayer)
  {
    return playerPoints.getAPI().look(paramPlayer.getName());
  }
  
  public static void addCoins(Player paramPlayer, int paramInt)
  {
    playerPoints.getAPI().give(paramPlayer.getName(), paramInt);
  }
  
  public static void removeCoins(Player paramPlayer, int paramInt)
  {
    playerPoints.getAPI().take(paramPlayer.getName(), paramInt);
  }
}