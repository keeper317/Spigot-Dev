package ak.CookLoco.SkyWars.utils.economy;

import org.bukkit.entity.Player;

import ak.CookLoco.SkyWars.SkyWars;

public class SkyEconomy
{
  public static void load()
  {
    if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("Vault")) {
      VaultUtils.setupEconomy();
    } else if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("PlayerPoints")) {
      PlayerPointsUtils.hookPlayerPoints();
    } else if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("Craftconomy3")) {
      CraftconomyUtils.loadCraftconomy();
    }
  }
  
  public static int getCoins(Player paramPlayer)
  {
    if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("Vault")) {
      return VaultUtils.getCoins(paramPlayer);
    }
    if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("PlayerPoints")) {
      return PlayerPointsUtils.getCoins(paramPlayer);
    }
    if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("Craftconomy3")) {
      return CraftconomyUtils.getCoins(paramPlayer);
    }
    return 0;
  }
  
  public static void addCoins(Player paramPlayer, int paramInt)
  {
    if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("Vault")) {
      VaultUtils.addCoins(paramPlayer, paramInt);
    } else if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("PlayerPoints")) {
      PlayerPointsUtils.addCoins(paramPlayer, paramInt);
    } else if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("Craftconomy3")) {
      CraftconomyUtils.addCoins(paramPlayer, paramInt);
    } else if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("Command")) {
      SkyWars.getPlugin().getServer().dispatchCommand(SkyWars.getPlugin().getServer().getConsoleSender(), SkyWars.getPlugin().getConfig().getString("economy.command_add").replace("%player%", paramPlayer.getName()).replace("%amount%", paramInt));
    }
  }
  
  public static void removeCoins(Player paramPlayer, int paramInt)
  {
    if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("Vault")) {
      VaultUtils.removeCoins(paramPlayer, paramInt);
    } else if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("PlayerPoints")) {
      PlayerPointsUtils.removeCoins(paramPlayer, paramInt);
    } else if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("Craftconomy3")) {
      CraftconomyUtils.removeCoins(paramPlayer, paramInt);
    } else if (SkyWars.getPlugin().getConfig().getString("economy.mode").equalsIgnoreCase("Command")) {
      SkyWars.getPlugin().getServer().dispatchCommand(SkyWars.getPlugin().getServer().getConsoleSender(), SkyWars.getPlugin().getConfig().getString("economy.command_remove").replace("%player%", paramPlayer.getName()).replace("%amount%", paramInt));
    }
  }
}