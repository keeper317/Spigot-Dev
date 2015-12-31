 package mediaapps.SCB.managers;
 import java.util.ArrayList;
 import java.util.logging.Logger;
 import net.milkbowl.vault.chat.Chat;
 import net.milkbowl.vault.economy.Economy;
 import net.milkbowl.vault.permission.Permission;
 import org.bukkit.Bukkit;
 import org.bukkit.Server;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.plugin.PluginDescriptionFile;
 import org.bukkit.plugin.PluginManager;
 import org.bukkit.plugin.RegisteredServiceProvider;
 import org.bukkit.plugin.ServicesManager;

import mediaapps.SCB.SCB;
 
 public class Dependency
 {
   Plugin plugin = SCB.getInstance();
   public static Dependency pmngr = new Dependency();
 
   public static Permission perms = null;
   public static Economy econ = null;
   public static Chat chat = null;
   private ArrayList<String> dep = new ArrayList();
 
   public static Dependency get()
   {
     return pmngr;
   }
 
   public boolean checkForVault()
   {
     if (this.plugin.getConfig().getBoolean("Vault")) {
       if (Bukkit.getPluginManager().getPlugin("Vault").isEnabled()) {
         return true;
       }
 
       this.plugin.getConfig().set("Vault", Boolean.valueOf(false));
       this.plugin.saveConfig();
     }
 
     return false;
   }
   public void disableVault() {
     this.plugin.getConfig().set("Vault", Boolean.valueOf(false));
     this.plugin.saveConfig();
   }
   public boolean loadVault() {
     if (!setupEconomy()) {
       this.plugin.getLogger().info("Disabling Vault dependency not found! " + this.plugin.getDescription().getName());
       disableVault();
       if (this.dep.contains("Vault")) {
         this.dep.remove("Vault");
       }
       return false;
     }
     setupPermissions();
     setupChat();
     this.dep.add("Vault");
     return true;
   }
   private boolean setupEconomy() {
     if (this.plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
       return false;
     }
     RegisteredServiceProvider rsp = this.plugin.getServer().getServicesManager().getRegistration(Economy.class);
     if (rsp == null) {
       return false;
     }
     econ = (Economy)rsp.getProvider();
     return econ != null;
   }
 
   private boolean setupChat() {
     RegisteredServiceProvider chatProvider = this.plugin.getServer().getServicesManager().getRegistration(Chat.class);
     if (chatProvider != null) {
       chat = (Chat)chatProvider.getProvider();
     }
     return chat != null;
   }
 
   private boolean setupPermissions() {
     RegisteredServiceProvider rsp = this.plugin.getServer().getServicesManager().getRegistration(Permission.class);
     perms = (Permission)rsp.getProvider();
     return perms != null;
   }
   public boolean vault() {
     if (this.dep.contains("Vault")) {
       return true;
     }
     return false;
   }
 }