package ak.CookLoco.SkyWars.database;

import ak.CookLoco.SkyWars.SkyWars;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;

public class Authentication
{
  public static String sql_host = SkyWars.getPlugin().getConfig().getString("data.mysql.server");
  public static String sql_db = SkyWars.getPlugin().getConfig().getString("data.mysql.db");
  public static String sql_user = SkyWars.getPlugin().getConfig().getString("data.mysql.user");
  public static String sql_pass = SkyWars.getPlugin().getConfig().getString("data.mysql.password");
  public static int sql_port = SkyWars.getPlugin().getConfig().getInt("data.mysql.port");
  public static final String mysql_url = "jdbc:mysql://" + sql_host + ":" + sql_port + "/" + sql_db + "?autoReconnect=true";
  public static final String sqlite_url = "jdbc:sqlite:" + new File(new StringBuilder().append(SkyWars.getPlugin().getDataFolder()).append(File.separator).append("Data.db").toString()).getAbsolutePath();
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\database\Authentication.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */