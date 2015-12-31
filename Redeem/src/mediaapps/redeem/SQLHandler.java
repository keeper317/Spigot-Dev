package mediaapps.redeem;

import java.sql.*;

import org.bukkit.Bukkit;
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
	public static String getPlayer(Player p) throws SQLException
	{
		
		String hu = "";
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT have_used FROM `redeem` WHERE username=?;");
		sql.setString(1, p.getName());
		ResultSet rs = sql.executeQuery();
		try
		{
				rs.next();
				hu = rs.getString("have_used");
		}catch(Exception e)
		{
			addPlayer(p);
			hu = "n";
		}
		connection.close();
		return hu;
		
	}
	private static void addPlayer(Player p) throws SQLException 
	{
		openConnection();
		
		PreparedStatement sql = connection.prepareStatement
		("INSERT INTO `redeem`(`have_used`, `uuid`, `username`) VALUES ('n',?,?);");
		sql.setString(1, p.getUniqueId().toString());
		sql.setString(2, p.getName().toString());
		
		connection.close();
	}
	public static void updatePlayer(Player p) throws SQLException
	{
		openConnection();
		try{
		PreparedStatement sql = connection.prepareStatement
				("UPDATE `redeem` SET have_used='y' WHERE uuid=?;");
				sql.setString(1, p.getUniqueId().toString());
		
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
		connection.close();
	}
	public static double getTokens(Player p) throws SQLException
	{
		
		String bal = "";
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT balance FROM `economy_balance` WHERE (username_id=(SELECT id FROM `economy_account` WHERE name=?) AND currency_id=2);");
		sql.setString(1, p.getName());
		
		ResultSet rs = sql.executeQuery();
		rs.next();
		bal = rs.getString("balance");
		
		connection.close();
		return Double.parseDouble(bal);
		
	}
	public static void updateTokens(Player p, double tokens) throws SQLException
	{
		openConnection();
		try{
		PreparedStatement sql = connection.prepareStatement
				("UPDATE `economy_balance` SET balance=? WHERE (username_id=(SELECT id FROM `economy_account` WHERE name=?) AND currency_id=2);");
				sql.setDouble(1, tokens);
				sql.setString(2, p.getName());
		
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
		connection.close();
		
	}
}
