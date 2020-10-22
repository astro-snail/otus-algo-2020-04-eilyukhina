package algo6.externalsort;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class FileGenerator {

	private String filename;
	private int size;
	private int bufferSize = 1024 * 8;

	public FileGenerator(String filename, int size) {
		this.filename = filename;
		this.size = size * 2;
	}

	public void generateFile() throws IOException {
		try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(filename), bufferSize)) {
			int i = 0;
			while (i < size) {
				int length = (i + bufferSize < size) ? bufferSize : size - i;
				byte[] buffer = new byte[length];
				new Random().nextBytes(buffer);
				os.write(buffer);
				i += length;
			}
		}
	}
}
