package algo4.list;

import java.util.NoSuchElementException;

import algo4.model.IArray;
import algo4.model.Iterator;
import algo4.model.Iterable;

public class SinglyLinkedList<T> implements Iterable<T>, IArray<T> {

	private Node<T> head;
	private int size;
	
	public SinglyLinkedList() {
		head = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T get(int index) {
		check(index);
		return find(index).item;
	}

	@Override
	public T set(T item, int index) {
		check(index);
		Node<T> node = find(index);
		T old = node.item;
		node.item = item;
		return old;
	}

	@Override
	public void add(T item) {
		Node<T> node = new Node<>(item, null, null);
		if (isEmpty()) {
			head = node;
		} else {
			Node<T> last = find(size - 1);
			last.next = node;
		}
		size++;
	}

	@Override
	public void add(T item, int index) {
		if (index == size) {
			add(item);
		} else {
			check(index);
			if (index == 0) {
				Node<T> node = new Node<>(item, null, head);
				head = node;
			} else {
				Node<T> previous = find(index - 1);
				Node<T> node = new Node<>(item, null, previous.next);
				previous.next = node;
			}	
			size++;
		}	
	}

	@Override
	public T remove(int index) {
		if (isEmpty()) 
			throw new NoSuchElementException();
		check(index);
		Node<T> old;
		if (index == 0) {
			old = head;
			head = old.next;
		} else {
			Node<T> previous = find(index - 1);
			old = previous.next;
			previous.next = old.next;
		}	
		old.next = null;
		size--;
		return old.item;
	}

	public boolean isEmpty() {
		return head == null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<T> temp = head;
		while (temp != null) {
			sb.append(temp);
			sb.append("->");
			temp = temp.next;
		}
		sb.append("null");
		return sb.toString();			
	}
	
	private Node<T> find(int index) {
		Node<T> current = head;
		int i = 0;
		while(i < index) {
			current = current.next;
			i++;
		}
		return current;
	}
	
	@Override
	public Iterator<T> iterator() {
        return new Itr();
    }
	
	private class Itr implements Iterator<T> {
		
		private Node<T> next;
		private Node<T> lastReturned;
		
		Itr() {
			next = head;
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastReturned = next;
            next = next.next;
            return lastReturned.item;
		}
	}
	
	private void check(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException();
	}

	@Override
	public void fill(T item, int size) {
		head = null;
		this.size = 0;
		for (int i = 0; i < size; i++) {
			add(item);
		}
	}
}
