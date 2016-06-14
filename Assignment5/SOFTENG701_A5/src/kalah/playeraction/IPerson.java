package kalah.playeraction;

public interface IPerson<T> {
	public void Act(IAction<T> action, T arg);
}
