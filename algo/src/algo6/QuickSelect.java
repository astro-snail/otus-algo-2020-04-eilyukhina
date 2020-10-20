package algo6;

import java.util.Arrays;

import algo5.SortUtils;

public class QuickSelect {

	public static void main(String[] args) {
		QuickSelect qs = new QuickSelect(SortUtils.generateRandomArray(10, 1000));
		System.out.println(Arrays.toString(qs.arr));
		int value = qs.select(0, qs.arr.length - 1, 5);
		System.out.println(value);
	}

	private int[] arr;

	public QuickSelect(int[] arr) {
		this.arr = arr;
	}

	public int select(int left, int right, int k) {
		if (left == right) {
			return arr[left];
		}

		int pivotIndex = left + (int) Math.floor(Math.random() * (right - left + 1));

		pivotIndex = partition(left, right, pivotIndex);

		if (k == pivotIndex) {
			return arr[pivotIndex];
		} else if (k < pivotIndex) {
			return select(left, pivotIndex - 1, k);
		} else {
			return select(pivotIndex + 1, right, k);
		}
	}

	private int partition(int left, int right, int pivotIndex) {
		int pivot = arr[pivotIndex];

		SortUtils.swap(arr, pivotIndex, right);

		int storeIndex = left;
		for (int i = left; i < right; i++) {
			if (arr[i] < pivot) {
				SortUtils.swap(arr, storeIndex, i);
				storeIndex++;
			}
		}
		SortUtils.swap(arr, right, storeIndex);
		return storeIndex;
	}
}
