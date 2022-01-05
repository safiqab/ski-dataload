package com.dish.scm.ski.skidataload.export;


import com.dish.scm.ski.skidataload.model.file.ManufacturerRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;
import static com.dish.scm.ski.skidataload.util.FileUtil.getHdrFromClass;

@Component
public class ManufacturerRefExport {
	
public void writeData(Sheet sheet,List<ManufacturerRefVM> lstData) {
		

		int rowCnt =0;
		int colCnt =0;
		try {

			List<String> hdr = getHdrFromClass(lstData.get(0).getClass());
			
			Row row = sheet.createRow(rowCnt++);
			for(String hname :  hdr) {
				
				Cell cell = row.createCell(colCnt++);
				cell.setCellValue(hname.toUpperCase());
			}
			
			for (ManufacturerRefVM obj : lstData) {
				
				row = sheet.createRow(rowCnt++);
				row.createCell(0).setCellValue(obj.getManf_code());
				row.createCell(1).setCellValue(obj.getManf_name());
				row.createCell(2).setCellValue(obj.getManf_desc());
				row.createCell(3).setCellValue(obj.getError_status());
				row.createCell(4).setCellValue(replaceNull(obj.getError_message()));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


}
