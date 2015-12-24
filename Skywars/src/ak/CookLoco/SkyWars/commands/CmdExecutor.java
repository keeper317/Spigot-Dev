package ak.CookLoco.SkyWars.commands;

import ak.CookLoco.SkyWars.SkyWars;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdExecutor
  implements CommandExecutor
{
  private HashMap<String, BaseCommand> commands = new HashMap();
  
  public CmdExecutor()
  {
    loadCommands();
  }
  
  private void loadCommands()
  {
    if (SkyWars.isEditMode())
    {
      this.commands.put("create", new CmdCreate());
      this.commands.put("addspawn", new CmdAddSpawn());
      this.commands.put("tp", new CmdTp());
      this.commands.put("load", new CmdLoad());
      this.commands.put("set", new CmdSet());
      this.commands.put("setspect", new CmdSetSpect());
      if (!SkyWars.isBungeeMode())
      {
        if (SkyWars.holo) {
          this.commands.put("addhologram", new CmdAddHologram());
        }
        this.commands.put("spawn", new CmdSpawn());
      }
    }
    else
    {
      this.commands.put("forcestart", new CmdForceStart());
    }
  }
  
  public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
  {
    Player localPlayer = null;
    if ((paramCommandSender instanceof Player))
    {
      localPlayer = (Player)paramCommandSender;
    }
    else
    {
      paramCommandSender.sendMessage("You need to be a player !");
      return true;
    }
    if (paramCommand.getName().equalsIgnoreCase("sw"))
    {
      if ((paramArrayOfString == null) || (paramArrayOfString.length < 1))
      {
        help(localPlayer);
        return true;
      }
      if (paramArrayOfString[0].equalsIgnoreCase("help"))
      {
        help(localPlayer);
        return true;
      }
      String str = paramArrayOfString[0];
      Vector localVector = new Vector();
      localVector.addAll(Arrays.asList(paramArrayOfString));
      localVector.remove(0);
      paramArrayOfString = (String[])localVector.toArray(new String[0]);
      if (!this.commands.containsKey(str))
      {
        localPlayer.sendMessage("This command doesnt exist");
        return true;
      }
      try
      {
        ((BaseCommand)this.commands.get(str)).onCommand(localPlayer, paramArrayOfString);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        localPlayer.sendMessage("An error occured while executing the command. Check the console");
        localPlayer.sendMessage("Type /sw help for help");
      }
      return true;
    }
    return true;
  }
  
  private void help(Player paramPlayer)
  {
    String str = "§c[§eSkyWars§c]";
    paramPlayer.sendMessage(str);
    for (BaseCommand localBaseCommand : this.commands.values()) {
      if (!localBaseCommand.help(paramPlayer).isEmpty()) {
        paramPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', localBaseCommand.help(paramPlayer)));
      }
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\commands\CmdExecutor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */