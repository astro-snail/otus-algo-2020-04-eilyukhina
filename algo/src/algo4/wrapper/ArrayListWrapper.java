package algo4.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algo4.model.IArray;

public class ArrayListWrapper<T> implements IArray<T> {
	
	private List<T> array;
	
	public ArrayListWrapper() {
        array = new ArrayList<>();
    }

	@Override
	public int size() {
		return array.size();
	}

	@Override
	public void add(T item) {
		array.add(item);		
	}

	@Override
	public T get(int index) {
		return array.get(index);
	}
	
	@Override
	public T set(T item, int index) {
		return array.set(index, item);
	}

	@Override
	public void add(T item, int index) {
		array.add(index, item);		
	}

	@Override
	public T remove(int index) {
		return array.remove(index);
	}
	
	@Override
	public String toString() {
		return array.toString();
	}
	
	@Override
	public void fill(T value, int size) {
		array = new ArrayList<T>(Collections.nCopies(size, value));
	}
}
