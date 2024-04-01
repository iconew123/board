package board;

import java.util.HashMap;
import java.util.Map;

public class WritingManager implements CRUD<Writing> {

	private WritingManager() {

	}

	private static WritingManager instance = new WritingManager();

	public static WritingManager getInstance() {
		return instance;
	}

	private Map<String, Writing> writingList = new HashMap<String, Writing>();

	@Override
	public boolean create(Writing info) {

		return true;
	}

	@Override
	public Writing read(String target) {
		return new Writing(target, target, target, target, 0);
	}

	@Override
	public void update(Writing writing) {

	}

	@Override
	public boolean delete(String id, String pw) {

		return true;
	}

}
