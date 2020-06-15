package algo4;

import java.util.Arrays;

import algo4.model.IArray;

public class GetTest extends OperationTest {
	
	public GetTest(Class<? extends IArray> type, String name) {
		super(type, name);
	}

	@Override
	public long doOperation(String... params) {
		int j = Integer.parseInt(params[1]);
	    return array.get(j);
	}
	
	@Override
	public void setUp(String[] data) {
		super.setUp(data);
		String[] params = data[0].split(" ");
		int i = Integer.parseInt(params[1]);
		int n = Integer.parseInt(params[2]);
		array.fill(0, n);
		array.set(i, i);
	}
}
