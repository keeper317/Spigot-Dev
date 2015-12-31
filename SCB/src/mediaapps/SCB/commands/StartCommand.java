 package mediaapps.SCB.commands;
import mediaapps.SCB.interfaces.SCBCommand;
import mediaapps.SCB.managers.Game;
import mediaapps.SCB.managers.PlayerManager;

 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 
 public class StartCommand
   implements SCBCommand
 {
   String scb = "§7[§cSCB§7] ";
 
   public void onCommand(Player p, String[] args) {
     if (PlayerManager.get().ingame(p)) {
       String arena = Game.get().getArena(p);
       if (!Game.get().getAIngame(arena)) {
         Game.get().forceStart(arena);
         p.sendMessage(this.scb + "You have started Arena " + arena);
         Game.get().broadcastArena(arena, "Force Starting Arena...");
       }
       else {
         p.sendMessage(this.scb + "Arena is ingame.");
       }
     }
     else {
       p.sendMessage(this.scb + "Please be in an arena.");
     }
   }
 
   public String help(Player p)
   {
     return "start: starts the arena you are in.";
   }
 
   public String permission(Player p)
   {
     return "scb.command.start";
   }
 }