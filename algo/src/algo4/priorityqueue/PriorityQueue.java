package algo4.priorityqueue;

import java.util.NoSuchElementException;

import algo4.list.Queue;

public class PriorityQueue<T> {
	
	private PriorityNode<Queue<T>> head;
	
	public PriorityQueue() {
		this.head = null;
	}
	
	public void enqueue(int priority, T item) {
		if (isEmpty() || head.priority > priority) {
			Queue<T> queue = new Queue<>();
			queue.enqueue(item);
			PriorityNode<Queue<T>> node = new PriorityNode<>(priority, queue);
			node.next = head;
			head = node;
		} else {
			// find the right priority
			PriorityNode<Queue<T>> temp = head;
			while (temp.next != null && temp.next.priority <= priority) {
				temp = temp.next;
			}
			if (temp.priority == priority) {
				temp.item.enqueue(item);
			} else {
				Queue<T> queue = new Queue<>();
				queue.enqueue(item);
				PriorityNode<Queue<T>> node = new PriorityNode<>(priority, queue);
				node.next = temp.next;
				temp.next = node;
			}
		}
	}
	
	public T dequeue() {
		if (isEmpty()) 
			throw new NoSuchElementException();
		PriorityNode<Queue<T>> node = head;
		T item = node.item.dequeue();
		if (node.item.isEmpty()) {
			head = node.next;
			node = null;
		}	
		return item;		
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		PriorityNode<Queue<T>> temp = head;
		while (temp != null) {
			sb.append(temp);
			sb.append("->");
			temp = temp.next;
		}
		sb.append("null");
		return sb.toString();			
	}
	
	private static class PriorityNode<T> {
		int priority;
		T item;
		PriorityNode<T> next;
		
		PriorityNode(int priority, T item) {
			this.priority = priority;
			this.item = item;
		}
		
		@Override
		public String toString() {
			return "(" + priority + ")[" + item.toString() + "]";
		}
	}
}