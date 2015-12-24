package ak.CookLoco.SkyWars.utils;

import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.player.SkyPlayer;

public class SkyScoreboard
{
  public static Scoreboard getScoreboard(SkyPlayer paramSkyPlayer)
  {
    if (paramSkyPlayer == null)
    {
      SkyWars.log(String.format("SkyScoreboard.getScoreboard - Trying to get a scoreboard for a NULL Player", new Object[0]));
      return null;
    }
    Scoreboard localScoreboard = SkyWars.getPlugin().getServer().getScoreboardManager().getNewScoreboard();
    Objective localObjective;
    if (localScoreboard.getObjective("sw") == null)
    {
      localObjective = localScoreboard.registerNewObjective("sw", "dummy");
      localObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }
    else
    {
      localObjective = localScoreboard.getObjective("sw");
    }
    SkyWars.log(String.format("SkyScoreboard.getScoreboard - Player is in arena: " + paramSkyPlayer.isInArena(), new Object[0]));
    Object localObject1;
    int j;
    String str3;
    Object localObject3;
    Object localObject2;
    Object localObject4;
    Object localObject5;
    if (paramSkyPlayer.isInArena())
    {
      localObject1 = paramSkyPlayer.getArena();
      SkyWars.log(String.format("SkyScoreboard.getScoreboard - Game is: Waiting: " + ((Arena)localObject1).isWaiting() + ", In Game: " + ((Arena)localObject1).isInGame() + ", Starting: " + ((Arena)localObject1).isStarting(), new Object[0]));
      if ((((Arena)localObject1).isWaiting()) || (((Arena)localObject1).isInGame()) || (((Arena)localObject1).isStarting()))
      {
        String str1 = SkyWars.score.getString("game.title").replace("%player%", paramSkyPlayer.getName())
          .replace("%a_name%", ((Arena)localObject1).getName());
        SkyWars.log("SkyScoreboard.getScoreboard - Game title: " + str1);
        localObjective.setDisplayName(ChatColor.translateAlternateColorCodes('&', str1));
        j = SkyWars.score.getStringList("game.lines").size();
        if (j > 15) {
          return null;
        }
        int k = j;
        
        str3 = "";
        for (localObject3 = SkyWars.score.getStringList("game.lines").iterator(); ((Iterator)localObject3).hasNext();)
        {
          localObject2 = (String)((Iterator)localObject3).next();
          if (k < 0) {
            break;
          }
          localObject4 = 
          
            ((String)localObject2).replace("%player%", paramSkyPlayer.getName()).replace("%empty%", str3 += "§r").replace("%a_name%", ((Arena)localObject1).getName()).replace("%a_max_p%", ((Arena)localObject1).getMaxPlayers()).replace("%a_ingame_p%", ((Arena)localObject1).getAlivePlayers()).replace("%coins%", paramSkyPlayer.getCoins());
          
          localObject5 = ((String)localObject4).length() > 15 ? ((String)localObject4).substring(0, 14) : localObject4;
          Score localScore = localObjective.getScore(ChatColor.translateAlternateColorCodes('&', (String)localObject5));
          localScore.setScore(k);
          k--;
        }
      }
    }
    else if ((SkyWars.isMultiArenaMode()) && 
      (SkyWars.score.getBoolean("lobby.enabled")))
    {
      localObject1 = SkyWars.score.getString("lobby.title").replace("%player%", paramSkyPlayer.getName());
      SkyWars.log("SkyScoreboard.getScoreboard - Lobby title: " + (String)localObject1);
      localObjective.setDisplayName(ChatColor.translateAlternateColorCodes('&', (String)localObject1));
      int i = SkyWars.score.getStringList("lobby.lines").size();
      if (i > 15) {
        return null;
      }
      j = i;
      String str2 = "";
      for (localObject2 = SkyWars.score.getStringList("lobby.lines").iterator(); ((Iterator)localObject2).hasNext();)
      {
        str3 = (String)((Iterator)localObject2).next();
        if (j < 0) {
          break;
        }
        localObject3 = SkyWars.getLobbyVariables(paramSkyPlayer, str3).replace("%empty%", str2 += "§r");
        
        localObject4 = ((String)localObject3).length() > 15 ? ((String)localObject3).substring(0, 14) : localObject3;
        localObject5 = localObjective.getScore(ChatColor.translateAlternateColorCodes('&', (String)localObject4));
        ((Score)localObject5).setScore(j);
        j--;
      }
    }
    return localScoreboard;
  }
  
  private static class ScoreboardLines
  {
    private Objective obj;
    private String[] lines;
    
    public ScoreboardLines(Objective paramObjective, int paramInt)
    {
      this.obj = paramObjective;
      this.lines = new String[paramInt];
    }
    
    public void addLine(String paramString, int paramInt)
    {
      this.lines[paramInt] = paramString;
    }
    
    public void build()
    {
      int i = this.lines.length;
      String[] arrayOfString;
      int k = (arrayOfString = this.lines).length;
      for (int j = 0; j < k; j++)
      {
        String str = arrayOfString[j];
        Score localScore;
        if (str == null)
        {
          localScore = this.obj.getScore(ChatColor.values()[i].toString());
          localScore.setScore(i--);
        }
        else
        {
          localScore = this.obj.getScore(str);
          localScore.setScore(i--);
        }
      }
    }
  }
}