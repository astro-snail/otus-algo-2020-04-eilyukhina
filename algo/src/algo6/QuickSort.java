package algo6;

import java.util.Arrays;

import algo5.Sort;
import algo5.SortUtils;

public class QuickSort implements Sort {

	public static void main(String[] args) {
		QuickSort qs = new QuickSort(SortUtils.generateRandomArray(100, 1024 * 64));
		System.out.println(Arrays.toString(qs.arr));
		qs.sort();
		System.out.println(Arrays.toString(qs.arr));
	}

	private int[] arr;

	public QuickSort(int[] arr) {
		this.arr = arr;
	}

	public void sort() {
		sort(0, arr.length - 1);
	}

	private void sort(int left, int right) {
		if (left >= right) return;
		int i = partition(left, right);
		sort(left, i - 1);
		sort(i + 1, right);
	}

	private int partition(int left, int right) {
		int i = left - 1;
		int pivot = arr[right];
		for (int j = left; j <= right; j++) {
			if (arr[j] <= pivot) {
				SortUtils.swap(arr, ++i, j);
			}
		}
		return i;
	}
}
