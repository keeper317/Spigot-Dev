 package mediaapps.SCB.listeners;
import mediaapps.SCB.SCB;
import mediaapps.SCB.managers.Arena;
import mediaapps.SCB.managers.Config;
import mediaapps.SCB.managers.Game;
import mediaapps.SCB.managers.PlayerManager;

 import java.io.File;
 import java.io.IOException;
 import org.bukkit.ChatColor;
 import org.bukkit.Location;
 import org.bukkit.Material;
 import org.bukkit.block.Block;
 import org.bukkit.block.Sign;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.configuration.file.YamlConfiguration;
 import org.bukkit.entity.Player;
 import org.bukkit.event.Event;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.block.Action;
 import org.bukkit.event.block.BlockBreakEvent;
 import org.bukkit.event.block.SignChangeEvent;
 import org.bukkit.event.player.PlayerInteractEvent;
 import org.bukkit.plugin.Plugin;
 
 public class SignListener
   implements Listener
 {
   String scb = "§7[§cSCB§7] ";
 
   static Plugin plugin = SCB.getInstance();
   File arenaFile;
   String plyFile = plugin.getDataFolder() + File.separator + "players" + File.separator;
 
   String araFile = plugin.getDataFolder() + File.separator + "arenas" + File.separator;
 
   public Event getSignListener(SignChangeEvent e)
   {
     return e;
   }
 
   @EventHandler
   public void onSignClick(PlayerInteractEvent e)
   {
     if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (
       (e.getClickedBlock().getType() == Material.SIGN) || (e.getClickedBlock().getType() == Material.WALL_SIGN) || (e.getClickedBlock().getType() == Material.SIGN_POST))) {
       Sign s = (Sign)e.getClickedBlock().getState();
       Player p = e.getPlayer();
       if ((p.hasPermission("scb.signs.use")) && (s.getLine(0).equalsIgnoreCase(this.scb)))
         if (s.getLine(1).equalsIgnoreCase("§4§lJoin")) {
           String arena = s.getLine(2);
           if (Arena.get().getArenaEnabled(arena)) {
             Game.get().joinArena(arena, p);
             File farena = new File(this.araFile + arena + ".yml");
             FileConfiguration fc = YamlConfiguration.loadConfiguration(farena);
             if (fc.get("signs") != null) {
               Arena.get().signUpdate(arena);
             }
           }
           else if (!Arena.get().getArenaEnabled(arena)) {
             p.sendMessage("§7[§cSCB§7] " + "§cArena Is Not Enabled");
           }
         }
         else if ((p.hasPermission("scb.signs.use")) && (s.getLine(0).equalsIgnoreCase(this.scb)) && (s.getLine(1).equalsIgnoreCase(ChatColor.RED + "Join"))) {
           p.sendMessage(this.scb + " Arena Is In Game!");
         }
         else if ((p.hasPermission("scb.signs.use")) && (s.getLine(0).equalsIgnoreCase(this.scb)) && 
           (s.getLine(1).equalsIgnoreCase("Right Click")) && 
           (s.getLine(2).equalsIgnoreCase("To Leave")) && 
           (PlayerManager.get().ingame(p)) && 
           (Game.get().getArena(p) != null)) {
           Game.get().playerLeave(p);
           p.sendMessage(this.scb + "§c" + p.getDisplayName() + " §7You have left the game");
         }
     }
   }
 
   @EventHandler
   public void onSignCreate(SignChangeEvent e)
   {
     if (e.getLine(0).equalsIgnoreCase("[scb]"))
       if (e.getLine(1).equalsIgnoreCase("join")) {
         this.arenaFile = new File(this.araFile + e.getLine(2) + ".yml");
         if (((e.getLine(1).equalsIgnoreCase("join")) && (e.getLine(2).equalsIgnoreCase(null))) || (!this.arenaFile.exists())) {
           e.setLine(1, "Error, Try");
           e.setLine(2, "putting a");
           e.setLine(3, "valid arena");
         }
         else {
           e.setLine(0, this.scb);
           e.setLine(1, "§4§lJoin");
           e.setLine(2, e.getLine(2));
           e.setLine(3, "");//Add num of players
           File farena = new File(this.araFile + e.getLine(2) + ".yml");
           FileConfiguration fc = YamlConfiguration.loadConfiguration(farena);
           if (fc.get(e.getLine(2), "signs") != null) {
             int signs = (int)fc.getDouble("signs");
             signs++;
             fc.set("signs", Integer.valueOf(signs));
             Arena.get().loglocation(e.getBlock().getLocation(), fc, "sign" + Integer.toString(signs));
             try {
               fc.save(farena);
             } catch (IOException e1) {
               e1.printStackTrace();
             }
           }
           else {
             fc.set("signs", Integer.valueOf(1));
             Arena.get().loglocation(e.getBlock().getLocation(), fc, "sign1");
             try {
               fc.save(farena);
             } catch (IOException e1) {
               e1.printStackTrace();
             }
           }
         }
       }
       else if (e.getLine(1).equalsIgnoreCase("leave")) {
         e.setLine(0, this.scb);
         e.setLine(1, "Right Click");
         e.setLine(2, "To Leave");
         e.setLine(3, "Arena");
       }
   }
 
   @EventHandler
   public void breakBlock(BlockBreakEvent e) {
     Location Loc = e.getBlock().getLocation();
     File file = new File(this.araFile);
 
     if (file.exists()) {
       int f = new File(this.araFile).listFiles().length;
       if (f > 0)
         for (int i = 0; i < f; i++) {
           File[] flist = new File(this.araFile).listFiles();
 
           String arena = flist[i].getName();
 
           File farena = new File(this.araFile + arena);
 
           FileConfiguration fc = YamlConfiguration.loadConfiguration(farena);
 
           String arenaName = fc.getString("ArenaName");
 
           if ((fc.get("signs") != null) && (arenaName != null) && (!PlayerManager.get().ingame(e.getPlayer()))) {
             int signs = fc.getInt("signs");
             for (int ii = 1; i <= signs; i++)
               if (fc.get("sign" + Integer.toString(ii)) != null) {
                 Location Loc2 = Arena.get().getLocation(arenaName, "sign" + Integer.toString(ii));
                 if ((Loc2.getBlockX() == Loc.getBlockX()) && (Loc2.getBlockY() == Loc.getBlockY()) && (Loc2.getBlockZ() == Loc.getBlockZ()) && (Loc2.getWorld() == Loc.getWorld())) {
                   if (fc.getInt("signs") == 1) {
                     fc.set("signs", null);
                   }
                   else {
                     fc.set("signs", Integer.valueOf(signs--));
                   }
                   fc.set("sign" + Integer.toString(ii), null);
                   Config.get().saveAllConfigs();
                 }
               }
           }
         }
     }
   }
 }