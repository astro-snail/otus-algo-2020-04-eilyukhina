package tester;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tester {
	
	private Task task;
	private String path; 
	
	public Tester(Task task, String path) {
		this.task = task;
		this.path = path;		
	}
	
	public void runTests() {
		int n = 0;
		while (true) {
			Path inFile = Paths.get(path, "test." + n + ".in");
			Path outFile = Paths.get(path, "test." + n + ".out");
			if (Files.notExists(inFile) || Files.notExists(outFile))
				break;
			System.out.println("Test " + n + " - " + runTest(inFile, outFile));
			n++;
		}
	}
	
	private boolean runTest(Path inFile, Path outFile) {
		try {
			String[] data = Files.readAllLines(inFile).toArray(new String[0]);
			String expected = new String(Files.readAllBytes(outFile)).trim();
			String actual = task.run(data);
			return actual.equals(expected);
		} catch (IOException e) {
			System.out.print(e.getMessage());
			return false;
		}
	}
}
