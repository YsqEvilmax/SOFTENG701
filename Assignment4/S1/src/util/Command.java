package util;

/**
 * Different possible commands based on user input
 */
public enum Command {
	
	VALID_MOVE,
	QUIT("q"),
	EMPTY_HOUSE,
	INVALID_MOVE;
	
	private String command;
	
	private Command(String command) {
		this.command = command;
	}
	
	private Command() {
		
	}
	
	public String getValue() {
		return command;
	}
	
}
