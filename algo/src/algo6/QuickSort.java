package algo6;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		System.out.println(Arrays.toString(qs.a));
		qs.sort(0, qs.a.length - 1);
		System.out.println(Arrays.toString(qs.a));
	}
	
	private int[] a = {5, 4, 9, 0, 8, 3, 2, 1, 7, 6};
	
	public void sort(int left, int right) {
		if (left >= right) return;
		int i = partition(left, right);
		sort(left, i - 1);
		sort(i + 1, right);
	}
	
	private int partition(int left, int right) {
		int i = left - 1;
		int pivot = a[right];
		for (int j = left; j <= right; j++) {
			if (a[j] <= pivot) {
				swap(++i, j);
			}
		}
		return i;
	}

	private void swap(int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
