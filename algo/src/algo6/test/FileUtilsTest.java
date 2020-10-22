package algo6.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.Test;

import algo6.externalsort.FileUtils;

class FileUtilsTest {

	@Test
	void testReadWriteInt() {
		int number = 250 * 250;
		int result = 0;

		try {
			try (OutputStream os = new BufferedOutputStream(new FileOutputStream("test"))) {
				FileUtils.writeNextInt(os, number);
			}

			try (InputStream is = new BufferedInputStream(new FileInputStream("test"))) {
				result = FileUtils.readNextInt(is);
			}

			assertEquals(number, result);

		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testnIntToByte() {
		int number = 250 * 250;
		int result = 0;

		byte[] bytes = new byte[2];

		FileUtils.intToByte(number, bytes, 0);

		result = FileUtils.byteToInt(bytes, 0);

		assertEquals(number, result);
	}

	@Test
	void testnByteToInt() {
		byte[] bytes = new byte[] {56, -119};
		byte[] result = new byte[2];

		int number = FileUtils.byteToInt(bytes, 0);

		FileUtils.intToByte(number, result, 0);

		assertArrayEquals(bytes, result);
	}
}
