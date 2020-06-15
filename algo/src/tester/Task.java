package tester;

public interface Task {
	
	default void setUp(String[] data) {};
	
	default void tearDown() {};

	String[] run(String[] data);
	
	String getName();
}
