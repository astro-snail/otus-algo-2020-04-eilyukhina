package algo6.externalsort;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

	public static int readNextInt(InputStream is) throws IOException {
		int byte1 = is.read();
		if (byte1 == -1) throw new EOFException();
		int byte2 = is.read();
		if (byte2 == -1) throw new EOFException();
		return byte1 << 8 | byte2;
	}

	public static void writeNextInt(OutputStream os, int number) throws IOException {
		int byte1 = (byte) (number >> 8);
		int byte2 = (byte) number;
		os.write(byte1);
		os.write(byte2);
	}

	public static void deleteFile(String filename) throws IOException {
		Files.delete(Paths.get(filename));
	}

	public static void renameFile(String oldFile, String newFile) throws IOException {
		Files.move(Paths.get(oldFile), Paths.get(newFile), StandardCopyOption.REPLACE_EXISTING);
	}

	// Read at most N 2-byte integer numbers from a file
	public static int[] readFromFile(InputStream is, int n) throws IOException {
		byte[] bytes = new byte[n * 2];
		int bytesRead = is.read(bytes);
		if (bytesRead == -1) {
			throw new EOFException();
		}
		return bytesToInts(bytes, bytesRead);
	}	

	public static void writeToFile(OutputStream os, int[] arr) throws IOException {
		os.write(intsToBytes(arr));
	}

	public static int byteToInt(byte[] buffer, int off) {
		return (buffer[off] & 0xff) << 8 | buffer[off + 1] & 0xff;
	}

	public static void intToByte(int number, byte[] buffer, int off) {
		buffer[off] = (byte) (number >> 8);
		buffer[off + 1] = (byte) number;
	}

	public static int[] bytesToInts(byte[] bytes, int length) {
		int[] numbers = new int[length >> 1];
		for (int i = 0; i < length; i += 2) {
			numbers[i >> 1] = byteToInt(bytes, i);
		}
		return numbers;
	}

	public static byte[] intsToBytes(int[] numbers) {
		byte[] bytes = new byte[numbers.length << 1];
		for (int i = 0; i < numbers.length; i++) {
			intToByte(numbers[i], bytes, i << 1);
		}
		return bytes;
	}
}
