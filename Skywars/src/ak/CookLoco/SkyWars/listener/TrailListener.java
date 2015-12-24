package ak.CookLoco.SkyWars.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.ParticleEffect;

public class TrailListener
  implements Listener
{
  @EventHandler
  public void trail(EntityShootBowEvent paramEntityShootBowEvent)
  {
    LivingEntity localLivingEntity = paramEntityShootBowEvent.getEntity();
    Entity localEntity = paramEntityShootBowEvent.getProjectile();
    if ((localEntity instanceof Projectile))
    {
      Projectile localProjectile = (Projectile)localEntity;
      if (((localProjectile instanceof Arrow)) && ((localLivingEntity instanceof Player)))
      {
        Player localPlayer = (Player)localLivingEntity;
        SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(localPlayer);
        if (localSkyPlayer.isInArena())
        {
          Arena localArena = localSkyPlayer.getArena();
          if ((localArena.isInGame()) && (localSkyPlayer.hasTrail()))
          {
            ParticleEffect localParticleEffect = localSkyPlayer.getTrail();
            float f1 = 0.3F;
            float f2 = 0.3F;
            float f3 = 0.3F;
            float f4 = 0.3F;
            int i = 30;
            int j = 1;
            double d = 20.0D;
            trail(f1, f2, f3, f4, i, j, localParticleEffect, localProjectile, d);
          }
        }
      }
    }
  }
  
  public void trail(final float paramFloat1, final float paramFloat2, final float paramFloat3, final float paramFloat4, final int paramInt1, int paramInt2, final ParticleEffect paramParticleEffect, final Projectile paramProjectile, final double paramDouble)
  {
    if ((!paramProjectile.isOnGround()) && (!paramProjectile.isDead())) {
      Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(SkyWars.getPlugin(), new BukkitRunnable()
      {
        public void run()
        {
          Location localLocation = paramProjectile.getLocation();
          paramParticleEffect.display(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt1, localLocation, paramDouble);
          TrailListener.this.trail(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt1, this.val$wait, paramParticleEffect, paramProjectile, paramDouble);
        }
      }, paramInt2);
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\listener\TrailListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */