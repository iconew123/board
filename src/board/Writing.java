package board;

public class Writing extends Info {

	private String title;
	private String content;
	private int count;

	public Writing(String id, String password, String title, String content) {
		super(id, password);
		this.title = title;
		this.content = content;
		this.count = 0;
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

	public void setCountPlus() {
		this.count++;
	}
}
