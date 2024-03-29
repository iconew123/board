package board;

import java.util.HashMap;
import java.util.Map;

public class UserManager implements CRUD {

	private UserManager() {

	}

	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}

	private Map<String, User> userList = new HashMap<String, User>();

	@Override
	public void create() {

	}

	@Override
	public void read() {

	}

	@Override
	public void delete() {

	}

	@Override
	public void update() {

	}
}
