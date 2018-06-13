package myInterfaces;

public interface MyMap<E, K> {

	public boolean insert(E key, K value);

	public K getValue(E key);

	public boolean delete(E key);

	public int size();
}
