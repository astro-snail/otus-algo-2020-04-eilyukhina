package algo4;

import algo4.model.IArray;

public class DeleteTest extends OperationTest {
	
	public DeleteTest(Class<? extends IArray> type, String name) {
		super(type, name);
	}

	@Override
	public long doOperation(String... params) {
		int j = Integer.parseInt(params[1]);
		int x = Integer.parseInt(params[2]);
	    for (int i = 0; i < x; i++) {
	    	array.remove(j);
	    }
	    return array.size();
	}
	
	@Override
	public void setUp(String[] data) {
		super.setUp(data);
		String[] params = data[0].split(" ");
		array.fill(1, Integer.parseInt(params[2]));		
	}
}
