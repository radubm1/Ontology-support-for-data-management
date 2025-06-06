package com.zheng.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class ConvertExcelToJsonBase {

	protected InputStream inputStream;
	protected Sheet sheet;
	protected Workbook workbook;

	public ConvertExcelToJsonBase() {
		super();
	}

	protected void closeResource() {
		try {
			workbook.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void initialize(final String excelFileName) throws IOException {
		inputStream = new FileInputStream(excelFileName);
		workbook = new XSSFWorkbook(inputStream);
		sheet = workbook.getSheetAt(0);
	}

}