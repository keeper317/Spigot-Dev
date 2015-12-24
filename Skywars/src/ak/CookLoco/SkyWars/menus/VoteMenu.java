package ak.CookLoco.SkyWars.menus;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VoteMenu
  extends Menu
{
  private static VoteChestMenu chests;
  private static VoteTimeMenu time;
  
  public VoteMenu(Arena paramArena)
  {
    super(SkyWars.getMessage("vote.menu.title"), 2);
    paramArena.addData("vote_chest_basic", Integer.valueOf(0));
    paramArena.addData("vote_chest_normal", Integer.valueOf(0));
    paramArena.addData("vote_chest_overpowered", Integer.valueOf(0));
    setItem(11, new ItemBuilder(Material.CHEST).setTitle(SkyWars.getMessage("vote.chests.name")).addLore(SkyWars.getMessage("vote.chests.lore")));
    
    paramArena.addData("vote_time_day", Integer.valueOf(0));
    paramArena.addData("vote_time_night", Integer.valueOf(0));
    paramArena.addData("vote_time_sunset", Integer.valueOf(0));
    setItem(15, new ItemBuilder(Material.WATCH).setTitle(SkyWars.getMessage("vote.time.name")).addLore(SkyWars.getMessage("vote.time.lore")));
    
    chests = new VoteChestMenu();
    time = new VoteTimeMenu();
  }
  
  public void onClick(SkyPlayer paramSkyPlayer, ItemStack paramItemStack)
  {
    if ((paramItemStack == null) || (paramItemStack.getType() == Material.AIR)) {
      return;
    }
    if (paramItemStack.getType() == Material.CHEST)
    {
      paramSkyPlayer.getPlayer().openInventory(chests.getInventory(paramSkyPlayer.getArena()));
      return;
    }
    if (paramItemStack.getType() == Material.WATCH)
    {
      paramSkyPlayer.getPlayer().openInventory(time.getInventory(paramSkyPlayer.getArena()));
      return;
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\menus\VoteMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */