package ak.CookLoco.SkyWars.utils.economy;

import org.apache.logging.log4j.core.helpers.Loader;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ak.CookLoco.SkyWars.SkyWars;

import com.sun.jna.platform.win32.Advapi32Util.Account;

public class CraftconomyUtils
{
  static Common craftconomy;
  static String currency = SkyWars.getPlugin().getConfig().getString("economy.craftconomy3_currency");
  
  public static void loadCraftconomy()
  {
    Plugin localPlugin = SkyWars.getPlugin().getServer().getPluginManager().getPlugin("Craftconomy3");
    if (localPlugin != null) {
      craftconomy = (Common)((Loader)localPlugin).getCommon();
    }
  }
  
  public static Account getAccount(Player paramPlayer)
  {
    return craftconomy.getAccountManager().getAccount(paramPlayer.getName(), false);
  }
  
  public static int getCoins(Player paramPlayer)
  {
    return (int)getAccount(paramPlayer).getBalance(((World)Bukkit.getWorlds().get(0)).getName(), currency);
  }
  
  public static void addCoins(Player paramPlayer, int paramInt)
  {
    getAccount(paramPlayer).deposit(paramInt, ((World)Bukkit.getWorlds().get(0)).getName(), currency);
  }
  
  public static void removeCoins(Player paramPlayer, int paramInt)
  {
    getAccount(paramPlayer).withdraw(paramInt, ((World)Bukkit.getWorlds().get(0)).getName(), currency);
  }
}