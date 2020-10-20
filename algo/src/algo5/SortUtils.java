package algo5;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SortUtils {

	public static <T> PairValue<T> swap(PairValue<T> pairValue) {
		return new PairValue<T>(pairValue.getB(), pairValue.getA());
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static int[] generateRandomArray(int length, int maxValue) {
		int[] arr = new int[length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) Math.floor(Math.random() * maxValue);
		}
		return arr;
	}

	// The following methods are required to test the results using test data provided

	public static int[] testInputToIntArray(String[] data) {
		// data[0] - size of array
		// data[1] - string with array elements separated by space
		int size = Integer.parseInt(data[0]);
		String[] arr = data[1].split(" ");
		assert size == arr.length;
		return Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
	}

	public static String[] intArrayToTestOutput(int[] arr) {
		// transform sorted array into a string with array elements separated by space
		// return the result as array of strings with just 1 element
		String result = Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" "));
		return new String[] {result};
	}
}
