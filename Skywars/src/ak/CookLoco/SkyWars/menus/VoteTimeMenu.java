package ak.CookLoco.SkyWars.menus;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class VoteTimeMenu
  extends InventoryMenu
{
  public VoteTimeMenu()
  {
    int i = 3;
    this.name = SkyWars.getMessage("vote.time.title");
    this.size = (i * 9);
    this.inv = Bukkit.createInventory(null, this.size, this.name);
  }
  
  public void onItemClick(Player paramPlayer, ItemStack paramItemStack)
  {
    SkyPlayer localSkyPlayer = SkyWars.getSkyPlayer(paramPlayer);
    if ((paramItemStack == null) || (paramItemStack.getType() == Material.AIR)) {
      return;
    }
    Arena localArena = localSkyPlayer.getArena();
    if ((paramItemStack.getType() == Material.STAINED_CLAY) && (paramItemStack.getDurability() == 4))
    {
      if (!localSkyPlayer.hasPermissions("skywars.vote.time.day"))
      {
        localSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.vote.time"));
        return;
      }
      if (!localSkyPlayer.hasData("voted_time"))
      {
        localSkyPlayer.addData("voted_time", Boolean.valueOf(true));
        localSkyPlayer.addData("voted_time_day", Boolean.valueOf(true));
        localArena.addData("vote_time_day", Integer.valueOf(localArena.getInt("vote_time_day") + 1));
        localSkyPlayer.getPlayer().sendMessage(String.format(SkyWars.getMessage("vote.time.successful"), new Object[] {
          ChatColor.stripColor(SkyWars.getMessage("vote.time.day.name")) }));
        localArena.broadcast(String.format(SkyWars.getMessage("game.player.vote.time"), new Object[] { localSkyPlayer.getName(), SkyWars.getMessage("selected.time.day"), Integer.valueOf(localArena.getInt("vote_time_day")) }));
      }
      else
      {
        localSkyPlayer.getPlayer().sendMessage(SkyWars.getMessage("vote.only1"));
        return;
      }
    }
    if ((paramItemStack.getType() == Material.STAINED_CLAY) && (paramItemStack.getDurability() == 15))
    {
      if (!localSkyPlayer.hasPermissions("skywars.vote.time.night"))
      {
        localSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.vote.time"));
        return;
      }
      if (!localSkyPlayer.hasData("voted_time"))
      {
        localSkyPlayer.addData("voted_time", Boolean.valueOf(true));
        localSkyPlayer.addData("voted_time_night", Boolean.valueOf(true));
        localArena.addData("vote_time_night", Integer.valueOf(localArena.getInt("vote_time_night") + 1));
        localSkyPlayer.getPlayer().sendMessage(String.format(SkyWars.getMessage("vote.time.successful"), new Object[] {
          ChatColor.stripColor(SkyWars.getMessage("vote.time.night.name")) }));
        localArena.broadcast(String.format(SkyWars.getMessage("game.player.vote.time"), new Object[] { localSkyPlayer.getName(), SkyWars.getMessage("selected.time.night"), Integer.valueOf(localArena.getInt("vote_time_night")) }));
      }
      else
      {
        localSkyPlayer.getPlayer().sendMessage(SkyWars.getMessage("vote.only1"));
        return;
      }
    }
    if ((paramItemStack.getType() == Material.STAINED_CLAY) && (paramItemStack.getDurability() == 14))
    {
      if (!localSkyPlayer.hasPermissions("skywars.vote.time.sunset"))
      {
        localSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.vote.time"));
        return;
      }
      if (!localSkyPlayer.hasData("voted_time"))
      {
        localSkyPlayer.addData("voted_time", Boolean.valueOf(true));
        localSkyPlayer.addData("voted_time_sunset", Boolean.valueOf(true));
        localArena.addData("vote_time_sunset", Integer.valueOf(localArena.getInt("vote_time_sunset") + 1));
        localSkyPlayer.getPlayer().sendMessage(String.format(SkyWars.getMessage("vote.time.successful"), new Object[] {
          ChatColor.stripColor(SkyWars.getMessage("vote.time.sunset.name")) }));
        localArena.broadcast(String.format(SkyWars.getMessage("game.player.vote.time"), new Object[] { localSkyPlayer.getName(), SkyWars.getMessage("selected.time.sunset"), Integer.valueOf(localArena.getInt("vote_time_sunset")) }));
      }
      else
      {
        localSkyPlayer.getPlayer().sendMessage(SkyWars.getMessage("vote.only1"));
        return;
      }
    }
  }
  
  public Inventory getInventory(Arena paramArena)
  {
    setItem(10, new ItemBuilder(Material.STAINED_CLAY, (short)4).setTitle(SkyWars.getMessage("vote.time.day.name"))
      .addLore(SkyWars.getMessage("vote.time.day.lore1"))
      .addLore(String.format(SkyWars.getMessage("vote.time.day.lore2"), new Object[] { Integer.valueOf(paramArena.getInt("vote_time_day")) })));
    
    setItem(13, new ItemBuilder(Material.STAINED_CLAY, (short)15).setTitle(SkyWars.getMessage("vote.time.night.name"))
      .addLore(SkyWars.getMessage("vote.time.night.lore1"))
      .addLore(String.format(SkyWars.getMessage("vote.time.night.lore2"), new Object[] { Integer.valueOf(paramArena.getInt("vote_time_night")) })));
    
    setItem(16, new ItemBuilder(Material.STAINED_CLAY, (short)14).setTitle(SkyWars.getMessage("vote.time.sunset.name"))
      .addLore(SkyWars.getMessage("vote.time.sunset.lore1"))
      .addLore(String.format(SkyWars.getMessage("vote.time.sunset.lore2"), new Object[] { Integer.valueOf(paramArena.getInt("vote_time_sunset")) })));
    return this.inv;
  }
  
  public Inventory getInventory()
  {
    setItem(10, new ItemBuilder(Material.STAINED_CLAY, (short)4).setTitle(SkyWars.getMessage("vote.time.day.name"))
      .addLore(SkyWars.getMessage("vote.time.day.lore1"))
      .addLore(String.format(SkyWars.getMessage("vote.time.day.lore2"), new Object[] { Integer.valueOf(0) })));
    
    setItem(13, new ItemBuilder(Material.STAINED_CLAY, (short)15).setTitle(SkyWars.getMessage("vote.time.night.name"))
      .addLore(SkyWars.getMessage("vote.time.night.lore1"))
      .addLore(String.format(SkyWars.getMessage("vote.time.night.lore2"), new Object[] { Integer.valueOf(0) })));
    
    setItem(16, new ItemBuilder(Material.STAINED_CLAY, (short)14).setTitle(SkyWars.getMessage("vote.time.sunset.name"))
      .addLore(SkyWars.getMessage("vote.time.sunset.lore1"))
      .addLore(String.format(SkyWars.getMessage("vote.time.sunset.lore2"), new Object[] { Integer.valueOf(0) })));
    return this.inv;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\menus\VoteTimeMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */