package devdayfp.ex;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ExcelCompareMain {
	
	private static final List<Integer> numbers = Arrays.asList(
				78,  3, 55, 22, 40, 53, 88, 35, 31, 50,
				22, 81, 72, 19, 19, 93, 73, 48, 23, 17,
				37, 16, 38, 97, 52, 26, 60,  9, 32, 49,
				92, 18, 37);
	
	public static void main(String[] args) {
		assertEquals(Integer.valueOf(40320), factorial(8));

		assertEquals(Integer.valueOf(33),   count(numbers));
		assertEquals(Integer.valueOf(1485), sum  (numbers));
	}

	private static Integer factorial(Integer i) {
		return null;
	}
	
	private static Integer count(List<Integer> list) {
		return null;
	}
	
	private static Integer sum(List<Integer> list) {
		return null;
	}
	
	// == Given ==
	
	private static Integer head(List<Integer> list) {
		return list.get(0);
	}
	private static List<Integer> rest(List<Integer> list) {
		return list.subList(1, list.size());
	}
	private static boolean isEmpty(List<Integer> list) {
		return list.isEmpty();
	}
	private static List<Integer> append(List<Integer> list, int value) {
		List<Integer> newList = new ArrayList<Integer>();
		newList.addAll(list);
		newList.add(value);
		return newList;
	}
	
}
