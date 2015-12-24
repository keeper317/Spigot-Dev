package ak.CookLoco.SkyWars.menus;

import ak.CookLoco.SkyWars.SkyWars;
import ak.CookLoco.SkyWars.arena.ArenaBox;
import ak.CookLoco.SkyWars.box.Box;
import ak.CookLoco.SkyWars.box.BoxManager;
import ak.CookLoco.SkyWars.player.SkyPlayer;
import ak.CookLoco.SkyWars.utils.ItemBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class SettingsBoxesMenu
  extends Menu
{
  public SettingsBoxesMenu()
  {
    super(SkyWars.getMessage("settings.menu.boxes.title"), SkyWars.boxes.getInt("menu_rows") - 1);
    Box[] arrayOfBox;
    int j = (arrayOfBox = BoxManager.getBoxes()).length;
    for (int i = 0; i < j; i++)
    {
      Box localBox = arrayOfBox[i];
      setItem(localBox.getSlot() - 1, localBox.getItemBuilder());
    }
  }
  
  public void onClick(SkyPlayer paramSkyPlayer, ItemStack paramItemStack)
  {
    if (paramItemStack == null) {
      return;
    }
    Box[] arrayOfBox;
    int j = (arrayOfBox = BoxManager.getBoxes()).length;
    for (int i = 0; i < j; i++)
    {
      Box localBox = arrayOfBox[i];
      if (paramItemStack.isSimilar(localBox.getItemBuilder().build()))
      {
        if ((!localBox.getSection().equals(SkyWars.boxes.getString("default"))) && 
          (!paramSkyPlayer.hasPermissions("skywars.settings.colour." + localBox.getSection())))
        {
          paramSkyPlayer.sendMessage(SkyWars.getMessage("player.needpermissions.settings.colour"));
          return;
        }
        ArenaBox localArenaBox = paramSkyPlayer.getBox();
        localArenaBox.setBox(localBox.getItem(), localBox.getData());
        paramSkyPlayer.getPlayer().setMetadata("upload_me", new FixedMetadataValue(SkyWars.getPlugin(), Boolean.valueOf(true)));
        paramSkyPlayer.setBoxSection(localBox.getSection());
        if (localBox.getSection().equals(SkyWars.boxes.getString("default"))) {
          paramSkyPlayer.sendMessage(SkyWars.getMessage("player.select.colour.default"));
        } else {
          paramSkyPlayer.sendMessage(String.format(SkyWars.getMessage("player.select.colour"), new Object[] { localBox.getName() }));
        }
      }
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\menus\SettingsBoxesMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */