package algo5;

import java.util.Arrays;
import java.util.stream.Collectors;

import tester.Task;

public abstract class CommonSort implements Task {

	protected int[] a;
	
	@Override
	public String[] run(String[] data) {
		int size = Integer.parseInt(data[0]);
		
		String[] str = data[1].split(" ");
		assert size == str.length;
		
		a = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
		
		sort();
		
		String result = Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(" "));
		
		return new String[] {result};
	}
	
	protected abstract void sort();
	
	protected void swap(int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
