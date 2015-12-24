package net.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String url = ("jdbc:mysql://#/");
		url.replace("#", Main.Host + ":" + Main.Port);
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
		
		PreparedStatement sql = connection.prepareStatement
		("SELECT parent FROM `pex_inheritance` WHERE child=(SELECT name FROM `pex_permissions` WHERE value=?);");
		sql.setString(1, p.getName());
		ResultSet rs = sql.executeQuery();
		rs.next();
		try
		{
			rank = rs.getString("parent");
		}catch(Exception e)
		{
			rank = "default";
		}
		connection.close();
		return rank;
	}
	public static void setRank(Player p) throws SQLException
	{
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement
			("UPDATE `bungeeperms_permissions` SET `value`=? WHERE `key`=?;");
			sql.setString(1, getRank(p));
			sql.setString(2, "users." + p.getName() + ".groups");
			sql.executeUpdate();
		}catch(Exception e)
		{
			
		}
		connection.close();
		
	}
}
