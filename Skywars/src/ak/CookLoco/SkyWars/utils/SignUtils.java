package ak.CookLoco.SkyWars.utils;

import org.bukkit.block.Sign;

public class SignUtils
{
  public static void setSignText(Sign paramSign, int paramInt, String paramString)
  {
    int i = 15;
    if ((paramString == null) || (paramSign == null)) {
      throw new IllegalArgumentException("Sign or Text to set in sign was null");
    }
    if (paramInt > 3) {
      throw new IllegalArgumentException("position was > than 3");
    }
    if (paramString.length() >= i)
    {
      String str = paramString.substring(0, i);
      paramSign.setLine(paramInt, str);
    }
    else
    {
      paramSign.setLine(paramInt, paramString);
    }
  }
}