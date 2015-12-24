package mediaapps.LilyPad;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigSet 
{
	public static void setDefaults(FileConfiguration config)
	{
		config.addDefault("Start", "world:1.0:1.0:1.0");
		config.addDefault("End", "world:1.0:1.0:1.0");
		config.addDefault("Lobby", "world:1.0:1.0:1.0");
		config.addDefault("Lobbye", "world:1.0:1.0:1.0");
		config.addDefault("Fields", "world:1.0:1.0:1.0");
		config.addDefault("Fielde", "world:1.0:1.0:1.0");
		config.addDefault("ObMsg", "&cYou have lost this round.");
		config.addDefault("minPlayers", "8");
		config.addDefault("Host", "host name");
		config.addDefault("Port", "Port Number");
		config.addDefault("Database", "Database info");
		config.addDefault("User", "Username");
		config.addDefault("Pass", "Password");
	}
}
