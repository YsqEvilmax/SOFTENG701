package kalah.wrapper;

public class Wrapper<T> implements IGetter<T>, ISetter<T> {

	public Wrapper() {
		// TODO Auto-generated constructor stub
	}
	
	public Wrapper(T content) {
		this();
		set(content);
	}
	
	@Override
	public T get(){
		return myContent;
	}
	
	@Override
	public void set(T content){
		myContent = content;
	}
	
	private T myContent;

}
