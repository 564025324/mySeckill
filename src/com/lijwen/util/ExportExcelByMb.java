package com.lijwen.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcelByMb {

	public static void main(String[] args) throws Exception {

		Date time = new Date();
		String sj = time.getHours() + "时" + time.getMinutes() + "分";
		/// https://blog.csdn.net/sinat_33151213/article/details/78405187
		ExportExcelByMb export = new ExportExcelByMb();
//		String srcFilePath = "D:\\dzd\\test.xlsx";
		String srcFilePath = "../mySeckill/src/fileforder/模板.xlsx";
		String fileName = "test.xlsx";
		String desFilePath = "../mySeckill/src/fileforder/" + fileName;
		System.out.println(sj);

		if (new File(desFilePath).exists()) {
			new File(desFilePath).delete();
		}


		// 创建Excel文件的输入流对象
		 FileInputStream fis = new FileInputStream(srcFilePath);
		// 创建Excel文件输出流对象
		 FileOutputStream fos = new FileOutputStream(desFilePath);
		 export.exportExcel(fis, fos, sj);
	}

	// 根据指定的excel模板导出数据
	public void exportExcel(FileInputStream fis, FileOutputStream fos, String now) {

		try {
			// 根据模板创建excel工作簿
			XSSFWorkbook workBook = new XSSFWorkbook(fis);
			// 获取创建的工作簿第一页
			XSSFSheet sheet = workBook.getSheetAt(0);
			// 给指定的sheet命名
			workBook.setSheetName(0, now);

			// 修改标题
			XSSFRow row = sheet.getRow(0);
			XSSFCell cell = row.getCell(0);
			// 获取指定单元格值
			String s = cell.getStringCellValue();
			cell.setCellValue("修改后的标题为:" + s);

			// 获取当前sheet最后一行数据对应的行索引
			int currentLastRowIndex = sheet.getLastRowNum();
			int newRowIndex = currentLastRowIndex + 1;
			XSSFRow newRow = sheet.createRow(newRowIndex);
			// 开始创建并设置该行每一单元格的信息，该行单元格的索引从 0 开始
			int cellIndex = 0;

			CellStyle cellstyle = sheet.getRow(1).getCell(0).getCellStyle();
			System.out.println(cellstyle);
			// 创建一个单元格，设置其内的数据格式为字符串，并填充内容，其余单元格类同
			XSSFCell newCell = newRow.createCell(cellIndex++);
			newCell.setCellValue("410015062529");
			newCell.setCellStyle(cellstyle);
			XSSFCell newCell2 = newRow.createCell(cellIndex++);
			newCell2.setCellValue("410015062529");
			newCell2.setCellStyle(cellstyle);
			XSSFCell newCell3 = newRow.createCell(cellIndex++);
			newCell3.setCellValue("工业富联");
			newCell3.setCellStyle(cellstyle);

			workBook.write(fos);

			fis.close();
			fos.flush();
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}