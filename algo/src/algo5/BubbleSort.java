package algo5;

import tester.Task;
import tester.Tester;

public class BubbleSort extends CommonSort {

	public static void main(String[] args) {
		Task task = new BubbleSort();
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/0.random");
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/1.digits");
		Tester tester = new Tester(task, "src/algo5/sorting-tests/2.sorted");
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/3.revers");
		tester.runTests();
	}
	
	@Override
	protected void sort() {
		boolean swapped = false;
		int n = a.length;
		do {
			swapped = false;
			for (int j = 0; j < n - 1; j++) {
				if (a[j] > a[j + 1]) {
					swap(j, j + 1);
					swapped = true;
				}
			}
			n--;
		} while (swapped);
	}

	@Override
	public String getName() {
		return "Bubble Sort";
	}
}
