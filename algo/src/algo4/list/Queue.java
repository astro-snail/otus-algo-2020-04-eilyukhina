package algo4.list;

import java.util.NoSuchElementException;

public class Queue<T> {
	
	private Node<T> head;
	private Node<T> tail;
	
	public Queue() {
		this.head = this.tail = null;
	}
	
	public void enqueue(T item) {
		final Node<T> node = new Node<>(item);
		if (isEmpty()) {
			head = node;
		} else  {
			tail.next = node;
		}
		tail = node;
	}
	
	public T dequeue() {
		if (isEmpty()) 
			throw new NoSuchElementException();
		final Node<T> node = head;
		head = node.next;
		node.next = null;
		if (isEmpty())
			tail = null;
		return node.item;		
	}	
	
	public boolean isEmpty() {
		return head == null;
	}
	
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
}
