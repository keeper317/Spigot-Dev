package mediaapps.blackhat;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigSet {

	public static void setDefaults(FileConfiguration config) 
	{
		config.addDefault("URL", "");
		config.addDefault("Host", "host name");
		config.addDefault("Port", "Port Number");
		config.addDefault("Database", "Database info");
		config.addDefault("User", "Username");
		config.addDefault("Pass", "Password");
	}

}
