package algo6.externalsort;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileCountingSort {

	public static void main(String[] args) throws IOException {

		String inputFile = "sorting-tests/external-sort/ExternalSort_In.dat";
		String outputFile = "sorting-tests/external-sort/ExternalSort_Out.dat";

		int size = 1_000_000_000;

		if (Files.exists(Paths.get(outputFile))) {
			Files.delete(Paths.get(outputFile));
		}

		if (!Files.exists(Paths.get(inputFile))) {
			FileGenerator fg = new FileGenerator(inputFile, size);
			fg.generateFile();
		}	

		FileCountingSort sort = new FileCountingSort(inputFile, outputFile);

		long start = System.currentTimeMillis();

		sort.sort();

		long end = System.currentTimeMillis();

		System.out.println("Size: " + size + ", sorting time: " + (end - start) + " ms");
	}	

	public FileCountingSort(String inputFile, String outputFile) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}

	private final String inputFile;
	private final String outputFile;
	private int[] values = new int[65536];

	public void sort() throws IOException {

		try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(inputFile))) {
			boolean eof = false;

			while (!eof) {
				try {
					values[FileUtils.readNextInt(is)]++;
				} catch (EOFException e) {
					eof = true;
				}
			}	
		}

		try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile))) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] == 0) continue;

				byte[] bytes = new byte[values[i] * 2];

				for (int j = 0; j < bytes.length; j += 2) {
					FileUtils.intToByte(i, bytes, j);
				}
				os.write(bytes);
			}	
		}
	}
}
