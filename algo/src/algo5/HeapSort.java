package algo5;

import tester.Task;
import tester.Tester;

public class HeapSort extends CommonSort {

	public static void main(String[] args) {
		Task task = new HeapSort();
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/0.random");
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/1.digits");
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/2.sorted");
		Tester tester = new Tester(task, "src/algo5/sorting-tests/3.revers");
		tester.runTests();
	}

	@Override
	public String getName() {
		return "Heap Sort";
	}

	@Override
	protected void sort() {
		int n = a.length;
		
		buildMaxHeap(n);
		
		int end = n - 1;
		
		while (end > 0) {
			swap(end, 0);
			end--;
			siftDown(0, end);
		}
	}
	
	private void buildMaxHeap(int n) {
		int end = 1;
		
		while (end < n) {
			siftUp(0, end);
			end++;
		}
	}
	
	private void siftUp(int start, int end) {
		int child = end;
		
		while (child > start) {
			int parent = parent(child);
			if (a[parent] < a[child]) {
				swap(parent, child);
				child = parent;
			} else {
				return;
			}
		}
	}
	
	private void siftDown(int start, int end) {
		int root = start;

	    while (leftChild(root) <= end) {
	    	int child = leftChild(root);
	    	int swap = root;
	    	if (a[swap] < a[child]) {
	    		swap = child;
	    	}
	    	if (child + 1 <= end && a[swap] < a[child + 1]) {
	    		swap = child + 1;
	    	}
	    	if (swap == root) {
	    		return;
	    	} else {
	    		swap(root, swap);
	    		root = swap;
	    	}
	    }
	}

	private int parent(int child) {
		return (child - 1) / 2;
	}
	
	private int leftChild(int parent) {
		return 2 * parent + 1;
	}
}
