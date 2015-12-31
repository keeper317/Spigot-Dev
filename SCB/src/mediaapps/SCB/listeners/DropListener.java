 package mediaapps.SCB.listeners;
 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.PlayerDropItemEvent;

import mediaapps.SCB.managers.PlayerManager;
 
 public class DropListener
   implements Listener
 {
   @EventHandler
   public void onDrop(PlayerDropItemEvent e)
   {
     Player p = e.getPlayer();
     if (PlayerManager.get().ingame(p)) {
       e.setCancelled(true);
       p.sendMessage("§7[§cSCB§7] §cYou Can't drop items in the game.");
     }
   }
 }