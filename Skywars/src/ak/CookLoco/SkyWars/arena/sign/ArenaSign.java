package ak.CookLoco.SkyWars.arena.sign;

import ak.CookLoco.SkyWars.arena.Arena;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

public class ArenaSign
{
  private Block block;
  private Sign sign;
  private Location loc;
  private boolean rotation;
  private Arena game;
  
  public ArenaSign(Location paramLocation)
  {
    this.loc = paramLocation;
    this.block = paramLocation.getBlock();
    if (this.block.getType() == Material.WALL_SIGN) {
      this.sign = ((Sign)this.block.getState());
    }
  }
  
  public Block getBlock()
  {
    return this.block;
  }
  
  public Sign getSign()
  {
    return this.sign;
  }
  
  public Location getLocation()
  {
    return this.loc;
  }
  
  public Arena getArena()
  {
    return this.game;
  }
  
  public boolean isRotation()
  {
    return this.rotation;
  }
  
  public void setBlock(Block paramBlock)
  {
    this.block = paramBlock;
  }
  
  public void setSign(Sign paramSign)
  {
    this.sign = paramSign;
  }
  
  public void setLocation(Location paramLocation)
  {
    this.loc = paramLocation;
  }
  
  public void setRotation(boolean paramBoolean)
  {
    this.rotation = paramBoolean;
  }
  
  public void setArena(Arena paramArena)
  {
    this.game = paramArena;
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\arena\sign\ArenaSign.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */