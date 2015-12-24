package ak.CookLoco.SkyWars.utils;

import org.bukkit.ChatColor;

public class Utils
{
  public static String color(String paramString)
  {
    ChatColor[] arrayOfChatColor;
    int j = (arrayOfChatColor = ChatColor.values()).length;
    for (int i = 0; i < j; i++)
    {
      ChatColor localChatColor = arrayOfChatColor[i];
      paramString = paramString.replaceAll("\\[" + localChatColor.getChar() + "\\]", localChatColor.toString());
    }
    return paramString;
  }
}