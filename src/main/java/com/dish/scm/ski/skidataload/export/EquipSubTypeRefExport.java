package com.dish.scm.ski.skidataload.export;


import com.dish.scm.ski.skidataload.model.file.EquipSubTypeRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;
import static com.dish.scm.ski.skidataload.util.FileUtil.getHdrFromClass;

@Component
public class EquipSubTypeRefExport {
	
public void writeData(Sheet sheet,List<EquipSubTypeRefVM> lstData) {
		

		int rowCnt =0;
		int colCnt =0;
		try {

			List<String> hdr = getHdrFromClass(lstData.get(0).getClass());
			
			Row row = sheet.createRow(rowCnt++);
			for(String hname :  hdr) {
				
				Cell cell = row.createCell(colCnt++);
				cell.setCellValue(hname.toUpperCase());
			}
			
			for (EquipSubTypeRefVM obj : lstData) {
				
				row = sheet.createRow(rowCnt++);
				row.createCell(0).setCellValue(obj.getEquip_sub_type());
				row.createCell(1).setCellValue(obj.getEquip_sub_type_name());
				row.createCell(2).setCellValue(obj.getEquip_sub_type_desc());
				row.createCell(3).setCellValue(obj.getError_status());
				row.createCell(4).setCellValue(replaceNull(obj.getError_message()));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


}
