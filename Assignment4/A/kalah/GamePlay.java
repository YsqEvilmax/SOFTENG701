package kalah;

import com.qualitascorpus.testsupport.IO;

public class GamePlay {
	private Board _board = new Board();
	private int _player = 1;
	private Player[] _players;
	private IO _io;
	public GamePlay(IO io, Player player1, Player player2) {
		_io = io;
		_players = new Player[2];
		_players[0] = player1;
		_players[1] = player2;
	}

	public MoveResult nextMove() {
		_board.print(_io);
		if (_board.isGameOver(_player)) {
			return MoveResult.GameOver;
		}		
		_io.print("Player P" + _player + "'s turn - ");
		int move = _players[_player-1].nextMove();
		if (move < 0) {
			return MoveResult.Quit;
		}
		MoveResult result = _board.move(_player,move);
		if (result == MoveResult.MoveEnded) {
			_player = 3 - _player;
		} else if (result == MoveResult.EmptyHouse) {
			_io.println("House is empty. Move again.");
		}
		return result;
	}
	
	public void endGame(boolean gameOver) {
		_io.println("Game over");
		_board.print(_io);
		if (gameOver) {
			int[] score = _board.getScore();
			_io.println("\tplayer 1:" + score[0]);
			_io.println("\tplayer 2:" + score[1]);
			if (score[0] > score[1]) {
				_io.println("Player 1 wins!");
			} else if (score[1] > score[0]) {
				_io.println("Player 2 wins!");
			} else {
				_io.println("A tie!");
			}
		}
	}
}
