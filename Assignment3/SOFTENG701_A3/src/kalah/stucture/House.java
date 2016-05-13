package kalah.stucture;

public class House extends Container{

	public House(int seedNum, Group group) {
		super(seedNum, group);
	}
	
	@Override
	public String toString(){
		return Integer.toString(myIndex) + "[" + super.toString() + "]";
	}
	
	private int myIndex;
	public void setIndex(int index){
		myIndex = index;
	}
}
