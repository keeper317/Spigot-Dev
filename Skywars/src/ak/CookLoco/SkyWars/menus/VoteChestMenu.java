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

public class VoteChestMenu
  extends InventoryMenu
{
  public VoteChestMenu()
  {
    int i = 3;
    this.name = SkyWars.getMessage("vote.chests.title");
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
    if (paramItemStack.getType() == Material.LEATHER_HELMET)
    {
      if (!localSkyPlayer.hasPermissions("skywars.vote.chest.basic"))
      {
        localSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.vote.chest"));
        return;
      }
      if (!localSkyPlayer.hasData("voted_chest"))
      {
        localSkyPlayer.addData("voted_chest", Boolean.valueOf(true));
        localSkyPlayer.addData("voted_chest_basic", Boolean.valueOf(true));
        localArena.addData("vote_chest_basic", Integer.valueOf(localArena.getInt("vote_chest_basic") + 1));
        localSkyPlayer.getPlayer().sendMessage(String.format(SkyWars.getMessage("vote.chests.successful"), new Object[] {
          ChatColor.stripColor(SkyWars.getMessage("vote.chests.basic.name")) }));
        localArena.broadcast(String.format(SkyWars.getMessage("game.player.vote.chests"), new Object[] { localSkyPlayer.getName(), SkyWars.getMessage("selected.chest.basic"), Integer.valueOf(localArena.getInt("vote_chest_basic")) }));
      }
      else
      {
        localSkyPlayer.getPlayer().sendMessage(SkyWars.getMessage("vote.only1"));
        return;
      }
    }
    if (paramItemStack.getType() == Material.CHAINMAIL_HELMET)
    {
      if (!localSkyPlayer.hasPermissions("skywars.vote.chest.normal"))
      {
        localSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.vote.chest"));
        return;
      }
      if (!localSkyPlayer.hasData("voted_chest"))
      {
        localSkyPlayer.addData("voted_chest", Boolean.valueOf(true));
        localSkyPlayer.addData("voted_chest_normal", Boolean.valueOf(true));
        localArena.addData("vote_chest_normal", Integer.valueOf(localArena.getInt("vote_chest_normal") + 1));
        localSkyPlayer.getPlayer().sendMessage(String.format(SkyWars.getMessage("vote.chests.successful"), new Object[] {
          ChatColor.stripColor(SkyWars.getMessage("vote.chests.normal.name")) }));
        localArena.broadcast(String.format(SkyWars.getMessage("game.player.vote.chests"), new Object[] { localSkyPlayer.getName(), SkyWars.getMessage("selected.chest.normal"), Integer.valueOf(localArena.getInt("vote_chest_normal")) }));
      }
      else
      {
        localSkyPlayer.getPlayer().sendMessage(SkyWars.getMessage("vote.only1"));
        return;
      }
    }
    if (paramItemStack.getType() == Material.DIAMOND_HELMET)
    {
      if (!localSkyPlayer.hasPermissions("skywars.vote.chest.overpowered"))
      {
        localSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.vote.chest"));
        return;
      }
      if (!localSkyPlayer.hasData("voted_chest"))
      {
        localSkyPlayer.addData("voted_chest", Boolean.valueOf(true));
        localSkyPlayer.addData("voted_chest_overpowered", Boolean.valueOf(true));
        localArena.addData("vote_chest_overpowered", Integer.valueOf(localArena.getInt("vote_chest_overpowered") + 1));
        localSkyPlayer.getPlayer().sendMessage(String.format(SkyWars.getMessage("vote.chests.successful"), new Object[] {
          ChatColor.stripColor(SkyWars.getMessage("vote.chests.overpowered.name")) }));
        localArena.broadcast(String.format(SkyWars.getMessage("game.player.vote.chests"), new Object[] { localSkyPlayer.getName(), SkyWars.getMessage("selected.chest.overpowered"), Integer.valueOf(localArena.getInt("vote_chest_overpowered")) }));
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
    setItem(10, new ItemBuilder(Material.LEATHER_HELMET).setTitle(SkyWars.getMessage("vote.chests.basic.name"))
      .addLore(SkyWars.getMessage("vote.chests.basic.lore1"))
      .addLore(SkyWars.getMessage("vote.chests.basic.lore2"))
      .addLore(SkyWars.getMessage("vote.chests.basic.lore3"))
      .addLore(SkyWars.getMessage("vote.chests.basic.lore4"))
      .addLore(SkyWars.getMessage("vote.chests.basic.lore5"))
      .addLore(String.format(SkyWars.getMessage("vote.chests.basic.lore6"), new Object[] { Integer.valueOf(paramArena.getInt("vote_chest_basic")) })));
    
    setItem(13, new ItemBuilder(Material.CHAINMAIL_HELMET).setTitle(SkyWars.getMessage("vote.chests.normal.name"))
      .addLore(SkyWars.getMessage("vote.chests.normal.lore1"))
      .addLore(SkyWars.getMessage("vote.chests.normal.lore2"))
      .addLore(SkyWars.getMessage("vote.chests.normal.lore3"))
      .addLore(SkyWars.getMessage("vote.chests.normal.lore4"))
      .addLore(SkyWars.getMessage("vote.chests.normal.lore5"))
      .addLore(String.format(SkyWars.getMessage("vote.chests.normal.lore6"), new Object[] { Integer.valueOf(paramArena.getInt("vote_chest_normal")) })));
    
    setItem(16, new ItemBuilder(Material.DIAMOND_HELMET).setTitle(SkyWars.getMessage("vote.chests.overpowered.name"))
      .addLore(SkyWars.getMessage("vote.chests.overpowered.lore1"))
      .addLore(SkyWars.getMessage("vote.chests.overpowered.lore2"))
      .addLore(SkyWars.getMessage("vote.chests.overpowered.lore3"))
      .addLore(SkyWars.getMessage("vote.chests.overpowered.lore4"))
      .addLore(SkyWars.getMessage("vote.chests.overpowered.lore5"))
      .addLore(String.format(SkyWars.getMessage("vote.chests.overpowered.lore6"), new Object[] { Integer.valueOf(paramArena.getInt("vote_chest_overpowered")) })));
    return this.inv;
  }
  
  public Inventory getInventory()
  {
    setItem(10, new ItemBuilder(Material.LEATHER_HELMET).setTitle(SkyWars.getMessage("vote.chests.basic.name"))
      .addLore(SkyWars.getMessage("vote.chests.basic.lore1"))
      .addLore(SkyWars.getMessage("vote.chests.basic.lore2"))
      .addLore(SkyWars.getMessage("vote.chests.basic.lore3"))
      .addLore(SkyWars.getMessage("vote.chests.basic.lore4"))
      .addLore(SkyWars.getMessage("vote.chests.basic.lore5"))
      .addLore(String.format(SkyWars.getMessage("vote.chests.basic.lore6"), new Object[] { Integer.valueOf(0) })));
    
    setItem(13, new ItemBuilder(Material.CHAINMAIL_HELMET).setTitle(SkyWars.getMessage("vote.chests.normal.name"))
      .addLore(SkyWars.getMessage("vote.chests.normal.lore1"))
      .addLore(SkyWars.getMessage("vote.chests.normal.lore2"))
      .addLore(SkyWars.getMessage("vote.chests.normal.lore3"))
      .addLore(SkyWars.getMessage("vote.chests.normal.lore4"))
      .addLore(SkyWars.getMessage("vote.chests.normal.lore5"))
      .addLore(String.format(SkyWars.getMessage("vote.chests.normal.lore6"), new Object[] { Integer.valueOf(0) })));
    
    setItem(16, new ItemBuilder(Material.DIAMOND_HELMET).setTitle(SkyWars.getMessage("vote.chests.overpowered.name"))
      .addLore(SkyWars.getMessage("vote.chests.overpowered.lore1"))
      .addLore(SkyWars.getMessage("vote.chests.overpowered.lore2"))
      .addLore(SkyWars.getMessage("vote.chests.overpowered.lore3"))
      .addLore(SkyWars.getMessage("vote.chests.overpowered.lore4"))
      .addLore(SkyWars.getMessage("vote.chests.overpowered.lore5"))
      .addLore(String.format(SkyWars.getMessage("vote.chests.overpowered.lore6"), new Object[] { Integer.valueOf(0) })));
    return this.inv;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\menus\VoteChestMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */