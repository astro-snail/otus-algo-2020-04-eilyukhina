package algo4.list;

import java.util.NoSuchElementException;

import algo4.model.IArray;
import algo4.model.Iterator;
import algo4.model.Iterable;

public class DoublyLinkedList<T> implements Iterable<T>, IArray<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	public DoublyLinkedList() {
		head = tail = null;
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
		Node<T> node = new Node<>(item, tail, null);
		if (isEmpty()) {
			head = node;
		} else {
			tail.next = node;
		}
		tail = node;
		size++;		
	}

	@Override
	public void add(T item, int index) {
		if (index == size) {
			add(item);
		} else {
			check(index);
			Node<T> current = find(index);
			Node<T> node = new Node<>(item, current.prev, current);
			current.prev = node;
			if (index == 0) {	
				head = node;
			} else {
				current.prev.next = node;
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
			if (isEmpty()) {
				tail = null;
			}	
		} else if (index == size - 1) {
			old = tail;
			tail = old.prev;
		} else {
			old = find(index);
			old.next.prev = old.prev;
			old.prev.next = old.next; 
		}	
		old.prev = old.next = null;
		size--;
		return old.item;
	}

	public boolean isEmpty() {
		return head == null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<T> current = head;
		while (current != null) {
			sb.append(current);
			sb.append("<->");
			current = current.next;
		}
		sb.append("null");
		return sb.toString();			
	}
	
	private Node<T> find(int index) {
		Node<T> current = null;
		if (index < (size >> 1)) {
			current = head;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
		} else {
			current = tail;
			for (int i = size - 1; i > index; i--) {
				current = current.prev;
			}
		}
		return current;
	}
	
	private void check(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException();
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Itr(head);
	}
	
	@Override
	public Iterator<T> descendingIterator() {
		return new DescItr();
	}
	
	private class DescItr implements Iterator<T> {
        private final Itr itr = new Itr(tail);

        public boolean hasNext() {
            return itr.hasPrevious();
        }
        
        public T next() {
            return itr.previous();
        }
    }
	
	private class Itr implements Iterator<T> {
		
		private Node<T> next;
		private Node<T> lastReturned;
		
		Itr(Node<T> next) {
			this.next = next;
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

		private boolean hasPrevious() {
			return next != null;
		}

		private T previous() {
			if (!hasPrevious())
                throw new NoSuchElementException();
            lastReturned = next;
            next = next.prev;
            return lastReturned.item;
		}
	}

	@Override
	public void fill(T item, int size) {
		head = tail = null;
		this.size = 0;
		for (int i = 0; i < size; i++) {
			add(item);
		}		
	}
}
