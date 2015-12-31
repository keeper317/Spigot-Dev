 package mediaapps.SCB.listeners;
 
 import net.minecraft.server.v1_7_R4.Block;

import org.bukkit.GameMode;
import org.bukkit.Material;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.block.BlockBreakEvent;
 import org.bukkit.event.block.BlockPlaceEvent;
 import org.bukkit.event.entity.EntityDamageEvent;
 import org.bukkit.event.player.PlayerQuitEvent;

import mediaapps.SCB.managers.Game;
import mediaapps.SCB.managers.PlayerManager;
 
 public class ProtectionsListener
   implements Listener
 {
   @EventHandler
   public void onDisconnect(PlayerQuitEvent e)
   {
     if (PlayerManager.get().ingame(e.getPlayer())) {
       if (Game.get().getArena(e.getPlayer()) != null) {
         String arena = Game.get().getArena(e.getPlayer());
         if ((Game.get().getPlyNum(arena) != null) && 
           (Game.get().getPlyNum(arena).intValue() - 1 <= 1)) {
           Game.get().broadcastArena(arena, "Reseting Arena Due to Lack Of Players");
           Game.get().arenaReset(Game.get().getArena(e.getPlayer()));
         }
       }
 
       Game.get().playerLeave(e.getPlayer());
     }
   }
 
   @EventHandler
   public void onPvp(EntityDamageEvent e) { if ((e.getEntity() instanceof Player)) {
       Player p = (Player)e.getEntity();
       if (PlayerManager.get().ingame(p)) {
         String arena = Game.get().getArena(p);
         if (!Game.get().getAIngame(arena))
           e.setCancelled(true);
       }
     } }
 
   @EventHandler
   public void onBlockBreak(BlockBreakEvent e)
   {
     if (PlayerManager.get().ingame(e.getPlayer()))
       e.setCancelled(true);
   }
 
   @EventHandler
   public void onBlockPlace(BlockPlaceEvent e) {
     Player p = e.getPlayer();
			 Block b = (Block) e.getBlock();
			 if(p.getGameMode() != GameMode.CREATIVE)
       e.setCancelled(true);
			 if(b.equals(Material.FIRE))
				 e.setCancelled(true);
   }
 }