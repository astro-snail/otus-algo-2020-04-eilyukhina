package algo4;

import algo4.model.IArray;

public class UpdateTest extends OperationTest {
	
	public UpdateTest(Class<? extends IArray> type, String name) {
		super(type, name);
	}

	@Override
	public long doOperation(String... params) {
		int j = Integer.parseInt(params[1]);
		int x = Integer.parseInt(params[2]);
	    for (int i = 0; i < x; i++) {
	    	array.set(i, j + i);
	    }
	    return array.size();
	}
	
	@Override
	public void setUp(String[] data) {
		super.setUp(data);
		String[] params = data[0].split(" ");
		array.fill(0, Integer.parseInt(params[2]));		
	}
}
