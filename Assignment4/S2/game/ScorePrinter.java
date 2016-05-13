package game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.qualitascorpus.testsupport.IO;

import game.player.Player;

public class ScorePrinter {

	private final IO io;

	public ScorePrinter(IO io) {
		this.io = io;
	}

	public void printScoreboard(LinkedHashMap<Player, Integer> scores) {
		printPlayerScores(scores);
		List<Player> topScoringPlayers = getTopScoringPlayers(scores);
		printWinner(topScoringPlayers);
	}

	private void printPlayerScores(LinkedHashMap<Player, Integer> scores) {
		for (Player player : scores.keySet()) {
			int playerScore = scores.get(player);
			String playerName = player.getLongName().toLowerCase();

			io.println("\t" + playerName + ":" + playerScore);
		}
	}

	private List<Player> getTopScoringPlayers(LinkedHashMap<Player, Integer> scores) {
		List<Player> topScoringPlayers = new ArrayList<Player>();
		int topScore = 0;

		for (Player player : scores.keySet()) {
			int playerScore = scores.get(player);
			if (playerScore > topScore) {
				topScoringPlayers.clear();
				topScoringPlayers.add(player);
				topScore = playerScore;
			} else if (playerScore == topScore) {
				topScoringPlayers.add(player);
			}
		}
		return topScoringPlayers;
	}

	private void printWinner(List<Player> topScoringPlayers) {
		if (topScoringPlayers.size() > 1) {
			io.println("A tie!");
		} else {
			Player winner = topScoringPlayers.get(0);
			io.println(winner.getLongName() + " wins!");
		}
	}
}
