package ak.CookLoco.SkyWars.listener;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.arena.ArenaManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener
  implements Listener
{
  @EventHandler
  public void onWeather(WeatherChangeEvent paramWeatherChangeEvent)
  {
    Arena[] arrayOfArena;
    int j = (arrayOfArena = ArenaManager.getGames()).length;
    for (int i = 0; i < j; i++)
    {
      Arena localArena = arrayOfArena[i];
      if ((localArena.getWorld() == paramWeatherChangeEvent.getWorld()) && 
        (!SkyWars.getPlugin().getConfig().getBoolean("options.weather")))
      {
        if (!paramWeatherChangeEvent.isCancelled()) {
          paramWeatherChangeEvent.setCancelled(paramWeatherChangeEvent.toWeatherState());
        }
        if (paramWeatherChangeEvent.getWorld().hasStorm()) {
          paramWeatherChangeEvent.getWorld().setWeatherDuration(0);
        }
      }
    }
  }
  
  @EventHandler
  public void onMobSpawn(CreatureSpawnEvent paramCreatureSpawnEvent)
  {
    Arena[] arrayOfArena;
    int j = (arrayOfArena = ArenaManager.getGames()).length;
    for (int i = 0; i < j; i++)
    {
      Arena localArena = arrayOfArena[i];
      if (localArena.getWorld() == paramCreatureSpawnEvent.getLocation().getWorld())
      {
        if ((paramCreatureSpawnEvent.getSpawnReason() == CreatureSpawnEvent.SpawnReason.EGG) || (paramCreatureSpawnEvent.getSpawnReason() == CreatureSpawnEvent.SpawnReason.DISPENSE_EGG)) {
          paramCreatureSpawnEvent.setCancelled(true);
        }
        if (!SkyWars.getPlugin().getConfig().getBoolean("options.creaturespawn")) {
          paramCreatureSpawnEvent.setCancelled(true);
        }
      }
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\listener\WorldListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */