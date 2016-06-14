package kalah.wrapper;

public class SeedNum extends Wrapper<Integer> {

	public SeedNum(int num) {
		super(num);
	}

	public void Increase(int num){
		set(this.get() + num);
	}
	
	public void Decrease(int num){
		set(this.get() - num);
	}
}
