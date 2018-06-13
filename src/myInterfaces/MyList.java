package myInterfaces;

import java.util.Comparator;

public interface MyList<E> {

	public void addLast(E elem);
	
	public void addTo(int index, E elem);

	public void set(int index, E elem);

	public E getElem(int index);

	public int indexOf(E elem);

	public void remove(int index);

	public void remove(E elem);

	public int size();

	public void sort(Comparator<E> comp);

}
