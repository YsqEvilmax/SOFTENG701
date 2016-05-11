package kalah.move;

public enum MoveStatus {
	Moving,
	MoveEnded,
	Continue,
	IllegalNotOwner,
	IllegalCannotRemove,
	EmptyHouse,
	GameOver,
	Quit,
	Capture,
}
