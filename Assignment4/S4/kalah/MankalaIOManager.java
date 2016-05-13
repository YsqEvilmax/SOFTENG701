package kalah;

import java.util.List;

import com.qualitascorpus.testsupport.IO;

import kalah.Config.Property;

public class MankalaIOManager implements IOManager {
	
	@Override
	public void runGame(GameRules rules, IO io){
		boolean gameOn = true;
		int move = -1;
		while(gameOn){
			printBoard(rules.getBoard(), io);
			move = io.readInteger("Player P"+rules.getActivePlayerBoard().getPlayer()+"'s turn - Specify house number or 'q' to quit: ", 1, Config.getProperty(Property.BOARDSIZE), -1, "q");
			if(move == -1){
				gameOn=false;
			}else{
				move--; //subtract 1 to allow move to be in range
				boolean validMove = rules.PlayTurn(move);
				if (!validMove){
					io.println("House is empty. Move again.");
				}else{
					gameOn = !rules.isGameOver();
				}
			}
		}
		
		// If player quit
		if (move == -1){
			io.println("Game over");
			printBoard(rules.getBoard(), io);
		}else{ // Otherwsise game ended
			printBoard(rules.getBoard(), io);					
			io.println("Game over");
			printBoard(rules.getBoard(), io);
			rules.calculateGameOverScore();
			rules.getBoard().getPlayerBoards().forEach( b -> io.println("	player "+b.getPlayer()+":"+b.getScore()));
			List<PlayerBoard> winners = rules.getWinners();
			if (winners.size()> 1){
				io.println("A tie!");
			}else{
				io.println("Player " + winners.get(0).getPlayer()+ " wins!" );
			}
		}
	}
	
	private void printBoard(Board board, IO io) {
		PlayerBoard pb1 = board.getPlayerBoards().get(0);
		PlayerBoard pb2 =  board.getPlayerBoards().get(1);
		
		String row1 = "", row2 = "", row3 = "", row4 = "", row5 = "";
		
		row1 += "+----+";
		row2 += "| P"+pb2.getPlayer().getPlayerNumber()+" |";
		row3 += "|    |";
		row4 += "| "+getFormattedScore(pb2.getScore())+" |";
		row5 += "+----+";
		
		for (int i = 0; i < Config.getProperty(Property.BOARDSIZE); i++){
			row1 += "-------+";
			row2 += " "+((Config.getProperty(Property.BOARDSIZE) - i))+"["+getFormattedScore(pb2.getSeedCount((Config.getProperty(Property.BOARDSIZE) - i)-1))+"] |";
			row3 += (i < Config.getProperty(Property.BOARDSIZE)-1) ? "-------+" : "-------|";
			row4 += " "+(i+1)+"["+getFormattedScore(pb1.getSeedCount(i))+"] |";
			row5 += "-------+";
		}
		
		row1 += "----+";
		row2 += " "+getFormattedScore(pb1.getScore())+" |";
		row3 += "    |";
		row4 += " P"+pb1.getPlayer().getPlayerNumber()+" |";
		row5 += "----+";
		
		io.println(row1);
		io.println(row2);
		io.println(row3);
		io.println(row4);
		io.println(row5);
		
	}
	
	
	private String getFormattedScore(int score){
		if (score > 9){
			return ""+score;
		}else{
			return " "+score;
		}
	}

}
