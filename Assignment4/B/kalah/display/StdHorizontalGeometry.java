package kalah.display;

import java.util.List;

import kalah.board.Pit;
import kalah.board.PitTopology;
import kalah.board.Side;

import com.qualitascorpus.testsupport.IO;

/**
 * Provides an ASCII version of the board using a "standard" layout.
 * The layout of houses has two rows opposite each other, player 1's 
 * houses on the bottom, anti-clockwise direction of play, 
 * and stores on each player's right at the ends of the board. 
 */
public class StdHorizontalGeometry implements BoardGeometry {
	private PitTopology _pitTopology;
	public StdHorizontalGeometry(PitTopology pitTopology) {
		_pitTopology = pitTopology;
	}
	public void display(IO io, String id1, String id2) {
		List<Pit> topRow = _pitTopology.getPits(Side.Second);
		List<Pit> bottomRow = _pitTopology.getPits(Side.First);

		io.print("+----");
		for (int i = 0; i < _pitTopology.nHouses(); i++) {
			io.print("+-------");
		}
		io.println("+----+");
		
		io.print("| " + id2 + " "); 
		for (int i = _pitTopology.nHouses()-1 ; i >= 0; i--) {
			io.print("| " + topRow.get(i) + " ");
		}
		io.println("| " + _pitTopology.getStore(Side.First) + " |");

		io.print("|    ");
		for (int i = 0; i < _pitTopology.nHouses(); i++) {
			String lead = (i == 0)?"|":"+";
			io.print(lead + "-------");
		}
		io.println("|    |");
		
		io.print("| " + _pitTopology.getStore(Side.Second) + " ");
		for (int i = 0; i < _pitTopology.nHouses(); i++) {
			io.print("| " + bottomRow.get(i) + " ");
		}
		io.println("| " + id1 + " |");
		
		io.print("+----");
		for (int i = 0; i < _pitTopology.nHouses(); i++) {
			io.print("+-------");
		}
		io.println("+----+");
	}
}
