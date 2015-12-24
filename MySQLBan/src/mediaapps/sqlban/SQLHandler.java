package mediaapps.sqlban;

import java.sql.*;
import org.bukkit.craftbukkit.libs.jline.internal.Log;

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
		//String url = ("jdbc:mysql://" + Main.url);
		connection = DriverManager.getConnection("158.83.184.37/SQL/" + Main.Database, Main.User, Main.Pass);
	}
	public synchronized static void closeConnection() throws SQLException
	{
		connection.close();
	}
	public static void pardonUser(String un) throws SQLException
	{
		openConnection();
		PreparedStatement sql = connection.prepareStatement
		("UPDATE 'user-reg' SET banned='n' WHERE username=?;");
		sql.setString(1, un);
		connection.close();
		
	}
	public static void banUser(String un) throws SQLException
	{
		openConnection();
		try{
		PreparedStatement sql = connection.prepareStatement
				("UPDATE `user-reg` SET banned='y' WHERE (username=?);");
		sql.setString(1, un);
		
		}catch(Exception e)
		{
			Log.error(e.getMessage());
		}
		connection.close();
		
	}
	public static void insertUser(String un, String email) throws SQLException
	{
		openConnection();
		try{
			PreparedStatement sql = connection.prepareStatement("INSERT INTO 'user-reg'('username','email','banned') VALUES (?,?,'n');");
			sql.setString(1, un);
			sql.setString(2, email);
		}catch(Exception e)
		{
			Log.error(e.getMessage());
		}
		connection.close();
	}
	public static boolean checkUser(String un) throws SQLException
	{
		openConnection();
		String email = "", banned = "";
		try{
			PreparedStatement sql = 
					connection.prepareStatement("SELECT * FROM 'user-reg' WHERE username=?");
			sql.setString(1, un);
			ResultSet rs = sql.executeQuery();
			if(rs != null)
			{
				email = rs.getString("email");
				banned = rs.getString("banned");
				if (email != null && banned != null && banned.equalsIgnoreCase("n"))
				{
					if(checkEmail(email))
						banUser(un);
					else
						return true;
					
				}
				else
					return false;
			}
			else
				return false;
		}catch(Exception e)
		{
			Log.error(e.getMessage());
		}
		connection.close();
		return false;
	}
	public static boolean checkEmail(String email) throws SQLException
	{
		openConnection();
		try{
			PreparedStatement sql = 
					connection.prepareStatement("SELECT  FROM 'user-reg' WHERE username=?");
			sql.setString(1, email);
			ResultSet rs = sql.executeQuery();
			String results = "";
			while(rs.next())
			{
				results += rs.getString("banned") + ":";
			}
			if(results.contains("y"))
				return true;
			else if(!results.contains("y"))
				return false;
				
		}catch(Exception e)
		{
			Log.error(e.getMessage());
		}
		connection.close();
		return true;
	}
}
