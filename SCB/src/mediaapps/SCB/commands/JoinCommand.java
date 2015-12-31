 package mediaapps.SCB.commands;
import mediaapps.SCB.interfaces.SCBCommand;
import mediaapps.SCB.managers.Arena;
import mediaapps.SCB.managers.Game;

import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 
 public class JoinCommand
   implements SCBCommand
 {
   public void onCommand(Player p, String[] args)
   {
     if ((args[0].length() > 0) && (args[0] != null)) {
       String arena = args[0];
       if (Arena.get().getArenaEnabled(arena)) {
         Game.get().joinArena(arena, p);
         Bukkit.broadcastMessage("§7[§cSCB§7] §c"+ p.getDisplayName() + " §7joined the game!");
       }
       else {
         p.sendMessage("§7[§cSCB§7] " + "§cArena " + arena + " Is Not Enabled");
       }
     }
     else {
       p.sendMessage("§7[§cSCB§7] " + "§cPlease Enter In An Arguement");
     }
   }
 
   public String help(Player p)
   {
     return "join <Arena>: Joins an arena.";
   }
 
   public String permission(Player p)
   {
     return "scb.command.join";
   }
 }
