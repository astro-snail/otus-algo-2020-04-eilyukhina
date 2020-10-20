package algo5;

import tester.Task;

public abstract class SortTask implements Sort, Task {

	protected int[] arr;

	@Override
	public String[] run(String[] data) {
		arr = SortUtils.testInputToIntArray(data);
		sort();
		return SortUtils.intArrayToTestOutput(arr);
	}

	@Override
	public void sort() {

	}	
}
