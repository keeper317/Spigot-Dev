package ak.CookLoco.SkyWars.utils.economy;

import ak.CookLoco.SkyWars.SkyWars;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;

public class VaultUtils
{
  public static Economy economy = null;
  
  public static int getCoins(Player paramPlayer)
  {
    return (int)economy.getBalance(paramPlayer);
  }
  
  public static void addCoins(Player paramPlayer, int paramInt)
  {
    economy.bankDeposit(paramPlayer.getName(), paramInt);
  }
  
  public static void removeCoins(Player paramPlayer, int paramInt)
  {
    economy.bankWithdraw(paramPlayer.getName(), paramInt);
  }
  
  public static boolean setupEconomy()
  {
    RegisteredServiceProvider localRegisteredServiceProvider = SkyWars.getPlugin().getServer().getServicesManager().getRegistration(Economy.class);
    if (localRegisteredServiceProvider != null) {
      economy = (Economy)localRegisteredServiceProvider.getProvider();
    }
    return economy != null;
  }
}