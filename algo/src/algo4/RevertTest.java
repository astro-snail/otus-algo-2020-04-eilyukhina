package algo4;

import algo4.model.IArray;
import algo4.model.Iterable;
import algo4.model.Iterator;

public class RevertTest extends OperationTest {
	
	public RevertTest(Class<? extends IArray> type, String name) {
		super(type, name);
	}

	@Override
	public long doOperation(String... params) {
		long sum = 0;
    	if (array instanceof Iterable && ((Iterable<Integer>)array).descendingIterator() != null) {
    		Iterator<Integer> iterator = ((Iterable<Integer>)array).descendingIterator();
    		while (iterator.hasNext()) {
    			sum += iterator.next();
    		}
    	} else {
    		for (int i = array.size() - 1; i >= 0; i--) {
    			sum += array.get(i);
    		}
    	}	
    	return sum;
	}
	
	@Override
	public void setUp(String[] data) {
		super.setUp(data);
		String[] params = data[0].split(" ");
		array.fill(1, Integer.parseInt(params[1]));		
	}
}
