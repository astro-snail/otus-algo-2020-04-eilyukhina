package algo4.list;

public class Node<T> {
	T item; 
	Node<T> prev;
	Node<T> next;
	
	public Node(T item) {
		this(item, null, null);
	}
	
	public Node(T item, Node<T> prev, Node<T> next) {
		this.item = item;
		this.prev = prev;
		this.next = next;
	}
	
	@Override
	public String toString() {
		return item.toString();
	}
}
