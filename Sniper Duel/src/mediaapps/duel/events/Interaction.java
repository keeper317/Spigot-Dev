package mediaapps.duel.events;

import mediaapps.duel.Main;
import mediaapps.duel.Misc;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interaction implements Listener
{
	public static Sign sign;
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		Action act = e.getAction();
		Player p = e.getPlayer();
		Block block = e.getClickedBlock();
		if(act == Action.RIGHT_CLICK_BLOCK)
			if((block.getType() == Material.WALL_SIGN || block.getType() == Material.SIGN_POST) && checkSignText(block, p))
				Misc.Joined(p);
	}
	public boolean checkSignText(Block block, Player p)
	{
		if(block.getType() == Material.WALL_SIGN || block.getType() == Material.SIGN_POST)
		{
				Sign s = (Sign) block.getState();
				if(s.getLine(0).equals("Join Now") || (s.getLine(0)).equals("Vip Only") && p.hasPermission("duel.join.full"))
					if(s.getLine(1).equals("Sniper Duel") && Main.players.size() < 4 && !Main.players.contains(p))
					{
						writeSign(s);
						return true;
					}
		}
		return false;
	}
	public void writeSign(Sign s)
	{
		int size = Main.players.size();
		int vip = (Main.maxPlayers - (Main.maxPlayers / 4));
		if(size == vip)
			s.setLine(0, "§4VIP");
		s.setLine(2, (size + 1) + "/4");
		s.update();
		sign = s;
	}
}
