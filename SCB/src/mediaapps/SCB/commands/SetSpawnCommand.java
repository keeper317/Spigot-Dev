 package mediaapps.SCB.commands;
import mediaapps.SCB.SCB;
import mediaapps.SCB.interfaces.SCBCommand;
import mediaapps.SCB.managers.Arena;

 import java.io.File;
 import java.io.IOException;
 import org.bukkit.ChatColor;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.configuration.file.YamlConfiguration;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 
 public class SetSpawnCommand
   implements SCBCommand
 {
   String scb = "§7[§cSCB§7] ";
   Plugin plugin = SCB.getInstance();
 
   public void onCommand(Player p, String[] args) {
     if (args[0].length() > 0) {
       String arena = args[0];
       File f = new File(this.plugin.getDataFolder() + "/arenas/" + arena + ".yml");
       FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
       Arena.get().loglocation(p.getLocation(), fc, "Spawn" + args[1]);
       try {
         fc.save(f);
       } catch (IOException e) {
         e.printStackTrace();
       }
       p.sendMessage(this.scb + "Successfully set spawnpoint" + args[1] + " for arena " + arena);
     }
     else {
       p.sendMessage(this.scb + "Please Enter In An Arena!");
     }
   }
 
   public String help(Player p)
   {
     return "SetSpawn <Arena> <1-4>: sets an arena SpawnPoint.";
   }
 
   public String permission(Player p)
   {
     return "scb.command.setspawn";
   }
 }