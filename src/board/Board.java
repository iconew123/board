package board;

public class Board {

	private UserManager userManager = UserManager.getInstance();
	private WritingManager writingManager = WritingManager.getInstance();

	private String type;
	private int sel;

	public Board(String type) {
		this.type = type;
		this.sel = 0;
	}

	public void run() {

	}
}
