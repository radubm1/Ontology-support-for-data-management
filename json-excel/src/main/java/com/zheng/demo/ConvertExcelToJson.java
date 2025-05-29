package com.zheng.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertExcelToJson extends ConvertExcelToJsonBase {

	public String convertExceltoJsonString(final String excelFileName) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<List<String>> data = convertExceltoListofString(excelFileName);
		String json = null;
		try {
			json = objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public List<List<String>> convertExceltoListofString(final String excelFileName) {
		List<List<String>> data = new ArrayList<>();

		try {
			initialize(excelFileName);
			Row headerRow = sheet.getRow(0);
			List<String> headers = new ArrayList<>();
			for (Cell cell : headerRow) {
				headers.add(cell.toString());
			}
			data.add(headers);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				List<String> rowData = new ArrayList<>();
				for (Cell cell : row) {
					rowData.add(cell.toString());
				}
				data.add(rowData);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeResource();

		}

		return data;

	}

}
