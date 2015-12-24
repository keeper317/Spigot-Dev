package ak.CookLoco.SkyWars.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ak.CookLoco.SkyWars.SkyWars;

public class CustomConfig
{
  SkyWars plugin;
  private String name;
  private File file;
  private FileConfiguration fileConfig;
  
  public CustomConfig(String paramString)
  {
    this.name = paramString;
  }
  
  public CustomConfig(SkyWars paramSkyWars)
  {
    this.plugin = paramSkyWars;
  }
  
  public FileConfiguration getCustomConfig(CustomConfig paramCustomConfig)
  {
    if (paramCustomConfig.fileConfig == null) {
      reloadCustomConfig(paramCustomConfig);
    }
    return paramCustomConfig.fileConfig;
  }
  
  public void reloadCustomConfig(CustomConfig paramCustomConfig)
  {
    if (paramCustomConfig.fileConfig == null) {
      paramCustomConfig.file = new File(this.plugin.getDataFolder(), paramCustomConfig.name + ".properties");
    }
    paramCustomConfig.fileConfig = YamlConfiguration.loadConfiguration(paramCustomConfig.file);
    InputStream localInputStream = this.plugin.getResource(paramCustomConfig.name + ".properties");
    if (localInputStream != null)
    {
      YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localInputStream);
      paramCustomConfig.fileConfig.setDefaults(localYamlConfiguration);
    }
  }
  
  public void saveCustomConfig(CustomConfig paramCustomConfig)
  {
    if ((paramCustomConfig.fileConfig == null) || (paramCustomConfig.file == null)) {
      return;
    }
    try
    {
      getCustomConfig(paramCustomConfig).save(paramCustomConfig.file);
    }
    catch (IOException localIOException)
    {
      this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + paramCustomConfig.file, localIOException);
    }
  }
  
  public void saveDefaultConfig(CustomConfig paramCustomConfig)
  {
    if (paramCustomConfig.file == null) {
      paramCustomConfig.file = new File(this.plugin.getDataFolder(), paramCustomConfig.name + ".properties");
    }
    if (!paramCustomConfig.file.exists()) {
      this.plugin.saveResource(paramCustomConfig.name + ".properties", false);
    }
  }
}
