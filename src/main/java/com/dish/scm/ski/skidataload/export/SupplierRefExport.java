package com.dish.scm.ski.skidataload.export;


import com.dish.scm.ski.skidataload.model.file.SupplierRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;
import static com.dish.scm.ski.skidataload.util.FileUtil.getHdrFromClass;

@Component
public class SupplierRefExport {
	
public void writeData(Sheet sheet,List<SupplierRefVM> lstData) {
		

		int rowCnt =0;
		int colCnt =0;
		try {

			List<String> hdr = getHdrFromClass(lstData.get(0).getClass());
			
			Row row = sheet.createRow(rowCnt++);
			for(String hname :  hdr) {
				
				Cell cell = row.createCell(colCnt++);
				cell.setCellValue(hname.toUpperCase());
			}
			
			for (SupplierRefVM obj : lstData) {
				
				row = sheet.createRow(rowCnt++);
				row.createCell(0).setCellValue(obj.getSupplier_code());
				row.createCell(1).setCellValue(obj.getSupplier_name());
				row.createCell(2).setCellValue(obj.getSupplier_desc());
				row.createCell(3).setCellValue(obj.getEDI_ISA_ID());
				row.createCell(4).setCellValue(obj.getEDI_GS_ID());
				row.createCell(5).setCellValue(obj.getError_status());
				row.createCell(6).setCellValue(replaceNull(obj.getError_message()));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


}
