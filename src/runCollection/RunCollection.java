package runCollection;

import java.util.Iterator;

import arrayList.MyArrayList;
import hashMap.MyHashMap;
import linkesList.MyLinkedList;
import xomparators.StringComparator;

public class RunCollection {

	public static void main(String[] args) throws InterruptedException {
		// Demo class MyArrayList

		MyArrayList<String> alist = new MyArrayList<String>();
		alist.addLast("aaa");
		alist.addLast("bbb");
		alist.addLast("ccc");
		alist.addLast("ddd");
		alist.addLast("bbb");
		alist.addLast("ddd");
		alist.addLast("ccc");
		alist.addLast("ddd");
		alist.addLast("aaa");
		alist.addLast("ddd");
		alist.addLast("eee");
		alist.addLast("ddd");
		alist.addTo(5, "vvv");
		alist.addLast("ccc");
		alist.addLast("ddd");
		alist.addLast("ddd");
		System.out.println(" Operation with MyArrayList: ");

		System.out.println("size of alist: " + alist.size());
		System.out.println();

		System.out.println("value of 2 element: " + alist.getElem(2));
		System.out.println();

		System.out.println("index of element 'ccc': " + alist.indexOf("ccc"));
		System.out.println();

		alist.set(2, "mmm");
		System.out.println("All elements of alist after setting element 2: ");

		Iterator<String> aiter = alist.iterator();
		while (aiter.hasNext())
			System.out.println(aiter.next());

		System.out.println("size of alist: " + alist.size());
		//Thread.sleep(1000);
		alist.remove(12);
		System.out.println();
		System.out.println("All elements of alist after sorting and removig element 12: ");
		alist.sort(new StringComparator());
		Iterator<String> aiter2 = alist.iterator();
		while (aiter2.hasNext())
			System.out.println(aiter2.next());
		System.out.println("size of alist after setting element and removing element:" + alist.size());
		System.out.println();

		// Demo class MyLinkedList
		MyLinkedList<String> llist = new MyLinkedList<String>();
		llist.addLast("aaa");
		llist.addFirst("bbb");
		llist.addLast("ccc");
		llist.addLast("ddd");
		llist.addLast("bbb");
		llist.addLast("ddd");
		llist.addLast("ccc");
		llist.addLast("ddd");
		llist.addLast("aaa");
		llist.addLast("ddd");
		llist.addLast("eee");
		llist.addLast("ddd");
		llist.addLast("ccc");
		llist.addLast("ddd");
		llist.addLast("ddd");
		llist.addTo(5, "vvv");

		System.out.println(" operation with MyLinkedList: ");

		System.out.println("size of llist: " + llist.size());
		System.out.println();

		System.out.println("value of 8 element: " + llist.getElem(8));
		System.out.println();

		System.out.println("index of element 'bbb': " + llist.indexOf("bbb"));
		System.out.println();

		llist.set(8, "mmm");
		System.out.println("All elements of llist after setting element 8: ");

		Iterator<String> liter = llist.iterator();
		while (liter.hasNext())
			System.out.println(liter.next());

		System.out.println("size of llist: " + llist.size());

		llist.remove(10);
		System.out.println();
		System.out.println("All elements of llist after sorting and removig element 10: ");
		llist.sort(new StringComparator());
		Iterator<String> liter2 = llist.iterator();
		while (liter2.hasNext())
			System.out.println(liter2.next());
		System.out.println("size of llist after setting element and removing element: " + llist.size());
		System.out.println();

		// Demo class MyHashMap
		MyHashMap<String, Integer> mymap = new MyHashMap<String, Integer>();
		System.out.println(" operation with MyLinkedList: ");

		System.out.println("insert element 'aaa - 11': " +  mymap.insert("aaa", 11));
		System.out.println("insert element 'aaa - 11': " +  mymap.insert("aaa", 11));
		System.out.println("insert element 'bbb - 22': " +  mymap.insert("bbb", 22));
		System.out.println("insert element 'ccc - 33': " +  mymap.insert("ccc", 33));
		System.out.println("insert element 'aaa - 12': " +  mymap.insert("aaa", 12));
		System.out.println("insert element 'ddd - 18': " +  mymap.insert("ddd", 18));
		System.out.println("insert element 'ddd - 18': " +  mymap.insert("ddd", 18));
		System.out.println("insert element 'hhh - 66': " +  mymap.insert("hhh", 66));

		System.out.println();
		System.out.println("size of mymap: " + mymap.size());
		
		System.out.println("delete element 'ccc': " +  mymap.delete("ccc"));
		System.out.println("value of element 'aaa': " + mymap.getValue("aaa"));
		System.out.println("value of element 'hhh': " + mymap.getValue("hhh"));

		System.out.println();
		System.out.println("All elements of mymap: ");
		Iterator<Integer> hiter = mymap.iterator();
		while (hiter.hasNext())
			System.out.println(hiter.next());
		System.out.println();

		System.out.println("size of mymap after deleting element 'ccc': " + mymap.size());

	}

}
