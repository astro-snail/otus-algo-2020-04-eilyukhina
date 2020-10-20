package algo6;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		MergeSort ms = new MergeSort();
		System.out.println(Arrays.toString(ms.a));
		ms.sort(0, ms.a.length - 1);
		System.out.println(Arrays.toString(ms.a));
	}
	
	private int[] a = {5, 4, 9, 0, 8, 3, 2, 1, 7, 6};
	
	public MergeSort() {
		
	}
	
	public MergeSort(int[] a) {
		this.a = a;
	}
	
	public void sort(int left, int right) {
		if (left >= right) return;
		int centre = (left + right) / 2;
		sort(left, centre);
		sort(centre + 1, right);
		merge(left, centre, right);
	}
	
	private void merge(int left, int centre, int right) {
		int i = left;
		int j = centre + 1;
		int k = 0;
		int[] s = new int[right - left + 1];
		
		while (i <= centre && j <= right) {
			if (a[i] < a[j]) {
				s[k++] = a[i++];
			} else {
				s[k++] = a[j++];
			}
		}
		while (i <= centre) {
			s[k++] = a[i++];
		}
		while (j <= right) {
			s[k++] = a[j++];
		}
		
		for (int l = left; l <= right; l++) {
			a[l] = s[l - left];
		}	
	}
}
