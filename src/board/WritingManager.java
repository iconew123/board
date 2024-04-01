package board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WritingManager implements CRUD<Writing> {

	private Map<Integer, Writing> writingList = new HashMap<Integer, Writing>();

	private WritingManager() {
		writingList.put(1, new Writing("ADMIN", "ADMIN", "조회수증가용 글", "해당 게시글을 클릭해서 조회수를 얻으세요.", 1));
	}

	private static WritingManager instance = new WritingManager();

	public static WritingManager getInstance() {
		return instance;
	}

	@Override
	public boolean create(Writing info) {

		writingList.put(info.getNumber(), info);

		return true;
	}

	@Override
	public Writing read(String target) {
		int number = Integer.parseInt(target);
		if (!findWriting(number)) {
			System.err.println("글을 찾을 수 없습니다.");
			return null;
		}

		int count = writingList.get(number).getCount();
		Writing cloneWriting = writingList.get(number).clone();
		cloneWriting.setCount(count);
		return cloneWriting;

	}

	private boolean findWriting(int number) {

		boolean isFind = false;
		List keySet = new ArrayList(writingList.keySet());
		for (Object key : keySet)
			if (key.equals(number)) {
				isFind = true;
				break;
			}

		return isFind;
	}

	@Override
	public void update(Writing writing) {
		writingList.replace(writing.getNumber(), writing);
	}

	@Override
	public boolean delete(String target) {

		int number = Integer.parseInt(target);
		Writing curWriting = writingList.get(number);
		curWriting.setIsDeleted();

		return true;
	}

	public void sort() {
		List keySet = new ArrayList(writingList.keySet());
		Collections.sort(keySet);
	}

}
