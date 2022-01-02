package test;

import java.util.HashMap;
import java.util.Map;

class testDTO {
	String test1;
	String test2;
	String test3;
	String test4;
	String test5;
}

public class Practice {

	public static void main(String[] args) {
		
		testDTO test = new testDTO();
		
		test.test1 = "일번";
		test.test2 = "이번";
		test.test3 = "삼번";
		test.test4 = "사번";
		test.test5 = "오번";
		
		Map <String, Object> mapTest = new HashMap<>();
		
		mapTest.put("첫번째", test);
		mapTest.put("두번째", test.test2);
		mapTest.put("세번째", test.test3);
		mapTest.put("네번째", test.test4);
		mapTest.put("다섯번째", test.test5);
		
		System.out.println(mapTest);
		
		System.out.println(1 - 50.0/100);
	}
}
