package com.dish.scm.ski.skidataload.export;


import com.dish.scm.ski.skidataload.model.file.SalesChannelVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;
import static com.dish.scm.ski.skidataload.util.DateUtil.getStringDate;


@Component
public class SalesChannelExport {

	public void writeData(Sheet sheet,List<SalesChannelVM> lstData) {

		int rowCnt =0;
		int colCnt =0;
		try {

			Row row = sheet.createRow(rowCnt++);

			Cell cell = row.createCell(0);
			cell.setCellValue("MODEL_NUMBER");
			cell = row.createCell(1);
			cell.setCellValue("SKU");
			cell = row.createCell(2);
			cell.setCellValue("EQUIP_TYPE");
			cell = row.createCell(3);
			cell.setCellValue("CHANNEL_CODE");
			cell = row.createCell(4);
			cell.setCellValue("BRAND_CODE");
			cell = row.createCell(5);
			cell.setCellValue("CHANNEL_SALES_START_DATE");
			cell = row.createCell(6);
			cell.setCellValue("CHANNEL_SALES_END_DATE");
			cell = row.createCell(7);
			cell.setCellValue("CHANNEL_PRICE");
			cell = row.createCell(8);
			cell.setCellValue("CHANNEL_COST");
			cell = row.createCell(9);
			cell.setCellValue("STATUS");
			cell = row.createCell(10);
			cell.setCellValue("MESSAGE");

			for (SalesChannelVM obj : lstData) {

				row = sheet.createRow(rowCnt++);
				row.createCell(0).setCellValue(obj.getModel_number());
				row.createCell(1).setCellValue(obj.getSku());
				row.createCell(2).setCellValue(obj.getChannel_code());
				row.createCell(3).setCellValue(obj.getBrand_code());
				row.createCell(4).setCellValue(!StringUtils.isEmpty(obj.getChannel_sales_start_date())?getStringDate(obj.getChannel_sales_start_date()):"");
				row.createCell(5).setCellValue(!StringUtils.isEmpty(obj.getChannel_sales_end_date())?getStringDate(obj.getChannel_sales_end_date()):"");
				row.createCell(6).setCellValue(obj.getChannel_price());
				row.createCell(7).setCellValue(obj.getChannel_cost());
				row.createCell(8).setCellValue(obj.getError_status());
				row.createCell(9).setCellValue(replaceNull(obj.getError_message()));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


public void writeData(String fname,String sheetName,List<SalesChannelVM> lstData) {
		
		File file;
		OutputStream fos =null;
		XSSFWorkbook wb = null;
		int rowCnt =0;
		int colCnt =0;
		try {
			file = new File(fname);
			Sheet sheet;
			if (file.exists()) {
				wb = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
			}
			else {
				wb = new XSSFWorkbook();
			}			
			
			sheet = wb.createSheet(sheetName);
			Row row = sheet.createRow(rowCnt++);

			Cell cell = row.createCell(0);
			cell.setCellValue("MODEL_NUMBER");
			cell = row.createCell(1);
			cell.setCellValue("SKU");
			cell = row.createCell(2);
			cell.setCellValue("CHANNEL_CODE");
			cell = row.createCell(3);
			cell.setCellValue("BRAND_CODE");
			cell = row.createCell(4);
			cell.setCellValue("CHANNEL_SALES_START_DATE");
			cell = row.createCell(5);
			cell.setCellValue("CHANNEL_SALES_END_DATE");
			cell = row.createCell(6);
			cell.setCellValue("CHANNEL_PRICE");
			cell = row.createCell(7);
			cell.setCellValue("CHANNEL_COST");
			cell = row.createCell(8);
			cell.setCellValue("STATUS");
			cell = row.createCell(9);
			cell.setCellValue("MESSAGE");
			
			for (SalesChannelVM obj : lstData) {
				
				row = sheet.createRow(rowCnt++);
				row.createCell(0).setCellValue(obj.getModel_number());
				row.createCell(1).setCellValue(obj.getSku());
				row.createCell(2).setCellValue(obj.getChannel_code());
				row.createCell(3).setCellValue(obj.getBrand_code());
				row.createCell(4).setCellValue(!StringUtils.isEmpty(obj.getChannel_sales_start_date())?getStringDate(obj.getChannel_sales_start_date()):"");
				row.createCell(5).setCellValue(!StringUtils.isEmpty(obj.getChannel_sales_end_date())?getStringDate(obj.getChannel_sales_end_date()):"");
				row.createCell(6).setCellValue(obj.getChannel_price());
				row.createCell(7).setCellValue(obj.getChannel_cost());
				row.createCell(8).setCellValue(obj.getError_status());
				row.createCell(9).setCellValue(replaceNull(obj.getError_message()));
			}
			fos = new FileOutputStream(file);
			wb.write(fos);
			fos.flush();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally  {
			try {
				
				if (fos != null) {
					fos.close();
				}
			}
			catch (IOException e) {				
			}
			try {
				if (wb != null) {
					wb.close();
				}
			}
			catch (IOException e) {
			}
		}
	}


}
