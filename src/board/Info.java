package board;

public class Info {

	private String id;
	private String password;
	private int count;

	public Info(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}

	// 비밀번호만 변경가능
	public void setPassWord(String password) {
		this.password = password;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
