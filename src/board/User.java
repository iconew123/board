package board;

public class User extends Info {

	private String name;
	// 사용자가 열람한 횟수(글쓰기 최소조건)
	private int count;

	public User(String id, String password, String name) {
		super(id, password);
		this.name = name;
		count = 0;
	}

	public String getName() {
		return this.name;
	}

	public int getCount() {
		return this.count;
	}

	public void SetCountPlus() {
		this.count++;
	}

}
