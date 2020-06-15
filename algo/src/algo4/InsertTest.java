package algo4;

import algo4.model.IArray;

public class InsertTest extends OperationTest {
	
	public InsertTest(Class<? extends IArray> type, String name) {
		super(type, name);
	}

	@Override
	public long doOperation(String... params) {
		int j = Integer.parseInt(params[1]);
		int x = Integer.parseInt(params[2]);
	    for (int i = 0; i < x; i++) {
	    	array.add(i, j + i);
	    }
	    return array.size();
	}
}
