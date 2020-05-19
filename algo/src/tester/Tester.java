package tester;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Tester {
	
	private Task task;
	private String path; 
	
	public Tester(Task task, String path) {
		this.task = task;
		this.path = path;		
	}
	
	public void runTests() {
		int n = 0;
		System.out.println(task.getName());
		while (true) {
			Path inFile = Paths.get(path, "test." + n + ".in");
			Path outFile = Paths.get(path, "test." + n + ".out");
			if (Files.notExists(inFile) || Files.notExists(outFile))
				break;
			System.out.println("Test " + n + " - " + runTest(inFile, outFile));
			n++;
		}
	}
	
	private String runTest(Path inFile, Path outFile) {
		try {
			String[] in = Files.readAllLines(inFile).toArray(new String[0]);
			String[] out = Files.readAllLines(outFile).toArray(new String[0]);
			
			long start = System.currentTimeMillis();
			
			String[] result = task.run(in);
			
			long end = System.currentTimeMillis();

			return Arrays.equals(result, out) + ", run time: " + (end - start) + " ms";
			
		} catch (IOException e) {
			return e.getMessage();
		}
	}
}
