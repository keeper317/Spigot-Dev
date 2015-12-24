package mediaapps.hubboard;

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
	public static String getRank(Player p) throws SQLException
	{
		openConnection();
		String rank = "Default";
		
		PreparedStatement sql = connection.prepareStatement("SELECT rank FROM `niftyranks_users` WHERE uuid=?;");
		sql.setString(1, p.getUniqueId().toString());
		ResultSet rs = sql.executeQuery();
		rs.next();
		try
		{
			rank = rs.getString("rank");
		}catch(Exception e)
		{
			rank = "default";
		}
		
		connection.close();
		return rank.toUpperCase();
	}
	public static String getTokens(Player p) throws SQLException
	{
		
		int bal = 0;
		String str = "ERROR!";
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT balance FROM `economy_balance` WHERE (username_id=(SELECT id FROM `economy_account` WHERE name=?));");
		sql.setString(1, p.getName());
		
		try
		{
			ResultSet rs = sql.executeQuery();
			rs.next();
			
			bal = (int) rs.getDouble("balance");
			str = "" + bal;
		}catch(Exception e)
		{
			Log.error(e.getMessage());
		}
		connection.close();
		
		return str;
		
	}
	public static String getCredits(Player p) throws SQLException
	{
		int bal = 0;
		openConnection();
		try{
		PreparedStatement sql = connection.prepareStatement
				("SELECT points FROM `playerpoints` WHERE (playername=(SELECT uuid FROM `economy_account` WHERE name=?));");
		sql.setString(1, p.getName());
		ResultSet rs = sql.executeQuery();
		rs.next();
		bal = (int) rs.getDouble("points");
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
		connection.close();
		String str = "" + bal;
		return str;
		
	}
}
