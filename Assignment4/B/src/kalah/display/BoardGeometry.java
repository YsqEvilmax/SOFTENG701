package kalah.display;

import com.qualitascorpus.testsupport.IO;

/**
 * Specifies how to lay out the board when it is displayed.
 */
public interface BoardGeometry {
	public void display(IO io, String id1, String id2);
}
