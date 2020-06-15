package algo4.array;

import algo4.model.IArray;

public class MatrixArray<T> implements IArray<T> {

	private IArray<IArray<T>> array;
    private int vector;
    private int size;

    public MatrixArray(int vector) {
        this.vector = vector;
        array = new FactorArray<>(100, vector);
        size = 0;
    }

    public MatrixArray() {
        this(100);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size == array.size() * vector)
            array.add(new VectorArray<T>(vector));
        
        array.get(size / vector).add(item);
        size++;
    }

    @Override
    public T get(int index) {
        return array.get(index / vector).get(index % vector);
    }

	@Override
	public void add(T item, int index) {
		if (size == array.size() * vector)
            array.add(new VectorArray<T>(vector));
		
		int k = array.size() - 1;
		IArray<T> current = array.get(k);
		
		while (k > index / vector) {
			k--;
			IArray<T> previous = array.get(k);
			current.add(previous.remove(previous.size() - 1), 0);
			current = previous;
		}
		current.add(item, index % vector);
		size++;
	}

	@Override
	public T remove(int index) {
		int k = index / vector;
		IArray<T> current = array.get(k);
		
		T item = current.remove(index % vector);
		while (k < array.size() - 1) {
			k++;
			IArray<T> next = array.get(k);
			current.add(next.remove(0));
			current = next;
		}
		size--;
		
		if ((array.size() - 1) * vector == size)
            array.remove(array.size() - 1);
		return item;
	}
	
	@Override
	public String toString() {
		return array.toString();
	}

	@Override
	public T set(T item, int index) {
		T old = get(index);
		array.get(index / vector).set(item, index % vector);
		return old;
	}
	
	@Override
	public void fill(T item, int size) {
		
		int numOfSubArrays = size / vector + (size % vector != 0 ? 1 : 0);
		
		array = new FactorArray<>(100, numOfSubArrays);
		
		for (int i = 0; i < numOfSubArrays; i++) {
			IArray<T> subArray = new VectorArray<>(vector);
			int sizeOfSubArray = (i == numOfSubArrays - 1) ? size - vector * i : vector;
			subArray.fill(item, sizeOfSubArray);
			array.add(subArray);
		}
		
		this.size = size;
	}
}
