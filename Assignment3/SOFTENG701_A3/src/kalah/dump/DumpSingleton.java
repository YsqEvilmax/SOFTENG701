package kalah.dump;

public class DumpSingleton{

	private DumpSingleton() {
		// TODO Auto-generated constructor stub
	}
	
	private static final DumpVisitor myInstance = new DumpVisitor();

	public static DumpVisitor getInstance() {
		return myInstance;
	}
}
