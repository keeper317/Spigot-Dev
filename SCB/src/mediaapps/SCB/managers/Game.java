 package mediaapps.SCB.managers;
import mediaapps.SCB.Lang;
import mediaapps.SCB.SCB;
import mediaapps.SCB.interfaces.ClassInterface;

 import java.io.File;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Random;
 import java.util.logging.Logger;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.GameMode;
 import org.bukkit.Location;
 import org.bukkit.World;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.configuration.file.YamlConfiguration;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.potion.PotionEffectType;
 import org.bukkit.scheduler.BukkitScheduler;
 
 public class Game
 {
   Plugin plugin = SCB.getInstance();
   public static Game amanger = new Game();
 
   HashMap<String, Integer> plNum = new HashMap();
 
   HashMap<String, Boolean> arenaInGame = new HashMap();
 
   HashMap<Player, String> pArena = new HashMap();
 
   String scb = "§7[§cSCB§7] ";
 
   String plyFile = this.plugin.getDataFolder() + File.separator + "players" + File.separator;
 
   int Time = 0;
   int id = 0;
   String a;
 
   public static Game get()
   {
     return amanger;
   }
 
   public void joinArena(String arena, Player p)
   {
     if ((getPlyNum(arena).intValue() != 4) && (!getAIngame(arena)) && (!PlayerManager.get().ingame(p))) {
       if (p.getGameMode() != GameMode.CREATIVE) {
	         Bukkit.broadcastMessage("§7[§cSCB§7] §c"+ p.getDisplayName() + " §7joined the game!");
         int tempnum = getPlyNum(arena).intValue();
         tempnum++;
         setPlyNum(arena, tempnum);
         File f = new File(this.plyFile + p.getName() + ".yml");
         FileConfiguration pc = YamlConfiguration.loadConfiguration(f);
         pc.set("Arena", arena);
         try {
           pc.save(f);
         } catch (IOException e) {
           e.printStackTrace();
         }
 
         Location Loc = Arena.get().getLocation(arena, "Lobby");
         p.teleport(Loc);
         PlayerManager.get().prepPlayer(p);
         this.pArena.put(p, arena);
         PlayerManager.get().setLives(p, Integer.valueOf(5));
         p.setHealth(20.0D);
         p.setFoodLevel(20);
         Arena.get().signUpdate(arena);
         Arena.get().scoreUpdate(arena);
         if (tempnum >= 2) {
           preStart(arena, 30);
         }
       }
       else
       {
         p.sendMessage(this.scb + "§cYou Are In Creative!");
       }
     }
     else
       p.sendMessage(this.scb + "§cArena Is Full!");
   }
 
   public void forceStart(String arena)
   {
     if (getPlys(arena).length >= 2) {
       preStart(arena, 3);
     }
     else
       for (Player p : getPlys(arena))
         this.plugin.getLogger().info(p.getName() + " Is in Arena");
   }
 
   public void preStart(String arena, int t)
   {
     this.Time = t;
     this.a = arena;
     Bukkit.getScheduler().cancelTask(this.id);
     this.id = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable()
     {
       public void run() {
         if ((Game.this.getPlyNum(Game.this.a).intValue() >= 2) && (!((Boolean)Game.this.arenaInGame.get(Game.this.a)).booleanValue()) && (Game.this.getPlys(Game.this.a).length > 1)) {
           if (Game.this.Time > 0) {
             for (Player p : Game.this.getPlys(Game.this.a)) {
               p.setLevel(Game.this.Time);
             }
 
             if (Game.this.Time <= 5) {
               Game.this.broadcastArena(Game.this.a, "The game will start in §c" + Integer.toString(Game.this.Time) + " §7seconds.");
             }
             Game.this.Time -= 1;
           }
           else {
             Bukkit.getScheduler().cancelTask(Game.this.id);
             Game.this.startGame(Game.this.a);
           }
         }
         else
         {
           Bukkit.getScheduler().cancelTask(Game.this.id);
         }
       }
     }
     , 0L, 20L);
   }
 
   public void broadcastArena(String arena, String msg) {
     for (Player p : getPlys(arena))
       p.sendMessage(this.scb + msg);
   }
 
   public void startGame(String arena)
   {
     this.arenaInGame.put(arena, Boolean.valueOf(true));
     if (getPlys(arena).length > 1)
       for (Player p : getPlys(arena)) {
         p.setHealth(20.0D);
         p.setFoodLevel(20);
         p.setHealthScale(20.0D);
         Random random = new Random();
         int num = random.nextInt(4) + 1;
         Location Loc = Arena.get().getLocation(arena, "Spawn" + Integer.toString(num));
         p.teleport(Loc);
         if (PlayerManager.get().getClasse(p) != null) {
           PlayerManager.get().getClasse(p).Spawn(p);
         }
         else {
           ClassInterface ci = ClassManager.get().getRandomClass();
           PlayerManager.get().setClass(p, ci);
           ci.Spawn(p);
         }
       }
   }
 
   public void playerLeave(Player p)
   {
     String arena = getArena(p);
     if ((getPlyNum(arena) != null) && (getPlyNum(arena).intValue() == 1) && (((Boolean)this.arenaInGame.get(arena)).booleanValue())) {
       Dependency.get().vault();
 
       File f = new File(this.plyFile + p.getName() + ".yml");
       FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
       int Wins = fc.getInt("Wins");
       Wins++;
       fc.set("Wins", Integer.valueOf(Wins));
       int gems = fc.getInt("Gems");
       gems++;
       gems++;
       fc.set("Gems", Integer.valueOf(gems));
       try {
         fc.save(f);
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
 
     if (getPlyNum(arena).intValue() > 0) {
       int i = getPlyNum(arena).intValue();
       i--;
       setPlyNum(arena, i);
     }
     p.setHealth(20.0D);
     FileConfiguration c = this.plugin.getConfig();
     double x = c.getDouble("Lobby.X");
     double y = c.getDouble("Lobby.Y");
     double z = c.getDouble("Lobby.Z");
     double pd = c.getDouble("Lobby.P");
     double yad = c.getDouble("Lobby.Ya");
     float pi = (float)pd;
     float ya = (float)yad;
     String worldname = c.getString("Lobby.World");
     World world = Bukkit.getWorld(worldname);
     Location Loc = new Location(world, x, y, z, ya, pi);
     p.teleport(Loc);
 
     PlayerManager.get().setLives(p, Integer.valueOf(0));
     PlayerManager.get().putBackInv(p);
     PlayerManager.get().setIngame(p, false);
     PlayerManager.get().setClass(p, null);
     this.pArena.put(p, null);
 
     Arena.get().signUpdate(arena);
     Arena.get().scorebRemove(p);
     Arena.get().scoreLobbyUpdate(p);
 
     p.setLevel(0);
     p.setAllowFlight(false);
     p.setFlying(false);
     p.removePotionEffect(PotionEffectType.SPEED);
     p.removePotionEffect(PotionEffectType.WITHER);
     File f = new File(this.plyFile + p.getName() + ".yml");
     FileConfiguration pc = YamlConfiguration.loadConfiguration(f);
     pc.set("Arena", null);
     try {
       pc.save(f);
     } catch (IOException e) {
       e.printStackTrace();
     }
     if (getPlyNum(arena).intValue() == 1)
       arenaReset(arena);
   }
 
   public void arenaReset(String arena)
   {
     for (Player p : Bukkit.getOnlinePlayers()) {
       FileConfiguration pc = PlayerManager.get().getPlayerConfig(p);
       if (pc.getString("Arena") != null)
       {
         playerLeave(p);
         this.plugin.getLogger().info("Reset Player " + p.getName());
       }
     }
     this.arenaInGame.put(arena, Boolean.valueOf(false));
     setPlyNum(arena, 0);
     Arena.get().signUpdate(arena);
   }
 
   public Integer getPlyNum(String arena) {
     if (this.plNum.get(arena) != null) {
       return (Integer)this.plNum.get(arena);
     }
 
     this.plNum.put(arena, Integer.valueOf(0));
     return (Integer)this.plNum.get(arena);
   }
 
   public void setPlyNum(String arena, int num)
   {
     this.plNum.put(arena, Integer.valueOf(num));
   }
 
   public Player[] getPlys(String arena) {
     List plys = new ArrayList();
     for (Player p : Bukkit.getOnlinePlayers()) {
       FileConfiguration pc = PlayerManager.get().getPlayerConfig(p);
       if ((arena != null) && (pc.getString("Arena") != null) && 
         (pc.getString("Arena").equals(arena))) {
         plys.add(p);
       }
     }
     return (Player[])plys.toArray(new Player[plys.size()]);
   }
 
   public void setArena(Player p, String arena) {
     this.pArena.put(p, arena);
   }
 
   public String getArena(Player p) {
     FileConfiguration pc = PlayerManager.get().getPlayerConfig(p);
     if (pc.getString("Arena") != null) {
       return pc.getString("Arena");
     }
     return null;
   }
 
   public boolean getAIngame(String arena) {
     if (this.arenaInGame.get(arena) == null) {
       this.arenaInGame.put(arena, Boolean.valueOf(false));
     }
     return ((Boolean)this.arenaInGame.get(arena)).booleanValue();
   }
 }