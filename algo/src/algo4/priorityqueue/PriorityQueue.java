package algo4.priorityqueue;

import java.util.NoSuchElementException;

import algo4.list.Queue;

public class PriorityQueue<T> {
	
	private PriorityNode<Queue<T>> head;
	
	public static void main(String[] args) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		System.out.println(queue);
		
		queue.enqueue(3, 9);
		queue.enqueue(2, 0);
		queue.enqueue(1, 1);
		queue.enqueue(1, 2);
		queue.enqueue(3, 3);
		queue.enqueue(3, 4);
		queue.enqueue(2, 5);
		queue.enqueue(1, 6);
		queue.enqueue(2, 7);
		queue.enqueue(1, 8);
		
		System.out.println(queue);
		
		while (!queue.isEmpty()) {
			System.out.print(queue.dequeue() + ": ");
			System.out.println(queue);
		}	
		
		for (int i = 0; i < 100; i++) {
			queue.enqueue(i % 10, i);
		}
		System.out.println(queue);
		
		while (!queue.isEmpty()) {
			System.out.print(queue.dequeue() + ", ");
		}	
	}
	
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
			// find the right priority queue
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