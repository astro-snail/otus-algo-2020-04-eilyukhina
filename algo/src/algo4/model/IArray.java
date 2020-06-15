package algo4.model;

public interface IArray<T> {
	
    int size();
    
    T get(int index);
    
    T set(T item, int index);
    
    void add(T item);
    
    void add(T item, int index); // with shift to tail
    
    T remove(int index); // return deleted element
    
    void fill(T item, int size);
}
