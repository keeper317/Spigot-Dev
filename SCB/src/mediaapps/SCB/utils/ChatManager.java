 package mediaapps.SCB.utils;
 
 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 
 public enum ChatManager
 {
    INFO(ChatColor.BLUE), MISC(ChatColor.BLUE), SUCCESS(ChatColor.GREEN), WARNING(ChatColor.RED);

	ChatColor color;
 
	private ChatManager(ChatColor color) 
	{
  	this.color = color;
	}

	public void sendMessage(String message, Player player)
	{
		player.sendMessage(this.color + message);
	}

	public static void sendMessage(String message, Player player, ChatColor color)
	{
    	player.sendMessage(color + message);
	}
}
