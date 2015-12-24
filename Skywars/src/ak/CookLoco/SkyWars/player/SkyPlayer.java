package ak.CookLoco.SkyWars.player;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.arena.ArenaBox;
import ak.CookLoco.SkyWars.database.Querys;
import ak.CookLoco.SkyWars.database.SQLConnectionThread;
import ak.CookLoco.SkyWars.kit.Kit;
import ak.CookLoco.SkyWars.kit.KitManager;
import ak.CookLoco.SkyWars.utils.ItemBuilder;
import ak.CookLoco.SkyWars.utils.ParticleEffect;
import ak.CookLoco.SkyWars.utils.SkyData;
import ak.CookLoco.SkyWars.utils.SkyScoreboard;
import ak.CookLoco.SkyWars.utils.Utils;
import ak.CookLoco.SkyWars.utils.economy.SkyEconomy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Player.Spigot;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

public class SkyPlayer
  extends SkyData
{
  private Player player;
  private String name;
  private Arena arena;
  private Kit kit;
  private ArenaBox box;
  private ParticleEffect trail;
  private String boxsection;
  private HashSet<String> owned_kits = new HashSet();
  private Location arena_spawn;
  private boolean spectating;
  private int wins = 0;
  private int kills = 0;
  private int deaths = 0;
  private int played = 0;
  
  public SkyPlayer(Player paramPlayer)
  {
    this.player = paramPlayer;
    this.name = paramPlayer.getName();
    load();
  }
  
  public Player getPlayer()
  {
    return this.player;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Arena getArena()
  {
    return this.arena;
  }
  
  public Kit getKit()
  {
    return this.kit;
  }
  
  public ArenaBox getBox()
  {
    return this.box;
  }
  
  public ParticleEffect getTrail()
  {
    return this.trail;
  }
  
  public int getWins()
  {
    return this.wins;
  }
  
  public int getKills()
  {
    return this.kills;
  }
  
  public int getDeaths()
  {
    return this.deaths;
  }
  
  public int getPlayed()
  {
    return this.played;
  }
  
  public Location getArenaSpawn()
  {
    if (getArena() != null) {
      return this.arena_spawn;
    }
    return null;
  }
  
  public void addWins(int paramInt)
  {
    this.wins += paramInt;
  }
  
  public void addKills(int paramInt)
  {
    this.kills += paramInt;
  }
  
  public void addDeaths(int paramInt)
  {
    this.deaths += paramInt;
  }
  
  public void addPlayed(int paramInt)
  {
    this.played += paramInt;
  }
  
  public void setArena(Arena paramArena)
  {
    this.arena = paramArena;
  }
  
  public void setKit(Kit paramKit)
  {
    this.kit = paramKit;
  }
  
  public void setBox(ArenaBox paramArenaBox)
  {
    this.box = paramArenaBox;
  }
  
  public void setTrail(ParticleEffect paramParticleEffect)
  {
    this.trail = paramParticleEffect;
  }
  
  public void setSpectating(boolean paramBoolean)
  {
    this.spectating = paramBoolean;
    Arena localArena = getArena();
    if (localArena == null) {
      return;
    }
    if (paramBoolean)
    {
      for (SkyPlayer localSkyPlayer : localArena.getPlayers()) {
        if (localSkyPlayer != this.player)
        {
          if ((localSkyPlayer == null) || (localSkyPlayer.isSpectating())) {
            this.player.getPlayer().hidePlayer(localSkyPlayer.getPlayer());
          }
          localSkyPlayer.getPlayer().hidePlayer(this.player.getPlayer());
        }
      }
      getPlayer().setAllowFlight(true);
      getPlayer().setFlying(true);
      getPlayer().spigot().setCollidesWithEntities(false);
      new BukkitRunnable()
      {
        public void run()
        {
          Player localPlayer = SkyPlayer.this.player.getPlayer();
          localPlayer.getInventory().clear();
          
          SkyPlayer.this.player.getPlayer().getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setTitle(SkyWars.getMessage("item.spectator.tracker.name")).build());
          SkyPlayer.this.player.getPlayer().getInventory().setItem(8, new ItemBuilder(Material.NETHER_STAR).setTitle(SkyWars.getMessage("item.spectator.exit.name")).build());
          SkyPlayer.this.player.updateInventory();
        }
      }.runTaskLater(SkyWars.getPlugin(), 3L);
    }
    else
    {
      getPlayer().setAllowFlight(false);
      getPlayer().setFlying(false);
      if (!getPlayer().spigot().getCollidesWithEntities()) {
        getPlayer().spigot().setCollidesWithEntities(true);
      }
    }
    localArena.recalculatePlayers();
  }
  
  public void setArenaSpawn(Location paramLocation)
  {
    this.arena_spawn = paramLocation;
  }
  
  public void setPlayer(Player paramPlayer)
  {
    this.player = paramPlayer;
  }
  
  public boolean isSpectating()
  {
    return this.spectating;
  }
  
  public boolean isInArena()
  {
    if (getArena() != null) {
      return true;
    }
    return false;
  }
  
  public boolean hasKit()
  {
    if (getKit() != null) {
      return true;
    }
    return false;
  }
  
  public boolean hasBox()
  {
    if (getBox() != null) {
      return true;
    }
    return false;
  }
  
  public boolean hasTrail()
  {
    if (getTrail() != null) {
      return true;
    }
    return false;
  }
  
  public boolean hasPermissions(String paramString)
  {
    return getPlayer().hasPermission(paramString);
  }
  
  public void sendMessage(String paramString)
  {
    this.player.sendMessage(Utils.color(paramString));
  }
  
  public void sendMessage(String paramString, Object... paramVarArgs)
  {
    sendMessage(String.format(paramString, paramVarArgs));
  }
  
  public void reset()
  {
    this.player.getInventory().setArmorContents(null);
    this.player.getInventory().clear();
    for (PotionEffect localPotionEffect : this.player.getActivePotionEffects()) {
      this.player.removePotionEffect(localPotionEffect.getType());
    }
    this.player.setHealth(20.0D);
    this.player.setFoodLevel(20);
    this.player.setExp(0.0F);
    this.player.setLevel(0);
    this.player.setFlying(false);
    this.player.setAllowFlight(false);
    updateInventory();
  }
  
  public void resetVotes()
  {
    if (hasData("voted_chest"))
    {
      if (hasData("voted_chest_basic"))
      {
        getArena().addData("vote_chest_basic", Integer.valueOf(getArena().getInt("vote_chest_basic") - 1));
        removeData("voted_chest_basic");
      }
      if (hasData("voted_chest_normal"))
      {
        getArena().addData("vote_chest_normal", Integer.valueOf(getArena().getInt("vote_chest_normal") - 1));
        removeData("voted_chest_normal");
      }
      if (hasData("voted_chest_overpowered"))
      {
        getArena().addData("vote_chest_overpowered", Integer.valueOf(getArena().getInt("vote_chest_overpowered") - 1));
        removeData("voted_chest_overpowered");
      }
      removeData("voted_chest");
    }
    if (hasData("voted_time"))
    {
      if (hasData("voted_time_day"))
      {
        getArena().addData("vote_time_day", Integer.valueOf(getArena().getInt("vote_time_day") - 1));
        removeData("voted_time_day");
      }
      if (hasData("voted_time_night"))
      {
        getArena().addData("vote_time_night", Integer.valueOf(getArena().getInt("vote_time_night") - 1));
        removeData("voted_time_night");
      }
      if (hasData("voted_time_sunset"))
      {
        getArena().addData("vote_time_sunset", Integer.valueOf(getArena().getInt("vote_time_sunset") - 1));
        removeData("voted_time_sunset");
      }
      removeData("voted_time");
    }
  }
  
  public void updateInventory()
  {
    this.player.updateInventory();
  }
  
  public void updateScoreboard()
  {
    this.player.getPlayer().setScoreboard(SkyScoreboard.getScoreboard(this));
    SkyWars.log(String.format("SkyPlayer.updateScoreboard - Update Scoreboard to " + this.player.getName(), new Object[0]));
  }
  
  public int getCoins()
  {
    return SkyEconomy.getCoins(this.player);
  }
  
  public void removeCoins(int paramInt)
  {
    SkyEconomy.removeCoins(this.player, paramInt);
    this.player.sendMessage(String.format(SkyWars.getMessage("player.coins.less"), new Object[] { Integer.valueOf(paramInt) }));
    this.player.playSound(getPlayer().getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
  }
  
  public void addCoins(int paramInt)
  {
    if (this.player.hasPermission("skywars.vip.coin.2")) {
      paramInt *= 3;
    }
    if (this.player.hasPermission("skywars.vip.coin.3")) {
      paramInt *= 2;
    }
    SkyEconomy.addCoins(this.player, paramInt);
    sendMessage(String.format(SkyWars.getMessage("player.coins.add"), new Object[] { Integer.valueOf(paramInt) }));
    getPlayer().playSound(getPlayer().getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
  }
  
  public void load()
  {
    loadData();
    
    loadStats();
  }
  
  public void loadData()
  {
    PreparedStatement localPreparedStatement = null;
    try
    {
      localPreparedStatement = SQLConnectionThread.getConnection().prepareStatement(String.format(SkyWars.getMysql() ? Querys.MYSQL_SELECT_DATA.getQuery() : Querys.SQLITE_SELECT_DATA.getQuery(), new Object[] { getName() }));
      localPreparedStatement.execute();
      ResultSet localResultSet = localPreparedStatement.getResultSet();
      if (localResultSet.next())
      {
        if (localResultSet.getString("kits") != null)
        {
          String str = localResultSet.getString("kits");
          Kit[] arrayOfKit;
          int j = (arrayOfKit = KitManager.getKits()).length;
          for (int i = 0; i < j; i++)
          {
            Kit localKit = arrayOfKit[i];
            if (str.contains(localKit.getName())) {
              addKit(localKit);
            }
          }
        }
        if (localResultSet.getString("last_colour") != null) {
          setBoxSection(localResultSet.getString("last_colour"));
        }
      }
      else
      {
        localPreparedStatement = SQLConnectionThread.getConnection().prepareStatement(String.format(SkyWars.getMysql() ? Querys.MYSQL_INSERT_DATA.getQuery() : Querys.SQLITE_INSERT_DATA.getQuery(), new Object[] { getName() }));
        localPreparedStatement.executeUpdate();
        setBoxSection(SkyWars.boxes.getString("default"));
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void loadStats()
  {
    PreparedStatement localPreparedStatement = null;
    try
    {
      localPreparedStatement = SQLConnectionThread.getConnection().prepareStatement(String.format(SkyWars.getMysql() ? Querys.MYSQL_SELECT_STATS_DATA.getQuery() : Querys.SQLITE_SELECT_STATS_DATA.getQuery(), new Object[] { getName() }));
      localPreparedStatement.execute();
      ResultSet localResultSet = localPreparedStatement.getResultSet();
      if (localResultSet.next())
      {
        if (localResultSet.getInt("wins") != 0) {
          this.wins = localResultSet.getInt("wins");
        }
        if (localResultSet.getInt("kills") != 0) {
          this.kills = localResultSet.getInt("kills");
        }
        if (localResultSet.getInt("deaths") != 0) {
          this.deaths = localResultSet.getInt("deaths");
        }
        if (localResultSet.getInt("played") != 0) {
          this.played = localResultSet.getInt("played");
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void unload()
  {
    String str1 = convertKitsToString();
    String str2 = getBoxSection();
    String str3;
    PreparedStatement localPreparedStatement;
    if (getPlayer().hasMetadata("upload_me"))
    {
      str3 = String.format(SkyWars.getMysql() ? Querys.MYSQL_UPDATE_DATA.getQuery() : Querys.SQLITE_UPDATE_DATA.getQuery(), new Object[] { str1, str2, getName() });
      try
      {
        localPreparedStatement = SQLConnectionThread.getConnection().prepareStatement(str3);
        localPreparedStatement.executeUpdate();
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
    }
    if (getPlayer().hasMetadata("upload_stats"))
    {
      str3 = String.format(SkyWars.getMysql() ? Querys.MYSQL_UPDATE_STATS_DATA.getQuery() : Querys.SQLITE_UPDATE_STATS_DATA.getQuery(), new Object[] { Integer.valueOf(getWins()), Integer.valueOf(getKills()), Integer.valueOf(getDeaths()), Integer.valueOf(getPlayed()), getName() });
      try
      {
        localPreparedStatement = SQLConnectionThread.getConnection().prepareStatement(str3);
        localPreparedStatement.executeUpdate();
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
  }
  
  private String convertKitsToString()
  {
    String str1 = "";
    for (String str2 : this.owned_kits) {
      str1 = str1 + str2 + ",";
    }
    if ((str1 != null) && (str1.endsWith(","))) {
      str1.substring(0, str1.length() - 1);
    }
    return str1;
  }
  
  public void setBoxSection(String paramString)
  {
    this.boxsection = paramString;
  }
  
  public String getBoxSection()
  {
    return this.boxsection;
  }
  
  public int getBoxItem(String paramString)
  {
    return SkyWars.boxes.getInt("boxes." + paramString + ".item");
  }
  
  public int getBoxData(String paramString)
  {
    return SkyWars.boxes.getInt("boxes." + paramString + ".data");
  }
  
  public boolean hasKit(Kit paramKit)
  {
    return (paramKit != null) && (this.owned_kits != null) && (this.owned_kits.contains(paramKit.getName()));
  }
  
  public void addKit(Kit paramKit)
  {
    this.owned_kits.add(paramKit.getName());
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\player\SkyPlayer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */