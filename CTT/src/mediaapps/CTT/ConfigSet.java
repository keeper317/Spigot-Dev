package mediaapps.CTT;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigSet 
{
	public static void setDefaults(FileConfiguration config) 
	{
		config.addDefault("rspawn", "world:1.0:1.0:1.0");
		config.addDefault("bspawn", "world:1.0:1.0:1.0");
		config.addDefault("lobby", "world:1.0:1.0:1.0");
		config.addDefault("rmonument", "world:1.0:1.0:1.0");
		config.addDefault("bmonument", "world:1.0:1.0:1.0");
		config.addDefault("rcorner", "world:1.0:1.0:1.0");
		config.addDefault("bcorner", "world:1.0:1.0:1.0");
		config.addDefault("center", "world:1.0:1.0:1.0");
		config.addDefault("Host", "host name");
		config.addDefault("Port", "Port Number");
		config.addDefault("Database", "Database info");
		config.addDefault("User", "Username");
		config.addDefault("Pass", "Password");
	}

}
