package algo6.externalsort;

public class MinHeap {

	HeapNode[] arr;
	int heapSize;

	public MinHeap(HeapNode[] arr) {
		this.arr = arr;
		this.heapSize = arr.length;

		int i = getParent(heapSize - 1); 
		while (i >= 0) { 
			MinHeapify(i); 
			i--; 
		} 
	}

	public HeapNode getMin() {
		return arr[0];
	}

	public void replaceMin(HeapNode node) {
		arr[0] = node; 
		MinHeapify(0); 
	}

	private int getParent(int i) {
		return (i - 1) / 2;
	}

	private int getLeftChild(int i) {
		return 2 * i + 1;
	}

	private int getRightChild(int i) {
		return 2 * i + 2;
	}

	private void MinHeapify(int i) {

		int left = getLeftChild(i); 
		int right = getRightChild(i);

		int min = i; 

		if (left < heapSize && arr[left].element < arr[min].element) 
			min = left; 
		if (right < heapSize && arr[right].element < arr[min].element) 
			min = right;

		if (min != i) {
			HeapNode temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp; 
			MinHeapify(min); 
		} 
	}
}

class HeapNode {
	int element;
	int index;

	HeapNode(int element, int index) {
		this.element = element;
		this.index = index;
	}
}	
