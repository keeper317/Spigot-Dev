package mediaapps.sqlban;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigSet {

	public static void setDefaults(FileConfiguration config) 
	{
		config.addDefault("URL", "158.83.184.37/SQL/");
		config.addDefault("Host", "host name");
		config.addDefault("Port", "Port Number");
		config.addDefault("Database", "calcraft");
		config.addDefault("User", "root");
		config.addDefault("Pass", "compclub2");
	}

}
