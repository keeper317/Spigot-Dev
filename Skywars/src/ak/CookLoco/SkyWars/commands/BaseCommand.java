package ak.CookLoco.SkyWars.commands;

import org.bukkit.entity.Player;

public abstract interface BaseCommand
{
  public abstract boolean onCommand(Player paramPlayer, String[] paramArrayOfString);
  
  public abstract String help(Player paramPlayer);
  
  public abstract String getPermission();
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\BaseCommand.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */