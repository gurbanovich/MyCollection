package linkesList;

import java.util.Comparator;
import java.util.Iterator;

import myInterfaces.DescendingIterator;
import myInterfaces.MyList;

public class MyLinkedList<E> implements MyList<E>, Iterable<E>, DescendingIterator<E> {
	/*
	 * The task: write own implementation of class LinkedList and implement
	 * methods: addLast(E elem), addTo(int index, E elem), set(int index, E
	 * elem), getElem(int index), indexOf(E elem), remove(int index), remove(E
	 * elem), size(), sort(Comparator<E> comp), interface Iterable.
	 */
	
	@SuppressWarnings("hiding")
	private class Node<E> {

		private E elem;
		private Node<E> next;
		private Node<E> prev;

		public Node(E elem, Node<E> prev, Node<E> next) {
			this.elem = elem;
			this.next = next;
			this.prev = prev;
		}

		public E getElem() {
			return elem;
		}

		public Node<E> getNext() {
			return next;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public void setElem(E elem) {
			this.elem = elem;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
	}

	private int size = 0;
	private Node<E> firstN;
	private Node<E> lastN;

	public MyLinkedList() {
		this.lastN = new Node<E>(null, null, null);
		this.firstN = new Node<E>(null, null, lastN);
		lastN.setPrev(firstN);
	}

	public int getSize() {
		return size;
	}

	public Node<E> getFirstN() {
		return firstN;
	}

	public Node<E> getLastN() {
		return lastN;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setfFrstN(Node<E> firstN) {
		this.firstN = firstN;
	}

	public void setLastN(Node<E> lastN) {
		this.lastN = lastN;
	}

	@Override
	public void addLast(E elem) {
		Node<E> prev = getLastN();
		prev.setElem(elem);
		setLastN(new Node<E>(null, prev, null));
		prev.setNext(lastN);
		size++;
	}

	public void addFirst(E elem) {
		Node<E> next = getFirstN();
		next.setElem(elem);
		firstN = new Node<E>(null, null, next);
		next.setPrev(firstN);
		size++;
	}

	@Override
	public void addTo(int index, E elem) {
		checkIndex(index);
		Node<E> next = getNode(index);
		Node<E> prev = next.getPrev();
		Node<E> cur = new Node<E>(elem, prev, next);
		next.setPrev(cur);
		prev.setNext(cur);
		size++;
	}

	@Override
	public E getElem(int index) {
		checkIndex(index);
		Node<E> target = getFirstN().getNext();
		for (int i = 0; i < index; i++) {
			target = getNextNode(target);
		}
		return target.getElem();
	}

	private Node<E> getNode(int index) {
		checkIndex(index);
		Node<E> target = firstN.getNext();
		for (int i = 0; i < index; i++) {
			target = getNextNode(target);
		}
		return target;
	}

	private Node<E> getNode(E elem) {
		Node<E> target = firstN.getNext();
		for (int i = 0; i < getSize(); i++) {
			if (target.getElem().equals(elem)) {
				break;
			} else
				target = getNextNode(target);
		}
		return target;
	}

	private Node<E> getNextNode(Node<E> node) {
		return node.getNext();
	}

	@Override
	public void set(int index, E e) {
		checkIndex(index);
		getNode(index).setElem(e);
	}

	@Override
	public int indexOf(E elem) {
		int index = 0;
		Node<E> target = firstN.getNext();
		for (int i = 0; i < getSize(); i++) {
			if (target.getElem().equals(elem)) {
				index = i;
				break;
			} else
				target = getNextNode(target);
		}
		return index;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void remove(int index) {
		Node<E> target = getNode(index);
	    target.getNext().setPrev(target.getPrev());
	    target.getPrev().setNext(target.getNext());
		target.setElem(null);
		size--;
	}

	@Override
	public void remove(E elem) {
		Node<E> target = getNode(elem);
		target.getPrev().setNext(target.getNext());
		target.getNext().setPrev(target.getPrev());
		target.setElem(null);
		size--;
	}

	private void checkIndex(int index) {
		if (index < 0 || index > (this.size - 1))
			throw new IllegalArgumentException();
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
			int counter = 0;

			@Override
			public boolean hasNext() {
				return counter < getSize();
			}

			@Override
			public E next() {
				return getElem(counter++);
			}
		};
	}

	@Override
	public Iterator<E> descendingIterator() {
		return new Iterator<E>() {
			int counter = size - 1;

			@Override
			public boolean hasNext() {
				return counter >= 0;
			}

			@Override
			public E next() {
				return getElem(counter--);
			}

		};
	}

}
