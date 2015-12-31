package mediaapps.duel;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigSet 
{
	public static void setDefaults(FileConfiguration config)
	{
		config.addDefault("Iron", "world:1.0:1.0:1.0");
		config.addDefault("Diamond", "world:1.0:1.0:1.0");
		config.addDefault("Gold", "world:1.0:1.0:1.0");
		config.addDefault("Emerald", "world:1.0:1.0:1.0");
		config.addDefault("Lobby", "world:1.0:1.0:1.0");
		config.addDefault("Center", "world:1.0:1.0:1.0");
	}
}
