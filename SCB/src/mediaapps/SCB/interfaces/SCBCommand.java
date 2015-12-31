package mediaapps.SCB.interfaces;

import org.bukkit.entity.Player;

public abstract interface SCBCommand
{
  public abstract void onCommand(Player paramPlayer, String[] paramArrayOfString);

  public abstract String help(Player paramPlayer);

  public abstract String permission(Player paramPlayer);
}