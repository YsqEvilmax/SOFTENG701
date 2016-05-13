package kalah.rules;

import kalah.board.Pit;
import kalah.board.PitTopology;
import kalah.board.Side;


/**
 * Process the capture by the specified player whose move ends in the
 * specified pit.
 */
public class KalahStdCaptureProcessor implements BoardStateProcessor {
	public void process(Side player, PitTopology boardState, Pit end) {
		Pit opposite = boardState.getOpposite(end);
		int captured = opposite.nSeeds();
		opposite.removeAll();
		captured++;
		end.removeAll();
		Pit store = boardState.getStore(player);
		store.capture(captured);
	}
}
