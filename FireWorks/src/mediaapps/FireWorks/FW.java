package mediaapps.FireWorks;
import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class FW extends JavaPlugin
{
	
	public final Logger logger = Logger.getLogger("Minecraft");
	@Override
	public void onEnable()
	{
		getLogger().info("Personal Fire Works Enabled");
		
	}
	public void onDisable()
	{
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version: " + pdfFile.getVersion() + " " + "Has Been Disabled!!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args)
	{
		Player player = (Player) sender;
			if(str.equalsIgnoreCase("FW"))
			{
				if(player.hasPermission("FireWork.fw.use"))
				{
					//Spawn the Firework, get the FireworkMeta.
			            Firework fw = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
			            FireworkMeta fwm = fw.getFireworkMeta();
			           
			            //Our random generator
			            Random r = new Random();
			 
			            //Get the type
			            int rt = r.nextInt(5) + 1;
			            Type type = Type.BALL;       
			            if (rt == 1) type = Type.BALL;
			            if (rt == 2) type = Type.BALL_LARGE;
			            if (rt == 3) type = Type.BURST;
			            if (rt == 4) type = Type.CREEPER;
			            if (rt == 5) type = Type.STAR;
			           
			            //Get our random colors   
			            int r1i = r.nextInt(17) + 1;
			            int r2i = r.nextInt(17) + 1;
			            Color c1 = getColor(r1i);
			            Color c2 = getColor(r2i);
			           
			            //Create our effect with this
			            FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
			           
			            //Then apply the effect to the meta
			            fwm.addEffect(effect);
			           
			            //Generate some power and set it
			            fwm.setPower(1);
			           
			            //Then apply this to our rocket
			            fw.setFireworkMeta(fwm);
					return true;
				}
				else
					sender.sendMessage("You do not have permission to do this!");
			}
		return false;
	}
	 private Color getColor(int i) 
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
