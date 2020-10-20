package algo6.externalsort;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

	public static int readNextInt(InputStream is) throws IOException {
		int byte1 = is.read();
		if (byte1 == -1) throw new EOFException();
		int byte2 = is.read();
		if (byte2 == -1) throw new EOFException();
		return (byte1 << 8) | byte2;
	}

	public static void writeNextInt(OutputStream os, int number) throws IOException {
		int byte1 = (byte) number;
		int byte2 = (byte) (number >> 8);
		os.write(byte1);
		os.write(byte2);
	}

	public static void renameFile(String oldFilename, String newFilename) {
		File oldFile = new File(oldFilename);
		oldFile.renameTo(new File(newFilename));
	}

	// Read first N 2-byte integer numbers from a file
	public static int[] readFromFile(String filename, int n) throws IOException {
		int[] numbers = new int[n];
		try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
			for (int i = 0; i < n; i++) {
				numbers[i] = readNextInt(is);
			}
		}
		return numbers;
	}

	// Read first N 2-byte integer numbers from a file (input stream is already open)
	public static int[] readFromFile(InputStream is, int n) throws IOException {
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = readNextInt(is);
		}
		return numbers;
	}

	public static void writeToFile(String filename, int[] arr) throws IOException {
		try (OutputStream os = new BufferedOutputStream(new FileOutputStream(filename))) {
			for (int i = 0; i < arr.length; i++) {
				writeNextInt(os, arr[i]);
			}
		}
	}
}
