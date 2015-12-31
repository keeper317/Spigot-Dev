 package mediaapps.SCB.listeners;
import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.managers.ClassManager;
import mediaapps.SCB.managers.PlayerManager;

 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.Material;
 import org.bukkit.Server;
 import org.bukkit.block.Block;
 import org.bukkit.block.Sign;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.block.Action;
 import org.bukkit.event.block.SignChangeEvent;
 import org.bukkit.event.inventory.InventoryClickEvent;
 import org.bukkit.event.player.PlayerInteractEvent;
 import org.bukkit.inventory.Inventory;
 import org.bukkit.inventory.ItemStack;
 
 public class ClassSignListener
   implements Listener
 {
   String scb = "§7[§cSCB§7] ";
 
   @EventHandler
   public void onSignCreate(SignChangeEvent e) { if ((e.getLine(0).equalsIgnoreCase("[scb]")) && (e.getLine(1).equalsIgnoreCase("classes"))) {
       e.setLine(0, scb);
       e.setLine(1, "§4§lRight Click");
       e.setLine(2, "§0To Pick");
       e.setLine(3, "§0A Class");
     }
     else if ((e.getLine(0).equalsIgnoreCase("[scb]")) && (e.getLine(1).equalsIgnoreCase("vipclasses"))) {
       e.setLine(0, scb);
       e.setLine(1, ChatColor.AQUA + "Vip Classes");
     }
     else if ((e.getLine(0).equalsIgnoreCase("[scb]")) && (e.getLine(1).equalsIgnoreCase("gemclasses"))) {
       e.setLine(0, scb);
       e.setLine(1, ChatColor.AQUA + "Gem Classes");
     } }
 
   @EventHandler
   public void onSignClick(PlayerInteractEvent e) {
     if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (
       (e.getClickedBlock().getType() == Material.SIGN) || (e.getClickedBlock().getType() == Material.SIGN_POST) || (e.getClickedBlock().getType() == Material.WALL_SIGN))) {
       Sign s = (Sign)e.getClickedBlock().getState();
       Player p = e.getPlayer();
       if ((s.getLine(0).equalsIgnoreCase(scb)) && (p.hasPermission("scb.signs.use")) && (PlayerManager.get().ingame(p)))
         if (s.getLine(1).equalsIgnoreCase("§4§lRight Click")) {
           Inventory inv = Bukkit.getServer().createInventory(p, 9, "Classes");
 
           for (int i = 0; i < ClassManager.get().getRegularClasses().length; i++) {
             ClassInterface ci = ClassManager.get().getRegularClasses()[i];
             inv.setItem(i, ci.Icon());
           }
           p.openInventory(inv);
         }
         else if ((s.getLine(1).equalsIgnoreCase(ChatColor.AQUA + "Vip Classes")) && (p.hasPermission("scb.vip"))) {
           Inventory inv = Bukkit.getServer().createInventory(p, 9, "VipClasses");
 
           for (int i = 0; i < ClassManager.get().getVipClasses().length; i++) {
             ClassInterface ci = ClassManager.get().getVipClasses()[i];
             inv.setItem(i, ci.Icon());
           }
           p.openInventory(inv);
         }
         else if (s.getLine(1).equalsIgnoreCase(ChatColor.AQUA + "Gem Classes")) {
           Inventory inv = Bukkit.getServer().createInventory(p, 9, "GemClasses");
 
           for (int i = 0; i < ClassManager.get().getGemClasses().length; i++) {
             ClassInterface ci = ClassManager.get().getGemClasses()[i];
             inv.setItem(i, ci.Icon());
           }
           p.openInventory(inv);
         }
     }
   }
 
   @EventHandler
   public void onclick(InventoryClickEvent e)
   {
     Player p = (Player)e.getWhoClicked();
     if (e.getInventory().getName().equalsIgnoreCase("Classes")) {
       for (int i = 0; i < ClassManager.get().getRegularClasses().length; i++) {
         ClassInterface ci = ClassManager.get().getRegularClasses()[i];
         if ((e.getCurrentItem().getType() == ci.Icon().getType()) && (e.getCurrentItem().getDurability() == ci.Icon().getDurability()) && (PlayerManager.get().ingame(p)) && (e.getCurrentItem().getType() != null)) {
           e.setCancelled(true);
           PlayerManager.get().setClass(p, ci);
           String name = ci.id();
           String str = name.substring(0, 1).toUpperCase();
           name = str + name.substring(1, ci.id().length());
           p.sendMessage(this.scb + "§7You have chosen the " + "§c" + name + " Class");
           p.closeInventory();
         }
         else {
           e.setCancelled(true);
           p.closeInventory();
         }
       }
     }
     else if (e.getInventory().getName().equalsIgnoreCase("VipClasses")) {
       for (int i = 0; i < ClassManager.get().getVipClasses().length; i++) {
         ClassInterface ci = ClassManager.get().getVipClasses()[i];
         if ((e.getCurrentItem().getType() == ci.Icon().getType()) && (e.getCurrentItem().getDurability() == ci.Icon().getDurability()) && (PlayerManager.get().ingame(p)) && (e.getCurrentItem().getType() != null) && (p.hasPermission("scb.vip"))) {
           e.setCancelled(true);
           PlayerManager.get().setClass(p, ci);
           String name = ci.id();
           String str = name.substring(0, 1).toUpperCase();
           name = str + name.substring(1, ci.id().length());
           p.sendMessage(this.scb + " §7You Have Choosen The Class " + ChatColor.RED + name);
           p.closeInventory();
         }
         else {
           e.setCancelled(true);
           p.closeInventory();
         }
       }
     }
     else if (e.getInventory().getName().equalsIgnoreCase("GemClasses"))
       for (int i = 0; i < ClassManager.get().getGemClasses().length; i++) {
         ClassInterface ci = ClassManager.get().getGemClasses()[i];
         if ((e.getCurrentItem().getType() == ci.Icon().getType()) && (e.getCurrentItem().getDurability() == ci.Icon().getDurability()) && (PlayerManager.get().ingame(p)) && (e.getCurrentItem().getType() != null)) {
           if (PlayerManager.get().getPlayerConfig(p).getBoolean(ci.id())) {
             e.setCancelled(true);
             PlayerManager.get().setClass(p, ci);
             String name = ci.id();
             String str = name.substring(0, 1).toUpperCase();
             name = str + name.substring(1, ci.id().length());
             p.sendMessage(this.scb + "§7You Have Choosen The Class " + ChatColor.RED + name);
             p.closeInventory();
           }
           else {
             p.sendMessage(this.scb + ChatColor.DARK_PURPLE + " §cYou Have Not Purchased That Class.");
           }
         }
         else {
           e.setCancelled(true);
           p.closeInventory();
         }
       }
   }
 }