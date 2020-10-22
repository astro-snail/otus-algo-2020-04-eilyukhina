package algo6.externalsort;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import algo5.Sort;
import algo6.MergeSort;
import algo6.QuickSort;

public class ExternalSort {

	public static void main(String[] args) throws IOException {

		String inputFile = "sorting-tests/external-sort/ExternalSort_In.dat";
		String outputFile = "sorting-tests/external-sort/ExternalSort_Out.dat";

		int size = 1_000_000_000;

		if (!Files.exists(Paths.get(inputFile))) {
			FileGenerator fg = new FileGenerator(inputFile, size);
			fg.generateFile();
		}	

		ExternalSort sort = new ExternalSort(inputFile, outputFile, size);

		long start = System.currentTimeMillis();

		sort.sort();

		long end = System.currentTimeMillis();

		System.out.println("Size: " + size + ", sorting time: " + (end - start) + " ms");
	}

	public ExternalSort(String inputFile, String outputFile, int size) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
		this.size = size;
	}

	private final String inputFile;
	private final String outputFile;
	private final int size;

	private final int bufferSize = 1024 * 32;
	private final int minSize = 100_000;

	private int fileCount = 0;

	public void sort() throws IOException {
		String fileResult;
		try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(inputFile), bufferSize)) {
			fileResult = sort(is, 0, size - 1);
		}
		FileUtils.renameFile(fileResult, outputFile);
	}

	private String sort(InputStream is, int left, int right) throws IOException {
		if (right - left + 1 <= minSize) {
			int[] arr = FileUtils.readFromFile(is, right - left + 1);

			//sortInMemory(new MergeSort(arr));
			sortInMemory(new QuickSort(arr));

			String fileResult = String.valueOf(fileCount++);
			try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(fileResult), bufferSize)) {
				FileUtils.writeToFile(os, arr);
			}
			return fileResult;
		}

		int centre = left + (right - left) / 2;
		String fileL = sort(is, left, centre);
		String fileR = sort(is, centre + 1, right);
		return merge2(fileL, fileR);
	}

	private String merge2(String fileL, String fileR) throws IOException {

		String fileResult = String.valueOf(fileCount++);

		try (BufferedInputStream isL = new BufferedInputStream(new FileInputStream(fileL), bufferSize);
				BufferedInputStream isR = new BufferedInputStream(new FileInputStream(fileR), bufferSize);
				BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(fileResult), bufferSize * 2)) {

			byte[] left = new byte[2];
			byte[] right = new byte[2];

			int bytesL = isL.read(left);
			int bytesR = isR.read(right);

			while (bytesL != -1 && bytesR != -1) {
				if (FileUtils.byteToInt(left, 0) < FileUtils.byteToInt(right, 0)) {
					os.write(left);
					bytesL = isL.read(left);
				} else {
					os.write(right);
					bytesR = isR.read(right);
				}
			}	

			while (bytesL != -1) {
				os.write(left);
				bytesL = isL.read(left);
			}

			while (bytesR != -1) {
				os.write(right);
				bytesR = isR.read(right);
			}
		}	

		FileUtils.deleteFile(fileL);
		FileUtils.deleteFile(fileR);

		return fileResult;
	}	

	private void sortInMemory(Sort sort) {
		sort.sort();
	}
}
