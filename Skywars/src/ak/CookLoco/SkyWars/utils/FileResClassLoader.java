package ak.CookLoco.SkyWars.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import ak.CookLoco.SkyWars.SkyWars;

public class FileResClassLoader
  extends ClassLoader
{
  private final transient File dataFolder;
  
  public FileResClassLoader(ClassLoader paramClassLoader)
  {
    super(paramClassLoader);
    this.dataFolder = SkyWars.getPlugin().getDataFolder();
  }
  
  public URL getResource(String paramString)
  {
    File localFile = new File(this.dataFolder, paramString);
    if (localFile.exists()) {
      try
      {
        return localFile.toURI().toURL();
      }
      catch (MalformedURLException localMalformedURLException) {}
    }
    return null;
  }
  
  public InputStream getResourceAsStream(String paramString)
  {
    File localFile = new File(this.dataFolder, paramString);
    if (localFile.exists()) {
      try
      {
        return new FileInputStream(localFile);
      }
      catch (FileNotFoundException localFileNotFoundException) {}
    }
    return null;
  }
}
