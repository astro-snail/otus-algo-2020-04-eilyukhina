package algo6;

import java.util.Arrays;

public class RadixSort {

	public static void main(String[] args) {
		RadixSort rs = new RadixSort();
		rs.generate();
		System.out.println(Arrays.toString(rs.a));
		rs.sort();
		System.out.println(Arrays.toString(rs.a));
	}
	
	private int[] a = new int[20];
	
	private void generate() {
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) Math.round(Math.random() * 1000);
		}
	}
	
	private void sort() {
		for (int divisor = 1; divisor <= 100; divisor *= 10) {
			
			int[] digits = new int[10];
			int[] sorted = new int[a.length];
			
			for (int i = 0; i < a.length; i++) {
				digits[(a[i] / divisor) % 10]++;
			}
			for (int j = 1; j < digits.length; j++) {
				digits[j] += digits[j - 1]; 
			}
			for (int i = a.length - 1; i >= 0; i--) {
				sorted[--digits[(a[i] / divisor) % 10]] = a[i];
			}
			a = sorted;	
		}
	}
}
