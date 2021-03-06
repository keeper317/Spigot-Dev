package mediaapps.CTT;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;

public class FieldManagement 
{
	public static void onGameStart()
	{
		int dx = (int) Math.abs(Math.abs(Main.field[0].getX()) - Math.abs(Main.field[1].getX()));
		int dy = (int) Math.abs(Math.abs(Main.field[0].getY()) - Math.abs(Main.field[1].getY()));
		int dz = (int) Math.abs(Math.abs(Main.field[0].getZ()) - Math.abs(Main.field[1].getZ()));
		for(int i = 0; i <= dx; i++)
			for(int k = 0; k <= dy; k++)
				for(int j = 0; j <= dz; j++)
				{
					if(Main.field[0].getX() < Main.field[1].getX())
					{
						if(Main.field[0].getY() < Main.field[1].getY())
						{
							if(Main.field[0].getZ() < Main.field[1].getZ())
								testBlock((int) (Main.field[0].getX() + i), (int) (Main.field[0].getY() + k), (int) (Main.field[0].getZ() + j), Main.field[0].getWorld());
							else if(Main.field[0].getZ() > Main.field[1].getZ())
								testBlock((int) (Main.field[0].getX() + i), (int) (Main.field[0].getY() + k), (int) (Main.field[0].getZ() - j), Main.field[0].getWorld());
						}
						else if(Main.field[0].getY() > Main.field[1].getY())
						{
							if(Main.field[0].getZ() < Main.field[1].getZ())
								testBlock((int) (Main.field[0].getX() + i), (int) (Main.field[0].getY() - k), (int) (Main.field[0].getZ() + j), Main.field[0].getWorld());
							else if(Main.field[0].getZ() > Main.field[1].getZ())
								testBlock((int) (Main.field[0].getX() + i), (int) (Main.field[0].getY() - k), (int) (Main.field[0].getZ() - j), Main.field[0].getWorld());
						}
					}
					else if(Main.field[0].getX() > Main.field[1].getX())
					{
						if(Main.field[0].getY() < Main.field[1].getY())
						{
							if(Main.field[0].getZ() < Main.field[1].getZ())
								testBlock((int) (Main.field[0].getX() - i), (int) (Main.field[0].getY() + k), (int) (Main.field[0].getZ() + j), Main.field[0].getWorld());
							else if(Main.field[0].getZ() > Main.field[1].getZ())
								testBlock((int) (Main.field[0].getX() - i), (int) (Main.field[0].getY() + k), (int) (Main.field[0].getZ() - j), Main.field[0].getWorld());
						}
						else if(Main.field[0].getY() > Main.field[1].getY())
						{
							if(Main.field[0].getZ() < Main.field[1].getZ())
								testBlock((int) (Main.field[0].getX() - i), (int) (Main.field[0].getY() - k), (int) (Main.field[0].getZ() + j), Main.field[0].getWorld());
							else if(Main.field[0].getZ() > Main.field[1].getZ())
								testBlock((int) (Main.field[0].getX() - i), (int) (Main.field[0].getY() - k), (int) (Main.field[0].getZ() - j), Main.field[0].getWorld());
						}
					}
					else if(Main.field[0].getX() == Main.field[1].getX() || Main.field[0].getY() == Main.field[1].getY() || Main.field[0].getZ() == Main.field[1].getZ())
						Bukkit.broadcastMessage("Set field start and end!");
				}
	}
	public static void testBlock(int x, int y, int z, World world)
	{
		Block tester = world.getBlockAt(x, y, z);
			if(tester.getType() == Material.CHEST)
			{
				Chest c = (Chest) tester.getState();
				if(c.getInventory().getTitle().equalsIgnoreCase("Archery"))
					Misc.setBlock(tester.getLocation(), Inventories.Archers);
				else if(c.getInventory().getTitle().equalsIgnoreCase("Potions"))
					Misc.setBlock(tester.getLocation(), Inventories.potions);
				else if(c.getInventory().getTitle().equalsIgnoreCase("Loot1"))
					Misc.setBlock(tester.getLocation(), Inventories.loot1);
				else if(c.getInventory().getTitle().equalsIgnoreCase("Loot2"))
					Misc.setBlock(tester.getLocation(), Inventories.loot2);
				else if(c.getInventory().getTitle().equalsIgnoreCase("Secret"))
					Misc.setBlock(tester.getLocation(), Inventories.secret);
			}
			else if(tester.getType() == Material.OBSIDIAN)
			{
				if(world.getBlockAt(x-2, y+1, z).getType() == Material.REDSTONE_BLOCK)
					for(int i = 1; i<=2; i++)
					{
						world.getBlockAt(x, y+i, z).setType(Material.WOOL);
						world.getBlockAt(x, y+i, z).setData((byte) 14, true);
					}
				else if(world.getBlockAt(x+2, y+1, z).getType() == Material.REDSTONE_BLOCK)
					for(int i = 1; i<=2; i++)
					{
						world.getBlockAt(x, y+i, z).setType(Material.WOOL);
						world.getBlockAt(x, y+i, z).setData((byte) 14, true);
					}
				else if(world.getBlockAt(x, y+1, z-2).getType() == Material.REDSTONE_BLOCK)
					for(int i = 1; i<=2; i++)
					{
						world.getBlockAt(x, y+i, z).setType(Material.WOOL);
						world.getBlockAt(x, y+i, z).setData((byte) 14, true);
					}
				else if(world.getBlockAt(x, y+1, z+2).getType() == Material.REDSTONE_BLOCK)
					for(int i = 1; i<=2; i++)
					{
						world.getBlockAt(x, y+i, z).setType(Material.WOOL);
						world.getBlockAt(x, y+i, z).setData((byte) 14, true);
					}
				else if(world.getBlockAt(x, y+1, z-2).getType() == Material.LAPIS_BLOCK)
					for(int i = 1; i<=2; i++)
					{
						world.getBlockAt(x, y+i, z).setType(Material.WOOL);
						world.getBlockAt(x, y+i, z).setData((byte) 9, true);
					}
				else if(world.getBlockAt(x-2, y+1, z).getType() == Material.LAPIS_BLOCK)
					for(int i = 1; i<=2; i++)
					{
						world.getBlockAt(x, y+i, z).setType(Material.WOOL); 
						world.getBlockAt(x, y+i, z).setData((byte) 9, true);
					}
				else if(world.getBlockAt(x+2, y+1, z).getType() == Material.LAPIS_BLOCK)
					for(int i = 1; i<=2; i++)
					{
						world.getBlockAt(x, y+i, z).setType(Material.WOOL); 
						world.getBlockAt(x, y+i, z).setData((byte) 9, true);
					}
				else if(world.getBlockAt(x, y+1, z+2).getType() == Material.LAPIS_BLOCK)
					for(int i = 1; i<=2; i++)
					{
						world.getBlockAt(x, y+i, z).setType(Material.WOOL);
						world.getBlockAt(x, y+i, z).setData((byte) 9, true);
					}
				for(int i = 3; i <=4; i++)
					world.getBlockAt(x, y+i, z).setType(Material.AIR);
			}
	}
}
