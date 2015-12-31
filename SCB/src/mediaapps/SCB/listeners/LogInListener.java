 package mediaapps.SCB.listeners;
import mediaapps.SCB.SCB;
import mediaapps.SCB.managers.Arena;
import mediaapps.SCB.managers.PlayerManager;

 import org.bukkit.ChatColor;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.PlayerJoinEvent;
 import org.bukkit.plugin.Plugin;
 
 public class LogInListener
   implements Listener
 {
   Plugin plugin = SCB.getInstance();
 
   @EventHandler
   public void onLogIn(PlayerJoinEvent e) { Player p = e.getPlayer();
     PlayerManager.get().newPlayer(p);
     Arena.get().scoreLobbyUpdate(p);
   }
 }