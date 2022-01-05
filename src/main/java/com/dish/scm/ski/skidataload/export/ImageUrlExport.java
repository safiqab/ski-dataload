package com.dish.scm.ski.skidataload.export;

import com.dish.scm.ski.skidataload.model.file.ImageUrlVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;


@Component
public class ImageUrlExport {

	public void writeData(Sheet sheet,List<ImageUrlVM> lstData) {


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
			cell.setCellValue("URL_TYPE");
			cell = row.createCell(colCnt++);
			cell.setCellValue("IMAGE_URL");
			cell = row.createCell(colCnt++);
			cell.setCellValue("IMAGE_ALT_TEXT");
			cell = row.createCell(colCnt++);
			cell.setCellValue("STATUS");
			cell = row.createCell(colCnt++);
			cell.setCellValue("MESSAGE");

			for (ImageUrlVM obj : lstData) {
				colCnt =0;
				row = sheet.createRow(rowCnt++);
				row.createCell(colCnt++).setCellValue(obj.getModel_number());
				row.createCell(colCnt++).setCellValue(obj.getSku());
				row.createCell(colCnt++).setCellValue(obj.getUrl_type());
				row.createCell(colCnt++).setCellValue(obj.getImage_url());
				row.createCell(colCnt++).setCellValue(obj.getImage_alt_text());
				row.createCell(colCnt++).setCellValue(obj.getError_status());
				row.createCell(colCnt++).setCellValue(replaceNull(obj.getError_message()));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}




}
