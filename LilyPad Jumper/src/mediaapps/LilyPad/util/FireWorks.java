package mediaapps.LilyPad.util;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;


public class FireWorks 
{
	public static void FWMaker(Player p)
	{
		//Spawn the Firework, get the FireworkMeta.
        Firework fw = (Firework) p.getWorld().spawn(p.getLocation(), Firework.class);
        FireworkMeta fwm = fw.getFireworkMeta();
       
        //Our random generator
        Random r = new Random();

        //Get the type
        int rt = r.nextInt(4) + 1;
        Type type = Type.STAR;
        if (rt == 1) type = Type.BALL;
        if (rt == 2) type = Type.BALL_LARGE;
        if (rt == 3) type = Type.BURST;
        if (rt == 4) type = Type.CREEPER;
       
        //Get our random colors   
        int r1i = r.nextInt(17) + 1;
        int r2i = r.nextInt(17) + 1;
        Color c1 = getColor(r1i);
        Color c2 = getColor(r2i);
       
        //Create our effect with this
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
       
        //Then apply the effect to the meta
        fwm.addEffects(effect);
       
        //Generate some power and set it
        fwm.setPower(1);
       
        //Then apply this to our rocket
        fw.setFireworkMeta(fwm);
	}
	private static Color getColor(int i) 
	 {
    	switch (i)
    	{
    	case 1:return Color.AQUA;
    	case 2:return Color.BLACK;
    	case 3:return Color.BLUE;
    	case 4:return Color.FUCHSIA;
    	case 5:return Color.GRAY;
    	case 6:return Color.GREEN;
    	case 7:return Color.LIME;
    	case 8:return Color.MAROON;
    	case 9:return Color.NAVY;
    	case 10:return Color.OLIVE;
    	case 11:return Color.PURPLE;
    	case 12:return Color.RED;
    	case 13:return Color.SILVER;
    	case 14:return Color.TEAL;
    	case 15:return Color.WHITE;
    	case 16:return Color.YELLOW;
    	case 17:return Color.ORANGE;
    	}
		return null;
	 }
}
