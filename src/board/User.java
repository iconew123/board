package board;

public class User extends Info {

	private String name;

	public User(String id, String password, String name) {
		super(id, password);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public User clone() {

		return new User(this.getId(), this.getPassword(), this.name);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s님의 총 조회수 %d회", this.name, this.getCount());
	}

}
