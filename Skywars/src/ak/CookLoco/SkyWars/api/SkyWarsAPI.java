package ak.CookLoco.SkyWars.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ak.CookLoco.SkyWars.database.Querys;
import ak.CookLoco.SkyWars.database.SQLConnectionThread;

public class SkyWarsAPI
{
  public static List<Map.Entry<String, Integer>> getTopWins(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    String str = "wins";
    PreparedStatement localPreparedStatement = null;
    try
    {
      localPreparedStatement = SQLConnectionThread.getConnection().prepareStatement(String.format(Querys.GENERAL_SELECT_TOP_STATS.getQuery(), new Object[] { str, Integer.valueOf(paramInt) }));
      ResultSet localResultSet = localPreparedStatement.executeQuery();
      while (localResultSet.next()) {
        localArrayList.add(new AbstractMap.SimpleEntry(localResultSet.getString("username"), Integer.valueOf(localResultSet.getInt(str))));
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public static List<Map.Entry<String, Integer>> getTopKills(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    String str = "kills";
    PreparedStatement localPreparedStatement = null;
    try
    {
      localPreparedStatement = SQLConnectionThread.getConnection().prepareStatement(String.format(Querys.GENERAL_SELECT_TOP_STATS.getQuery(), new Object[] { str, Integer.valueOf(paramInt) }));
      ResultSet localResultSet = localPreparedStatement.executeQuery();
      while (localResultSet.next()) {
        localArrayList.add(new AbstractMap.SimpleEntry(localResultSet.getString("username"), Integer.valueOf(localResultSet.getInt(str))));
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public static List<Map.Entry<String, Integer>> getTopDeaths(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    String str = "deaths";
    PreparedStatement localPreparedStatement = null;
    try
    {
      localPreparedStatement = SQLConnectionThread.getConnection().prepareStatement(String.format(Querys.GENERAL_SELECT_TOP_STATS.getQuery(), new Object[] { str, Integer.valueOf(paramInt) }));
      ResultSet localResultSet = localPreparedStatement.executeQuery();
      while (localResultSet.next()) {
        localArrayList.add(new AbstractMap.SimpleEntry(localResultSet.getString("username"), Integer.valueOf(localResultSet.getInt(str))));
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public static List<Map.Entry<String, Integer>> getTopPlayed(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    String str = "played";
    PreparedStatement localPreparedStatement = null;
    try
    {
      localPreparedStatement = SQLConnectionThread.getConnection().prepareStatement(String.format(Querys.GENERAL_SELECT_TOP_STATS.getQuery(), new Object[] { str, Integer.valueOf(paramInt) }));
      ResultSet localResultSet = localPreparedStatement.executeQuery();
      while (localResultSet.next()) {
        localArrayList.add(new AbstractMap.SimpleEntry(localResultSet.getString("username"), Integer.valueOf(localResultSet.getInt(str))));
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\api\SkyWarsAPI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */