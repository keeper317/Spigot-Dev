package mediaapps.FlyMe;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class fly
  extends JavaPlugin
{
  public final Logger logger = Logger.getLogger("Minecraft");
  public static fly plugin;
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    Player player = (Player)sender;
    if (commandLabel.equalsIgnoreCase("fly"))
    {
      if (!player.hasPermission("simple.fly")) {
        return false;
      }
      if (!player.getAllowFlight())
      {
        player.setAllowFlight(true);
        player.sendMessage("§7[§cFlight§7] Flight §aenabled");
      }
      else
      {
        player.setAllowFlight(false);
        player.sendMessage("§7[§cFlight§7] Flight §cdisabled");
        player.setFlying(false);
      }
      return false;
    }
    return false;
  }
}