package algo4.model;

public interface Iterable<T> {
	
	Iterator<T> iterator();
	
	default Iterator<T> descendingIterator() {
		return null;
	}
}
