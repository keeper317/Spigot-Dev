 package mediaapps.SCB.managers;
 import java.io.File;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.logging.Logger;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.Location;
 import org.bukkit.Material;
 import org.bukkit.World;
 import org.bukkit.block.Block;
 import org.bukkit.block.Sign;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.configuration.file.YamlConfiguration;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.scoreboard.DisplaySlot;
 import org.bukkit.scoreboard.Objective;
 import org.bukkit.scoreboard.Score;
 import org.bukkit.scoreboard.Scoreboard;
 import org.bukkit.scoreboard.ScoreboardManager;

import mediaapps.SCB.SCB;
 
 public class Arena
 {
   ArrayList<String> arenaList = new ArrayList();
 
   String scb = "Â§7[Â§cSCBÂ§7] ";
   Plugin plugin = SCB.getInstance();
   String plyFile = this.plugin.getDataFolder() + File.separator + "players" + File.separator;
 
   String araFile = this.plugin.getDataFolder() + File.separator + "arenas" + File.separator;
   public static Arena amanger = new Arena();
 
   public static Arena get() { return amanger; }
 
   public void deleteArena(String arena)
   {
     File f = new File(this.araFile + arena + ".yml");
     if (f.exists()) {
       Config.get().deleteFile(arena);
       this.arenaList.remove(arena);
     }
   }
 
   public void enableArena(String arena) {
     if (!this.arenaList.contains(arena)) {
       FileConfiguration cf = Config.get().getArenaConfig(arena);
       cf.set("Enabled", Boolean.valueOf(true));
       this.arenaList.add(arena);
       try {
         cf.save(Config.get().getArenaFile(arena));
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
   }
 
   public String[] getArenaList() {
     String[] ars = new String[this.arenaList.size()];
     return (String[])this.arenaList.toArray(ars);
   }
   public void disableArena(String arena) {
     if (this.arenaList.contains(arena)) {
       File f = Config.get().getArenaFile(arena);
       FileConfiguration cf = Config.get().getArenaConfig(arena);
       cf.set("Enabled", "false");
       this.arenaList.remove(arena);
       Config.get().saveCustomConfig(f, cf);
     }
   }
 
   public void loglocation(Location loc, FileConfiguration c, String name) {
     int locX = loc.getBlockX();
     int locY = loc.getBlockY();
     int locZ = loc.getBlockZ();
     float locP = loc.getPitch();
     float locYa = loc.getYaw();
     String world = loc.getWorld().getName();
     c.set(name + ".X", Integer.valueOf(locX));
     c.set(name + ".Y", Integer.valueOf(locY));
     c.set(name + ".Z", Integer.valueOf(locZ));
     c.set(name + ".P", Float.valueOf(locP));
     c.set(name + ".Ya", Float.valueOf(locYa));
     c.set(name + ".World", world);
   }
 
   public void createArena(String name, Player p) {
     FileConfiguration c = Config.get().getArenaConfig(name);
     c.set("ArenaName", name);
     c.set("Enabled", "false");
     loglocation(p.getLocation(), c, "Lobby");
     File arenaConfig = new File(this.araFile + name + ".yml");
     try {
       c.save(arenaConfig);
     } catch (IOException e) {
       this.plugin.getLogger().info("An error has occured saving arenaconfig at createarena method.");
       e.printStackTrace();
     }
   }
 
   public void removeArena(String name) {
     Config.get().deleteFile(name);
   }
 
   public boolean getArenaEnabled(String arena) {
     if (this.arenaList.contains(arena)) {
       return true;
     }
     return false;
   }
 
   public void loadArenas() {
     File file = new File(this.araFile);
 
     if (file.exists()) {
       File[] f = new File(this.araFile).listFiles();
       if (f.length > 0)
         for (File arenaFile : f) {
           String arena = arenaFile.getName();
           File farena = new File(this.araFile + arena);
           FileConfiguration fc = YamlConfiguration.loadConfiguration(farena);
           String arenaName = fc.getString("ArenaName");
           if ((fc.getBoolean("Enabled")) && (arenaName != null)) {
             enableArena(arenaName);
             this.plugin.getLogger().info("Enabled Arena " + arenaName);
           }
           else if (arenaName != null) {
             this.plugin.getLogger().info("Disabled Arena " + arenaName);
           }
         }
     }
   }
 
   public Location getLocation(String arena, String name)
   {
     FileConfiguration c = Config.get().getArenaConfig(arena);
     double x = c.getDouble(name + ".X");
     double y = c.getDouble(name + ".Y");
     double z = c.getDouble(name + ".Z");
     double pd = c.getDouble(name + ".P");
     double yad = c.getDouble(name + ".Ya");
     float p = (float)pd;
     float ya = (float)yad;
     String worldname = c.getString(name + ".World");
     World world = Bukkit.getWorld(worldname);
     Location Loc = new Location(world, x, y, z, ya, p);
     return Loc;
   }
 
   public void resetArenas()
   {
   }
 
   public void signUpdate(String arena) {
     FileConfiguration c = Config.get().getArenaConfig(arena);
     int signNum = c.getInt("signs");
     if (Game.get().getPlyNum(arena) == null) {
       Game.get().setPlyNum(arena, 0);
     }
     for (int i = 1; i <= signNum; i++) {
       World w = Bukkit.getWorld("world");
       Location dumbLoc = new Location(w, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
       if ((getLocation(arena, "sign" + Integer.toString(i)) != dumbLoc) && (getLocation(arena, "sign" + Integer.toString(i)).getBlock().getType() != Material.AIR)) {
         Location Loc = getLocation(arena, "sign" + Integer.toString(i));
         Sign s = (Sign)Loc.getBlock().getState();
         if ((s.getLine(0).equalsIgnoreCase(scb)) && (!Game.get().getAIngame(arena))) {
           s.setLine(1, "Â§4Â§lJoin");
           s.setLine(3, "(" + Game.get().getPlyNum(arena).toString() + "/4)");
           s.update();
         }
         else if ((s.getLine(0).equalsIgnoreCase(scb)) && (Game.get().getAIngame(arena))) {
           s.setLine(1, ChatColor.RED + "Join");
           s.setLine(3, "(" + Game.get().getPlyNum(arena).toString() + "/4)");
           s.update();
         }
       }
     }
   }
 
   public void scoreUpdate(String arena) { ScoreboardManager m = Bukkit.getScoreboardManager();
     Scoreboard b = m.getNewScoreboard();
     Objective o = b.registerNewObjective(arena, arena);
     o.setDisplaySlot(DisplaySlot.SIDEBAR);
			  Score score = null;
     Player[] plys = Game.get().getPlys(arena);
 
     for (Player p : plys) {
       o.setDisplayName("Â§6Â§lLIVES");
				if(p.getDisplayName().length() >= 14)
       score = o.getScore(p.getDisplayName().substring(0, 14) + ":");
				else
				score = o.getScore(p.getDisplayName() + ":");
       score.setScore(PlayerManager.get().getLives(p).intValue());
     }
     for (Player p : plys)
       p.setScoreboard(b); }
 
   public void scorebRemove(Player p)
   {
     ScoreboardManager m = Bukkit.getScoreboardManager();
     Scoreboard b = m.getNewScoreboard();
     p.setScoreboard(b);
   }
   public void scoreLobbyUpdate(Player p) {
     if (this.plugin.getConfig().getBoolean("OutsideStats")) {
       ScoreboardManager m = Bukkit.getScoreboardManager();
       Scoreboard b = m.getNewScoreboard();
       Objective o = b.registerNewObjective(p.getName(), p.getName());
       o.setDisplaySlot(DisplaySlot.SIDEBAR);
       o.setDisplayName("Â§6Â§lSCB");
       File f = new File(this.plyFile + p.getName() + ".yml");
       FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
       Score score = o.getScore("Â§eTokens:");
       Score score2 = o.getScore("Â§aWins:");
       Score score3 = o.getScore("Â§cLosses:");
       score.setScore(fc.getInt("Gems"));
       score2.setScore(fc.getInt("Wins"));
       score3.setScore(fc.getInt("Losses"));
       p.setScoreboard(b);
     }
   }
 
   public boolean nullC(String s) { if (s != null) {
       return true;
     }
     return false;
   }
 }