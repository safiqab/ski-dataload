package com.dish.scm.ski.skidataload.export;


import com.dish.scm.ski.skidataload.model.file.LanguageSupportVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;

@Component
public class LanguageSupportExport {

public void writeData(Sheet sheet,List<LanguageSupportVM> lstData) {

		int rowCnt =0;
		int colCnt =0;
		try {

			Row row = sheet.createRow(rowCnt++);
			Cell cell = row.createCell(colCnt++);
			cell.setCellValue("MODEL_NUMBER");
			cell = row.createCell(colCnt++);
			cell.setCellValue("SKU");
			cell = row.createCell(colCnt++);
			cell.setCellValue("EQUIP_TYPE");
			cell = row.createCell(colCnt++);
			cell.setCellValue("LANGUAGE_CODE");
			cell = row.createCell(colCnt++);
			cell.setCellValue("LANGUAGE_PROD_NAME");
			cell = row.createCell(colCnt++);
			cell.setCellValue("LANGUAGE_PROD_DESC");
			cell = row.createCell(colCnt++);
			cell.setCellValue("STATUS");
			cell = row.createCell(colCnt++);
			cell.setCellValue("MESSAGE");
			
			for (LanguageSupportVM obj : lstData) {
				colCnt = 0;
				row = sheet.createRow(rowCnt++);
				row.createCell(colCnt++).setCellValue(obj.getModel_number());
				row.createCell(colCnt++).setCellValue(obj.getLanguage_code());
				row.createCell(colCnt++).setCellValue(obj.getLang_prod_name());
				row.createCell(colCnt++).setCellValue(obj.getLang_prod_desc());
				row.createCell(colCnt++).setCellValue(obj.getError_status());
				row.createCell(colCnt++).setCellValue(replaceNull(obj.getError_message()));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
