 package mediaapps.SCB.commands;
import mediaapps.SCB.interfaces.SCBCommand;
import mediaapps.SCB.managers.Game;
import mediaapps.SCB.managers.PlayerManager;

import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 
 public class LeaveCommand
   implements SCBCommand
 {
   public void onCommand(Player p, String[] args)
   {
     if (PlayerManager.get().ingame(p)) {
       Game.get().playerLeave(p);
       Bukkit.broadcastMessage("§7[§cSCB§7] §c" + p.getDisplayName() + " §7has left the game.");
     }
     else {
       p.sendMessage("§7[§cSCB§7] " + "§cYou are not in an arena.");
     }
   }
 
   public String help(Player p)
   {
     return "leave: Leaves the current arena";
   }
 
   public String permission(Player p)
   {
     return "scb.command.leave";
   }
 }