 package mediaapps.SCB.listeners;
 import org.bukkit.Material;
 import org.bukkit.block.Block;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.block.Action;
 import org.bukkit.event.player.PlayerInteractEvent;
 import org.bukkit.inventory.ItemStack;

import mediaapps.SCB.managers.PlayerManager;
 
 public class SelectionToolListener
   implements Listener
 {
   @EventHandler
   public void onInteraction(PlayerInteractEvent e)
   {
     if ((e.getAction() == Action.LEFT_CLICK_BLOCK) && (e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD) && (e.getPlayer().hasPermission(""))) {
       e.setCancelled(true);
       String p = e.getPlayer().getName();
       PlayerManager.get().setPos1(p, e.getClickedBlock().getLocation());
     }
     if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD) && (e.getPlayer().hasPermission(""))) {
       String p = e.getPlayer().getName();
       PlayerManager.get().setPos2(p, e.getClickedBlock().getLocation());
     }
   }
 }