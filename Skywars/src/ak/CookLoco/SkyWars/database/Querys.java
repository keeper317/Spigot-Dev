package ak.CookLoco.SkyWars.database;

import ak.CookLoco.SkyWars.SkyWars;
import org.bukkit.ChatColor;

public enum Querys
{
  MYSQL_CREATE("CREATE TABLE IF NOT EXISTS SkyWars_Data (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, username VARCHAR(32), kits TEXT, last_colour VARCHAR(32) DEFAULT '" + ChatColor.stripColor(SkyWars.getMessage("settings.boxes.default.name")) + "', wins INT(12) DEFAULT '0', kills INT(12) DEFAULT '0', deaths INT(12) DEFAULT '0', played INT(14) DEFAULT '0')"),  MYSQL_SELECT_DATA("SELECT kits, last_colour FROM SkyWars_Data WHERE username='%s'"),  MYSQL_SELECT_STATS_DATA("SELECT wins, kills, deaths, played FROM SkyWars_Data WHERE username='%s'"),  MYSQL_INSERT_DATA("INSERT INTO SkyWars_Data (username) VALUES ('%s')"),  MYSQL_UPDATE_STATS_DATA("UPDATE SkyWars_Data SET wins='%s', kills='%s', deaths='%s', played='%s' WHERE username='%s'"),  MYSQL_UPDATE_DATA("UPDATE SkyWars_Data SET kits='%s', last_colour='%s' WHERE username='%s'"),  GENERAL_SELECT_TOP_STATS("SELECT * FROM `SkyWars_Data` ORDER BY `%s` DESC LIMIT %s"),  SQLITE_CREATE("CREATE TABLE IF NOT EXISTS 'SkyWars_Data' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'username' TEXT(32), 'kits' TEXT, 'last_colour' TEXT(32) DEFAULT '" + ChatColor.stripColor(SkyWars.getMessage("settings.boxes.default.name")) + "', 'wins' INT(12) DEFAULT '0', 'kills' INT(12) DEFAULT '0', 'deaths' INT(12) DEFAULT '0', 'played' INT(14) DEFAULT '0')"),  SQLITE_SELECT_DATA("SELECT kits, last_colour FROM SkyWars_Data WHERE username='%s'"),  SQLITE_SELECT_STATS_DATA("SELECT wins, kills, deaths, played FROM SkyWars_Data WHERE username='%s'"),  SQLITE_INSERT_DATA("INSERT INTO 'SkyWars_Data' ('username') VALUES ('%s')"),  SQLITE_UPDATE_STATS_DATA("UPDATE SkyWars_Data SET wins='%s', kills='%s', deaths='%s', played='%s' WHERE username='%s'"),  SQLITE_UPDATE_DATA("UPDATE SkyWars_Data SET kits='%s', last_colour='%s' WHERE username='%s'");
  
  String query;
  
  private Querys(String paramString1)
  {
    this.query = paramString1;
  }
  
  public String getQuery()
  {
    return this.query;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\database\Querys.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */