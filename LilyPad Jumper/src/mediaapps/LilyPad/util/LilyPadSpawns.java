package mediaapps.LilyPad.util;

import java.util.Random;

import org.bukkit.Bukkit;

import mediaapps.LilyPad.Main;

public class LilyPadSpawns 
{
	public static Random rand = new Random();
	public static void placeLily()
	{
		//Bukkit.broadcastMessage("In Lily Spawns");
		int dx, dz;
		
		dx = (int) Math.abs(Math.abs(Main.field[0].getX()) - Math.abs(Main.field[1].getX()));
		dz = (int) Math.abs(Math.abs(Main.field[0].getZ()) - Math.abs(Main.field[1].getZ()));
		
		for(int i = 0; i <= dx; i++)
			for(int j = 0; j <= dz; j++)
				if(rand.nextInt(4) == 2)
				{
					if(Main.field[0].getX() < Main.field[1].getX())
					{
						if(Main.field[0].getZ() < Main.field[1].getZ())
							Misc.setBlock((int) (Main.field[0].getX() + i), (int) (Main.field[0].getZ() + j), Main.field[0].getWorld());
						else if(Main.field[0].getZ() > Main.field[1].getZ())
							Misc.setBlock((int) (Main.field[0].getX() + i), (int) (Main.field[0].getZ() - j), Main.field[0].getWorld());
					}
					else if(Main.field[0].getX() > Main.field[1].getX())
					{
						if(Main.field[0].getZ() < Main.field[1].getZ())
							Misc.setBlock((int) (Main.field[0].getX() - i), (int) (Main.field[0].getZ() + j), Main.field[0].getWorld());
						else if(Main.field[0].getZ() > Main.field[1].getZ())
							Misc.setBlock((int) (Main.field[0].getX() - i), (int) (Main.field[0].getZ() - j), Main.field[0].getWorld());
					}
					else if(Main.field[0].getX() == Main.field[1].getX() || Main.field[0].getZ() == Main.field[1].getZ())
						Bukkit.broadcastMessage("Set field start and end!");
				}
	}
	public static void breakLily()
	{
		int dx, dz;
		
		dx = (int) Math.abs(Math.abs(Main.field[0].getX()) - Math.abs(Main.field[1].getX()));
		dz = (int) Math.abs(Math.abs(Main.field[0].getZ()) - Math.abs(Main.field[1].getZ()));
		
		for(int i = 0; i <= dx; i++)
			for(int j = 0; j <= dz; j++)
					if(Main.field[0].getX() < Main.field[1].getX())
					{
						if(Main.field[0].getZ() < Main.field[1].getZ())
							Misc.setBlockAir((int) (Main.field[0].getX() + i), (int) (Main.field[0].getZ() + j), Main.field[0].getWorld());
						else if(Main.field[0].getZ() > Main.field[1].getZ())
							Misc.setBlockAir((int) (Main.field[0].getX() + i), (int) (Main.field[0].getZ() - j), Main.field[0].getWorld());
					}
					else if(Main.field[0].getX() > Main.field[1].getX())
					{
						if(Main.field[0].getZ() < Main.field[1].getZ())
							Misc.setBlockAir((int) (Main.field[0].getX() - i), (int) (Main.field[0].getZ() + j), Main.field[0].getWorld());
						else if(Main.field[0].getZ() > Main.field[1].getZ())
							Misc.setBlockAir((int) (Main.field[0].getX() - i), (int) (Main.field[0].getZ() - j), Main.field[0].getWorld());
					}
	}
}
