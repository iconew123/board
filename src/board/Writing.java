package board;

public class Writing extends Info {

	private String title;
	private String content;
	private int number;
	private boolean isDeleted;

	public Writing(String id, String password, String title, String content, int number) {
		super(id, password);
		this.title = title;
		this.content = content;
		this.number = number;
		this.isDeleted = false;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNumber() {
		return this.number;
	}

	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted() {
		this.isDeleted = !this.isDeleted;
	}
}
