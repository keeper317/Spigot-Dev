package ak.CookLoco.SkyWars.database;

import ak.CookLoco.SkyWars.SkyWars;
import java.sql.Connection;
import java.sql.DriverManager;
import org.bukkit.configuration.file.FileConfiguration;

public class SQLConnectionThread
{
  private static Connection con = null;
  public static int query_count = 0;
  
  public static Connection getConnection()
  {
    try
    {
      if (query_count >= 1000)
      {
        if (con != null) {
          con.close();
        }
        if (SkyWars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("SQLite")) {
          con = DriverManager.getConnection(Authentication.sqlite_url);
        } else if (SkyWars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("MySQL")) {
          con = DriverManager.getConnection(Authentication.mysql_url, Authentication.sql_user, Authentication.sql_pass);
        }
        query_count = 0;
      }
      if ((con == null) || (con.isClosed())) {
        if (SkyWars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("SQLite"))
        {
          Class.forName("org.sqlite.JDBC");
          con = DriverManager.getConnection(Authentication.sqlite_url);
        }
        else if (SkyWars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("MySQL"))
        {
          Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection(Authentication.mysql_url, Authentication.sql_user, Authentication.sql_pass);
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    query_count += 1;
    return con;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\database\SQLConnectionThread.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */