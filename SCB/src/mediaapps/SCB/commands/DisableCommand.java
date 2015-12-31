 package mediaapps.SCB.commands;
import mediaapps.SCB.interfaces.SCBCommand;
import mediaapps.SCB.managers.Arena;

 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 
 public class DisableCommand
   implements SCBCommand
 {
   String permission = "scb.command.disable";
 
   public void onCommand(Player p, String[] args)
   {
     if (args[0].length() > 0) {
       String arena = args[0];
       Arena.get().disableArena(arena);
       p.sendMessage("§7[§cSCB§7] " + "Disabled Arena " + arena);
     }
     else {
       p.sendMessage("§7[§cSCB§7] " + "Please Enter In An Arguement");
     }
   }
 
   public String help(Player p)
   {
     return "disable <arena>: disables an arena";
   }
 
   public String permission(Player p)
   {
     return this.permission;
   }
 }
