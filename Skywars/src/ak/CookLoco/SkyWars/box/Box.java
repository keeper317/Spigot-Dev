package ak.CookLoco.SkyWars.box;

import ak.CookLoco.SkyWars.utils.ItemBuilder;
import org.bukkit.Material;

public class Box
{
  private String name;
  private String desc;
  private String section;
  private int item;
  private int data;
  private int slot;
  
  public Box(String paramString1, String paramString2)
  {
    this.name = paramString1;
    this.desc = paramString2;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getDescription()
  {
    return this.desc;
  }
  
  public int getItem()
  {
    return this.item;
  }
  
  public int getData()
  {
    return this.data;
  }
  
  public ItemBuilder getItemBuilder()
  {
    return new ItemBuilder(Material.getMaterial(getItem()), (short)getData()).setTitle(getName()).addLore(getDescription());
  }
  
  public int getSlot()
  {
    return this.slot;
  }
  
  public String getSection()
  {
    return this.section;
  }
  
  public void setItem(int paramInt)
  {
    this.item = paramInt;
  }
  
  public void setData(int paramInt)
  {
    this.data = paramInt;
  }
  
  public void setDescription(String paramString)
  {
    this.desc = paramString;
  }
  
  public void setSlot(int paramInt)
  {
    this.slot = paramInt;
  }
  
  public void setSection(String paramString)
  {
    this.section = paramString;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\box\Box.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */