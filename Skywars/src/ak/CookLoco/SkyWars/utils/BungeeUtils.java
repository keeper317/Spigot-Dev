package ak.CookLoco.SkyWars.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ak.CookLoco.SkyWars.SkyWars;

public class BungeeUtils
{
  public static void teleToServer(Player paramPlayer, String paramString1, String paramString2)
  {
    if (!paramString1.equalsIgnoreCase("")) {
      paramPlayer.sendMessage(paramString1);
    }
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
      localDataOutputStream.writeUTF("Connect");
      localDataOutputStream.writeUTF(paramString2);
      paramPlayer.sendPluginMessage(SkyWars.getPlugin(), "BungeeCord", localByteArrayOutputStream.toByteArray());
      localByteArrayOutputStream.close();
      localDataOutputStream.close();
    }
    catch (Exception localException)
    {
      paramPlayer.sendMessage(ChatColor.GOLD + "Error: Couldn't sent you to " + ChatColor.RED + paramString2);
    }
  }
}


