package algo5;

import tester.Task;
import tester.Tester;

public class InsertionSort extends CommonSort {

	public static void main(String[] args) {
		Task task = new InsertionSort();
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/0.random");
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/1.digits");
		//Tester tester = new Tester(task, "src/algo5/sorting-tests/2.sorted");
		Tester tester = new Tester(task, "src/algo5/sorting-tests/3.revers");
		tester.runTests();
	}

	@Override
	public String getName() {
		return "Insertion Sort";
	}

	@Override
	protected void sort() {
		for (int i = 1; i < a.length; i++) {
			int key = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = key;
		}
	}
}
