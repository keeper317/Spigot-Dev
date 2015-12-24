package ak.CookLoco.SkyWars.menus;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.ItemBuilder;
import ak.CookLoco.SkyWars.utils.ParticleEffect;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SettingsTrailsMenu
  extends Menu
{
  private ItemBuilder none;
  private ItemBuilder slime;
  private ItemBuilder flame;
  private ItemBuilder water;
  private ItemBuilder lava;
  private ItemBuilder potion;
  private ItemBuilder notes;
  
  public SettingsTrailsMenu()
  {
    super(SkyWars.getMessage("settings.menu.trails.title"), 0);
    
    this.none = new ItemBuilder(Material.GLASS)
      .setTitle(SkyWars.getMessage("settings.trails.default.name"));
    
    this.slime = new ItemBuilder(Material.SLIME_BALL)
      .setTitle(SkyWars.getMessage("settings.trails.slime.name"))
      .addLore(SkyWars.getMessage("settings.trails.slime.lore"));
    
    this.flame = new ItemBuilder(Material.FLINT_AND_STEEL)
      .setTitle(SkyWars.getMessage("settings.trails.flame.name"))
      .addLore(SkyWars.getMessage("settings.trails.flame.lore"));
    
    this.water = new ItemBuilder(Material.WATER_BUCKET)
      .setTitle(SkyWars.getMessage("settings.trails.water.name"))
      .addLore(SkyWars.getMessage("settings.trails.water.lore"));
    
    this.lava = new ItemBuilder(Material.LAVA_BUCKET)
      .setTitle(SkyWars.getMessage("settings.trails.lava.name"))
      .addLore(SkyWars.getMessage("settings.trails.lava.lore"));
    
    this.potion = new ItemBuilder(Material.POTION)
      .setTitle(SkyWars.getMessage("settings.trails.potion.name"))
      .addLore(SkyWars.getMessage("settings.trails.potion.lore"));
    
    this.notes = new ItemBuilder(Material.JUKEBOX)
      .setTitle(SkyWars.getMessage("settings.trails.notes.name"))
      .addLore(SkyWars.getMessage("settings.trails.notes.lore"));
    
    setItem(0, this.none);
    setItem(1, this.slime);
    setItem(2, this.flame);
    setItem(3, this.water);
    setItem(4, this.lava);
    setItem(5, this.potion);
    setItem(6, this.notes);
  }
  
  public void onClick(SkyPlayer paramSkyPlayer, ItemStack paramItemStack)
  {
    if (paramItemStack == null) {
      return;
    }
    if (paramItemStack.isSimilar(this.none.build()))
    {
      paramSkyPlayer.setTrail(null);
      paramSkyPlayer.sendMessage(String.format(SkyWars.getMessage("player.select.trail"), new Object[] { "Default" }));
    }
    if (paramItemStack.isSimilar(this.slime.build()))
    {
      if (!paramSkyPlayer.hasPermissions("skywars.settings.trail.slime"))
      {
        paramSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.settings.trail"));
        return;
      }
      paramSkyPlayer.setTrail(ParticleEffect.SLIME);
      paramSkyPlayer.sendMessage(String.format(SkyWars.getMessage("player.select.trail"), new Object[] {
        ChatColor.stripColor(SkyWars.getMessage("settings.trails.slime.name")) }));
    }
    if (paramItemStack.isSimilar(this.flame.build()))
    {
      if (!paramSkyPlayer.hasPermissions("skywars.settings.trail.flame"))
      {
        paramSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.settings.trail"));
        return;
      }
      paramSkyPlayer.setTrail(ParticleEffect.FLAME);
      paramSkyPlayer.sendMessage(String.format(SkyWars.getMessage("player.select.trail"), new Object[] {
        ChatColor.stripColor(SkyWars.getMessage("settings.trails.flame.name")) }));
    }
    if (paramItemStack.isSimilar(this.water.build()))
    {
      if (!paramSkyPlayer.hasPermissions("skywars.settings.trail.water"))
      {
        paramSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.settings.trail"));
        return;
      }
      paramSkyPlayer.setTrail(ParticleEffect.WATER_SPLASH);
      paramSkyPlayer.sendMessage(String.format(SkyWars.getMessage("player.select.trail"), new Object[] {
        ChatColor.stripColor(SkyWars.getMessage("settings.trails.water.name")) }));
    }
    if (paramItemStack.isSimilar(this.lava.build()))
    {
      if (!paramSkyPlayer.hasPermissions("skywars.settings.trail.lava"))
      {
        paramSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.settings.trail"));
        return;
      }
      paramSkyPlayer.setTrail(ParticleEffect.DRIP_LAVA);
      paramSkyPlayer.sendMessage(String.format(SkyWars.getMessage("player.select.trail"), new Object[] {
        ChatColor.stripColor(SkyWars.getMessage("settings.trails.lava.name")) }));
    }
    if (paramItemStack.isSimilar(this.potion.build()))
    {
      if (!paramSkyPlayer.hasPermissions("skywars.settings.trail.potion"))
      {
        paramSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.settings.trail"));
        return;
      }
      paramSkyPlayer.setTrail(ParticleEffect.REDSTONE);
      paramSkyPlayer.sendMessage(String.format(SkyWars.getMessage("player.select.trail"), new Object[] {
        ChatColor.stripColor(SkyWars.getMessage("settings.trails.potion.name")) }));
    }
    if (paramItemStack.isSimilar(this.notes.build()))
    {
      if (!paramSkyPlayer.hasPermissions("skywars.settings.trail.notes"))
      {
        paramSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.settings.trail"));
        return;
      }
      paramSkyPlayer.setTrail(ParticleEffect.NOTE);
      paramSkyPlayer.sendMessage(String.format(SkyWars.getMessage("player.select.trail"), new Object[] {
        ChatColor.stripColor(SkyWars.getMessage("settings.trails.notes.name")) }));
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\menus\SettingsTrailsMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */