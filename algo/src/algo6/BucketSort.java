package algo6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algo5.Sort;
import algo5.SortUtils;

public class BucketSort implements Sort {

	public static void main(String[] args) {
		BucketSort bs = new BucketSort(SortUtils.generateRandomArray(100, 1024 * 64), 20);
		System.out.println(Arrays.toString(bs.arr));
		bs.sort();
		System.out.println(Arrays.toString(bs.arr));
	}

	private int[] arr;
	private int k; // number of buckets

	public BucketSort(int[] a, int k) {
		this.arr = a;
		this.k = k;
	}

	@Override
	public void sort() {
		List[] buckets = new ArrayList[k];
		for (int i = 0; i < k; i++) {
			buckets[i] = new ArrayList();
		}
		int max = 65535;
		for (int i = 0; i < arr.length; i++) {
			int index = Math.floorDiv(k * arr[i], max);
			addToBucket(buckets[index], arr[i]);
		}
		int start = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < buckets[i].size(); j++) {
				arr[start++] = (int) buckets[i].get(j);
			}
		}
	}

	private void addToBucket(List bucket, int element) {
		int i = 0;
		while (i < bucket.size() && element > (int) bucket.get(i)) {
			i++;
		}
		bucket.add(i, element);	
	}	
}