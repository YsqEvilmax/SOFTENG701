package kalah.dump;

public interface IDumpable {
	public void Accept(DumpVisitor v, Object arg);
}
