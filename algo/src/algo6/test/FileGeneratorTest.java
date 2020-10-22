package algo6.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import algo6.externalsort.FileGenerator;

class FileGeneratorTest {

	@Test
	void testGenerateFile() {
		try {
			String filename = "FG_Test.dat";
			FileGenerator fg = new FileGenerator(filename, 1000);
			fg.generateFile();

			File generatedFile = new File(filename);
			assertTrue(generatedFile.exists());
			assertEquals(generatedFile.length(), 1000 * 2);

		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
}
