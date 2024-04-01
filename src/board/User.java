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

}
