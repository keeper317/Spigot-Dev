 package mediaapps.SCB.listeners;
 
  import java.io.File;
 import java.io.IOException;
 import java.util.Random;

 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
 import org.bukkit.GameMode;
 import org.bukkit.Location;
 import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
 import org.bukkit.block.BlockFace;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.configuration.file.YamlConfiguration;
 import org.bukkit.entity.Arrow;
 import org.bukkit.entity.Entity;
 import org.bukkit.entity.EntityType;
 import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.block.Action;
 import org.bukkit.event.entity.EntityDamageByEntityEvent;
 import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
 import org.bukkit.event.entity.PlayerDeathEvent;
 import org.bukkit.event.entity.PotionSplashEvent;
 import org.bukkit.event.entity.ProjectileHitEvent;
 import org.bukkit.event.entity.ProjectileLaunchEvent;
 import org.bukkit.event.player.PlayerInteractEvent;
 import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
 import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
 import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
 import org.bukkit.util.Vector;

import mediaapps.SCB.SCB;
import mediaapps.SCB.interfaces.ClassInterface;
import mediaapps.SCB.managers.Arena;
import mediaapps.SCB.managers.Game;
import mediaapps.SCB.managers.PlayerManager;
 
 
 
 
 
 
 
 
 
 
 
 public class ClassListener
   implements Listener
 {
   String scb = "Â§7[" + ChatColor.GREEN + "Â§cSCB" + ChatColor.RESET + "Â§7] ";
   Plugin plugin = SCB.getInstance();
   String plyFile = this.plugin.getDataFolder() + File.separator + "players" + File.separator;
 
   String araFile = this.plugin.getDataFolder() + File.separator + "arenas" + File.separator;
   ClassInterface ci;
   Player player;
   int id = 0;
 
   @EventHandler
   public void Movement(PlayerMoveEvent e)
   {
     Player p = e.getPlayer();
     boolean ingame = PlayerManager.get().ingame(p);
     if (ingame) {
       ClassInterface pClass = PlayerManager.get().getClasse(p);
       if (pClass != null)
         if (pClass.Movement(p)) {
           pClass.Movement(p);
           p.setFoodLevel(20);
         }
         else {
           if ((e.getPlayer().getGameMode() != GameMode.CREATIVE) && (PlayerManager.get().ingame(e.getPlayer())) && 
             (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
             e.getPlayer().setAllowFlight(true);
           }
 
           e.getPlayer().setFoodLevel(20);
         }
     }
   }
 
   @EventHandler
   public void onFly(PlayerToggleFlightEvent e) {
     Player p = e.getPlayer();
     boolean ingame = PlayerManager.get().ingame(p);
     if (ingame) {
       ClassInterface pClass = PlayerManager.get().getClasse(p);
       if (pClass != null)
         if (pClass.Jump(p)) {
           pClass.Jump(p);
         }
         else if (p.getGameMode() != GameMode.CREATIVE) {
					p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 10, 1);
           e.setCancelled(true);
           p.setAllowFlight(false);
           p.setFlying(false);
           p.setVelocity(new Vector(p.getVelocity().getX(), 0.8D, p.getVelocity().getZ()));
         }
     }
   }
 
   @EventHandler
   public void Attack(EntityDamageByEntityEvent e)
   {
     if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player))) {
       Player pAttacker = (Player)e.getDamager();
       Player pAttacke = (Player)e.getEntity();
       boolean ingame = PlayerManager.get().ingame(pAttacker);
       if (ingame) {
         ClassInterface pClass = PlayerManager.get().getClasse(pAttacker);
         if (pClass != null)
           pClass.Attack(pAttacker, pAttacke);
       }
     }
   }
 
   @EventHandler
   public void ShootArrow(ProjectileLaunchEvent e)
   {
     if ((e.getEntity().getShooter() instanceof Player)) {
       Player p = (Player)e.getEntity().getShooter();
       boolean ingame = PlayerManager.get().ingame(p);
       if ((ingame) && ((e.getEntity() instanceof Arrow))) {
         ClassInterface pClass = PlayerManager.get().getClasse(p);
         if ((pClass != null) && 
           (pClass.ShootArrow(p))) {
           e.setCancelled(true);
           pClass.ShootArrow(p);
         }
       }
     }
   }
 
   @EventHandler
   public void ArrowHit(ProjectileHitEvent e)
   {
     if ((e.getEntity().getShooter() instanceof Player)) {
       Player p = (Player)e.getEntity().getShooter();
       boolean ingame = PlayerManager.get().ingame(p);
       if (ingame) {
         ClassInterface pClass = PlayerManager.get().getClasse(p);
         if (pClass != null) {
           Entity projectile = e.getEntity();
           pClass.ArrowHit(p, projectile);
         }
         if ((e.getEntity() instanceof Fireball)) {
           Location loc = e.getEntity().getLocation();
           loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 2.0F, false, false);
         }
       }
				if(e.getEntity().hasMetadata("Explosions"))
				{
					Location l = e.getEntity().getLocation();
					e.getEntity().getWorld().createExplosion(l.getX(), l.getY(), l.getZ(), 2.0F, false, false);
				}
     }

				
				
   }
 
   @EventHandler
   public void Spawn(PlayerDeathEvent e)
   {
     Player p = e.getEntity();
     this.player = e.getEntity();
     boolean ingame = PlayerManager.get().ingame(p);
			  if(PlayerManager.get().ingame(p) && PlayerManager.get().ingame(p.getKiller()))
			  {
				  Bukkit.broadcastMessage(e.getDeathMessage().toString());
				  Bukkit.broadcastMessage("§c" + p.getDisplayName() + " §7was killed by §c " + p.getKiller().getDisplayName() + " §7 using §c" + p.getKiller().getItemInHand().getType().toString());
			  }
     if ((PlayerManager.get().getLives(p) != null) && (PlayerManager.get().getLives(p).intValue() > 0)) {
				p.removePotionEffect(PotionEffectType.POISON);
				p.removePotionEffect(PotionEffectType.WITHER);
				p.removePotionEffect(PotionEffectType.SLOW);
       p.setHealth(20.0D);
       e.getDrops().clear();
       int i = PlayerManager.get().getLives(p).intValue();
       String arena = Game.get().getArena(p);
       if ((i != 1) && (ingame)) {
         p.getInventory().clear();
         p.getInventory().setArmorContents(null);
         i--;
         PlayerManager.get().setLives(p, Integer.valueOf(i));
         ClassInterface pClass = PlayerManager.get().getClasse(p);
         pClass.Death(p);
         Arena.get().scoreUpdate(arena);

         Random random = new Random();
         int num = random.nextInt(4) + 1;
         Location Loc = Arena.get().getLocation(arena, "Spawn" + Integer.toString(num));
         p.teleport(Loc);
         this.plugin.getLogger().info("Respawning " + p.getName());
         Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
         {
           public void run() {
             ClassListener.this.ci = PlayerManager.get().getClasse(ClassListener.this.player);
             ClassListener.this.ci.Spawn(ClassListener.this.player);
             ClassListener.this.player.updateInventory();
           }
         }
         , 1L);
       }
       else if ((i == 1) && (ingame)) {
         p.getInventory().clear();
         p.getInventory().setArmorContents(null);
         p.updateInventory();
         File f = new File(this.plyFile + p.getName() + ".yml");
         FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
         int losses = fc.getInt("Losses");
         losses++;
         fc.set("Losses", Integer.valueOf(losses));
         if ((Game.get().getPlyNum(arena) != null) && (Game.get().getPlyNum(arena).intValue() == 2)) {
           int gemsL = fc.getInt("Gems");
           fc.set("Gems", Integer.valueOf(gemsL + 1));

					Bukkit.broadcastMessage("§c" + Game.get().getPlys(arena)[0].getDisplayName() + " §7has won!");
         }
				  if(Game.get().getPlyNum(arena) < 2)
				  {
				  }
         try {
           fc.save(f);
         } catch (IOException e2) {
           e2.printStackTrace();
         }
         Game.get().playerLeave(p);
         if (Game.get().getPlyNum(arena).intValue() == 1) {
           Player winner = Game.get().getPlys(arena)[0];
           if (winner != p) {
						for(int z = 0; z < 5; z++)
						fireWorks(winner.getLocation());
             Bukkit.broadcastMessage(this.scb + ChatColor.AQUA + winner.getName() + " has won arena " + arena);
             winner.getInventory().clear();
             winner.getInventory().setArmorContents(null);
             winner.updateInventory();
             Game.get().playerLeave(winner);
           }
         }
       }
     }
   }
 
   @EventHandler
   public void RightClick(PlayerInteractEvent e) {
     Player p = e.getPlayer();
     Action a = e.getAction();
     boolean ingame = PlayerManager.get().ingame(p);
     if (ingame) {
       ClassInterface pClass = PlayerManager.get().getClasse(p);
       if ((pClass != null) && ((a == Action.RIGHT_CLICK_BLOCK)))
         pClass.RightClick(p, a, (Block) e.getClickedBlock());
				else if (a == Action.RIGHT_CLICK_AIR && !pClass.id().equalsIgnoreCase("Enderman"))
					pClass.RightClick(p, a, null);
				else if((pClass != null) && (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK))
					pClass.LeftClick(p, a, e.getClickedBlock());
     }
   }
 
   @EventHandler
   public void ThrowPotion(PotionSplashEvent e)
   {
     
   }
 
   @EventHandler
   public void explodeHeight(EntityExplodeEvent e)
   {
     if (e.getEntityType() == EntityType.PRIMED_TNT) {
       e.blockList().clear();
       e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 1.0F, false);
     }
     if (e.getEntityType().equals(EntityType.FIREBALL))
			  {
				e.blockList().clear();
				e.setCancelled(true);
				e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 1.0F);
			  }
   }
			@EventHandler
			public void pickUp(PlayerPickupItemEvent e)
			{
				ItemStack is = e.getItem().getItemStack();
				if(BlockList(is) || is.getType().equals(Material.CACTUS))
				{
					Item item = e.getItem();
					if(item.getVelocity().length() < 1)
					{
						e.setCancelled(true);
						item.remove();
					}
					else if(item.getVelocity().length() > 1)
					{
						e.setCancelled(true);
						e.getPlayer().damage(4.0D);
						item.remove();
					}
				}
			}
			private boolean BlockList(ItemStack is) 
			{
				Material mat = is.getType();
				if(mat == Material.WOOL || mat == Material.STAINED_CLAY || mat == Material.GLASS
						|| mat == Material.GRASS || mat == Material.SMOOTH_STAIRS || mat == Material.STEP
						|| mat == Material.QUARTZ_BLOCK || mat == Material.COAL_BLOCK)
					return true;
				return false;
			}
			@EventHandler
			public void exploded(ExplosionPrimeEvent e)
			{
				e.setFire(false);
			}
		   public void fireWorks(Location loc)
		   {
			   org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework) player.getWorld().spawn(loc, org.bukkit.entity.Firework.class);
	            FireworkEffectMeta fwm = (FireworkEffectMeta) fw.getFireworkMeta();
	           
	            //Our random generator
	            Random r = new Random();
	 
	            //Get the type
	            int rt = r.nextInt(5) + 1;
	            org.bukkit.FireworkEffect.Type type = org.bukkit.FireworkEffect.Type.BALL;       
	            if (rt == 1) type = Type.BALL;
	            if (rt == 2) type = Type.BALL_LARGE;
	            if (rt == 3) type = Type.BURST;
	            if (rt == 4) type = Type.CREEPER;
	            if (rt == 5) type = Type.STAR;
	           
	            //Get our random colors   
	            int r1i = r.nextInt(17) + 1;
	            int r2i = r.nextInt(17) + 1;
	            Color c1 = getColor(r1i);
	            Color c2 = getColor(r2i);
	           
	            //Create our effect with this
	            FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
	           
	            //Then apply the effect to the meta
	            fwm.setEffect(effect);
	           
	            //Generate some power and set it
	            ((FireworkMeta) fwm).setPower(r.nextInt(3) + 1);
	            //Then apply this to our rocket
	            fw.setFireworkMeta((FireworkMeta) fwm);
		   }
		   private Color getColor(int i) 
			 {
		     	switch (i)
		     	{
		     	case 1:return Color.AQUA;
		     	case 2:return Color.BLACK;
		     	case 3:return Color.BLUE;
		     	case 4:return Color.FUCHSIA;
		     	case 5:return Color.GRAY;
		     	case 6:return Color.GREEN;
		     	case 7:return Color.LIME;
		     	case 8:return Color.MAROON;
		     	case 9:return Color.NAVY;
		     	case 10:return Color.OLIVE;
		     	case 11:return Color.PURPLE;
		     	case 12:return Color.RED;
		     	case 13:return Color.SILVER;
		     	case 14:return Color.TEAL;
		     	case 15:return Color.WHITE;
		     	case 16:return Color.YELLOW;
		     	case 17:return Color.ORANGE;
		     	}
				return null;
		 	 }
 }