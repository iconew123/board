package board;

// 제네릭 타입으로
public interface CRUD<T> {

	public boolean create(T info);

	public T read(String target);

	public void update(T info);

	public boolean delete(String log);

}
