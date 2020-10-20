package algo5;

import tester.Task;
import tester.Tester;

public class BubbleSort extends SortTask {

	public static void main(String[] args) {
		Task task = new BubbleSort();
		//Tester tester = new Tester(task, "sorting-tests/0.random");
		//Tester tester = new Tester(task, "sorting-tests/1.digits");
		Tester tester = new Tester(task, "sorting-tests/2.sorted");
		//Tester tester = new Tester(task, "sorting-tests/3.revers");
		tester.runTests();
	}

	@Override
	public void sort() {
		boolean swapped = false;
		int length = arr.length;
		do {
			swapped = false;
			for (int i = 0; i < length - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					SortUtils.swap(arr, i, i + 1);
					swapped = true;
				}
			}
			length--;
		} while (swapped);
	}

	@Override
	public String getName() {
		return "Bubble Sort";
	}
}
