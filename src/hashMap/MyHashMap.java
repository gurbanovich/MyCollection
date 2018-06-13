package hashMap;

import java.util.Iterator;
import java.util.Objects;

import linkesList.MyLinkedList;
import myInterfaces.MyMap;

public class MyHashMap<E, K> implements MyMap<E, K>, Iterable<K> {
	/*
	 * The task: write own implementation of class HashMap and implement
	 * methods: insert(E key, K value), getValue(E key), delete(E key), size(),
	 * interface Iterable.
	 */

	@SuppressWarnings("hiding")
	public class Entry<E, K> {

		private E key;
		private K value;
		private int hash;
		private MyLinkedList<Entry<E, K>> elements;

		public Entry(E key, K value) {
			this.key = key;
			this.value = value;
			elements = new MyLinkedList<Entry<E, K>>();
		}

		public E getKey() {
			return key;
		}

		public K getValue() {
			return value;
		}

		public int hash() {
			return hashCode() % entryes.length;
		}

		public MyLinkedList<Entry<E, K>> getElements() {
			return elements;
		}

		public void setKey(E key) {
			this.key = key;
		}

		public void setValue(K value) {
			this.value = value;
		}

		@Override
		public int hashCode() {
			hash = 37;
			hash = hash * 17 + key.hashCode();
			return hash;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj instanceof Entry) {
				@SuppressWarnings("unchecked")
				Entry<E, K> e = (Entry<E, K>) obj;
				return (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue()));
			}
			return false;
		}

	}

	private int size;
	private float threshold;
	private Entry<E, K>[] entryes;

	@SuppressWarnings("unchecked")
	public MyHashMap() {
		this.entryes = new Entry[16];
		threshold = entryes.length * 0.75f;
	}

	public int hash(Entry<E, K> e) {
		return e.hashCode() % entryes.length;
	}

	public int hash(final E key) {
		int hash = 37;
		hash = hash * 17 + key.hashCode();
		return hash % entryes.length;
	}

	@Override
	public boolean insert(E key, K value) {
		if (size + 1 >= threshold) {
			threshold *= 2;
			arrayDoubling();
		}
		Entry<E, K> entry = new Entry<E, K>(key, value);
		int index = hash(key);
		if (entryes[index] == null)
			return simpleAdd(index, entry);
		MyLinkedList<Entry<E, K>> entryList = entryes[index].getElements();
		for (Entry<E, K> en : entryList) {
			if ((keyExistNewValue(en, entry, value)) || (collisionProc(en, entry, entryList)))
				return true;
		}
		return false;
	}

	private boolean simpleAdd(int index, Entry<E, K> entry) {
		entryes[index] = new Entry<E, K>(null, null);
		entryes[index].getElements().addLast(entry);
		size++;
		return true;
	}

	private boolean keyExistNewValue(final Entry<E, K> en, final Entry<E, K> entry, final K value) {
		if ((entry.getKey().equals(en.getKey())) && (!entry.getValue().equals(en.getValue()))) {
			en.setValue(value);
			return true;
		}
		return false;
	}

	private boolean collisionProc(final Entry<E, K> en, final Entry<E, K> entry, MyLinkedList<Entry<E, K>> entryList) {
		if ((entry.hashCode() == en.hashCode()) && (!Objects.equals(entry.getKey(), en.getKey()))
				&& (!Objects.equals(entry.getValue(), en.getValue()))) {
			entryList.addLast(entry);
			size++;
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private void arrayDoubling() {
		Entry<E, K>[] curEl = entryes;
		entryes = new Entry[curEl.length * 2];
		size = 0;
		for (Entry<E, K> en : curEl) {
			if (en != null) {
				for (Entry<E, K> inEn : en.getElements()) {
					insert(inEn.getKey(), inEn.getValue());
				}
			}
		}
	}

	@Override
	public boolean delete(final E key) {
		int index = hash(key);
		if (entryes[index] == null)
			return false;
		if (entryes[index].getElements().size() == 1) {
			entryes[index].getElements().remove(0);
			entryes[index] = null;
			size--;
			return true;
		}
		MyLinkedList<Entry<E, K>> entryList = entryes[index].getElements();
		for (Entry<E, K> en : entryList) {
			if (key.equals(en.getKey())) {
				entryList.remove(en);
				size--;
				return true;
			}
		}
		return false;
	}

	@Override
	public K getValue(E key) {
		int index = hash(key);
		if ((index < entryes.length) && (entryes[index] != null)) {
			MyLinkedList<Entry<E, K>> entryList = entryes[index].getElements();
			for (Entry<E, K> en : entryList) {
				if (key.equals(en.getKey())) {
					return en.getValue();
				}
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<K> iterator() {
		return new Iterator<K>() {

			int counterArray = 0;
			int valueCount = 0;
			Iterator<Entry<E, K>> subIter = null;

			@Override
			public boolean hasNext() {
				if (valueCount == size)
					return false;
				if (subIter == null || !subIter.hasNext()) {
					if (moveToNextEl()) {
						subIter = entryes[counterArray].getElements().iterator();
					} else {
						return false;
					}
				}
				return subIter.hasNext();
			}

			private boolean moveToNextEl() {
				counterArray++;
				while (counterArray < entryes.length && entryes[counterArray] == null) {
					counterArray++;
				}
				return counterArray < entryes.length && entryes[counterArray] != null;
			}

			@Override
			public K next() {
				valueCount++;
				return subIter.next().getValue();
			}
		};
	}
}
