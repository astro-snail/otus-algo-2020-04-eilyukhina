package algo5;

import tester.Task;
import tester.Tester;

public class HeapSort extends SortTask {

	public static void main(String[] args) {
		Task task = new HeapSort();
		//Tester tester = new Tester(task, "sorting-tests/0.random");
		//Tester tester = new Tester(task, "sorting-tests/1.digits");
		//Tester tester = new Tester(task, "sorting-tests/2.sorted");
		Tester tester = new Tester(task, "sorting-tests/3.revers");
		tester.runTests();
	}

	@Override
	public String getName() {
		return "Heap Sort";
	}

	@Override
	public void sort() {
		int length = arr.length;

		buildMaxHeap(length);

		int end = length - 1;

		while (end > 0) {
			SortUtils.swap(arr, end, 0);
			end--;
			siftDown(0, end);
		}
	}

	private void buildMaxHeap(int length) {
		int end = 1;

		while (end < length) {
			siftUp(0, end);
			end++;
		}
	}

	private void siftUp(int start, int end) {
		int child = end;

		while (child > start) {
			int parent = getParent(child);
			if (arr[parent] < arr[child]) {
				SortUtils.swap(arr, parent, child);
				child = parent;
			} else {
				return;
			}
		}
	}

	private void siftDown(int start, int end) {
		int root = start;

		while (getLeftChild(root) <= end) {

			int left = getLeftChild(root);
			int right = getRightChild(root);

			int max = root;

			if (left <= end && arr[max] < arr[left]) {
				max = left;
			}
			if (right <= end && arr[max] < arr[right]) {
				max = right;
			}
			if (max == root) {
				return;
			} else {
				SortUtils.swap(arr, root, max);
				root = max;
			}
		}
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
}
