package mediaapps.blackhat;

import java.sql.*;

import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

public class SQLHandler 
{
	private static Connection connection;
	public static void isDisabled() throws SQLException
	{
		if(connection != null && !connection.isClosed())
			connection.close();
	}
	public synchronized static void openConnection() throws SQLException
	{
		String url = ("jdbc:mysql://66.85.144.162:3306/");
		connection = DriverManager.getConnection(url + Main.Database, Main.User, Main.Pass);
	}
	public synchronized static void closeConnection() throws SQLException
	{
		connection.close();
	}
	public static String getTokens(Player p) throws SQLException
	{
		
		int bal = 0;
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT balance FROM `economy_balance` WHERE (username_id=(SELECT id FROM `economy_account` WHERE name=?) AND currency_id=2);");
		sql.setString(1, p.getName());
		
		ResultSet rs = sql.executeQuery();
		rs.next();
		bal = (int) rs.getDouble("balance");

		connection.close();
		String str = "" + bal;
		return str;
		
	}
	public static void updateTokens(Player p, double tokens) throws SQLException
	{
		openConnection();
		try{
		PreparedStatement sql = connection.prepareStatement
				("UPDATE `economy_balance` SET balance=? WHERE (username_id=(SELECT id FROM `economy_account` WHERE name=?) AND currency_id=2);");
				sql.setDouble(1, tokens);
				sql.setString(1, p.getName());
		
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
		connection.close();
		
	}
	public static int getWins(Player p) throws SQLException
	{
		int wins = 0;
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT wins FROM `black_hat` WHERE uuid=?;");
		sql.setString(1, p.getUniqueId().toString());
		
		ResultSet rs = sql.executeQuery();
		rs.next();
		wins = rs.getInt("wins");
		connection.close();
		return wins;

	}
	public static void addWins(Player p) throws SQLException
	{
		openConnection();
		try{
		PreparedStatement sql = connection.prepareStatement
				("UPDATE `black_hat` SET wins=? WHERE uuid=?;");
				sql.setDouble(1, getWins(p) + 1);
				sql.setString(2, p.getUniqueId().toString());
		
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
		connection.close();
	}
	public static void addKills(Player p) throws SQLException
	{
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement
					("UPDATE 'black_hat' SET agent_kills=?,hacker_kills=? WHERE uuid=?;");
					sql.setInt(1, Misc.getAKills(p) + 1);
					sql.setInt(2, Misc.getHKills(p) + 1);
					sql.setString(3, p.getUniqueId().toString());
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
	}
	public static String[] getKills(Player p) throws SQLException
	{
		String str[] = new String[2];
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement
					("SELECT * FROM 'black_hat' WHERE uuid=?;");
					sql.setString(1, p.getUniqueId().toString());
					
					ResultSet rs = sql.executeQuery();
					rs.next();
					str[0] = rs.getString("agent_kills");
					str[1] = rs.getString("hacker_kills");
					connection.close();
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
		return str;
	}
	public static int getWeapon(Player p) throws SQLException 
	{
		int level = 0;
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT level FROM `bh_weapon` WHERE uuid=?;");
		sql.setString(1, p.getUniqueId().toString());
		
		ResultSet rs = sql.executeQuery();
		rs.next();
		level = rs.getInt("level");
		connection.close();
		return level;
	}
	public static void setWeapon(Player p, int weapon) throws SQLException 
	{
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement
					("UPDATE 'bh_weapon' SET level=? WHERE uuid=?;");
					sql.setInt(1, weapon);
					sql.setString(2, p.getUniqueId().toString());
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
	}
	public static int getHelm(Player p) throws SQLException 
	{
		int level = 0;
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT helm FROM `bh_armor` WHERE uuid=?;");
		sql.setString(1, p.getUniqueId().toString());
		
		ResultSet rs = sql.executeQuery();
		rs.next();
		level = rs.getInt("helm");
		connection.close();
		return level;
	}
	public static void setHelm(Player p, int helm) throws SQLException 
	{
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement
					("UPDATE 'bh_armor' SET helm=? WHERE uuid=?;");
					sql.setInt(1, helm);
					sql.setString(2, p.getUniqueId().toString());
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
	}
	public static int getChest(Player p) throws SQLException 
	{
		int level = 0;
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT chest FROM `bh_armor` WHERE uuid=?;");
		sql.setString(1, p.getUniqueId().toString());
		
		ResultSet rs = sql.executeQuery();
		rs.next();
		level = rs.getInt("chest");
		connection.close();
		return level;
	}
	public static void setChest(Player p, int chest) throws SQLException 
	{
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement
					("UPDATE 'bh_armor' SET chest=? WHERE uuid=?;");
					sql.setInt(1, chest);
					sql.setString(2, p.getUniqueId().toString());
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
	}
	public static int getLegs(Player p) throws SQLException 
	{
		int level = 0;
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT legs FROM `bh_armor` WHERE uuid=?;");
		sql.setString(1, p.getUniqueId().toString());
		
		ResultSet rs = sql.executeQuery();
		rs.next();
		level = rs.getInt("legs");
		connection.close();
		return level;
	}
	public static void setLegs(Player p, int legs) throws SQLException 
	{
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement
					("UPDATE 'bh_armor' SET legs=? WHERE uuid=?;");
					sql.setInt(1, legs);
					sql.setString(2, p.getUniqueId().toString());
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
	}
	public static int getBoots(Player p) throws SQLException 
	{
		int level = 0;
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT boots FROM `bh_armor` WHERE uuid=?;");
		sql.setString(1, p.getUniqueId().toString());
		
		ResultSet rs = sql.executeQuery();
		rs.next();
		level = rs.getInt("boots");
		connection.close();
		return level;
	}
	public static void setBoots(Player p, int boots) throws SQLException 
	{
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement
					("UPDATE 'bh_armor' SET boots=? WHERE uuid=?;");
					sql.setInt(1, boots);
					sql.setString(2, p.getUniqueId().toString());
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
	}
}
