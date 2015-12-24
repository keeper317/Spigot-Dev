    package com.galaxacraft.cp;
    
    import java.util.HashSet;
    import java.util.Set;
    import org.bukkit.Bukkit;
    import org.bukkit.Material;
    import org.bukkit.Sound;
    import org.bukkit.block.BlockFace;
    import org.bukkit.command.Command;
    import org.bukkit.command.CommandSender;
    import org.bukkit.configuration.file.FileConfiguration;
    import org.bukkit.entity.Player;
    import org.bukkit.event.Event;
    import org.bukkit.event.EventHandler;
    import org.bukkit.event.Listener;
    import org.bukkit.event.block.Action;
    import org.bukkit.event.player.PlayerInteractEvent;
    import org.bukkit.event.player.PlayerJoinEvent;
    import org.bukkit.event.player.PlayerMoveEvent;
    import org.bukkit.event.player.PlayerToggleFlightEvent;
    import org.bukkit.inventory.ItemStack;
    import org.bukkit.inventory.meta.ItemMeta;
    import org.bukkit.plugin.java.JavaPlugin;
    
    public class BlastJump extends JavaPlugin
      implements Listener
    {
    	int slot = 7;
    Set<String> bj = new HashSet();
    Set<String> bj2 = new HashSet();
    
      public void onEnable() {
		FileConfiguration config = getConfig();
		setDefaults(config);
		config.options().copyDefaults(true);
		saveConfig();
		slot = getConfig().getInt("invSlot");
      Bukkit.getServer().getPluginManager().registerEvents(this, this);
      }
    private void setDefaults(FileConfiguration config)
    {
    	config.addDefault("invSlot", 7);
    	config.addDefault("BJAP", "true");
    }
    
      public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
      {
      if ((sender instanceof Player)) {
        Player player = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("blastjumpadmin")) {
          if ((sender.hasPermission("bj.admin")) && 
            (args[0].equalsIgnoreCase("toggle"))) {
            String toggle = getConfig().getString("BJAP");
            if (toggle.endsWith("true")) {
              getConfig().set("BJAP", "false");
              saveConfig();
              player.sendMessage("You have turned Blast Jump OFF for all players!");
              }
              else {
              getConfig().set("BJAP", "true");
              saveConfig();
              player.sendMessage("You have turned Blast Jump ON for all players!");
              }
            }
    
          }
        else if ((cmd.getName().equalsIgnoreCase("blastjump")) && 
          (sender.hasPermission("blastjump.boost")) && 
          (args[0].equalsIgnoreCase("boost"))) {
          if (this.bj.contains(player.getName())) {
            this.bj.remove(player.getName());
            this.bj2.add(player.getName());
            player.sendMessage("You have turned Blast Jump x3 ON!");
            }
            else {
            this.bj2.remove(player.getName());
            this.bj.add(player.getName());
            player.sendMessage("You have turned Blast Jump x3 ON!");
            }
          }
    
        }
    
      return true;
      }
    
      @EventHandler
      public void onRightClick(PlayerInteractEvent e)
      {
    	  if(e.getPlayer().hasPermission("hub.blastjump"))
    	  {
		      ItemStack feather = new ItemStack(Material.FEATHER);
		      ItemMeta meta2 = feather.getItemMeta();
		      meta2.setDisplayName("§eBlastJump §8>> §aON");
		    
		      ItemStack tnt = new ItemStack(Material.TNT);
		      ItemMeta meta3 = tnt.getItemMeta();
		      meta3.setDisplayName("§eFly §8>> §aON");
		    
		      feather.setItemMeta(meta2);
		      tnt.setItemMeta(meta3);
		    
		      Player p = e.getPlayer();
		    
		      if ((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK)) return;
		      if (e.getItem().getType() == Material.FEATHER) {
		        p.getInventory().setItem(slot, tnt);
		        p.updateInventory();
		        p.setAllowFlight(true);
		        this.bj.remove(p.getName());
		        }
		     else if (e.getItem().getType() == Material.TNT) {
		       p.getInventory().setItem(slot, feather);
		       p.updateInventory();
		       this.bj.add(p.getName());
		       e.setUseItemInHand(Event.Result.DENY);
		       e.setCancelled(true);
	     }
        }
      }
    
      @EventHandler
      public void onMove(PlayerMoveEvent e)
      {
     String toggle = getConfig().getString("BJAP");
    
     if ((this.bj.contains(e.getPlayer().getName())) && (
       ((e.getPlayer().hasPermission("blastjump.jump")) && (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)) || (toggle.equalsIgnoreCase("true"))))
       e.getPlayer().setAllowFlight(true);
      }
    
      @EventHandler
      public void onFly(PlayerToggleFlightEvent e)
      {
   Player p = e.getPlayer();
    
     String toggle = getConfig().getString("BJAP");
    
     if (this.bj.contains(p.getName())) {
       if ((p.hasPermission("blastjump.jump")) || (toggle.equalsIgnoreCase("true"))) {
         e.setCancelled(true);
         p.setAllowFlight(false);
         p.setFlying(false);
         p.setVelocity(p.getLocation().getDirection().multiply(1.6D).setY(1.0D));
         p.getLocation().getWorld().playSound(p.getLocation(), Sound.EXPLODE, 1.0F, -5.0F);
          }
        }
     else if (this.bj2.contains(p.getName())) {
       e.setCancelled(true);
       p.setAllowFlight(false);
       p.setFlying(false);
       p.setVelocity(p.getLocation().getDirection().multiply(4.8D).setY(3.0D));
       p.getLocation().getWorld().playSound(p.getLocation(), Sound.EXPLODE, 1.0F, -5.0F);
        }
      }
    
      @EventHandler
      public void onJoin(PlayerJoinEvent e)
      {
	     Player p = e.getPlayer();
	     if(p.hasPermission("hub.blastjump"))
	     {
	     ItemStack feather = new ItemStack(Material.FEATHER);
	     ItemMeta meta2 = feather.getItemMeta();
	     meta2.setDisplayName("§eBlastJump §8>> §aON");
	    
	     feather.setItemMeta(meta2);
	    
	     if (p.hasPermission("blastjump.item"))
	         p.getInventory().setItem(slot, feather);
	     }
	     
     }
}