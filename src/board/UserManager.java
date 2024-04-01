package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager implements CRUD<User> {

	private Map<String, User> userList = new HashMap<String, User>();

	private UserManager() {
		userList.put("ADMIN", new User("ADMIN", "ADMIN", "ADMIN"));
	}

	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}

	private boolean overlapId(String id) {

		boolean overlap = false;
		List keySet = new ArrayList(userList.keySet());
		for (Object key : keySet)
			if (key.equals(id)) {
				overlap = true;
				break;
			}

		return overlap;
	}

	@Override
	public boolean create(User info) {
		if (overlapId(info.getId())) {
			System.err.println("중복아이디입니다.");
			return false;
		}

		userList.put(info.getId(), info);
		return true;
	}

	private boolean findError(String id, String pw) {

		if (!overlapId(id)) {
			System.err.println("아이디를 찾을 수 없습니다.");
			return false;
		}

		if (!userList.get(id).getPassword().equals(pw)) {
			System.err.println("비밀번호가 일치하지 않습니다.");
			return false;
		}

		return true;
	}

	boolean checkLogIn(String id, String pw) {

		if (!findError(id, pw))
			return false;
		return true;
	}

	@Override
	public User read(String target) {

		int count = userList.get(target).getCount();
		User cloneUser = userList.get(target).clone();
		cloneUser.setCount(count);

		return cloneUser;
	}

	@Override
	public boolean delete(String id) {

		userList.remove(id);
		return true;
	}

	@Override
	public void update(User user) {
		userList.replace(user.getId(), user);
	}
}
