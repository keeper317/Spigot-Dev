 package mediaapps.SCB.commands;
import mediaapps.SCB.interfaces.SCBCommand;
import mediaapps.SCB.managers.Arena;

 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 
 public class EnableCommand
   implements SCBCommand
 {
   String permission = "scb.command.enable";
 
   public void onCommand(Player p, String[] args)
   {
     if (args[0].length() > 0) {
       String arena = args[0];
       Arena.get().enableArena(arena);
       p.sendMessage("§7[§cSCB§7] " + "Enabled Arena " + arena);
     }
     else {
       p.sendMessage("§7[§cSCB§7] " + "Please Enter In An Argument!");
     }
   }
 
   public String help(Player p)
   {
     return "enable <arena>: enables an arena";
   }
 
   public String permission(Player p)
   {
     return this.permission;
   }
 }
