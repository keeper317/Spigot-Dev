package mediaapps.LilyPad.util;

import java.sql.SQLException;
import mediaapps.LilyPad.SQLHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreBoardHandler 
{
	public static void statBoard(Player p) throws IllegalArgumentException, IllegalStateException, SQLException
	{
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("stats", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§e§lLILY JUMPER");
		Score s1 = obj.getScore("§2 ");
		s1.setScore(15);
		Score wins = obj.getScore("§aWins: ");
		wins.setScore(14);
		Score winScore = obj.getScore("§f" + SQLHandler.getWins(p));
		winScore.setScore(13);
		Score s2 = obj.getScore("§3 ");
		s2.setScore(12);
		Score money = obj.getScore("§aTokens: ");
		money.setScore(11);
		Score money2 = obj.getScore("§f" + SQLHandler.getTokens(p));
		money2.setScore(10);
		Score s3 = obj.getScore("§4 ");
		s3.setScore(9);
		Score jumps = obj.getScore("§aDouble Jumps:");
		jumps.setScore(8);
		Score jumpScore = obj.getScore("§f" + SQLHandler.getJumps(p));
		jumpScore.setScore(7);
		/*Score s4 = obj.getScore("§f ");
		s4.setScore(6);
		/*Score name = obj.getScore("§fTheGameBoxMC");
		name.setScore(5);*/
		p.setScoreboard(board);
	}
	public static void clearBoard(Player p)
	{
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		p.setScoreboard(board);
	}
}
