package com.zheng.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class ConvertExcelToJSONArrayTest {

	public static String filePath = Objects
			.requireNonNull(ConvertExcelToJSONArrayTest.class.getClassLoader().getResource("rap.xlsx")).getFile();
	private ConvertExcelToJSONArray testClass = new ConvertExcelToJSONArray();

	@Test
	void test_convertExceltoJSONArray() {
		JSONArray convertedData = testClass.convertExceltoJSONArray_withValueOnly(filePath);
		assertEquals(4, convertedData.length());

		assertEquals("[\"header1\",\"header2\",\"header3\",\"stringHeader\",\"numberHeader\"]",
				convertedData.get(0).toString());
		assertEquals("[\"1.0\",\"2.0\",\"3.0\",\"test\",\"12.5\"]", convertedData.get(1).toString());
		assertEquals("[\"4.0\",\"5.0\",\"6.0\",\"mary\",\"99.99\"]", convertedData.get(2).toString());
		assertEquals("[\"7.0\",\"8.0\",\"9.0\",\"John\",\"100000.0\"]", convertedData.get(3).toString());
	}

	@Test
	void test_convertExceltoJSONArray_withObject() {
		JSONArray convertedData = testClass.convertExceltoJSONArray_withObject(filePath);
		System.out.print(convertedData);
		assertEquals(3, convertedData.length());

		assertEquals(1.0, ((JSONObject) convertedData.get(0)).get("header1"));
		assertEquals(2.0, ((JSONObject) convertedData.get(0)).get("header2"));
		assertEquals(3.0, ((JSONObject) convertedData.get(0)).get("header3"));
		assertEquals("test", ((JSONObject) convertedData.get(0)).get("stringHeader"));
		assertEquals(12.5, ((JSONObject) convertedData.get(0)).get("numberHeader"));

		assertEquals(7.0, ((JSONObject) convertedData.get(2)).get("header1"));
		assertEquals(8.0, ((JSONObject) convertedData.get(2)).get("header2"));
		assertEquals(9.0, ((JSONObject) convertedData.get(2)).get("header3"));
		assertEquals("John", ((JSONObject) convertedData.get(2)).get("stringHeader"));
		assertEquals(100000.0, ((JSONObject) convertedData.get(2)).get("numberHeader"));

	}

}
