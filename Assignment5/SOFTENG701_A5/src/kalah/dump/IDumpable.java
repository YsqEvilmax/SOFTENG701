package kalah.dump;

public interface IDumpable<A> {
	public void Accept(IArgVisitor<A> v, Object arg);
}
