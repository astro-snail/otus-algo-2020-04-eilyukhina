package algo4;

import algo4.model.IArray;
import tester.Task;

public abstract class OperationTest implements Task {
	
	protected IArray<Integer> array;
	
	private final Class<? extends IArray> type;
	private final String name;
	
	public OperationTest(Class<? extends IArray> type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public String[] run(String[] data) {
		String[] params = data[0].split(" ");
		//System.out.println(array);
		long result = doOperation(params);
		//System.out.println(array);
		return new String[] {String.valueOf(result)};
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setUp(String[] data) {
		try {
			array = type.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public void tearDown() {
		array = null;
	}
	
	public abstract long doOperation(String ... params);
}
