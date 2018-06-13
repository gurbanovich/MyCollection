package arrayList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import myInterfaces.MyList;

public class MyArrayList<E> implements MyList<E>, Iterable<E> {
	/*
	 * The task: write own implementation of class ArrayList and implement
	 * methods: addLast(E elem), addTo(int index, E elem), set(int index, E
	 * elem), getElem(int index), indexOf(E elem), remove(int index), remove(E
	 * elem), size(), sort(Comparator<E> comp), interface Iterable.
	 */

	private int index;
	private int size;
	private E[] elem;

	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.index = 0;
		this.elem = (E[]) new Object[16];
	}

	@SuppressWarnings("unchecked")
	public MyArrayList(int initSize) {
		this.index = 0;
		this.elem = (E[]) new Object[initSize];
	}

	@Override
	public void addLast(E e) {
		if (size == elem.length)
			growArray();
		elem[index] = e;
		index++;
		size++;
	}

	@Override
	public void addTo(int ind, E e) {
		if (size == elem.length)
			growArray();
		System.arraycopy(elem, ind, elem, ind + 1, size - ind);
		elem[ind] = e;
		size++;
		this.index++;
	}

	private void growArray() {
		elem = Arrays.copyOf(elem, elem.length * 2);
	}

	private void checkIndex(int ind) {
		if (ind < 0 || ind >= this.index)
			throw new IllegalArgumentException();
	}

	@Override
	public E getElem(int ind) {
		checkIndex(ind);
		return elem[ind];
	}

	@Override
	public void set(int ind, E e) {
		checkIndex(ind);
		elem[ind] = e;
	}

	@Override
	public int indexOf(E e) {
		int res = -1;
		for (int i = 0; i < index; i++) {
			if (elem[i].equals(e)) {
				res = i;
				break;
			}
		}
		return res;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void remove(int ind) {
		checkIndex(ind);
		System.arraycopy(elem, ind + 1, elem, ind, size - ind - 1);
		size--;
		this.index--;
	}

	@Override
	public void remove(E e) {
		int i = indexOf(e);
		if (i >= 0) {
			remove(i);
		}
	}

	@Override
	public void sort(Comparator<E> comp) {
		for (int i = 0; i < size(); i++) {
			for (int j = i + 1; j < size(); j++) {
				if (comp.compare(getElem(j), getElem(i)) < 0) {
					E elem = getElem(i);
					set(i, getElem(j));
					set(j, elem);
				}
			}
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return (index < size()) && (elem[index] != null);
			}

			@Override
			public E next() {
				return getElem(index++);
			}
		};
	}

}
