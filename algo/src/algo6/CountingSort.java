package algo6;

import java.util.Arrays;

public class CountingSort {

	public static void main(String[] args) {
		CountingSort cs = new CountingSort();
		System.out.println(Arrays.toString(cs.a));
		cs.sort();
		System.out.println(Arrays.toString(cs.a));
	}
	
	private int[] a = {7, 5, 3, 1, 5, 9, 8, 2, 6, 4, 9, 5, 1, 7, 5, 3, 6, 4, 2, 8, 3, 6, 9, 2, 5, 8, 1, 4, 7, 7, 8, 9, 4, 5, 6, 1, 2, 3, 1, 4, 7, 2, 5, 8, 3, 6, 9, 7, 8, 9, 4, 5, 6, 1, 2, 3};
	
	private void sort() {
		int[] digits = new int[10];
		int[] sorted = new int[a.length];
		
		for (int i = 0; i < a.length; i++) {
			digits[a[i]]++;
		}
		
		for (int j = 1; j < digits.length; j++) {
			digits[j] += digits[j - 1]; 
		}
		
		for (int i = a.length - 1; i >= 0; i--) {
			sorted[--digits[a[i]]] = a[i];
		}
		
		a = sorted;
	}
}
