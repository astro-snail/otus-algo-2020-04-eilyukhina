package algo6;

import java.util.Arrays;

import algo5.Sort;
import algo5.SortUtils;

public class CountingSort implements Sort {

	public static void main(String[] args) {
		CountingSort cs = new CountingSort(SortUtils.generateRandomArray(100, 10));
		System.out.println(Arrays.toString(cs.arr));
		cs.sort();
		System.out.println(Arrays.toString(cs.arr));
	}

	private int[] arr;

	public CountingSort(int[] arr) {
		this.arr = arr;
	}

	@Override
	public void sort() {
		int[] digits = new int[10];
		int[] sorted = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
			digits[arr[i]]++;
		}

		for (int j = 1; j < digits.length; j++) {
			digits[j] += digits[j - 1]; 
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			sorted[--digits[arr[i]]] = arr[i];
		}

		arr = sorted;
	}
}
