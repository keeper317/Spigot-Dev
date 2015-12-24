package mediaapps.hubboard;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreBoardHandler 
{
	public static void statBoard(Player p) throws IllegalArgumentException, IllegalStateException, ClassNotFoundException, SQLException
	{
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("stats", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§f§l" + Main.boardname);
		String name = p.getName();
		if(name.length() > 12)
			name = name.substring(0, 12);
		Score blank0 = obj.getScore("§o");
		blank0.setScore(15);
		Score un = obj.getScore("§b§l" + name);
		un.setScore(14);
		Score blank = obj.getScore("§a");
		blank.setScore(13);
		Score title2 = obj.getScore(("§a§lRank:"));
		title2.setScore(12);
		Score Rank = obj.getScore(("§f" + SQLHandler.getRank(p)));
		Rank.setScore(11);
		Score blank1 = obj.getScore(("§5"));
		blank1.setScore(10);
		Score title = obj.getScore(("§e§lCredits:"));
		title.setScore(9);
		Score credits = obj.getScore(("§f" + SQLHandler.getCredits(p)));
		credits.setScore(8);
		Score blank3 = obj.getScore(("§1"));
		blank3.setScore(7);
		Score title1 = obj.getScore(("§6§lTokens:"));
		title1.setScore(6);
		Score Tokens = obj.getScore(("§f" + SQLHandler.getTokens(p)));
		Tokens.setScore(5);
		Score blank2 = obj.getScore(("§7"));
		blank2.setScore(4);
		Score title3 = obj.getScore(("§c§lWebsite:"));
		title3.setScore(3);
		Score url = obj.getScore((Main.url));
		url.setScore(2);
		p.setScoreboard(board);
	}
}
