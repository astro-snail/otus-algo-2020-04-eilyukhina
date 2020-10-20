package algo6;

import java.util.Arrays;

import algo5.Sort;
import algo5.SortUtils;

public class RadixSort implements Sort {

	public static void main(String[] args) {
		RadixSort rs = new RadixSort(SortUtils.generateRandomArray(100, 1000));
		System.out.println(Arrays.toString(rs.arr));
		rs.sort();
		System.out.println(Arrays.toString(rs.arr));
	}

	private int[] arr;

	public RadixSort(int[] arr) {
		this.arr = arr;
	}

	@Override
	public void sort() {
		for (int divisor = 1; divisor <= 100; divisor *= 10) {

			int[] digits = new int[10];
			int[] sorted = new int[arr.length];

			for (int i = 0; i < arr.length; i++) {
				digits[(arr[i] / divisor) % 10]++;
			}
			for (int j = 1; j < digits.length; j++) {
				digits[j] += digits[j - 1]; 
			}
			for (int i = arr.length - 1; i >= 0; i--) {
				sorted[--digits[(arr[i] / divisor) % 10]] = arr[i];
			}
			arr = sorted;	
		}
	}
}
