package mediaapps.CTT;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreBoardHandler 
{
	public static void gameBoard(Player p)
	{
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("Game", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("CTTX");
		Score Red = obj.getScore(("§CRed: "));
		Red.setScore(Main.woolBlocks[0]);
		Score Blue = obj.getScore(("§3Blue: "));
		Blue.setScore(Main.woolBlocks[1]);
		Score Kills = obj.getScore(("§dKills: "));
		Kills.setScore(Misc.getKills(p));
		p.setScoreboard(board);
	}
	public static void statBoard(Player p)
	{
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("Stat", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("CTTX Stats");
		Score Wins = obj.getScore(("§aWins: "));
		Wins.setScore(Misc.getWins(p));
		Score Kills = obj.getScore(("§4Kills: "));
		Kills.setScore(Misc.getTotalKills(p));
		Score money = obj.getScore(("§9Tokens: "));
		money.setScore((int) Main.econ.getBalance(p.getName()));
		p.setScoreboard(board);
	}
	public static void clearBoard(Player p)
	{
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		p.setScoreboard(board);
	}
	public static void updateBoard()
	{
		for(Player p : Main.players)
		{
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();
			Objective obj = board.registerNewObjective("stats", "dummy");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			obj.setDisplayName("CTTX");
			Score Red = obj.getScore(("§CRed: "));
			Red.setScore(Main.woolBlocks[0]);
			Score Blue = obj.getScore(("§3Blue: "));
			Blue.setScore(Main.woolBlocks[1]);
			Score Kills = obj.getScore(("§dKills: "));
			Kills.setScore(Misc.getKills(p));
			p.setScoreboard(board);
		}
	}
}
