package ak.CookLoco.SkyWars.arena;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.kit.Kit;
import ak.CookLoco.SkyWars.menus.VoteMenu;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.BungeeUtils;
import ak.CookLoco.SkyWars.utils.ItemBuilder;
import ak.CookLoco.SkyWars.utils.LocationUtil;
import ak.CookLoco.SkyWars.utils.SkyData;
import ak.CookLoco.SkyWars.utils.SkyHologram;

public class Arena
  extends SkyData
{
  private String name;
  private int min_players;
  private int max_players;
  private World world;
  private boolean arena_started = false;
  private boolean arena_ending = false;
  private boolean arena_waiting = true;
  private boolean arena_starting = false;
  private boolean force_start = false;
  private boolean firstjoined = false;
  private boolean nowfull = false;
  private boolean loading = true;
  private boolean fall_damage = true;
  private int end_countdown = 10;
  private int start_countdown = 90;
  private int released_countdown = 10;
  File config_file = null;
  FileConfiguration config = null;
  private final List<SkyPlayer> players = new ArrayList();
  private final List<Location> spawnpoints = new ArrayList();
  private HashMap<Location, Boolean> used = new HashMap();
  private List<ArenaBox> glassboxes = new ArrayList();
  private List<String> selected_chest = new ArrayList();
  private List<String> selected_time = new ArrayList();
  private final List<BukkitRunnable> tickers = new ArrayList();
  private Random r;
  public BukkitRunnable countdown;
  public BukkitRunnable released;
  public BukkitRunnable check;
  private VoteMenu votemenu;
  private String chest = "chests.normal";
  
  public Arena(String paramString)
  {
    this.name = paramString;
    this.world = (Bukkit.getWorld(paramString) != null ? Bukkit.getWorld(paramString) : loadWorld());
    this.config_file = new File(SkyWars.getPlugin().getDataFolder(), SkyWars.arenas + File.separator + paramString + ".yml");
    this.config = YamlConfiguration.loadConfiguration(this.config_file);
    if (!this.config_file.exists())
    {
      try
      {
        this.config_file.createNewFile();
        this.config.set("name", paramString);
        this.config.set("min_players", Integer.valueOf(2));
        this.config.set("max_players", Integer.valueOf(6));
        this.config.save(this.config_file);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    else
    {
      this.min_players = this.config.getInt("min_players");
      this.max_players = this.config.getInt("max_players");
      loadSpawnPoints();
      loadGlassBoxes();
      loadUsedPoints();
    }
    ArenaManager.games.put(paramString, this);
    this.r = new Random();
    
    this.votemenu = new VoteMenu(this);
    
    checkPlayers();
    this.loading = false;
  }
  
  public World loadWorld()
  {
    WorldCreator localWorldCreator = new WorldCreator(this.name);
    localWorldCreator.generateStructures(false);
    World localWorld = localWorldCreator.createWorld();
    localWorld.setAutoSave(false);
    localWorld.setKeepSpawnInMemory(false);
    localWorld.setGameRuleValue("doMobSpawning", "false");
    localWorld.setGameRuleValue("doDaylightCycle", "false");
    localWorld.setGameRuleValue("mobGriefing", "false");
    localWorld.setGameRuleValue("commandBlockOutput", "false");
    localWorld.setTime(0L);
    return localWorld;
  }
  
  public void reloadWorld()
  {
    this.loading = true;
    Bukkit.unloadWorld(getWorld(), false);
    
    new BukkitRunnable()
    {
      public void run()
      {
        File localFile1 = new File(SkyWars.maps);
        File[] arrayOfFile;
        int j = (arrayOfFile = localFile1.listFiles()).length;
        for (int i = 0; i < j; i++)
        {
          File localFile2 = arrayOfFile[i];
          if ((localFile2.getName().equals(Arena.this.getName())) && 
            (localFile2.isDirectory())) {
            try
            {
              ArenaManager.delete(new File(localFile2.getName()));
              ArenaManager.copyFolder(localFile2, new File(localFile2.getName()));
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
          }
        }
      }
    }.runTaskLater(SkyWars.getPlugin(), 20L);
    
    new BukkitRunnable()
    {
      public void run()
      {
        Arena.this.world = Arena.this.loadWorld();
      }
    }.runTaskLater(SkyWars.getPlugin(), 40L);
    
    new BukkitRunnable()
    {
      public void run()
      {
        Arena.this.loadSpawnPoints();
        Arena.this.loadGlassBoxes();
        Arena.this.loadUsedPoints();
        Arena.this.checkPlayers();
      }
    }.runTaskLater(SkyWars.getPlugin(), 60L);
    
    this.loading = false;
  }
  
  public void loadSpawnPoints()
  {
    this.spawnpoints.clear();
    for (Object localObject : this.config.getList("spawnpoints")) {
      this.spawnpoints.add(LocationUtil.getLocation(localObject.toString()));
    }
  }
  
  public void loadGlassBoxes()
  {
    this.glassboxes.clear();
    for (Location localLocation : this.spawnpoints)
    {
      ArenaBox localArenaBox = new ArenaBox(localLocation);
      localArenaBox.setBox(SkyWars.boxes.getInt("boxes." + SkyWars.boxes.getString("default") + ".item"), SkyWars.boxes.getInt("boxes." + SkyWars.boxes.getString("default") + ".data"));
      this.glassboxes.add(localArenaBox);
    }
  }
  
  public void loadUsedPoints()
  {
    this.used.clear();
    for (Location localLocation : this.spawnpoints) {
      this.used.put(localLocation, Boolean.valueOf(false));
    }
  }
  
  public void checkPlayers()
  {
    addTimer(this. = new BukkitRunnable()
    {
      public void run()
      {
        if (Arena.this.isInGame())
        {
          if ((Arena.this.getAlivePlayers() <= 1) && (!Arena.this.isEnding())) {
            Arena.this.end();
          }
          if ((SkyWars.isBungeeMode()) && 
            (Bukkit.getOnlinePlayers().size() <= 1) && (!Arena.this.isEnding())) {
            Arena.this.end();
          }
        }
      }
    }, 0L, 100L);
  }
  
  public List<SkyPlayer> getPlayers()
  {
    return this.players;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getMaxPlayers()
  {
    return this.max_players;
  }
  
  public int getMinPlayers()
  {
    return this.min_players;
  }
  
  public World getWorld()
  {
    return this.world;
  }
  
  public Location getSpawn()
  {
    if (hasSpectSpawn()) {
      return LocationUtil.getLocation(this.config.getString("spectator_spawn"));
    }
    return getWorld().getSpawnLocation();
  }
  
  public Location getSpawnPoint()
  {
    for (Map.Entry localEntry : this.used.entrySet()) {
      if (!((Boolean)localEntry.getValue()).booleanValue()) {
        return (Location)localEntry.getKey();
      }
    }
    return null;
  }
  
  public List<Location> getLocations()
  {
    return this.spawnpoints;
  }
  
  public List<ArenaBox> getGlassBoxes()
  {
    return this.glassboxes;
  }
  
  public int getAlivePlayers()
  {
    int i = 0;
    for (SkyPlayer localSkyPlayer : this.players) {
      if (!localSkyPlayer.isSpectating()) {
        i++;
      }
    }
    return i;
  }
  
  public List<SkyPlayer> getAlivePlayer()
  {
    ArrayList localArrayList = new ArrayList();
    for (SkyPlayer localSkyPlayer : this.players) {
      if (!localSkyPlayer.isSpectating()) {
        localArrayList.add(localSkyPlayer);
      }
    }
    return localArrayList;
  }
  
  public Inventory getVoteInventory()
  {
    return this.votemenu.getInventory();
  }
  
  public String getSelectedChest()
  {
    this.selected_chest.clear();
    this.selected_chest.add("vote_chest_basic");
    this.selected_chest.add("vote_chest_normal");
    this.selected_chest.add("vote_chest_overpowered");
    
    int i = -1;
    String str1 = null;
    for (String str2 : this.selected_chest) {
      if (getInt(str2) > i)
      {
        i = getInt(str2);
        str1 = str2.split("_")[2];
      }
    }
    if (i <= 0) {
      return "default";
    }
    return str1;
  }
  
  public String getSelectedTime()
  {
    this.selected_time.clear();
    this.selected_time.add("vote_time_day");
    this.selected_time.add("vote_time_night");
    this.selected_time.add("vote_time_sunset");
    
    int i = -1;
    String str1 = null;
    for (String str2 : this.selected_time) {
      if (getInt(str2) > i)
      {
        i = getInt(str2);
        str1 = str2.split("_")[2];
      }
    }
    if (i <= 0) {
      return "default";
    }
    return str1;
  }
  
  public String getChest()
  {
    String str = getSelectedChest();
    if (str.equalsIgnoreCase("basic")) {
      return "chests.basic";
    }
    if (str.equalsIgnoreCase("normal")) {
      return "chests.normal";
    }
    if (str.equalsIgnoreCase("overpowered")) {
      return "chests.overpowered";
    }
    return "chests.normal";
  }
  
  public long getTime()
  {
    String str = getSelectedTime();
    if (str.equalsIgnoreCase("day")) {
      return 0L;
    }
    if (str.equalsIgnoreCase("night")) {
      return 18000L;
    }
    if (str.equalsIgnoreCase("sunset")) {
      return 12000L;
    }
    return 24000L;
  }
  
  public boolean isInGame()
  {
    return this.arena_started;
  }
  
  public boolean isWaiting()
  {
    return this.arena_waiting;
  }
  
  public boolean isStarting()
  {
    return this.arena_starting;
  }
  
  public boolean isEnding()
  {
    return this.arena_ending;
  }
  
  public boolean isLoading()
  {
    return this.loading;
  }
  
  public boolean isFull()
  {
    return this.players.size() >= getMaxPlayers();
  }
  
  public boolean isUsed(Location paramLocation)
  {
    return ((Boolean)this.used.get(paramLocation)).booleanValue();
  }
  
  public boolean isFirstJoined()
  {
    return this.firstjoined;
  }
  
  public boolean isNowFull()
  {
    return this.nowfull;
  }
  
  public boolean isForceStart()
  {
    return this.force_start;
  }
  
  public boolean isFallDamage()
  {
    return this.fall_damage;
  }
  
  public boolean hasSpectSpawn()
  {
    if (this.config.getString("spectator_spawn") != null) {
      return true;
    }
    return false;
  }
  
  public void setUsed(Location paramLocation, boolean paramBoolean)
  {
    if (this.used.containsKey(paramLocation)) {
      this.used.put(paramLocation, Boolean.valueOf(paramBoolean));
    }
  }
  
  public void setForceStart()
  {
    if (!this.force_start) {
      this.force_start = true;
    }
  }
  
  public void addTimer(BukkitRunnable paramBukkitRunnable, long paramLong1, long paramLong2)
  {
    this.tickers.add(paramBukkitRunnable);
    paramBukkitRunnable.runTaskTimer(SkyWars.getPlugin(), paramLong1, paramLong2);
  }
  
  public void removeTimer(BukkitRunnable paramBukkitRunnable)
  {
    this.tickers.remove(paramBukkitRunnable);
  }
  
  public void addPlayer(SkyPlayer paramSkyPlayer, boolean paramBoolean)
  {
    if (paramSkyPlayer == null)
    {
      SkyWars.log(String.format("Arena.addPlayer - Trying to add a NULL Player", new Object[0]));
      return;
    }
    if (isLoading())
    {
      SkyWars.log(String.format("Arena.addPlayer - Trying to join Player when game is Reloading", new Object[0]));
      paramSkyPlayer.sendMessage(SkyWars.getMessage("game.loading"));
      return;
    }
    if (this.players.size() >= this.max_players)
    {
      SkyWars.log(String.format("Arena.addPlayer - Trying to join Player whe game is Full", new Object[0]));
      paramSkyPlayer.sendMessage(SkyWars.getMessage("game.full.message"));
      return;
    }
    SkyWars.log(String.format("Arena.addPlayer - Player list is empty: " + this.players.isEmpty(), new Object[0]));
    if ((this.players.isEmpty()) && 
      (isWaiting())) {
      this.firstjoined = true;
    }
    Location localLocation1 = getSpawnPoint();
    SkyWars.log(String.format("Arena.addPlayer - Get Spawn Point " + localLocation1, new Object[0]));
    if (localLocation1 == null)
    {
      SkyWars.log(String.format("Arena.addPlayer - Trying to add a Player in a spawn point used", new Object[0]));
      paramSkyPlayer.getPlayer().kickPlayer(SkyWars.getMessage("game.spawn.used"));
      return;
    }
    paramSkyPlayer.reset();
    paramSkyPlayer.setArena(this);
    
    SkyWars.log(String.format("Arena.addPlayer - Player already in list: " + this.players.contains(paramSkyPlayer), new Object[0]));
    if (!this.players.contains(paramSkyPlayer))
    {
      this.players.add(paramSkyPlayer);
      SkyWars.log(String.format("Arena.addPlayer - Player add in list", new Object[0]));
    }
    for (Iterator localIterator = this.players.iterator(); localIterator.hasNext();)
    {
      localObject = (SkyPlayer)localIterator.next();
      ((SkyPlayer)localObject).getPlayer().showPlayer(paramSkyPlayer.getPlayer());
    }
    for (localIterator = this.glassboxes.iterator(); localIterator.hasNext();)
    {
      localObject = (ArenaBox)localIterator.next();
      Location localLocation2 = ((ArenaBox)localObject).getLocation();
      if (localLocation2 == localLocation1) {
        paramSkyPlayer.setBox((ArenaBox)localObject);
      }
    }
    SkyWars.log("Arena.addPlayer - " + paramSkyPlayer.getName() + " is teleporting to " + localLocation1.toString());
    Validate.notNull(localLocation1);
    setUsed(localLocation1, true);
    paramSkyPlayer.setArenaSpawn(localLocation1);
    paramSkyPlayer.getPlayer().teleport(localLocation1);
    if (paramBoolean) {
      recalculatePlayers();
    }
    Object localObject = paramSkyPlayer.getPlayer().getInventory();
    ((Inventory)localObject).clear();
    
    ((Inventory)localObject).setItem(0, new ItemBuilder(Material.PAPER).setTitle(SkyWars.getMessage("item.kits.name")).addLore(SkyWars.getMessage("item.kits.lore")).build());
    ((Inventory)localObject).setItem(1, new ItemBuilder(Material.DIAMOND).setTitle(SkyWars.getMessage("item.settings.name")).addLore(SkyWars.getMessage("item.settings.lore")).build());
    ((Inventory)localObject).setItem(2, new ItemBuilder(Material.EMPTY_MAP).setTitle(SkyWars.getMessage("item.vote.name")).addLore(SkyWars.getMessage("item.vote.lore")).build());
    paramSkyPlayer.updateInventory();
    SkyWars.log(String.format("Arena.addPlayer - Successfull add " + paramSkyPlayer.getName() + " to " + paramSkyPlayer.getArena().getName(), new Object[0]));
    if ((paramSkyPlayer.getBoxSection() != SkyWars.boxes.getString("default")) || (paramSkyPlayer.getBoxSection() != null)) {
      if (paramSkyPlayer.getBoxItem(paramSkyPlayer.getBoxSection()) != 0)
      {
        paramSkyPlayer.getBox().setBox(paramSkyPlayer.getBoxItem(paramSkyPlayer.getBoxSection()), paramSkyPlayer.getBoxData(paramSkyPlayer.getBoxSection()));
      }
      else
      {
        paramSkyPlayer.getPlayer().setMetadata("upload_me", new FixedMetadataValue(SkyWars.getPlugin(), Boolean.valueOf(true)));
        paramSkyPlayer.setBoxSection(SkyWars.boxes.getString("default"));
        paramSkyPlayer.getBox().setBox(paramSkyPlayer.getBoxItem(SkyWars.boxes.getString("default")), paramSkyPlayer.getBoxData(SkyWars.boxes.getString("default")));
      }
    }
    broadcast(String.format(SkyWars.getMessage("game.player.join"), new Object[] { paramSkyPlayer.getName(), Integer.valueOf(getAlivePlayers()), Integer.valueOf(getMaxPlayers()) }));
    
    SkyHologram.removeHologram(paramSkyPlayer);
  }
  
  public void removePlayer(SkyPlayer paramSkyPlayer, boolean paramBoolean)
  {
    setUsed(paramSkyPlayer.getArenaSpawn(), false);
    if (!paramSkyPlayer.isSpectating())
    {
      SkyWars.log("Arena.removePlayer - Removing to " + paramSkyPlayer.getName() + " from " + paramSkyPlayer.getArena().getName());
      if (paramSkyPlayer.getPlayer().isOnline())
      {
        paramSkyPlayer.reset();
        if (!SkyWars.isBungeeMode()) {
          SkyWars.goToSpawn(paramSkyPlayer);
        }
        if (this.players.contains(paramSkyPlayer)) {
          this.players.remove(paramSkyPlayer);
        }
        SkyWars.log("Arena.removePlayer - Successfull remove to " + paramSkyPlayer.getName() + " from " + getName());
      }
      broadcast(String.format(SkyWars.getMessage("game.player.quit"), new Object[] { paramSkyPlayer.getName(), Integer.valueOf(getAlivePlayers()), Integer.valueOf(getMaxPlayers()) }));
    }
    else
    {
      SkyWars.log("Arena.removePlayer - Removing spectator to " + paramSkyPlayer.getName() + " from " + paramSkyPlayer.getArena().getName());
      paramSkyPlayer.reset();
      paramSkyPlayer.setSpectating(false);
      if (paramSkyPlayer.getPlayer().isOnline())
      {
        if (!SkyWars.isBungeeMode()) {
          SkyWars.goToSpawn(paramSkyPlayer);
        }
        if (this.players.contains(paramSkyPlayer)) {
          this.players.remove(paramSkyPlayer);
        }
      }
      SkyWars.log("Arena.removePlayer - Successfull remove spectator to " + paramSkyPlayer.getName() + " from " + getName());
    }
    paramSkyPlayer.getBox().setBox(paramSkyPlayer.getBoxItem(SkyWars.boxes.getString("default")), paramSkyPlayer.getBoxData(SkyWars.boxes.getString("default")));
    paramSkyPlayer.setBox(null);
    paramSkyPlayer.setArena(null);
    paramSkyPlayer.setArenaSpawn(null);
    paramSkyPlayer.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR).unregister();
    paramSkyPlayer.reset();
    paramSkyPlayer.getPlayer().updateInventory();
    for (Player localPlayer : Bukkit.getOnlinePlayers()) {
      if (!localPlayer.canSee(paramSkyPlayer.getPlayer())) {
        localPlayer.showPlayer(paramSkyPlayer.getPlayer());
      }
    }
    if (paramBoolean) {
      recalculatePlayers();
    }
  }
  
  public void recalculatePlayers()
  {
    if ((isInGame()) || (isStarting()))
    {
      int i = 0;
      for (Object localObject2 = this.players.iterator(); ((Iterator)localObject2).hasNext();)
      {
        localObject1 = (SkyPlayer)((Iterator)localObject2).next();
        if (!((SkyPlayer)localObject1).isSpectating()) {
          i++;
        }
      }
      if (i <= 1)
      {
        localObject1 = null;
        for (Iterator localIterator = this.players.iterator(); localIterator.hasNext();)
        {
          localObject2 = (SkyPlayer)localIterator.next();
          if (!((SkyPlayer)localObject2).isSpectating())
          {
            ((SkyPlayer)localObject2).getPlayer().playSound(((SkyPlayer)localObject2).getPlayer().getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
            localObject1 = localObject2;
          }
        }
        if (localObject1 != null) {
          end((SkyPlayer)localObject1);
        }
      }
    }
    else if ((isFirstJoined()) && (this.players.size() >= this.min_players))
    {
      this.firstjoined = false;
      addTimer(this. = new BukkitRunnable()
      {
        public void run()
        {
          if (Arena.this.players.isEmpty())
          {
            Arena.this.start_countdown = 90;
            cancel();
          }
          SkyPlayer localSkyPlayer1;
          for (Iterator localIterator1 = Arena.this.players.iterator(); localIterator1.hasNext();)
          {
            localSkyPlayer1 = (SkyPlayer)localIterator1.next();
            localSkyPlayer1.getPlayer().setLevel(Arena.this.start_countdown);
          }
          if ((Arena.this.players.size() >= Arena.this.max_players) && (!Arena.this.isNowFull()))
          {
            Arena.this.nowfull = true;
            if (Arena.this.start_countdown > 10) {
              Arena.this.start_countdown = 10;
            }
            Arena.this.broadcast(String.format(SkyWars.getMessage("game.start.nowfull"), new Object[] { Integer.valueOf(Arena.this.start_countdown) }));
          }
          if ((Arena.this.start_countdown <= 20) && (Arena.this.start_countdown % 5 == 0) && (Arena.this.start_countdown > 0)) {
            Arena.this.broadcast(String.format(SkyWars.getMessage("game.start.countdown"), new Object[] { Integer.valueOf(Arena.this.start_countdown) }));
          }
          if ((Arena.this.isForceStart()) && (Arena.this.start_countdown > 3)) {
            Arena.this.start_countdown = 3;
          }
          if (Arena.this.start_countdown <= 0) {
            if ((Arena.this.players.size() < Arena.this.min_players) && (!Arena.this.isForceStart()))
            {
              Arena.this.start_countdown += 10;
              Arena.this.broadcast(SkyWars.getMessage("game.start.norequiredplayers"));
            }
            else
            {
              Arena.this.broadcast(SkyWars.getMessage("game.start.done"));
              cancel();
              Arena.this.countdown = null;
              Arena.this.start();
              Iterator localIterator2;
              for (localIterator1 = Arena.this.players.iterator(); localIterator1.hasNext(); localIterator2.hasNext())
              {
                localSkyPlayer1 = (SkyPlayer)localIterator1.next();
                localIterator2 = Arena.this.players.iterator(); continue;SkyPlayer localSkyPlayer2 = (SkyPlayer)localIterator2.next();
                if (localSkyPlayer2 != localSkyPlayer1) {
                  localSkyPlayer2.getPlayer().showPlayer(localSkyPlayer1.getPlayer());
                }
              }
            }
          }
          Arena.this.start_countdown -= 1;
        }
      }, 0L, 20L);
    }
    for (Object localObject1 = this.players.iterator(); ((Iterator)localObject1).hasNext();)
    {
      SkyPlayer localSkyPlayer = (SkyPlayer)((Iterator)localObject1).next();
      localSkyPlayer.updateScoreboard();
    }
  }
  
  public void start()
  {
    this.arena_waiting = false;
    this.arena_starting = true;
    
    addTimer(this. = new BukkitRunnable()
    {
      public void run()
      {
        Object localObject;
        for (Iterator localIterator1 = Arena.this.players.iterator(); localIterator1.hasNext();)
        {
          localObject = (SkyPlayer)localIterator1.next();
          ((SkyPlayer)localObject).getPlayer().setLevel(Arena.this.released_countdown);
        }
        if ((Arena.this.released_countdown <= 5) && (Arena.this.released_countdown > 0))
        {
          Arena.this.broadcast(String.format(SkyWars.getMessage("game.start.released"), new Object[] { Integer.valueOf(Arena.this.released_countdown) }));
          for (localIterator1 = Arena.this.players.iterator(); localIterator1.hasNext();)
          {
            localObject = (SkyPlayer)localIterator1.next();
            ((SkyPlayer)localObject).getPlayer().playSound(((SkyPlayer)localObject).getPlayer().getLocation(), Sound.CLICK, 1.0F, 1.0F);
          }
        }
        if (Arena.this.released_countdown == 0)
        {
          Arena.this.broadcast(SkyWars.getMessage("game.start.go"));
          for (localIterator1 = Arena.this.getGlassBoxes().iterator(); localIterator1.hasNext();)
          {
            localObject = (ArenaBox)localIterator1.next();
            ((ArenaBox)localObject).removeBase();
          }
          Arena.this.fall_damage = false;
          Arena.this.released = null;
          Arena.this.arena_starting = false;
          Arena.this.arena_started = true;
          
          Arena.this.chest = Arena.this.getChest();
          if (Arena.this.chest.contains("basic")) {
            Arena.this.broadcast(String.format(SkyWars.getMessage("selected.chest"), new Object[] { SkyWars.getMessage("selected.chest.basic") }));
          }
          if (Arena.this.chest.contains("normal")) {
            Arena.this.broadcast(String.format(SkyWars.getMessage("selected.chest"), new Object[] { SkyWars.getMessage("selected.chest.normal") }));
          }
          if (Arena.this.chest.contains("overpowered")) {
            Arena.this.broadcast(String.format(SkyWars.getMessage("selected.chest"), new Object[] { SkyWars.getMessage("selected.chest.overpowered") }));
          }
          if (Arena.this.chest.contains("default")) {
            Arena.this.broadcast(SkyWars.getMessage("selected.chest.default"));
          }
          long l = Arena.this.getTime();
          if (l == 0L) {
            Arena.this.broadcast(String.format(SkyWars.getMessage("selected.time"), new Object[] { SkyWars.getMessage("selected.time.day") }));
          }
          if (l == 18000L) {
            Arena.this.broadcast(String.format(SkyWars.getMessage("selected.time"), new Object[] { SkyWars.getMessage("selected.time.night") }));
          }
          if (l == 12000L) {
            Arena.this.broadcast(String.format(SkyWars.getMessage("selected.time"), new Object[] { SkyWars.getMessage("selected.time.sunset") }));
          }
          if (l == 24000L) {
            Arena.this.broadcast(SkyWars.getMessage("selected.time.default"));
          }
          Arena.this.getWorld().setTime(l);
          
          Arena.this.fillAllChests();
          for (SkyPlayer localSkyPlayer : Arena.this.players)
          {
            localSkyPlayer.getPlayer().getInventory().clear();
            localSkyPlayer.getPlayer().closeInventory();
            if (localSkyPlayer.hasKit())
            {
              Kit localKit = localSkyPlayer.getKit();
              for (ItemStack localItemStack : localKit.getItems()) {
                localSkyPlayer.getPlayer().getInventory().addItem(new ItemStack[] { localItemStack });
              }
            }
            localSkyPlayer.resetVotes();
            localSkyPlayer.addPlayed(1);
            localSkyPlayer.getPlayer().setMetadata("upload_stats", new FixedMetadataValue(SkyWars.getPlugin(), Boolean.valueOf(true)));
          }
        }
        if (Arena.this.released_countdown == -5)
        {
          cancel();
          Arena.this.fall_damage = true;
        }
        Arena.this.released_countdown -= 1;
      }
    }, 0L, 20L);
  }
  
  public void end(final SkyPlayer paramSkyPlayer)
  {
    if (isEnding()) {
      return;
    }
    new BukkitRunnable()
    {
      public void run()
      {
        Arena.this.broadcast(String.format(SkyWars.getMessage("game.finish.broadcast.winner"), new Object[] { paramSkyPlayer.getName(), Arena.this.name }));
        paramSkyPlayer.addCoins(SkyWars.getPlugin().getConfig().getInt("reward.win"));
        paramSkyPlayer.addWins(1);
        paramSkyPlayer.getPlayer().setMetadata("upload_stats", new FixedMetadataValue(SkyWars.getPlugin(), Boolean.valueOf(true)));
        paramSkyPlayer.reset();
        
        Arena.this.addTimer(new BukkitRunnable()
        {
          public void run()
          {
            Arena.this.launchFirework(this.val$player);
          }
        }, 0L, 20L);
        Arena.this.arena_ending = true;
        Arena.this.end();
      }
    }.runTaskLater(SkyWars.getPlugin(), 5L);
  }
  
  public void end()
  {
    addTimer(new BukkitRunnable()
    {
      public void run()
      {
        Iterator localIterator;
        Player localPlayer;
        if (Arena.this.end_countdown == 0) {
          if (SkyWars.isBungeeMode()) {
            for (localIterator = Bukkit.getOnlinePlayers().iterator(); localIterator.hasNext();)
            {
              localPlayer = (Player)localIterator.next();
              BungeeUtils.teleToServer(localPlayer, SkyWars.getMessage("player.teleport.lobby"), SkyWars.getPlugin().getConfig().getString("lobby_server"));
            }
          } else {
            Arena.this.restart();
          }
        }
        if ((Arena.this.end_countdown == -3) && 
          (SkyWars.isBungeeMode())) {
          if (SkyWars.isAutoStart())
          {
            for (localIterator = Bukkit.getOnlinePlayers().iterator(); localIterator.hasNext();)
            {
              localPlayer = (Player)localIterator.next();
              localPlayer.kickPlayer(SkyWars.getMessage("game.restart"));
            }
            Arena.this.restart();
          }
          else
          {
            Bukkit.shutdown();
          }
        }
        Arena.this.end_countdown -= 1;
      }
    }, 0L, 20L);
  }
  
  public void restart()
  {
    this.arena_ending = false;
    this.arena_started = false;
    this.arena_starting = false;
    this.arena_waiting = true;
    this.force_start = false;
    this.firstjoined = false;
    this.nowfull = false;
    this.fall_damage = true;
    
    clearData();
    addData("vote_chest_basic", Integer.valueOf(0));
    addData("vote_chest_normal", Integer.valueOf(0));
    addData("vote_chest_overpowered", Integer.valueOf(0));
    
    addData("vote_time_day", Integer.valueOf(0));
    addData("vote_time_night", Integer.valueOf(0));
    addData("vote_time_sunset", Integer.valueOf(0));
    Object localObject;
    for (Iterator localIterator1 = this.players.iterator(); localIterator1.hasNext();)
    {
      localObject = (SkyPlayer)localIterator1.next();
      if (((SkyPlayer)localObject).isSpectating()) {
        ((SkyPlayer)localObject).setSpectating(false);
      }
      ((SkyPlayer)localObject).resetVotes();
      if (!SkyWars.isBungeeMode()) {
        SkyWars.goToSpawn((SkyPlayer)localObject);
      }
      ((SkyPlayer)localObject).setBox(null);
      ((SkyPlayer)localObject).setArena(null);
      ((SkyPlayer)localObject).setSpectating(false);
      ((SkyPlayer)localObject).setArenaSpawn(null);
      ((SkyPlayer)localObject).getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR).unregister();
      ((SkyPlayer)localObject).reset();
      ((SkyPlayer)localObject).getPlayer().updateInventory();
      for (SkyPlayer localSkyPlayer : this.players) {
        if (localSkyPlayer != localObject) {
          localSkyPlayer.getPlayer().showPlayer(((SkyPlayer)localObject).getPlayer());
        }
      }
      ((SkyPlayer)localObject).updateScoreboard();
    }
    this.players.clear();
    this.used.clear();
    this.glassboxes.clear();
    this.selected_chest.clear();
    this.selected_time.clear();
    this.start_countdown = 80;
    this.end_countdown = 10;
    this.released_countdown = 10;
    for (localIterator1 = this.tickers.iterator(); localIterator1.hasNext();)
    {
      localObject = (BukkitRunnable)localIterator1.next();
      if (localObject != this.check) {
        ((BukkitRunnable)localObject).cancel();
      }
    }
    this.tickers.clear();
    new BukkitRunnable()
    {
      public void run()
      {
        Arena.this.reloadWorld();
      }
    }.runTaskLater(SkyWars.getPlugin(), 20L);
  }
  
  public void broadcast(String paramString)
  {
    for (SkyPlayer localSkyPlayer : this.players)
    {
      SkyWars.log(String.format("Arena.broadcast - Broadcast to " + localSkyPlayer.getName(), new Object[0]));
      localSkyPlayer.sendMessage(paramString);
    }
  }
  
  public void launchFirework(SkyPlayer paramSkyPlayer)
  {
    Location localLocation = paramSkyPlayer.getPlayer().getLocation();
    Firework localFirework = (Firework)localLocation.getWorld().spawnEntity(localLocation, EntityType.FIREWORK);
    FireworkMeta localFireworkMeta = localFirework.getFireworkMeta();
    localFireworkMeta.addEffect(FireworkEffect.builder().flicker(true).with(FireworkEffect.Type.STAR).withColor(new Color[] { Color.YELLOW, Color.WHITE }).withFade(Color.YELLOW).build());
    localFireworkMeta.setPower(1);
    localFirework.setFireworkMeta(localFireworkMeta);
  }
  
  public void fillAllChests()
  {
    Chunk[] arrayOfChunk;
    int j = (arrayOfChunk = getWorld().getLoadedChunks()).length;
    for (int i = 0; i < j; i++)
    {
      Chunk localChunk = arrayOfChunk[i];
      BlockState[] arrayOfBlockState;
      int m = (arrayOfBlockState = localChunk.getTileEntities()).length;
      for (int k = 0; k < m; k++)
      {
        BlockState localBlockState = arrayOfBlockState[k];
        if ((localBlockState instanceof Chest))
        {
          Inventory localInventory = ((Chest)localBlockState).getInventory();
          fillChests(localInventory);
        }
      }
    }
  }
  
  public void fillChests(Inventory paramInventory)
  {
    paramInventory.clear();
    for (int i = 0; i < SkyWars.getPlugin().getConfig().getInt("max_items_types_chest"); i++) {
      paramInventory.setItem(this.r.nextInt(paramInventory.getSize() - 1), nextitem());
    }
  }
  
  public ItemStack nextitem()
  {
    return read((String)SkyWars.chests.getStringList(this.chest).get(this.r.nextInt(SkyWars.chests.getStringList(this.chest).size() - 1)));
  }
  
  public static ItemStack read(String paramString)
  {
    String[] arrayOfString = paramString.split(",");
    ArrayList localArrayList = new ArrayList();
    ItemStack localItemStack = new ItemStack(Integer.parseInt(arrayOfString[0]), Integer.parseInt(arrayOfString[1]), Short.parseShort(arrayOfString[2]));
    for (int i = 1; i < arrayOfString.length; i++)
    {
      ItemMeta localItemMeta;
      if (arrayOfString[i].startsWith("lore:"))
      {
        localItemMeta = localItemStack.getItemMeta();
        String str1 = arrayOfString[i].replace("lore:", "");
        String str3 = ChatColor.translateAlternateColorCodes('&', str1);
        localArrayList.add(str3);localItemMeta.setLore(localArrayList);localItemStack.setItemMeta(localItemMeta);
      }
      Enchantment[] arrayOfEnchantment;
      int k = (arrayOfEnchantment = Enchantment.values()).length;
      for (int j = 0; j < k; j++)
      {
        localItemMeta = arrayOfEnchantment[j];
        if (arrayOfString[i].toUpperCase().startsWith(localItemMeta.getName().toUpperCase()))
        {
          String str4 = arrayOfString[i].replace(localItemMeta.getName().toUpperCase() + ":", "");
          localItemStack.addUnsafeEnchantment(localItemMeta, Integer.parseInt(str4));
        }
      }
      if (arrayOfString[i].startsWith("name:"))
      {
        localItemMeta = localItemStack.getItemMeta();
        String str2 = arrayOfString[i].replace("name:", "");
        localItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', str2));
        localItemStack.setItemMeta(localItemMeta);
      }
    }
    return localItemStack;
  }
  
  public void goToSpawn(SkyPlayer paramSkyPlayer)
  {
    paramSkyPlayer.getPlayer().teleport(getSpawn());
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\arena\Arena.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */