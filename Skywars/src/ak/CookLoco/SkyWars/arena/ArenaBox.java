package ak.CookLoco.SkyWars.arena;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class ArenaBox
{
  private Location base;
  private World world;
  private int x;
  private int y;
  private int z;
  
  public ArenaBox(Location paramLocation)
  {
    this.base = paramLocation;
    this.world = paramLocation.getWorld();
  }
  
  public void setBox(int paramInt1, int paramInt2)
  {
    this.x = this.base.getBlockX();
    this.y = this.base.getBlockY();
    this.z = this.base.getBlockZ();
    
    Material localMaterial = Material.getMaterial(paramInt1);
    
    this.world.getBlockAt(this.x, this.y - 1, this.z).setType(localMaterial);
    this.world.getBlockAt(this.x, this.y - 1, this.z).setData((byte)paramInt2);
    
    this.world.getBlockAt(this.x, this.y, this.z - 1).setType(localMaterial);
    this.world.getBlockAt(this.x, this.y, this.z - 1).setData((byte)paramInt2);
    this.world.getBlockAt(this.x, this.y + 1, this.z - 1).setType(localMaterial);
    this.world.getBlockAt(this.x, this.y + 1, this.z - 1).setData((byte)paramInt2);
    this.world.getBlockAt(this.x, this.y + 2, this.z - 1).setType(localMaterial);
    this.world.getBlockAt(this.x, this.y + 2, this.z - 1).setData((byte)paramInt2);
    
    this.world.getBlockAt(this.x, this.y, this.z + 1).setType(localMaterial);
    this.world.getBlockAt(this.x, this.y, this.z + 1).setData((byte)paramInt2);
    this.world.getBlockAt(this.x, this.y + 1, this.z + 1).setType(localMaterial);
    this.world.getBlockAt(this.x, this.y + 1, this.z + 1).setData((byte)paramInt2);
    this.world.getBlockAt(this.x, this.y + 2, this.z + 1).setType(localMaterial);
    this.world.getBlockAt(this.x, this.y + 2, this.z + 1).setData((byte)paramInt2);
    
    this.world.getBlockAt(this.x + 1, this.y, this.z).setType(localMaterial);
    this.world.getBlockAt(this.x + 1, this.y, this.z).setData((byte)paramInt2);
    this.world.getBlockAt(this.x + 1, this.y + 1, this.z).setType(localMaterial);
    this.world.getBlockAt(this.x + 1, this.y + 1, this.z).setData((byte)paramInt2);
    this.world.getBlockAt(this.x + 1, this.y + 2, this.z).setType(localMaterial);
    this.world.getBlockAt(this.x + 1, this.y + 2, this.z).setData((byte)paramInt2);
    
    this.world.getBlockAt(this.x - 1, this.y, this.z).setType(localMaterial);
    this.world.getBlockAt(this.x - 1, this.y, this.z).setData((byte)paramInt2);
    this.world.getBlockAt(this.x - 1, this.y + 1, this.z).setType(localMaterial);
    this.world.getBlockAt(this.x - 1, this.y + 1, this.z).setData((byte)paramInt2);
    this.world.getBlockAt(this.x - 1, this.y + 2, this.z).setType(localMaterial);
    this.world.getBlockAt(this.x - 1, this.y + 2, this.z).setData((byte)paramInt2);
    
    this.world.getBlockAt(this.x, this.y + 3, this.z).setType(Material.AIR);
  }
  
  public Location getLocation()
  {
    return this.base;
  }
  
  public void removeBase()
  {
    this.world.getBlockAt(this.x, this.y - 1, this.z).setType(Material.AIR);
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\arena\ArenaBox.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */