package kalah.rule;

public class RuleObserSingleton {

	private RuleObserSingleton() {
		// TODO Auto-generated constructor stub
	}

	private static final RuleObserver<Object> myInstance = new RuleObserver<Object>();

	public static RuleObserver<Object> getInstance() {
		return myInstance;
	}
}
