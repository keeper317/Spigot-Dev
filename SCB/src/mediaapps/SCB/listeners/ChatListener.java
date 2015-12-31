 package mediaapps.SCB.listeners;
import mediaapps.SCB.SCB;
import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.managers.Game;
import mediaapps.SCB.managers.PlayerManager;

 import java.util.Set;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.AsyncPlayerChatEvent;
 import org.bukkit.plugin.Plugin;
 
 public class ChatListener
   implements Listener
 {
   Plugin plugin = SCB.getInstance();
 
  // @EventHandler
   //public void onChat(AsyncPlayerChatEvent e) { Player p = e.getPlayer();
   //  if ((this.plugin.getConfig().getBoolean("ArenaChat")) && (this.plugin.getConfig().get("ArenaChat") != null))
     //{
     //  String arena;
     //  Player player;
     //  if (PlayerManager.get().ingame(p)) {
      //   e.setCancelled(true);
      //   String msg = e.getMessage();
      //   arena = Game.get().getArena(p);
        // Player localPlayer1;
      //   if (PlayerManager.get().getClasse(p) != null) {
      //     String ClassDisplay = PlayerManager.get().getClasse(p).DisplayName();
      //     Player[] arrayOfPlayer2;
      //     int i = (arrayOfPlayer2 = Game.get().getPlys(arena)).length; for (localPlayer1 = 0; localPlayer1 < i; localPlayer1++) { player = arrayOfPlayer2[localPlayer1];
       //      player.sendMessage(ClassDisplay + p.getName() + ": " + msg);
       //    }
      //   }
      //   else
      //   {
      //     Player[] arrayOfPlayer1;
      //     localPlayer1 = (arrayOfPlayer1 = Game.get().getPlys(arena)).length; for (player = 0; player < localPlayer1; player++) { player = arrayOfPlayer1[player];
       //      player.sendMessage(ChatColor.GRAY + "[Default]" + p.getName() + ": " + msg); }
      //   }
     //  }
    //   else
    //   {
     //    e.getRecipients().clear();
     //    player = (player = Bukkit.getOnlinePlayers()).length; for (arena = 0; arena < player; arena++) { Player player = player[arena];
     //      if (!PlayerManager.get().ingame(player))
     //        e.getRecipients().add(player);
     //    }
    //   }
    // }
  // }
 }