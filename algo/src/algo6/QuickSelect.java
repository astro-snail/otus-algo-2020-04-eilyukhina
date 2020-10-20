package algo6;

import java.util.Arrays;

public class QuickSelect {

	public static void main(String[] args) {
		QuickSelect qs = new QuickSelect();
		System.out.println(Arrays.toString(qs.a));
		int value = qs.select(0, qs.a.length - 1, 9);
		System.out.println(value);
	}
	
	private int[] a = {5, 4, 9, 0, 8, 3, 2, 1, 7, 6};
	
	public int select(int left, int right, int k) {
		if (left == right) {
			return a[left];
		}
		
		int pivotIndex = left + (int) Math.floor(Math.random() * (right - left + 1));
		
		pivotIndex = partition(left, right, pivotIndex);
		
		if (k == pivotIndex) {
			return a[pivotIndex];
		} else if (k < pivotIndex) {
			return select(left, pivotIndex - 1, k);
		} else {
			return select(pivotIndex + 1, right, k);
		}
	}
	
	private int partition(int left, int right, int pivotIndex) {
		int pivot = a[pivotIndex];
		
		swap(pivotIndex, right);
		
		int storeIndex = left;
		for (int i = left; i < right; i++) {
			if (a[i] < pivot) {
				swap(storeIndex, i);
				storeIndex++;
			}
		}
		swap(right, storeIndex);
		return storeIndex;
	}
	
	private void swap(int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
