package algo6;

import java.util.Arrays;

import algo5.Sort;
import algo5.SortUtils;

public class MergeSort implements Sort {

	public static void main(String[] args) {
		MergeSort ms = new MergeSort(SortUtils.generateRandomArray(100, 1024 * 64));
		System.out.println(Arrays.toString(ms.arr));
		ms.sort();
		System.out.println(Arrays.toString(ms.arr));
	}

	private int[] arr;

	public MergeSort(int[] a) {
		this.arr = a;
	}

	@Override
	public void sort() {
		sort(0, arr.length - 1);
	}

	private void sort(int left, int right) {
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
			if (arr[i] < arr[j]) {
				s[k++] = arr[i++];
			} else {
				s[k++] = arr[j++];
			}
		}
		while (i <= centre) {
			s[k++] = arr[i++];
		}
		while (j <= right) {
			s[k++] = arr[j++];
		}
		for (int l = left; l <= right; l++) {
			arr[l] = s[l - left];
		}	
	}
}
