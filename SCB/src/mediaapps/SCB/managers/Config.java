 package mediaapps.SCB.managers;
 import java.io.File;
 import java.io.IOException;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 import org.bukkit.ChatColor;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.configuration.file.YamlConfiguration;
 import org.bukkit.plugin.Plugin;

import mediaapps.SCB.SCB;
 
 public class Config
 {
   String scb = "§7[§cSCB§7] ";
   Plugin plugin = SCB.getInstance();
   String plyFile = this.plugin.getDataFolder() + File.separator + "players" + File.separator;
   String araFile = this.plugin.getDataFolder() + File.separator + "arenas" + File.separator;
   public static Config cmngr = new Config();
 
   public static Config get() { return cmngr; }
 
   public void defaultArenaConfig(String name) {
     File f = new File(this.araFile + name + ".yml");
     FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
     if (!f.exists()) {
       fc.set("ArenaName", name);
       fc.set("Enabled", "false");
       try {
         fc.save(f);
       } catch (IOException e) {
         e.printStackTrace();
         this.plugin.getLogger().info("Could not create default arena config " + name);
       }
     }
     else {
       this.plugin.getLogger().info(this.scb + "Error Could Not Create Arena " + name);
     }
   }
 
   public void deleteFile(String name) { File f = new File(this.araFile + name + ".yml");
     if (f.exists())
       f.delete(); }
 
   public FileConfiguration getArenaConfig(String arena)
   {
     File arenaConfig = new File(this.araFile + arena + ".yml");
     FileConfiguration arenaFileConfig = YamlConfiguration.loadConfiguration(arenaConfig);
     return arenaFileConfig;
   }
   public File getArenaFile(String arena) {
     File arenaFile = new File(this.araFile + arena + ".yml");
     return arenaFile;
   }
 
   public void reloadArenaConfig(String arena)
   {
   }
 
   public void saveCustomConfig(File f, FileConfiguration fc)
   {
     try
     {
       fc.save(f);
     } catch (IOException ex) {
       this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + f, ex);
     }
   }
 
   public void saveAllConfigs() { File arenas = new File(this.plugin.getDataFolder() + File.separator + "arenas" + File.separator);
     File players = new File(this.plugin.getDataFolder() + File.separator + "players" + File.separator);
     if (arenas.exists()) {
       for (File file : arenas.listFiles()) {
         FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
         try {
           fc.save(file);
         } catch (IOException e) {
           e.printStackTrace();
         }
       }
     }
     if (players.exists())
       for (File file2 : players.listFiles()) {
         FileConfiguration fc2 = YamlConfiguration.loadConfiguration(file2);
         try {
           fc2.save(file2);
         }
         catch (IOException e) {
           e.printStackTrace();
         }
       }
   }
 }