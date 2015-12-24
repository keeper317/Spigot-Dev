package ak.CookLoco.SkyWars.arena.sign;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.arena.ArenaManager;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.LocationUtil;
import ak.CookLoco.SkyWars.utils.SignUtils;

public class SignManager
  implements Listener
{
  public static HashMap<Location, ArenaSign> signs = new HashMap();
  public static Random r;
  
  public static void initSigns()
  {
    if (SkyWars.signs.getConfigurationSection("signs") != null) {
      for (String str : SkyWars.signs.getConfigurationSection("signs").getKeys(false))
      {
        Location localLocation = LocationUtil.getLocation(SkyWars.signs.getString("signs." + str + ".location"));
        Arena localArena = ArenaManager.getGame(SkyWars.signs.getString("signs." + str + ".arena"));
        ArenaSign localArenaSign = new ArenaSign(localLocation);
        localArenaSign.setRotation(SkyWars.signs.getBoolean("rotation"));
        localArenaSign.setArena(localArena);
        signs.put(localLocation, localArenaSign);
      }
    }
    r = new Random();
    new BukkitRunnable()
    {
      public void run() {}
    }.runTaskTimer(SkyWars.getPlugin(), 0L, 5L);
    SkyWars.log("SignManager.initSigns - Signs loaded");
  }
  
  public void addSign(Location paramLocation, Arena paramArena)
  {
    ArenaSign localArenaSign = new ArenaSign(paramLocation);
    localArenaSign.setRotation(SkyWars.signs.getBoolean("rotation"));
    localArenaSign.setArena(paramArena);
    signs.put(paramLocation, localArenaSign);
  }
  
  public void removeSign(Location paramLocation)
  {
    signs.remove(paramLocation);
  }
  
  public static ArenaSign[] getSigns()
  {
    return (ArenaSign[])signs.values().toArray(new ArenaSign[signs.values().size()]);
  }
  
  public static ArenaSign getSign(Location paramLocation)
  {
    return (ArenaSign)signs.get(paramLocation);
  }
  
  public static void loadSigns()
  {
    ArenaSign[] arrayOfArenaSign;
    int j = (arrayOfArenaSign = getSigns()).length;
    for (int i = 0; i < j; i++)
    {
      ArenaSign localArenaSign = arrayOfArenaSign[i];
      Arena localArena;
      Object localObject1;
      Object localObject2;
      Object localObject3;
      Object localObject5;
      if (!localArenaSign.isRotation())
      {
        localArena = localArenaSign.getArena();
        org.bukkit.material.Sign localSign = (org.bukkit.material.Sign)localArenaSign.getBlock().getState().getData();
        localObject1 = localArenaSign.getBlock().getRelative(localSign.getAttachedFace());
        localObject2 = null;
        localObject3 = null;
        int m = 0;
        if (localArena == null)
        {
          SkyWars.log("SignManager.loadSigns - trying to load a null game (rotation false)");
          return;
        }
        if (localArena.isWaiting())
        {
          localObject3 = SkyWars.getMessage("motd.waiting");
          localObject2 = Material.getMaterial(Integer.parseInt(SkyWars.signs.getString("state.waiting").split(":")[0]));
          m = Integer.parseInt(SkyWars.signs.getString("state.waiting").split(":")[1]);
        }
        if (localArena.isStarting())
        {
          localObject3 = SkyWars.getMessage("motd.starting");
          localObject2 = Material.getMaterial(Integer.parseInt(SkyWars.signs.getString("state.starting").split(":")[0]));
          m = Integer.parseInt(SkyWars.signs.getString("state.starting").split(":")[1]);
        }
        if (localArena.isFull())
        {
          localObject3 = SkyWars.getMessage("motd.full");
          localObject2 = Material.getMaterial(Integer.parseInt(SkyWars.signs.getString("state.full").split(":")[0]));
          m = Integer.parseInt(SkyWars.signs.getString("state.full").split(":")[1]);
        }
        if (localArena.isInGame())
        {
          localObject3 = SkyWars.getMessage("motd.ingame");
          localObject2 = Material.getMaterial(Integer.parseInt(SkyWars.signs.getString("state.ingame").split(":")[0]));
          m = Integer.parseInt(SkyWars.signs.getString("state.ingame").split(":")[1]);
        }
        if (SkyWars.signs.getBoolean("change_block"))
        {
          ((Block)localObject1).setType((Material)localObject2);
          ((Block)localObject1).setData((byte)m);
        }
        localObject5 = new ArrayList();
        localObject5 = SkyWars.signs.getStringList("format.game");
        SignUtils.setSignText(localArenaSign.getSign(), 0, ChatColor.translateAlternateColorCodes('&', ((String)((List)localObject5).get(0)).replace("%map%", localArena.getName()).replace("%state%", (CharSequence)localObject3).replace("%players%", localArena.getAlivePlayers()).replace("%maxplayers%", localArena.getMaxPlayers())));
        SignUtils.setSignText(localArenaSign.getSign(), 1, ChatColor.translateAlternateColorCodes('&', ((String)((List)localObject5).get(1)).replace("%map%", localArena.getName()).replace("%state%", (CharSequence)localObject3).replace("%players%", localArena.getAlivePlayers()).replace("%maxplayers%", localArena.getMaxPlayers())));
        SignUtils.setSignText(localArenaSign.getSign(), 2, ChatColor.translateAlternateColorCodes('&', ((String)((List)localObject5).get(2)).replace("%map%", localArena.getName()).replace("%state%", (CharSequence)localObject3).replace("%players%", localArena.getAlivePlayers()).replace("%maxplayers%", localArena.getMaxPlayers())));
        SignUtils.setSignText(localArenaSign.getSign(), 3, ChatColor.translateAlternateColorCodes('&', ((String)((List)localObject5).get(3)).replace("%map%", localArena.getName()).replace("%state%", (CharSequence)localObject3).replace("%players%", localArena.getAlivePlayers()).replace("%maxplayers%", localArena.getMaxPlayers())));
        localArenaSign.getSign().update();
      }
      else
      {
        if (ArenaManager.getGames().length < 2)
        {
          SkyWars.log("SignManager.loadSigns - Need 2 or more games (Arenas)");
          return;
        }
        localArena = ArenaManager.getGames()[r.nextInt(ArenaManager.getGames().length)];
        if (localArena == null)
        {
          SkyWars.log("SignManager.loadSigns - trying to load a null game");
          return;
        }
        if (localArenaSign.getArena() == null) {
          localArenaSign.setArena(localArena);
        }
        int k = 0;
        Object localObject4;
        Object localObject6;
        for (localObject2 = signs.entrySet().iterator(); ((Iterator)localObject2).hasNext();)
        {
          localObject1 = (Map.Entry)((Iterator)localObject2).next();
          if (((ArenaSign)((Map.Entry)localObject1).getValue()).getArena() == localArenaSign.getArena())
          {
            k++;
            if (k >= 2)
            {
              localObject3 = new ArrayList();
              localObject3 = SkyWars.signs.getStringList("format.searching");
              SignUtils.setSignText(localArenaSign.getSign(), 0, ChatColor.translateAlternateColorCodes('&', (String)((List)localObject3).get(0)));
              SignUtils.setSignText(localArenaSign.getSign(), 1, ChatColor.translateAlternateColorCodes('&', (String)((List)localObject3).get(1)));
              SignUtils.setSignText(localArenaSign.getSign(), 2, ChatColor.translateAlternateColorCodes('&', (String)((List)localObject3).get(2)));
              SignUtils.setSignText(localArenaSign.getSign(), 3, ChatColor.translateAlternateColorCodes('&', (String)((List)localObject3).get(3)));
              localArenaSign.setArena(null);
              localArenaSign.getSign().update();
              
              localObject4 = (org.bukkit.material.Sign)localArenaSign.getBlock().getState().getData();
              localObject5 = localArenaSign.getBlock().getRelative(((org.bukkit.material.Sign)localObject4).getAttachedFace());
              localObject6 = Material.getMaterial(Integer.parseInt(SkyWars.signs.getString("state.searching").split(":")[0]));
              int i1 = Integer.parseInt(SkyWars.signs.getString("state.searching").split(":")[1]);
              if (SkyWars.signs.getBoolean("change_block"))
              {
                ((Block)localObject5).setType((Material)localObject6);
                ((Block)localObject5).setData((byte)i1);
              }
              return;
            }
          }
        }
        int n;
        if (localArenaSign.getArena().isInGame())
        {
          localObject1 = new ArrayList();
          localObject1 = SkyWars.signs.getStringList("format.searching");
          SignUtils.setSignText(localArenaSign.getSign(), 0, ChatColor.translateAlternateColorCodes('&', (String)((List)localObject1).get(0)));
          SignUtils.setSignText(localArenaSign.getSign(), 1, ChatColor.translateAlternateColorCodes('&', (String)((List)localObject1).get(1)));
          SignUtils.setSignText(localArenaSign.getSign(), 2, ChatColor.translateAlternateColorCodes('&', (String)((List)localObject1).get(2)));
          SignUtils.setSignText(localArenaSign.getSign(), 3, ChatColor.translateAlternateColorCodes('&', (String)((List)localObject1).get(3)));
          localArenaSign.setArena(null);
          localArenaSign.getSign().update();
          
          localObject2 = (org.bukkit.material.Sign)localArenaSign.getBlock().getState().getData();
          localObject3 = localArenaSign.getBlock().getRelative(((org.bukkit.material.Sign)localObject2).getAttachedFace());
          localObject4 = Material.getMaterial(Integer.parseInt(SkyWars.signs.getString("state.searching").split(":")[0]));
          n = Integer.parseInt(SkyWars.signs.getString("state.searching").split(":")[1]);
          if (SkyWars.signs.getBoolean("change_block"))
          {
            ((Block)localObject3).setType((Material)localObject4);
            ((Block)localObject3).setData((byte)n);
          }
        }
        else if (!localArenaSign.getArena().isInGame())
        {
          localArena = localArenaSign.getArena();
          localObject1 = (org.bukkit.material.Sign)localArenaSign.getBlock().getState().getData();
          localObject2 = localArenaSign.getBlock().getRelative(((org.bukkit.material.Sign)localObject1).getAttachedFace());
          localObject3 = null;
          localObject4 = null;
          n = 0;
          if (localArena.isWaiting())
          {
            localObject4 = SkyWars.getMessage("motd.waiting");
            localObject3 = Material.getMaterial(Integer.parseInt(SkyWars.signs.getString("state.waiting").split(":")[0]));
            n = Integer.parseInt(SkyWars.signs.getString("state.waiting").split(":")[1]);
          }
          if (localArena.isStarting())
          {
            localObject4 = SkyWars.getMessage("motd.starting");
            localObject3 = Material.getMaterial(Integer.parseInt(SkyWars.signs.getString("state.starting").split(":")[0]));
            n = Integer.parseInt(SkyWars.signs.getString("state.starting").split(":")[1]);
          }
          if (localArena.isFull())
          {
            localObject4 = SkyWars.getMessage("motd.full");
            localObject3 = Material.getMaterial(Integer.parseInt(SkyWars.signs.getString("state.full").split(":")[0]));
            n = Integer.parseInt(SkyWars.signs.getString("state.full").split(":")[1]);
          }
          if (localArena.isInGame())
          {
            localObject4 = SkyWars.getMessage("motd.ingame");
            localObject3 = Material.getMaterial(Integer.parseInt(SkyWars.signs.getString("state.ingame").split(":")[0]));
            n = Integer.parseInt(SkyWars.signs.getString("state.ingame").split(":")[1]);
          }
          if (SkyWars.signs.getBoolean("change_block"))
          {
            ((Block)localObject2).setType((Material)localObject3);
            ((Block)localObject2).setData((byte)n);
          }
          localObject6 = new ArrayList();
          localObject6 = SkyWars.signs.getStringList("format.game");
          SignUtils.setSignText(localArenaSign.getSign(), 0, ChatColor.translateAlternateColorCodes('&', ((String)((List)localObject6).get(0)).replace("%map%", localArena.getName()).replace("%state%", (CharSequence)localObject4).replace("%players%", localArena.getAlivePlayers()).replace("%maxplayers%", localArena.getMaxPlayers())));
          SignUtils.setSignText(localArenaSign.getSign(), 1, ChatColor.translateAlternateColorCodes('&', ((String)((List)localObject6).get(1)).replace("%map%", localArena.getName()).replace("%state%", (CharSequence)localObject4).replace("%players%", localArena.getAlivePlayers()).replace("%maxplayers%", localArena.getMaxPlayers())));
          SignUtils.setSignText(localArenaSign.getSign(), 2, ChatColor.translateAlternateColorCodes('&', ((String)((List)localObject6).get(2)).replace("%map%", localArena.getName()).replace("%state%", (CharSequence)localObject4).replace("%players%", localArena.getAlivePlayers()).replace("%maxplayers%", localArena.getMaxPlayers())));
          SignUtils.setSignText(localArenaSign.getSign(), 3, ChatColor.translateAlternateColorCodes('&', ((String)((List)localObject6).get(3)).replace("%map%", localArena.getName()).replace("%state%", (CharSequence)localObject4).replace("%players%", localArena.getAlivePlayers()).replace("%maxplayers%", localArena.getMaxPlayers())));
          localArenaSign.getSign().update();
        }
      }
    }
  }
  
  @EventHandler
  public void onPlace(SignChangeEvent paramSignChangeEvent)
  {
    if (paramSignChangeEvent.getLine(0).equalsIgnoreCase("[SW]"))
    {
      String str1 = paramSignChangeEvent.getLine(1);
      Arena localArena = ArenaManager.getGame(str1);
      
      int i = 0;
      if (SkyWars.signs.getConfigurationSection("signs") != null) {
        for (String str2 : SkyWars.signs.getConfigurationSection("signs").getKeys(false)) {
          i++;
        }
      }
      SkyWars.signs.set("signs." + i + ".location", LocationUtil.getString(paramSignChangeEvent.getBlock().getLocation(), false));
      if (str1 != null) {
        SkyWars.signs.set("signs." + i + ".arena", str1);
      }
      try
      {
        SkyWars.signs.save(SkyWars.signs_file);
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
      if (localArena != null) {
        addSign(paramSignChangeEvent.getBlock().getLocation(), localArena);
      }
      paramSignChangeEvent.getPlayer().sendMessage("§aSign Added");
    }
  }
  
  @EventHandler
  public void onSignClick(PlayerInteractEvent paramPlayerInteractEvent)
  {
    if (((paramPlayerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK) || (paramPlayerInteractEvent.getAction() == Action.RIGHT_CLICK_AIR)) && (paramPlayerInteractEvent.hasBlock()) && ((paramPlayerInteractEvent.getClickedBlock().getState() instanceof org.bukkit.block.Sign)))
    {
      org.bukkit.block.Sign localSign = (org.bukkit.block.Sign)paramPlayerInteractEvent.getClickedBlock().getState();
      
      ArenaSign localArenaSign = getSign(localSign.getLocation());
      if (localArenaSign.getArena() == null) {
        return;
      }
      String str = localArenaSign.getArena().getName();
      
      Arena localArena = ArenaManager.getGame(str);
      if (localArena == null) {
        return;
      }
      SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(paramPlayerInteractEvent.getPlayer());
      if (localArena.isInGame())
      {
        localSkyPlayer.sendMessage(SkyWars.getMessage("game.ingame.message"));
        return;
      }
      if (localArena.getAlivePlayers() >= localArena.getMaxPlayers())
      {
        localSkyPlayer.sendMessage(SkyWars.getMessage("game.full.message"));
        return;
      }
      if (localArena.isLoading())
      {
        localSkyPlayer.sendMessage(SkyWars.getMessage("game.loading"));
        return;
      }
      localArena.addPlayer(localSkyPlayer, true);
    }
  }
  
  @EventHandler
  public void onSignBreak(BlockBreakEvent paramBlockBreakEvent)
  {
    if (((paramBlockBreakEvent.getBlock().getType() == Material.SIGN) || (paramBlockBreakEvent.getBlock().getType() == Material.SIGN_POST)) && (
      (paramBlockBreakEvent.getPlayer().isOp()) || (paramBlockBreakEvent.getPlayer().hasPermission("skywars.admin"))))
    {
      ArenaSign[] arrayOfArenaSign;
      int j = (arrayOfArenaSign = getSigns()).length;
      for (int i = 0; i < j; i++)
      {
        ArenaSign localArenaSign = arrayOfArenaSign[i];
        if (paramBlockBreakEvent.getBlock().getLocation().equals(localArenaSign.getLocation()))
        {
          removeSign(localArenaSign.getLocation());
          for (String str : SkyWars.signs.getConfigurationSection("signs").getKeys(false))
          {
            Location localLocation = LocationUtil.getLocation(SkyWars.signs.getString("signs." + str + ".location"));
            if (paramBlockBreakEvent.getBlock().getLocation().equals(localLocation))
            {
              SkyWars.signs.set("signs." + str, null);
              paramBlockBreakEvent.getPlayer().sendMessage("§cSign Deleted - You need remove in the config file (signs.yml)");
            }
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\arena\sign\SignManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */