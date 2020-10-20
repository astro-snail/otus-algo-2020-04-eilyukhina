package algo6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {

	public static void main(String[] args) {
		BucketSort bs = new BucketSort();
		bs.generate();
		System.out.println(Arrays.toString(bs.a));
		bs.sort();
		System.out.println(Arrays.toString(bs.a));
	}
	
	private int[] a = new int[100];
	private int k = 20;
	
	public BucketSort() {
		
	}
	
	public BucketSort(int[] a, int k) {
		this.a = a;
		this.k = k;
	}
	
	private void generate() {
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) Math.round(Math.random() * 1024 * 64);
		}
	}
	
	private void sort() {
	    List[] buckets = new ArrayList[k];
	    for (int i = 0; i < k; i++) {
	    	buckets[i] = new ArrayList();
	    }
	    int max = 65535;
	    for (int i = 0; i < a.length; i++) {
	    	int index = Math.floorDiv(k * a[i], max);
	    	addToBucket(buckets[index], a[i]);
	    }
	    int start = 0;
	    for (int i = 0; i < k; i++) {
	    	for (int j = 0; j < buckets[i].size(); j++) {
	    		a[start++] = (int) buckets[i].get(j);
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