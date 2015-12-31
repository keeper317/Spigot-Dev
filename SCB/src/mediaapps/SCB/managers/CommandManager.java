 package mediaapps.SCB.managers;
import mediaapps.SCB.SCB;
import mediaapps.SCB.commands.CreateCommand;
import mediaapps.SCB.commands.DeleteCommand;
import mediaapps.SCB.commands.DisableCommand;
import mediaapps.SCB.commands.EnableCommand;
import mediaapps.SCB.commands.JoinCommand;
import mediaapps.SCB.commands.LeaveCommand;
import mediaapps.SCB.commands.SetGLobbyCommand;
import mediaapps.SCB.commands.SetSpawnCommand;
import mediaapps.SCB.commands.StartCommand;
import mediaapps.SCB.interfaces.SCBCommand;

 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.List;
 import org.bukkit.ChatColor;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.plugin.PluginDescriptionFile;
 
 public class CommandManager
   implements CommandExecutor
 {
   static Plugin plugin = SCB.getInstance();
   private HashMap<String, SCBCommand> commands = new HashMap();
 
   public CommandManager(Plugin plugin) {
     getCommands();
   }
 
   private void getCommands() {
     this.commands.put("join", new JoinCommand());
     this.commands.put("leave", new LeaveCommand());
     this.commands.put("create", new CreateCommand());
     this.commands.put("delete", new DeleteCommand());
     this.commands.put("setspawn", new SetSpawnCommand());
     this.commands.put("enable", new EnableCommand());
     this.commands.put("disable", new DisableCommand());
     this.commands.put("setglobby", new SetGLobbyCommand());
     this.commands.put("start", new StartCommand());
   }
 
   public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
     if (!(s instanceof Player)) {
       s.sendMessage("[SuperCubeBrothers] You Must Be Ingame.");
			   s.sendMessage(ChatColor.GOLD + "Type /scb help for help");
       return true;
     }
     if (cmd.getName().equalsIgnoreCase("scb")) {
       Player p = (Player)s;
       if ((args.length < 1) || (args == null)) {
          s.sendMessage(ChatColor.GOLD + "Type /scb help for help");
         return true;
       }
       if (args[0].equalsIgnoreCase("help")) {
         help(p);
         return true;
       }
       if (this.commands.containsKey(args[0])) {
         String command = args[0].toLowerCase();
         if (p.hasPermission("scb.command." + command)) {
           List list = new ArrayList();
           list.addAll(Arrays.asList(args));
           list.remove(0);
           args = (String[])list.toArray(new String[list.size()]);
           ((SCBCommand)this.commands.get(command)).onCommand(p, args);
           return true;
         }
         return false;
       }
       if (!this.commands.containsKey(args[0])) {
         p.sendMessage("§7[§cSCB§7] Command Does Not Exist");
         return true;
       }
     }
     return false;
   }
 
   public void help(Player p) {
     p.sendMessage("[" + ChatColor.RED + "SuperCubeBrothers" + ChatColor.WHITE + "Help]");
     for (SCBCommand help : this.commands.values())
       p.sendMessage(ChatColor.GOLD + help.help(p));
   }
 
   public void permission(Player p)
   {
     p.sendMessage("[" + ChatColor.RED + "SuperCubeBrothers" + ChatColor.WHITE + "Permissions]");
     for (SCBCommand perm : this.commands.values())
       p.sendMessage(ChatColor.GOLD + perm.permission(p));
   }
 }