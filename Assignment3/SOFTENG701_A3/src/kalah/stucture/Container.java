package kalah.stucture;

import kalah.wrapper.SeedNum;

public abstract class Container {

	public Container(int seedNum, Group group) {
		mySeedNum = new SeedNum(seedNum);
		this.myGroup = group;
	}
	
	@Override
	public String toString(){
		String result = "";
		if(mySeedNum.get() < 10){
			result += " ";
		}
		return result + String.valueOf(mySeedNum.get());	
	}
	
	protected Group myGroup;
	public Group getGroup(){
		return myGroup;
	}
	
	public SeedNum mySeedNum;
}
