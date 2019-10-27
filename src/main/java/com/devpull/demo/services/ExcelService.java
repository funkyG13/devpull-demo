package com.devpull.demo.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devpull.demo.model.User;

@Service
@Transactional
public class ExcelService {

	private static String[] columns = { "Id", "Role-Id", "FirstName", "LastName", "Username", "Password", "Email" };

	@Autowired
	AdminService adminService;

	public void generateUsersExcel() throws IOException {
		Workbook workbook = createWorkbook();
		Sheet sheet = workbook.getSheet("User");
		int rowNum = 1;
		List<User> users = adminService.findAll();
		for (User user : users) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getRole().getRoleName());
			row.createCell(2).setCellValue(user.getFirstName());
			row.createCell(3).setCellValue(user.getLastName());
			row.createCell(4).setCellValue(user.getUsername());
			row.createCell(5).setCellValue(user.getPassword());
			row.createCell(6).setCellValue(user.getEmail());
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("user-database-fields.xlsx");
		workbook.write(fileOut);
		fileOut.close();
		// Closing the workbook
		workbook.close();
	}

	public Workbook createWorkbook() throws IOException {
		Workbook workbook = new XSSFWorkbook();

//      CreationHelper createHelper = workbook.getCreationHelper();
		Sheet sheet = workbook.createSheet("User");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		return workbook;
	}
}
