package algo4;

import algo4.model.IArray;

public class AddTest extends OperationTest {
	
	public AddTest(Class<? extends IArray> type, String name) {
		super(type, name);
	}

	@Override
	public long doOperation(String... params) {
		int x = Integer.parseInt(params[1]);
		for (int i = 0; i < x; i++) {
	    	array.add(i);
	    }
		return array.size();
	}
}
