 package mediaapps.SCB.commands;
import mediaapps.SCB.SCB;
import mediaapps.SCB.interfaces.SCBCommand;
import mediaapps.SCB.managers.Arena;

 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 
 public class SetGLobbyCommand
   implements SCBCommand
 {
   Plugin plugin = SCB.getInstance();
   String scb = "§7[§cSCB§7] ";
 
   public void onCommand(Player p, String[] args) {
     Arena.get().loglocation(p.getLocation(), this.plugin.getConfig(), "Lobby");
     this.plugin.saveConfig();
     p.sendMessage(this.scb + "Successfully set SCB's global lobby.");
   }
 
   public String help(Player p)
   {
     return "setglobby: Sets The Return Place When The Match Ends.";
   }
 
   public String permission(Player p)
   {
     return "scb.command.setglobby";
   }
 }