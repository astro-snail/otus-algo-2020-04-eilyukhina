package algo5;

import tester.Task;
import tester.Tester;

public class ShellSort extends SortTask {

	public static void main(String[] args) {
		Task task = new ShellSort();
		//Tester tester = new Tester(task, "sorting-tests/0.random");
		//Tester tester = new Tester(task, "sorting-tests/1.digits");
		//Tester tester = new Tester(task, "sorting-tests/2.sorted");
		Tester tester = new Tester(task, "sorting-tests/3.revers");
		tester.runTests();
	}

	@Override
	public String getName() {
		return "Shell Sort";
	}

	@Override
	public void sort() {
		int length = arr.length;

		// gap sequence = n/2, n/4, ...
		//sort(n);

		// gap sequence = 2^k - 1
		//sort(n, new GapSequence1(n));

		// gap sequence = 4^k + 3*2^(k-1) + 1, prefixed with 1
		sort(length, new GapSequence2(length));
	}

	private void sortGap(int length, int gap) {
		// Insertion sort is a special case with "gap" = 1
		for (int i = gap; i < length; i++) {
			int keyValue = arr[i];
			int j = i - gap;
			while (j >= 0 && arr[j] > keyValue) {
				arr[j + gap] = arr[j];
				j -= gap;
			}
			arr[j + gap] = keyValue;
		}
	}

	private void sort(int length) {
		for (int gap = length/2; gap > 0; gap /= 2) {
			sortGap(length, gap);
		}		
	}

	private void sort(int n, GapSequence gapSequence) {
		int s = gapSequence.increment();
		while (s >= 0) {
			int gap = gapSequence.gap(s--);
			sortGap(n, gap);
		}		
	}

	abstract class GapSequence {
		int[] seq;
		int n;

		GapSequence(int n) {
			this.seq = new int[20];
			this.n = n;
		}

		abstract int increment();

		int gap(int s) {
			return seq[s];
		}
	}

	class GapSequence1 extends GapSequence {

		GapSequence1(int n) {
			super(n);
		}

		int increment() {
			int p = 1;
			int i = -1;
			do {
				seq[++i] = 2 * p - 1;
				p *= 2;
			} while (seq[i] <= n);

			return i - 1;
		}
	}

	class GapSequence2 extends GapSequence {

		GapSequence2(int n) {
			super(n);
		}

		int increment() {
			seq[0] = 1;
			int p1 = 1;
			int p2 = 1;
			int i = 0;
			do {
				seq[++i] = 4 * p1 + 3 * p2 + 1;
				p1 *= 4;
				p2 *= 2;
			} while (seq[i] <= n);

			return i - 1;
		}
	}
}