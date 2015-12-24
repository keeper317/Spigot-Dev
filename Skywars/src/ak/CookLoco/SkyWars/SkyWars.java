package ak.CookLoco.SkyWars;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.arena.ArenaManager;
import ak.CookLoco.SkyWars.arena.sign.SignManager;
import ak.CookLoco.SkyWars.box.BoxManager;
import ak.CookLoco.SkyWars.commands.CmdExecutor;
import ak.CookLoco.SkyWars.commands.CmdOthers;
import ak.CookLoco.SkyWars.database.Querys;
import ak.CookLoco.SkyWars.database.SQLConnectionThread;
import ak.CookLoco.SkyWars.database.SQLQueryThread;
import ak.CookLoco.SkyWars.kit.KitManager;
import ak.CookLoco.SkyWars.listener.DamageListener;
import ak.CookLoco.SkyWars.listener.InteractListener;
import ak.CookLoco.SkyWars.listener.LoginListener;
import ak.CookLoco.SkyWars.listener.PlayerListener;
import ak.CookLoco.SkyWars.listener.TrailListener;
import ak.CookLoco.SkyWars.listener.WorldListener;
import ak.CookLoco.SkyWars.menus.SettingsMenu;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.CustomConfig;
import ak.CookLoco.SkyWars.utils.FileResClassLoader;
import ak.CookLoco.SkyWars.utils.LocationUtil;
import ak.CookLoco.SkyWars.utils.SkyHologram;
import ak.CookLoco.SkyWars.utils.economy.SkyEconomy;

public class SkyWars
  extends JavaPlugin
  implements Listener
{
  public static CustomConfig Custom = null;
  public static CustomConfig lang_en = null;
  public static CustomConfig lang_es = null;
  public static CustomConfig lang_nl = null;
  private static ResourceBundle messageBundle;
  private static ResourceBundle customBundle;
  private static final ResourceBundle NULL_BUNDLE = null;
  public static SkyWars plugin;
  public static SettingsMenu settings_menu;
  public static List<String> added = new ArrayList();
  public static HashMap<String, SkyPlayer> skyplayer = new HashMap();
  public static org.bukkit.Location spawn;
  public static List<Location> hologram = new ArrayList();
  public static String arenas = "games";
  public static String kits = "kits";
  public static String maps = "maps";
  public SQLQueryThread sql_worker;
  public static volatile CopyOnWriteArrayList<String> sql_query = new CopyOnWriteArrayList();
  public static File chests_file = null;
  public static FileConfiguration chests = null;
  public static File boxes_file = null;
  public static FileConfiguration boxes = null;
  public static File signs_file = null;
  public static FileConfiguration signs = null;
  public static File score_file = null;
  public static FileConfiguration score = null;
  public static boolean editmode;
  public static boolean holo;
  public static String prefix = "&8[&7SkyWars&8] ";
  
  public void onEnable()
  {
    plugin = this;
    
    isSet("check_updates", Boolean.valueOf(true));
    isSet("debug", Boolean.valueOf(false));
    isSet("locale", "en");
    isSet("kitmenu_rows", Integer.valueOf(3));
    isSet("kit_permission", Boolean.valueOf(false));
    isSet("editmode", Boolean.valueOf(true));
    isSet("spawn", "");
    isSet("lobby_server", "Lobby");
    isSet("max_items_types_chest", Integer.valueOf(15));
    isSet("economy.mode", "PlayerPoints");
    isSet("economy.craftconomy3_currency", "Dollar");
    isSet("economy.command_add", "money give %player% %amount%");
    isSet("economy.command_remove", "money take %player% %amount%");
    isSet("reward.death", Integer.valueOf(1));
    isSet("reward.kill", Integer.valueOf(2));
    isSet("reward.win", Integer.valueOf(10));
    isSet("mode.bungee", Boolean.valueOf(true));
    isSet("mode.bungee-autostart", Boolean.valueOf(true));
    isSet("mode.bungeerandom", Boolean.valueOf(true));
    isSet("mode.bungeemapset", "default");
    isSet("data.type", "SQLite");
    isSet("data.mysql.server", "localhost");
    isSet("data.mysql.db", "SkyWars");
    isSet("data.mysql.user", "root");
    isSet("data.mysql.port", Integer.valueOf(3306));
    isSet("data.mysql.password", "password");
    isSet("options.weather", Boolean.valueOf(false));
    isSet("options.creaturespawn", Boolean.valueOf(false));
    saveConfig();
    
    score_file = new File(getPlugin().getDataFolder(), "scoreboard.yml");
    if (!score_file.exists()) {
      saveResource("scoreboard.yml", false);
    }
    score = YamlConfiguration.loadConfiguration(score_file);
    
    chests_file = new File(getPlugin().getDataFolder(), "chests.yml");
    if (!chests_file.exists()) {
      saveResource("chests.yml", false);
    }
    chests = YamlConfiguration.loadConfiguration(chests_file);
    
    boxes_file = new File(getPlugin().getDataFolder(), "boxes.yml");
    if (!boxes_file.exists()) {
      saveResource("boxes.yml", false);
    }
    boxes = YamlConfiguration.loadConfiguration(boxes_file);
    
    signs_file = new File(getPlugin().getDataFolder(), "signs.yml");
    if (!signs_file.exists()) {
      saveResource("signs.yml", false);
    }
    signs = YamlConfiguration.loadConfiguration(signs_file);
    
    File localFile1 = new File(getDataFolder(), arenas);
    File localFile2 = new File(getDataFolder(), kits);
    File localFile3 = new File(maps);
    if (!localFile1.exists()) {
      localFile1.mkdirs();
    }
    if (!localFile3.exists()) {
      localFile3.mkdirs();
    }
    if (!localFile2.exists())
    {
      localFile2.mkdirs();
      saveResource("kits/Noobly.yml", false);
      saveResource("kits/Builder.yml", false);
      saveResource("kits/Blacksmith.yml", false);
    }
    console(prefix + "&aLoading all config files");
    
    SkyEconomy.load();
    
    console(prefix + "&aEconomy: &e" + getConfig().getString("economy.mode"));
    
    Custom = new CustomConfig(this);
    lang_en = new CustomConfig("messages_en");
    lang_es = new CustomConfig("messages_es");
    lang_nl = new CustomConfig("messages_nl");
    Custom.saveDefaultConfig(lang_en);
    Custom.saveDefaultConfig(lang_es);
    Custom.saveDefaultConfig(lang_nl);
    
    editmode = getConfig().getBoolean("editmode");
    
    getServer().getPluginManager().registerEvents(this, this);
    getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    getServer().getPluginManager().registerEvents(new TrailListener(), this);
    getServer().getPluginManager().registerEvents(new InteractListener(), this);
    getServer().getPluginManager().registerEvents(new DamageListener(), this);
    getServer().getPluginManager().registerEvents(new LoginListener(), this);
    getServer().getPluginManager().registerEvents(new SignManager(), this);
    getServer().getPluginManager().registerEvents(new WorldListener(), this);
    
    getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    try
    {
      messageBundle = ResourceBundle.getBundle("messages", new Locale(getConfig().getString("locale", "en")));
    }
    catch (MissingResourceException localMissingResourceException1)
    {
      messageBundle = NULL_BUNDLE;
    }
    try
    {
      customBundle = ResourceBundle.getBundle("messages", new Locale(getConfig().getString("locale", "en")), new FileResClassLoader(SkyWars.class.getClassLoader()));
    }
    catch (MissingResourceException localMissingResourceException2)
    {
      customBundle = NULL_BUNDLE;
    }
    console(prefix + "&aLoading lang files");
    
    PreparedStatement localPreparedStatement = null;
    try
    {
      localPreparedStatement = SQLConnectionThread.getConnection().prepareStatement(getMysql() ? Querys.MYSQL_CREATE.getQuery() : Querys.SQLITE_CREATE.getQuery());
      localPreparedStatement.execute();
    }
    catch (SQLException localSQLException)
    {
      localSQLException.printStackTrace();
    }
    BoxManager.initBoxes();
    KitManager.initKits();
    
    console(prefix + "&aLoading arenas (Games)");
    ArenaManager.initGames();
    console(prefix + "&e" + ArenaManager.getGames().length + " arenas &ahave been enabled");
    
    settings_menu = new SettingsMenu();
    
    this.sql_worker = new SQLQueryThread();this.sql_worker.start();
    String str;
    if (!getConfig().getString("spawn").isEmpty())
    {
      str = getConfig().getString("spawn");
      spawn = LocationUtil.getLocation(str);
    }
    else
    {
      spawn = ((World)Bukkit.getWorlds().get(0)).getSpawnLocation();
    }
    holo = Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays");
    if (holo)
    {
      for (Iterator localIterator = score.getStringList("hologram.locations").iterator(); localIterator.hasNext();)
      {
        str = (String)localIterator.next();
        hologram.add(LocationUtil.getLocation(str));
      }
      console(prefix + "&aHologram has been enabled");
    }
    getCommand("sw").setExecutor(new CmdExecutor());
    getCommand("leave").setExecutor(new CmdOthers(this));
  }
  
  public void onDisable()
  {
    for (SkyPlayer localSkyPlayer : skyplayer.values()) {
      localSkyPlayer.unload();
    }
    this.sql_worker.interrupt();
    console(prefix + "&cDisabling all data");
  }
  
  public static SkyWars getPlugin()
  {
    return plugin;
  }
  
  public static void isSet(String paramString, Object paramObject)
  {
    if (!getPlugin().getConfig().isSet(paramString)) {
      getPlugin().getConfig().set(paramString, paramObject);
    }
  }
  
  private static String translate(String paramString)
  {
    try
    {
      return customBundle.getString(paramString);
    }
    catch (MissingResourceException localMissingResourceException1)
    {
      return messageBundle.getString(paramString);
    }
  }
  
  public static String getMessage(String paramString)
  {
    if (messageBundle.containsKey(paramString)) {
      return ChatColor.translateAlternateColorCodes('&', translate(paramString));
    }
    return paramString;
  }
  
  public static String getMapSet()
  {
    return getPlugin().getConfig().getString("mode.bungeemapset");
  }
  
  public static SkyPlayer getSkyPlayer(Player paramPlayer)
  {
    if (skyplayer.containsKey(paramPlayer.getName())) {
      return (SkyPlayer)skyplayer.get(paramPlayer.getName());
    }
    return null;
  }
  
  public static Location getSpawn()
  {
    return spawn;
  }
  
  public static List<Location> getHoloLocations()
  {
    return hologram;
  }
  
  public static String getLobbyVariables(SkyPlayer paramSkyPlayer, String paramString)
  {
    String str = paramString.replace("%player%", paramSkyPlayer.getName())
      .replace("%stats_kills%", "" + paramSkyPlayer.getKills())
      .replace("%stats_deaths%", "" + paramSkyPlayer.getDeaths())
      .replace("%stats_wins%", "" + paramSkyPlayer.getWins())
      .replace("%stats_played%", "" + paramSkyPlayer.getPlayed())
      .replace("%coins%", "" + paramSkyPlayer.getCoins());
    return str;
  }
  
  public static void log(String paramString)
  {
    if (getPlugin().getConfig().getBoolean("debug")) {
      System.out.println(paramString);
    }
  }
  
  public static boolean isBungeeMode()
  {
    if (getPlugin().getConfig().getBoolean("mode.bungee")) {
      return true;
    }
    return false;
  }
  
  public static boolean isMultiArenaMode()
  {
    if (!getPlugin().getConfig().getBoolean("mode.bungee")) {
      return true;
    }
    return false;
  }
  
  public static boolean isAutoStart()
  {
    if (getPlugin().getConfig().getBoolean("mode.bungee-autostart")) {
      return true;
    }
    return false;
  }
  
  public static boolean isEditMode()
  {
    return editmode;
  }
  
  public static boolean isRandomMap()
  {
    if (getPlugin().getConfig().getBoolean("mode.bungeerandom")) {
      return true;
    }
    return false;
  }
  
  public static void goToSpawn(SkyPlayer paramSkyPlayer)
  {
    paramSkyPlayer.getPlayer().teleport(spawn);
    if ((holo) && 
      (!getHoloLocations().isEmpty())) {
      SkyHologram.createHologram(paramSkyPlayer);
    }
  }
  
  public static void console(String paramString)
  {
    ConsoleCommandSender localConsoleCommandSender = Bukkit.getServer().getConsoleSender();
    localConsoleCommandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', paramString));
  }
  
  @EventHandler
  public void ping(ServerListPingEvent paramServerListPingEvent)
  {
    if (isBungeeMode())
    {
      Arena[] arrayOfArena;
      int j = (arrayOfArena = ArenaManager.getGames()).length;
      for (int i = 0; i < j; i++)
      {
        Arena localArena = arrayOfArena[i];
        paramServerListPingEvent.setMaxPlayers(localArena.getMaxPlayers());
        if (localArena.isLoading())
        {
          paramServerListPingEvent.setMotd(getMessage("motd.loading"));
          return;
        }
        if (localArena.isWaiting()) {
          paramServerListPingEvent.setMotd(getMessage("motd.waiting"));
        }
        if (localArena.isStarting()) {
          paramServerListPingEvent.setMotd(getMessage("motd.starting"));
        }
        if (localArena.isInGame()) {
          paramServerListPingEvent.setMotd(getMessage("motd.ingame"));
        }
        if (localArena.isEnding()) {
          paramServerListPingEvent.setMotd(getMessage("motd.ending"));
        }
      }
    }
  }
  
  public static boolean getMysql()
  {
    if (getPlugin().getConfig().getString("data.type").equalsIgnoreCase("MySQL")) {
      return true;
    }
    if (getPlugin().getConfig().getString("data.type").equalsIgnoreCase("SQLite")) {
      return false;
    }
    return false;
  }
  
  public static boolean checkUpdate()
  {
    if (!getPlugin().getConfig().getBoolean("check_updates")) {
      return false;
    }
    try
    {
      int i = 6525;
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL("http://www.spigotmc.org/api/general.php").openConnection();
      localHttpURLConnection.setDoOutput(true);
      localHttpURLConnection.setRequestMethod("POST");
      localHttpURLConnection.getOutputStream().write(
        ("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + i).getBytes("UTF-8"));
      String str1 = new BufferedReader(new InputStreamReader(localHttpURLConnection.getInputStream())).readLine();
      String str2 = getPlugin().getDescription().getVersion();
      if (!str2.equalsIgnoreCase(str1)) {
        return true;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return false;
    }
    return false;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\SkyWars.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */