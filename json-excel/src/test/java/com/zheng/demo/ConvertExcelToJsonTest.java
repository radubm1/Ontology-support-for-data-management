package com.zheng.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

class ConvertExcelToJsonTest {

	public static String filePath = Objects
			.requireNonNull(ConvertExcelToJsonTest.class.getClassLoader().getResource("dq.xlsx")).getFile();
	private ConvertExcelToJson testClass = new ConvertExcelToJson();

	@Test
	void test_convertExceltoJsonString() {

		String convertedData = testClass.convertExceltoJsonString(filePath);
		assertTrue(convertedData.contains("[\"header1\",\"header2\",\"header3\",\"stringHeader\",\"numberHeader\"]"));
	}

	@Test
	void test_convertExceltoListofString() {

		List<List<String>> convertedData = testClass.convertExceltoListofString(filePath);
		assertEquals(4, convertedData.size());
		// header row
		assertEquals("header1", convertedData.get(0).get(0));
		assertEquals("header2", convertedData.get(0).get(1));
		assertEquals("header3", convertedData.get(0).get(2));
		assertEquals("stringHeader", convertedData.get(0).get(3));
		assertEquals("numberHeader", convertedData.get(0).get(4));

		// data row 1
		assertEquals("1.0", convertedData.get(1).get(0));
		assertEquals("2.0", convertedData.get(1).get(1));
		assertEquals("3.0", convertedData.get(1).get(2));
		assertEquals("test", convertedData.get(1).get(3));
		assertEquals("12.5", convertedData.get(1).get(4));

		// data row 2
		assertEquals("4.0", convertedData.get(2).get(0));
		assertEquals("5.0", convertedData.get(2).get(1));
		assertEquals("6.0", convertedData.get(2).get(2));
		assertEquals("mary", convertedData.get(2).get(3));
		assertEquals("99.99", convertedData.get(2).get(4));

		// data row 3
		assertEquals("7.0", convertedData.get(3).get(0));
		assertEquals("8.0", convertedData.get(3).get(1));
		assertEquals("9.0", convertedData.get(3).get(2));
		assertEquals("John", convertedData.get(3).get(3));
		assertEquals("100000.0", convertedData.get(3).get(4));

	}

}
