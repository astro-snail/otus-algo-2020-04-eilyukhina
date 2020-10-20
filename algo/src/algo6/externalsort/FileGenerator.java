package algo6.externalsort;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileGenerator {

	private String filename;
	private int size;

	public FileGenerator(String filename, int size) {
		this.filename = filename;
		this.size = size;
	}

	public void generateFile() throws IOException {
		writeToFile();
	}

	private void writeToFile() throws IOException {
		try (OutputStream os = new BufferedOutputStream(new FileOutputStream(filename))) {
			for (int i = 0; i < size; i++) {
				FileUtils.writeNextInt(os, getRandomInt());
			}
		}
	}

	// Returns random integer 2-byte number 
	private int getRandomInt() {
		return (int) Math.floor(Math.random() * 1024 * 64);
	}
}
