package mediaapps.SCB.interfaces;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public abstract interface ClassInterface
{
  public abstract String id();

  public abstract String DisplayName();

  public abstract boolean Movement(Player paramPlayer);

  public abstract boolean Jump(Player paramPlayer);

  public abstract void Attack(Player paramPlayer1, Player paramPlayer2);

  public abstract boolean ShootArrow(Player paramPlayer);

  public abstract void ArrowHit(Player paramPlayer, Entity paramEntity);

  public abstract void Spawn(Player paramPlayer);

  public abstract void Death(Player paramPlayer);

  public abstract void RightClick(Player paramPlayer, Action paramAction, Block block);
  
  public abstract void LeftClick(Player paramPlayer, Action paramAction, Block paramBlock);

  public abstract void ThrowPotion(Player paramPlayer1, Player paramPlayer2);

  public abstract ItemStack Icon();
}