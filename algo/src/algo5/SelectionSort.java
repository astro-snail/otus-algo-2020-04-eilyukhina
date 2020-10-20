package algo5;

import tester.Task;
import tester.Tester;

public class SelectionSort extends SortTask {

	public static void main(String[] args) {
		Task task = new SelectionSort();
		//Tester tester = new Tester(task, "sorting-tests/0.random");
		//Tester tester = new Tester(task, "sorting-tests/1.digits");
		//Tester tester = new Tester(task, "sorting-tests/2.sorted");
		Tester tester = new Tester(task, "sorting-tests/3.revers");
		tester.runTests();
	}

	@Override
	public String getName() {
		return "Selection Sort";
	}

	@Override
	public void sort() {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				SortUtils.swap(arr, i, minIndex);
			}	
		}
	}
}
