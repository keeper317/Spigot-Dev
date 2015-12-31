 package mediaapps.ForceField;
 
 import java.io.File;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 import org.bukkit.Bukkit;
 import org.bukkit.Location;
 import org.bukkit.Server;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandSender;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.configuration.file.FileConfigurationOptions;
 import org.bukkit.entity.Entity;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.PlayerMoveEvent;
 import org.bukkit.plugin.PluginManager;
 import org.bukkit.plugin.java.JavaPlugin;
 import org.bukkit.scheduler.BukkitScheduler;
 import org.bukkit.util.Vector;
 
 public class ForceField extends JavaPlugin
   implements Listener
 {
   public ArrayList<Player> toggled = new ArrayList();
   public static ArrayList<Player> msgCooldown = new ArrayList();
   File configFile = new File(getDataFolder(), "config.yml");
   public static ForceField instance;
 
   public void onEnable()
   {
     instance = this;
     Bukkit.getServer().getPluginManager().registerEvents(instance, instance);
 
     getConfig().addDefault("Message", "&cYou are not allowed to get near this player!");
     getConfig().addDefault("Toggle", "&aForceField is now %s;&2ON;&cOFF");
     getConfig().addDefault("ToggleOther", "&aForceField for Player &2%s &ais now %s;&2ON;&cOFF");
     getConfig().addDefault("Radius", Integer.valueOf(5));
     getConfig().addDefault("Force", Double.valueOf(2.0D));
     getConfig().options().copyDefaults(true);
     saveConfig();
 
     setupMetrics();
   }
 
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
     if (cmd.getLabel().equalsIgnoreCase("forcefield"))
       if ((sender instanceof Player)) {
         Player p = (Player)sender;
         if (p.hasPermission("forcefield.toggle")) {
           if (args.length == 0)
             toggle(p);
           else if (args.length == 1) {
             if (p.hasPermission("forcefield.toggle.other")) {
               if (Bukkit.getPlayerExact(args[0]) != null)
                 toggle(Bukkit.getPlayerExact(args[0]), p);
               else
                 p.sendMessage("§cPlayer §2" + args[0] + " §cis not online!");
             }
             else p.sendMessage("§cYou don't have permission to execute this Command!"); 
           }
           else
             p.sendMessage("§cToo many Arguments!");
         }
         else p.sendMessage("§cYou don't have permission to execute this Command!"); 
       }
       else { sender.sendMessage("Only Players can use this Command!"); }
 
     return false;
   }
 
   @EventHandler
   public void onMove(PlayerMoveEvent e) {
     Player player = e.getPlayer();
     double radius = getConfig().getDouble("Radius");
     if (this.toggled.contains(player)) {
       List<Entity> nearbyPlayers = player.getNearbyEntities(radius, radius, radius);
       for (Entity entity : nearbyPlayers)
         if (((entity instanceof Player)) && 
           (!((Player)entity).hasPermission("forcefield.bypass"))) {
           entity.setVelocity(calculateVelocity(player, entity));
           sendMessage((Player)entity, player);
           ParticleEffects eff = ParticleEffects.PORTAL;
           try {
             for (int i = 0; i < 10; i++) {
               float x = 0.5F;
               float y = 1.0F;
               float z = 0.5F;
               float speed = 0.1F;
               int count = 5;
               Location loc = new Location(entity.getLocation().getWorld(), entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ());
               eff.display(loc, x, y, z, speed, count);
             }
           } catch (Exception ex) {
             ex.printStackTrace();
           }
         }
     }
     else
     {
       List<Entity> nearbyPlayers = player.getNearbyEntities(radius, radius, radius);
       for (Entity entity : nearbyPlayers)
         if (((entity instanceof Player)) && 
           (this.toggled.contains(entity)) && (!player.hasPermission("forcefield.bypass"))) {
           player.setVelocity(calculateVelocity((Player)entity, player));
           sendMessage(player, (Player)entity);
           ParticleEffects eff = ParticleEffects.PORTAL;
           try {
             for (int i = 0; i < 10; i++) {
               float x = 0.5F;
               float y = 1.0F;
               float z = 0.5F;
               float speed = 0.1F;
               int count = 5;
               Location loc = new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
               eff.display(loc, x, y, z, speed, count);
             }
           } catch (Exception ex) {
             ex.printStackTrace();
           }
         }
     }
   }
 
   public Vector calculateVelocity(Entity e1, Entity e2)
   {
     return e2.getLocation().toVector().subtract(e1.getLocation().toVector()).normalize().multiply(getConfig().getDouble("Force"));
   }
 
   public Vector calculateVelocity_OLD(Player p, Entity e) {
     Location ploc = p.getLocation();
     Location eloc = e.getLocation();
 
     double px = ploc.getX();
     double py = ploc.getY();
     double pz = ploc.getZ();
     double ex = eloc.getX();
     double ey = eloc.getY();
     double ez = eloc.getZ();
 
     double x = 0.0D;
     double y = 0.0D;
     double z = 0.0D;
 
     if (px < ex)
       x = 1.0D;
     else if (px > ex) {
       x = -1.0D;
     }
 
     if (py < ey)
       y = 0.5D;
     else if (py > ey) {
       y = -0.5D;
     }
 
     if (pz < ez)
       z = 1.0D;
     else if (pz > ez) {
       z = -1.0D;
     }
 
     return new Vector(x, y, z);
   }
 
   public void toggle(Player p) {
     if (!this.toggled.contains(p)) {
       this.toggled.add(p);
 
       String[] msg = getConfig().getString("Toggle").split(";");
       p.sendMessage("§7[§6The GameBox§7] " + String.format(colorize(msg[0]), new Object[] { colorize(msg[1]) }));
     } else if (this.toggled.contains(p)) {
       this.toggled.remove(p);
 
       String[] msg = getConfig().getString("Toggle").split(";");
       p.sendMessage("§7[§6The GameBox§7] " + String.format(colorize(msg[0]), new Object[] { colorize(msg[2]) }));
     }
   }
 
   public void toggle(Player p, Player sender) {
     if (!this.toggled.contains(p)) {
       this.toggled.add(p);
 
       String[] msg = getConfig().getString("ToggleOther").split(";");
       p.sendMessage("§7[§6The GameBox§7] " + String.format(colorize(msg[0]), new Object[] { p.getName(), colorize(msg[1]) }));
     } else if (this.toggled.contains(p)) {
       this.toggled.remove(p);
 
       String[] msg = getConfig().getString("ToggleOther").split(";");
       p.sendMessage("§7[6aThe GameBox§7] " + String.format(colorize(msg[0]), new Object[] { p.getName(), colorize(msg[2]) }));
     }
   }
 
   public static void sendMessage(Player p, Player owner) {
     if (!msgCooldown.contains(p)) {
       msgCooldown.add(p);
       p.sendMessage("§7[§6The GameBox§7] " + colorize(instance.getConfig().getString("Message")).replaceAll("%Player%", owner.getName()));
 
       instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, new Runnable()
       {
         public void run()
         {
          
         }
       }
       , 20L);
     }
   }
 
   public void setupMetrics() {
     try {
       MetricsLite metrics = new MetricsLite(this);
       metrics.start();
     } catch (IOException localIOException) {
     }
   }
 
   public static String colorize(String Message) {
     return Message.replaceAll("&([a-z0-9])", "§$1");
   }
 }
