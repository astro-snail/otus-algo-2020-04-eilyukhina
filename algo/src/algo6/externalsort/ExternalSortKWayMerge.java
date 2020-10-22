package algo6.externalsort;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import algo5.Sort;
import algo6.MergeSort;
import algo6.QuickSort;

public class ExternalSortKWayMerge {

	public static void main(String[] args) throws IOException {

		String inputFile = "sorting-tests/external-sort/ExternalSort_In.dat";
		String outputFile = "sorting-tests/external-sort/ExternalSort_Out.dat";

		int size = 1_000_000_000;
		int fileSize = 100_000;
		int filesToMerge = 100;

		if (!Files.exists(Paths.get(inputFile))) {
			FileGenerator fg = new FileGenerator(inputFile, size);
			fg.generateFile();
		}	

		ExternalSortKWayMerge sort = new ExternalSortKWayMerge(inputFile, outputFile, size, fileSize, filesToMerge);

		long start = System.currentTimeMillis();

		sort.sort();

		long end = System.currentTimeMillis();

		System.out.println("Size: " + size + ", sorting time: " + (end - start) + " ms");
	}

	private String inputFile;
	private String outputFile;
	private int size;
	private int fileSize;
	private int filesToMerge;

	public ExternalSortKWayMerge(String inputFile, String outputFile, int size, int fileSize, int filesToMerge) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
		this.size = size;
		this.fileSize = fileSize;
		this.filesToMerge = filesToMerge;
	}

	public void sort() throws IOException {

		String fileResult = null; 

		createFiles(inputFile, fileSize);

		int n = size / fileSize; // initial number of files to merge
		int from = 0;

		while (n > 1) {

			int fileCount = 0; // count of resulting files in each run

			while (fileCount * filesToMerge < n) {
				int remainingFiles = n - fileCount * filesToMerge;
				int to = remainingFiles < filesToMerge ? from + remainingFiles : from + filesToMerge;

				fileResult = String.valueOf(n + fileCount); // name of the resulting file 
				mergeFiles(fileResult, from, to);

				from += filesToMerge;
				fileCount++;
			}

			from = n;
			n = fileCount; // number of files to merge in the next iteration
		}	

		if (fileResult != null) {
			FileUtils.renameFile(fileResult, outputFile);
		}
	}

	private void sortInMemory(Sort sort) {
		sort.sort();
	}

	private void createFiles(String inputFile, int fileSize) throws IOException {
		try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(inputFile))) {

			boolean eof = false;
			int fileCount = 0;

			while (!eof) {
				try {
					int[] arr = FileUtils.readFromFile(is, fileSize);
					sortInMemory(new QuickSort(arr));
					try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(String.valueOf(fileCount++)))) {
						FileUtils.writeToFile(os, arr);
					}
				} catch (EOFException e) {
					eof = true;
				}
			}	
		}		
	}

	private void mergeFiles(String outputFile, int from, int to) throws IOException {
		InputStream[] is = openFiles(from, to);

		try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile))) {
			doKWayMerge(is, os);
		}

		closeFiles(is, from, to, true);
	}

	private InputStream[] openFiles(int from, int to) throws IOException {
		InputStream[] is = new InputStream[to - from];
		for (int i = 0; i < to - from; i++) {
			is[i] = new BufferedInputStream(new FileInputStream(String.valueOf(from + i)));	
		}
		return is;
	}

	private void closeFiles(InputStream[] is, int from, int to, boolean delete) throws IOException {
		for (int i = 0; i < to - from; i++) {
			is[i].close();
			if (delete) {
				FileUtils.deleteFile(String.valueOf(from + i));
			}	
		}
	}

	// Merge k files into one using heap data structure
	private void doKWayMerge(InputStream[] is, OutputStream os) throws IOException {
		int numberOfFiles = is.length; // number of files to merge

		HeapNode[] arr = new HeapNode[numberOfFiles];
		for (int i = 0; i < numberOfFiles; i++) { 
			arr[i] = new HeapNode(FileUtils.readNextInt(is[i]), i);
		}  

		MinHeap minHeap = new MinHeap(arr); 

		int fileCount = 0; // number of processed files

		while (fileCount < numberOfFiles) { 
			HeapNode root = minHeap.getMin(); 
			FileUtils.writeNextInt(os, root.element); 
			try {
				root.element = FileUtils.readNextInt(is[root.index]);
			} catch (EOFException e) {
				root.element = Integer.MAX_VALUE;
				fileCount++;
			}
			minHeap.replaceMin(root); 
		} 
	}
}
