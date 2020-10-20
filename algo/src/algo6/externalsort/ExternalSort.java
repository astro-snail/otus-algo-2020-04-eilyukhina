package algo6.externalsort;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algo5.Sort;
import algo6.MergeSort;

public class ExternalSort {

	public static void main(String[] args) {
		String inputFile = "sorting-tests/external-sort/ExternalSort_In.dat";	//ExtenalSort_Test_In.dat";
		String outputFile = "sorting-tests/external-sort/ExternalSort_Out.dat";	//ExtenalSort_Test_Out.dat";
		int size = 1_000_000_000;	// 1_200_000;
		int fileSize = 100_000;		// 10_000;
		int filesToMerge = 100;		// 10;

		try {
			FileGenerator fg = new FileGenerator(inputFile, size);
			fg.generateFile();

			ExternalSort externalSort = new ExternalSort(inputFile, outputFile, size, fileSize, filesToMerge);
			externalSort.sort();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String inputFile;
	private String outputFile;
	private int size;
	private int fileSize;
	private int filesToMerge;

	public ExternalSort(String inputFile, String outputFile, int size, int fileSize, int filesToMerge) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
		this.size = size;
		this.fileSize = fileSize;
		this.filesToMerge = filesToMerge;
	}

	public void sort() throws IOException {
		createFiles(inputFile, fileSize);

		String tmpOutputFile = null; 
		int n = size / fileSize; // initial number of files to merge
		int from = 0;

		while (n > 1) {

			int count = 0; // count of resulting files in each run

			while (count * filesToMerge < n) {
				tmpOutputFile = String.valueOf(n + count); // name of the resulting file 
				int to = (n - count * filesToMerge < filesToMerge) ? from + (n - count * filesToMerge) : from + filesToMerge;
				mergeFiles(tmpOutputFile, from, to);
				from += filesToMerge;
				count++;
			}

			from = n;
			n = count; // number of files to merge in the next iteration
		}	

		if (tmpOutputFile != null) {
			FileUtils.renameFile(tmpOutputFile, outputFile);
		}
	}

	private void sortInMemory(Sort sort) {
		sort.sort();
	}

	private void createFiles(String inputFile, int fileSize) throws IOException {
		try (InputStream is = new BufferedInputStream(new FileInputStream(inputFile))) {

			boolean eof = false;
			int fileCount = 0;

			while (!eof) {
				try {
					int[] arr = FileUtils.readFromFile(is, fileSize);
					sortInMemory(new MergeSort(arr));
					FileUtils.writeToFile(String.valueOf(fileCount++), arr);
				} catch (EOFException e) {
					eof = true;
				}
			}	
		}		
	}

	private void mergeFiles(String outputFile, int from, int to) throws IOException {
		InputStream[] is = openFiles(from, to);

		try (OutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile))) {
			doKWayMerge(is, os);
		}

		closeFiles(is, from, to, true);
	}

	private InputStream[] openFiles(int from, int to) throws FileNotFoundException {
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
				File file = new File(String.valueOf(from + i));
				file.delete();
			}	
		}
	}

	// Merge k files into one using heap data structure
	private void doKWayMerge(InputStream[] is, OutputStream os) throws IOException {
		int k = is.length;

		HeapNode[] arr = new HeapNode[k];
		for (int i = 0; i < k; i++) { 
			arr[i] = new HeapNode(FileUtils.readNextInt(is[i]), i);
		}  

		MinHeap minHeap = new MinHeap(arr); 

		int count = 0; // number of processed files

		while (count < k) { 
			HeapNode root = minHeap.getMin(); 
			FileUtils.writeNextInt(os, root.element); 
			try {
				root.element = FileUtils.readNextInt(is[root.index]);
			} catch (EOFException e) {
				root.element = Integer.MAX_VALUE;
				count++;
			}
			minHeap.replaceMin(root); 
		} 
	}
}
