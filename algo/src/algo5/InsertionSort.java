package algo5;

import tester.Task;
import tester.Tester;

public class InsertionSort extends SortTask {

	public static void main(String[] args) {
		Task task = new InsertionSort();
		//Tester tester = new Tester(task, "sorting-tests/0.random");
		//Tester tester = new Tester(task, "sorting-tests/1.digits");
		//Tester tester = new Tester(task, "sorting-tests/2.sorted");
		Tester tester = new Tester(task, "sorting-tests/3.revers");
		tester.runTests();
	}

	@Override
	public String getName() {
		return "Insertion Sort";
	}

	@Override
	public void sort() {
		for (int i = 1; i < arr.length; i++) {
			int keyValue = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > keyValue) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = keyValue;
		}
	}
}
