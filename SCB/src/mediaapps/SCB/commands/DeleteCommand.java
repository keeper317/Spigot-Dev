 package mediaapps.SCB.commands;
import mediaapps.SCB.interfaces.SCBCommand;
import mediaapps.SCB.managers.Arena;

 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 
 public class DeleteCommand
   implements SCBCommand
 {
   String permission = "scb.command.delete";
 
   public void onCommand(Player p, String[] args)
   {
     if (args[0].length() > 0) {
       String arena = args[0];
       Arena.get().deleteArena(arena);
       p.sendMessage("§7[§cSCB§7] " + "Deleted arena " + arena);
     }
     else {
       p.sendMessage("§7[§cSCB§7] " + "Please Enter in Some Arguements");
     }
   }
 
   public String help(Player p)
   {
     return "delete <arena>: deletes an arena.";
   }
 
   public String permission(Player p)
   {
     return this.permission;
   }
 }