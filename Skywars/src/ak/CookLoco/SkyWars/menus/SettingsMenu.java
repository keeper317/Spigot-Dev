package ak.CookLoco.SkyWars.menus;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SettingsMenu
  extends Menu
{
  SettingsBoxesMenu boxmenu;
  SettingsTrailsMenu trailmenu;
  
  public SettingsMenu()
  {
    super(SkyWars.getMessage("settings.menu.title"), 0);
    
    this.boxmenu = new SettingsBoxesMenu();
    this.trailmenu = new SettingsTrailsMenu();
    setItem(2, new ItemBuilder(Material.GLASS).setTitle(SkyWars.getMessage("settings.menu.boxes.name"))
      .addLore(SkyWars.getMessage("settings.menu.boxes.lore")));
    setItem(6, new ItemBuilder(Material.ARROW).setTitle(SkyWars.getMessage("settings.menu.trails.name"))
      .addLore(SkyWars.getMessage("settings.menu.trails.lore")));
  }
  
  public void onClick(SkyPlayer paramSkyPlayer, ItemStack paramItemStack)
  {
    if (paramItemStack == null) {
      return;
    }
    if (paramItemStack.getType() == Material.GLASS) {
      paramSkyPlayer.getPlayer().openInventory(this.boxmenu.getInventory());
    }
    if (paramItemStack.getType() == Material.ARROW) {
      paramSkyPlayer.getPlayer().openInventory(this.trailmenu.getInventory());
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\menus\SettingsMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */