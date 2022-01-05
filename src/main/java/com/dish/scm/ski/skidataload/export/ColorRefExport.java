package com.dish.scm.ski.skidataload.export;


import com.dish.scm.ski.skidataload.model.file.ColorRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;
import static com.dish.scm.ski.skidataload.util.FileUtil.getHdrFromClass;

@Component
public class ColorRefExport {
	
public void writeData(Sheet sheet,List<ColorRefVM> lstData) {
		

		int rowCnt =0;
		int colCnt =0;
		try {

			List<String> hdr = getHdrFromClass(lstData.get(0).getClass());
			
			Row row = sheet.createRow(rowCnt++);
			for(String hname :  hdr) {
				
				Cell cell = row.createCell(colCnt++);
				cell.setCellValue(hname.toUpperCase());
			}
			
			for (ColorRefVM obj : lstData) {
				
				row = sheet.createRow(rowCnt++);
				row.createCell(0).setCellValue(obj.getColor());
				row.createCell(1).setCellValue(obj.getColor_name());
				row.createCell(2).setCellValue(obj.getColor_desc());
				row.createCell(3).setCellValue(obj.getHex_color());
				row.createCell(4).setCellValue(obj.getFull_name_code());
				row.createCell(5).setCellValue(obj.getFour_digit_code());
				row.createCell(6).setCellValue(obj.getThree_digit_code());
				row.createCell(7).setCellValue(obj.getTwo_digit_code());
				row.createCell(8).setCellValue(obj.getError_status());
				row.createCell(9).setCellValue(replaceNull(obj.getError_message()));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


}
