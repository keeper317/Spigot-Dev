package mediaapps.LilyPad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public static double getTokens(Player p) throws SQLException
	{
		
		double bal = 0;
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT balance FROM `economy_balance` WHERE (username_id=(SELECT id FROM `economy_account` WHERE name=?));");
		sql.setString(1, p.getName());
		
		ResultSet rs = sql.executeQuery();
		rs.next();
		bal = rs.getDouble("balance");
		connection.close();
		return bal;
		
	}
	public static void updateTokens(Player p, double tokens) throws SQLException
	{
		openConnection();
		try{
		PreparedStatement sql = connection.prepareStatement
				("UPDATE `economy_balance` SET balance=? WHERE (username_id=(SELECT id FROM `economy_account` WHERE name=?);");
				sql.setDouble(1, getTokens(p) + tokens);
				sql.setString(2, p.getName());
		sql.executeUpdate();
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
		connection.close();
		
	}
	public static int getJumps(Player p) throws SQLException
	{
		int jumps = 0;
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("SELECT jumps FROM `lily_jumper` WHERE username = ?;");
		sql.setString(1, p.getName());
		
		ResultSet rs = sql.executeQuery();
		rs.next();
		jumps = rs.getInt("jumps");
		connection.close();
		return jumps;
	}
	public static void updateJumps(Player p, int i) throws SQLException
	{
		openConnection();
		try{
		PreparedStatement sql = connection.prepareStatement
				("UPDATE `lily_jumper` SET jumps = ? WHERE username = ?;");
				sql.setDouble(1, getJumps(p) + i);
				sql.setString(2, p.getName());
		sql.executeUpdate();
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
		("SELECT wins FROM `lily_jumper` WHERE username=?;");
		sql.setString(1, p.getName());
		
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
				("UPDATE `lily_jumper` SET wins = ? WHERE username = ?;");
				sql.setDouble(1, getWins(p) + 1);
				sql.setString(2, p.getName());
		sql.executeUpdate();
		}catch(Exception e)
		{
			Log.error(p.getName());
			Log.error(e.getMessage());
		}
		connection.close();
	}
	public static void addPlayer(Player p) throws SQLException
	{
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("INSERT INTO `lily_jumper`(`jumps`, `wins`, `username`) VALUES (0, 0, ?);");
		sql.setString(1, p.getName());
		sql.executeUpdate();
		connection.close();
	}
	public static boolean checkPlayer(Player p) throws SQLException
	{
		boolean a = false;
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement
			("SELECT username FROM `lily_jumper` WHERE username = ?;");
			sql.setString(1, p.getName());
			
			ResultSet rs = sql.executeQuery();
			rs.next();
			if(rs.getString("username") == null)
				a = false;
			else
				a = true;
		}catch(Exception e)
		{
			
		}
		closeConnection();
		return a;
		
	}
}
