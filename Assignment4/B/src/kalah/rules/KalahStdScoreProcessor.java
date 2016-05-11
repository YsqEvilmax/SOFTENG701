package kalah.rules;

import kalah.board.Pit;
import kalah.board.PitTopology;
import kalah.board.Side;
import kalah.display.Display;

public class KalahStdScoreProcessor implements BoardStateProcessor {
	private Display _display;
	public KalahStdScoreProcessor(Display display) {
		_display = display;
	}

	@Override
	public void process(Side player, PitTopology pitTopology, Pit pid) {
		int[] score = new int[2];
		score[0] = pitTopology.getStore(Side.First).nSeeds();
		for (Pit pit: pitTopology.getPits(Side.First)) {
			score[0] += pit.nSeeds();
		}
		score[1] = pitTopology.getStore(Side.Second).nSeeds();
		for (Pit pit: pitTopology.getPits(Side.Second)) {
			score[1] += pit.nSeeds();
		}
		
		_display.message("\tplayer 1:" + score[0]);
		_display.message("\tplayer 2:" + score[1]);
		if (score[0] > score[1]) {
			_display.message("Player 1 wins!");
		} else if (score[1] > score[0]) {
			_display.message("Player 2 wins!");
		} else {
			_display.message("A tie!");
		}
		
	}
}
