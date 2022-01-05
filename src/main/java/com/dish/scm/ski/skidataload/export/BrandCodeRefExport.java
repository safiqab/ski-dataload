package com.dish.scm.ski.skidataload.export;


import com.dish.scm.ski.skidataload.model.file.BrandCodeRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;
import static com.dish.scm.ski.skidataload.util.FileUtil.getHdrFromClass;

@Component
public class BrandCodeRefExport {
	
public void writeData(Sheet sheet,List<BrandCodeRefVM> lstData) {
		

		int rowCnt =0;
		int colCnt =0;
		try {

			List<String> hdr = getHdrFromClass(lstData.get(0).getClass());
			
			Row row = sheet.createRow(rowCnt++);
			for(String hname :  hdr) {
				
				Cell cell = row.createCell(colCnt++);
				cell.setCellValue(hname.toUpperCase());
			}
			
			for (BrandCodeRefVM obj : lstData) {
				
				row = sheet.createRow(rowCnt++);
				row.createCell(0).setCellValue(obj.getBrand_code());
				row.createCell(1).setCellValue(obj.getBrand_name());
				row.createCell(2).setCellValue(obj.getBrand_type());
				row.createCell(3).setCellValue(obj.getBrand_desc());
				row.createCell(4).setCellValue(obj.getError_status());
				row.createCell(5).setCellValue(replaceNull(obj.getError_message()));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


}
