package mediaapps.redeem;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigSet {

	public static void setDefaults(FileConfiguration config) 
	{
		config.addDefault("URL", "ignore this");
		config.addDefault("Host", "host name");
		config.addDefault("Port", "Port Number");
		config.addDefault("Database", "Database info");
		config.addDefault("User", "Username");
		config.addDefault("Pass", "Password");
	}

}
