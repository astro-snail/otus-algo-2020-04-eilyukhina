package algo5;

import tester.Task;
import tester.Tester;

public class SelectionSort extends CommonSort {

	public static void main(String[] args) {
		Task task = new SelectionSort();
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/0.random");
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/1.digits");
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/2.sorted");
		Tester tester = new Tester(task, "src/algo5/sorting-tests/3.revers");
		tester.runTests();
	}

	@Override
	public String getName() {
		return "Selection Sort";
	}

	@Override
	protected void sort() {
		for (int i = 0; i < a.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[minIdx]) {
					minIdx = j;
				}
			}
			if (minIdx != i) {
				swap(i, minIdx);
			}	
		}
	}
}
