package kalah.display;

import java.util.HashMap;
import java.util.Map;

import kalah.board.Side;
import kalah.player.Player;

import com.qualitascorpus.testsupport.IO;

public class Display {
	private final BoardGeometry _geometry;
	private final Map<Side,Player> _players;
	private final IO _io;
	public Display(IO io, BoardGeometry geometry, Player player1, Player player2) {
		_io = io;
		_geometry = geometry;
		_players = new HashMap<Side,Player>();
		_players.put(Side.First, player1);
		_players.put(Side.Second, player2);
	}
	public void message(String msg) {
		_io.println(msg);
	}
	public void boardState() {
		_geometry.display(_io, _players.get(Side.First).getID(), 
				_players.get(Side.Second).getID());
	}
}
