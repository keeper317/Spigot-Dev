 package mediaapps.SCB;
 
  import java.io.File;
 import java.io.IOException;
 import java.io.InputStream;
 import java.util.logging.Level;
 import java.util.logging.Logger;

 import org.bukkit.Bukkit;
 import org.bukkit.configuration.file.YamlConfiguration;
 import org.bukkit.plugin.java.JavaPlugin;

import mediaapps.SCB.listeners.BlockPlaced;
import mediaapps.SCB.listeners.ChatListener;
import mediaapps.SCB.listeners.ClassListener;
import mediaapps.SCB.listeners.ClassSignListener;
import mediaapps.SCB.listeners.DropListener;
import mediaapps.SCB.listeners.LogInListener;
import mediaapps.SCB.listeners.ProtectionsListener;
import mediaapps.SCB.listeners.SelectionToolListener;
import mediaapps.SCB.listeners.SignListener;
import mediaapps.SCB.managers.Arena;
import mediaapps.SCB.managers.ClassManager;
import mediaapps.SCB.managers.CommandManager;
import mediaapps.SCB.managers.Dependency;
import mediaapps.SCB.managers.Game;
 
 public class SCB extends JavaPlugin
 {
   public YamlConfiguration LANG;
   public File LANG_FILE;
   private Logger log;
   public static SCB plugin;
 
   public void onEnable()
   {
     this.log = Bukkit.getLogger();
     loadLang();
     plugin = this;
     getLogger().info("Booting Up");
     getLogger().info("created by Pauldg7, Paul G, with help by mike1665");
			  getLogger().info("MADE USEABLE BY KEEPER317");
     getCommand("scb").setExecutor(new CommandManager(this));
     getServer().getPluginManager().registerEvents(new ClassListener(), this);
     getServer().getPluginManager().registerEvents(new ClassSignListener(), this);
     getServer().getPluginManager().registerEvents(new SignListener(), this);
     getServer().getPluginManager().registerEvents(new DropListener(), this);
     getServer().getPluginManager().registerEvents(new LogInListener(), this);
     getServer().getPluginManager().registerEvents(new SelectionToolListener(), this);
     getServer().getPluginManager().registerEvents(new ProtectionsListener(), this);
     getServer().getPluginManager().registerEvents(new ChatListener(), this);
getServer().getPluginManager().registerEvents(new BlockPlaced(), this);
     saveDefaultConfig();
     getConfig().options().copyDefaults(true);
     ClassManager.get().loadClasses();
     Arena.get().loadArenas();
     if ((Dependency.get().checkForVault()) && 
       (Dependency.get().loadVault()))
       getLogger().info("Successfully Setup Vault");
   }
 
   public void onDisable()
   {
     Arena.get().resetArenas();
     for (String arena : Arena.get().getArenaList()) {
       Arena.get().signUpdate(arena);
       Game.get().arenaReset(arena);
     }
   }
 
   public static SCB getInstance() { return plugin; }
 
   public File getLangFile()
   {
     return this.LANG_FILE;
   }
 
   public YamlConfiguration getLang() {
     return this.LANG;
   }
   public void loadLang() {
     File lang = new File(getDataFolder(), "lang.yml");
     if (!lang.exists()) {
       try {
         getDataFolder().mkdir();
         lang.createNewFile();
         InputStream defConfigStream = getResource("lang.yml");
         if (defConfigStream != null) {
           YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
           defConfig.save(lang);
           Lang.setFile(defConfig);
           return;
         }
       } catch (IOException e) {
         e.printStackTrace();
         this.log.severe("[SCB] Couldn't create language file.");
         this.log.severe("[SCB] This is a fatal error. Now disabling");
         setEnabled(false);
       }
     }
     YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
     for (Lang item : Lang.values()) {
       if (conf.getString(item.getPath()) == null) {
         conf.set(item.getPath(), item.getDefault());
       }
     }
     Lang.setFile(conf);
     this.LANG = conf;
     this.LANG_FILE = lang;
     try {
       conf.save(getLangFile());
     } catch (IOException e) {
       this.log.log(Level.WARNING, "SCB: Failed to save lang.yml.");
       this.log.log(Level.WARNING, "SCB: Report this stack trace to <your name>.");
       e.printStackTrace();
     }
   }
 }
