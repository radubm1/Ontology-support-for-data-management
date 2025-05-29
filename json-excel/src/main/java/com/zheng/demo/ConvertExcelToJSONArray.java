package com.zheng.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;


public class ConvertExcelToJSONArray extends ConvertExcelToJsonBase {

	public JSONArray convertExceltoJSONArray_withObject(final String excelFileName) {
	    JSONArray jsonArray = new JSONArray(); // Create a new JSON array to store the parsed data
	    try {
	        initialize(excelFileName); // Initialize the Excel file for processing

	        // Create a headers array to store column names extracted from the first row
	        String[][] headers = new String[sheet.getRow(0).getLastCellNum()][10];

	        Iterator<Row> iterator = sheet.iterator(); // Get an iterator for the rows in the sheet

	        while (iterator.hasNext()) {
	            int hcount = 0; // Counter for header row management
	            Row currentRow = iterator.next(); // Get the next row from the Excel sheet

	            // Check if the first cell of the current row contains a string (indicating a header row)
	            if (currentRow.getCell(0).getCellType() == CellType.NUMERIC) {
	                // Store the header values from the current row
	                for (int i = 0; i < currentRow.getLastCellNum(); i++) {
	                	if (currentRow.getCell(i).getCellType() == CellType.NUMERIC)
	                		headers[i][hcount] = Double.toString(currentRow.getCell(i).getNumericCellValue());
	                	else
	                		headers[i][hcount] = currentRow.getCell(i).getStringCellValue();
	                }
	            } else {
	                JSONObject obj = new JSONObject(); // Create a new JSON object for data storage

	                // Loop through the cells in the row and add their values to the JSON object
	                for (int i = 0; i < currentRow.getLastCellNum(); i++) {
	                    Cell currentCell = currentRow.getCell(i);
	                    String str = currentCell.getStringCellValue();
	                    
	                    if (str.matches(".*[,]{1,}.*"))
	                    	obj.put(headers[i][hcount], str.split(",").length); // Store string value
	                    else
	                    	obj.put(headers[i][hcount], str);
	                }
	                jsonArray.put(obj); // Add the JSON object to the array
	            }
	            hcount++;
	        }

	    } catch (IOException e) {
	        e.printStackTrace(); // Handle potential IO exceptions
	    } finally {
	        closeResource(); // Close any resources used
	    }

	    return jsonArray; // Return the constructed JSON array
	}


	public JSONArray convertExceltoJSONArray_withValueOnly(final String excelFileName) {
		JSONArray jsonArray = new JSONArray();
		try {
			initialize(excelFileName);

			Row headerRow = sheet.getRow(0);
			List<String> headers = new ArrayList<>();
			for (Cell cell : headerRow) {
				headers.add(cell.getStringCellValue());
			}
			jsonArray.put(headers);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				List<String> rowData = new ArrayList<>();
				for (Cell cell : row) {
					rowData.add(cell.toString());
				}
				jsonArray.put(rowData);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}

		return jsonArray;
	}

}
