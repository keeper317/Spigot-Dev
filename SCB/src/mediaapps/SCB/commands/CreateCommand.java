 package mediaapps.SCB.commands;
import mediaapps.SCB.SCB;
import mediaapps.SCB.interfaces.SCBCommand;
import mediaapps.SCB.managers.Arena;

 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 
 public class CreateCommand
   implements SCBCommand
 {
   String permission = "scb.command.create";
 
   String scb = "§7[§cSCB§7] ";
 
   Plugin plugin = SCB.getInstance();
 
   public void onCommand(Player p, String[] args)
   {
     if (args[0].length() > 0) {
       String arena = args[0];
       Arena.get().createArena(arena, p);
       p.sendMessage(this.scb + "Successfully created arena " + arena);
     }
     else {
       p.sendMessage(this.scb + "Please enter in an arena name.");
     }
   }
 
   public String help(Player p)
   {
     return "create <arena>: creates an arena.";
   }
 
   public String permission(Player p)
   {
     return this.permission;
   }
 }