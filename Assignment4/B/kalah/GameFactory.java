package kalah;

import kalah.board.Board;
import kalah.board.PitTopology;
import kalah.board.StdTopology;
import kalah.display.BoardGeometry;
import kalah.display.Display;
import kalah.display.StdHorizontalGeometry;
import kalah.player.Human;
import kalah.player.Player;
import kalah.rules.KalahStdRuleSet;
import kalah.rules.RuleSet;

import com.qualitascorpus.testsupport.IO;

public class GameFactory {
	/**
	 * 
	 * @param io Where to send the IO to
	 * @param gameID String specifying the mancala variant. Format is:-
	 * <pre><topology>-<nHouses>-<initSeeds>-<rulesetid>-<player1>-<player2>-<geometry></pre>
	 * Topology: 
	 *    ST=Standard topology (for each player, houses followed by store, in anti-clockwise order) [default]
	 * nHouses: How many houses [default 6]
	 * initSeeds: How many seeds initially in the houses [default 4]
	 * rulesetid: 
	 *    SK=Standard Kalah rules (standard capture and end game is when player has no move)
	 * player1,player2: <type>:<id>. <id> is any string, <type> is:
	 *    H=Human [Default, id1=P1, id2=P2]
	 * geometry: 
	 *    SH=Standard Horizontal (player 1 at bottom, store to right. player 2 at top, store to left on screen)
	 * @return A GamePlay object for the specified game variant
	 */
	public static Game getGame(IO io, String gameID) {
		if (gameID.equals("")) {
			gameID = "ST-6-4-SK-H:P1-H:P2-SH"; // This is what the tests are applied to
		}
		String[] bits = gameID.split("-");
		if (bits.length != 7) {
			throw new RuntimeException("Invalid game specification {" + gameID + "}");
		}
		String topo = bits[0];
		int nHouses = Integer.parseInt(bits[1]);
		int initSeeds = Integer.parseInt(bits[2]);
		String rulesetid = bits[3];
		String p1 = bits[4];
		String p2 = bits[5];
		String geo = bits[6];
    
		// Wire together the required implementations
    	PitTopology topology = getTopology(topo, nHouses, initSeeds);
    	BoardGeometry geometry = getGeometry(geo, topology);
    	Board board = new Board(topology);
    	Player player1 = getPlayer(p1, io, nHouses, board);
    	Player player2 = getPlayer(p2, io, nHouses, board);
    	GameState gameState = new GameState(player1, player2, board);
    	Display display = new Display(io, geometry, player1, player2);
    	RuleSet ruleSet = getRuleSet(rulesetid, display);
		Game game = new Game(ruleSet, gameState, display);
		return game;
	}
	
	private static PitTopology getTopology(String spec, int nHouses, int initSeeds) {
		if (spec.equals("ST")) {
			return new StdTopology(nHouses, initSeeds);
		}
		throw new RuntimeException("Unknown topology spec {" + spec + "}");
	}
	
	private static BoardGeometry getGeometry(String spec, PitTopology pitTopology) {
		if (spec.equals("SH")) {
			return new StdHorizontalGeometry(pitTopology);
		}
		throw new RuntimeException("Unknown geometry spec {" + spec + "}");
	}
	
	private static RuleSet getRuleSet(String rulesetid, Display display) {
    	if (rulesetid.equals("SK")) {
    		return new KalahStdRuleSet(display);
    	}
    	throw new RuntimeException("Unknown ruleset spec {" + rulesetid + "}");
	}
	
	private static Player getPlayer(String spec, IO io, int nHouses, Board board) {
		String[] bits = spec.split(":");
		String type = bits[0];
		String playerID = bits[1];
    	if (type.equals("H")) {
    		return new Human(io, playerID, nHouses);
    	}
    	throw new RuntimeException("Unknown player spec {" + type + "}");
	}
}
