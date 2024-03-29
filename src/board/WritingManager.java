package board;

import java.util.HashMap;
import java.util.Map;

public class WritingManager implements CRUD {

	private WritingManager() {

	}

	private static WritingManager instance = new WritingManager();

	public static WritingManager getInstance() {
		return instance;
	}

	private Map<String, Writing> writingList = new HashMap<String, Writing>();

	@Override
	public void create() {

	}

	@Override
	public void read() {

	}

	@Override
	public void update() {

	}

	@Override
	public void delete() {

	}

}
