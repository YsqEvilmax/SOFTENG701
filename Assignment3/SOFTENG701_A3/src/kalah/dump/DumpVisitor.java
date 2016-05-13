package kalah.dump;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import com.qualitascorpus.testsupport.IO;

import kalah.Game;
import kalah.stucture.House;
import kalah.stucture.PlayBoard;
import kalah.wrapper.Wrapper;

public class DumpVisitor implements IArgVisitor<Object>{
	public DumpVisitor(){	
	}
	
	public DumpVisitor(IO io) {
		myIO = new Wrapper<IO>(io);
	}
	
	public void Visit(String n, Object arg){
		myIO.get().println(n);
	}
	
	@Override
	public void Visit(Game n, Object arg) {
		myIO.get().println("Game over");
		Visit(n.getPlayBoard(), arg);
	}

	@Override
	public void Visit(PlayBoard n, Object arg){
		String line = FormatLine("+", "+", "----", "----", 
				new ArrayList<String>(
						Collections.nCopies(
								n.getGroups().get(0).getHouses().size(),
								"-------")
						)
				);
		myIO.get().println(line);
		
		ArrayList<House> houses = new ArrayList<House>(n.getGroups().get(1).getHouses());
		Collections.reverse(houses);
		line = FormatLine("|", "|",
				" " + n.getGroups().get(1).myPlayer.get().myName.get() + " ",
				" " + n.getGroups().get(0).getStore().toString() + " ", 
				TransCollection(houses)
				);
		myIO.get().println(line);
		
		line = FormatLine("|", "+", "    ", "    ", 
				new ArrayList<String>(
						Collections.nCopies(
								n.getGroups().get(0).getHouses().size(),
								"-------")
						)
				);
		myIO.get().println(line);	
		
		line = FormatLine("|", "|", 
				" " + n.getGroups().get(1).getStore().toString() + " ", 
				" " + n.getGroups().get(0).myPlayer.get().myName.get() + " ",			
				TransCollection(n.getGroups().get(0).getHouses())
				);
		myIO.get().println(line);
		
		line = FormatLine("+", "+", "----", "----", 
				new ArrayList<String>(
						Collections.nCopies(
								n.getGroups().get(0).getHouses().size(),
								"-------")
						)
				);
		myIO.get().println(line);
	}
	
	private String FormatLine(String border, String skeleton, String arg1, String arg2, Collection<String> list){
		String line = border + arg1 + border;
		for(Iterator<String> itor = list.iterator(); itor.hasNext();){
			String s = itor.next();
			line += s;
			if(itor.hasNext()){
				line += skeleton;
			}
		}
		line += border + arg2 + border;
		return line;
	}
	
	private <T extends Object> Collection<String> TransCollection(Collection<T> collect){
		Collection<String> result = new ArrayList<String>();
		for(Object o : collect){
			result.add(" " + o.toString() + " ");
		}
		return result;
	}
	
	public Wrapper<IO> myIO = new Wrapper<IO>();
}
