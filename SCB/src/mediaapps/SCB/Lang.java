 package mediaapps.SCB;
 
 import org.bukkit.ChatColor;
 import org.bukkit.configuration.file.YamlConfiguration;
 
 public enum Lang
 {
   TITLE("title-name", "&4[&bSCB&4]"), 
   PLAYER_JOIN("player-join", "§7[§cSCB§7] §c%p §7joined the game!"), 
   PLAYER_MSG_JOIN("player-msg-all-join", "%p Joined the game choose a class! &b[&2+&b]"), 
   PLAYER_LEAVE("player-leave", "&b&l%p Left!"), 
   PLAYER_ELIMINATED("player-eliminated", "&4%p Lost the game!"), 
   NO_PERMS("no-permissions", "&4&lYou don't have permission for that!"), 
   GAME_FULL("cannot-join-game-full", "&4GAME IS FULL!"), 
   GAME_STARTED("cannot-join-game-started", "&c&lGAME ALREADY STARTED!"), 
   GAME_CANNOT("cannot-join-game", "&c&lCANNOT JOIN GAME"), 
   NO_CLASS("kicked-from-game-no-class", "&cYou have been kicked for not choosing a class! D:"), 
   COUNTDOWN("count-down", "&bGame starting in %c"), 
   CHOOSE_CLASS("class-chosen", "&6You chose %class"), 
   LIVES_LEFT("lives-left", "You have %lives Left!"), 
   PLAYER_ELIMINATE("player-eliminated", "%p Has been eliminated!"), 
   BROADCAST_WIN("player-win-broadcast", "%p Has won on %arena"), 
   NOT_ENOUGH("not-enough-players", "&cNOT ENOUGH PLAYERS");
 
   private static YamlConfiguration LANG;
   private String path;
   private String def;
 
   private Lang(String path, String start) { this.path = path;
     this.def = start; }
 
   public static void setFile(YamlConfiguration config)
   {
     LANG = config;
   }
 
   public String toString()
   {
     if (this == TITLE)
       return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, this.def)) + " ";
     return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, this.def));
   }
 
   public String getDefault() {
     return this.def;
   }
 
   String getPath() {
     return this.path;
   }
 }
