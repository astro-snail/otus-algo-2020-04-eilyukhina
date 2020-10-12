package algo4;

import algo4.array.*;
import algo4.list.*;
import algo4.model.*;
import algo4.wrapper.*;
import tester.Task;
import tester.Tester;

public class Main {
	
	enum Operation {
		ADD,
		INS,
		UPD,
		DEL,
		GET,
		SUM,
		REV
	}

	private static Task createTask(Operation operation, Class<? extends IArray> type) {
		switch (operation) {
		case ADD:
			return new AddTest(type, operation + " - " + type.getSimpleName());
		case INS:
			return new InsertTest(type, operation + " - " + type.getSimpleName());
		case UPD:
			return new UpdateTest(type, operation + " - " + type.getSimpleName());
		case DEL:
			return new DeleteTest(type, operation + " - " + type.getSimpleName());
		case GET:
			return new GetTest(type, operation + " - " + type.getSimpleName());
		case SUM:
			return new SumTest(type, operation + " - " + type.getSimpleName());
		case REV:
			return new RevertTest(type, operation + " - " + type.getSimpleName());
		default:
			return null;
		}
	}

	public static void main(String[] args) {
		testArray(SingleArray.class);
		testArray(VectorArray.class);
		testArray(FactorArray.class);
		testArray(MatrixArray.class);
		testArray(ArrayListWrapper.class);
		testArray(SinglyLinkedList.class);
		testArray(DoublyLinkedList.class);
	}
	
	private static void testArray(Class<? extends IArray> type) {
		Tester tester;
		for (Operation operation : Operation.values()) {
			String path = "src/algo4/0.ARRAY/" + operation.ordinal() + "." + operation;
			tester = new Tester(createTask(operation, type), path);
			tester.runTests();
		}	
	}
}
