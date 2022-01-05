package com.dish.scm.ski.skidataload.export;


import com.dish.scm.ski.skidataload.model.file.SkuTypeRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;
import static com.dish.scm.ski.skidataload.util.FileUtil.getHdrFromClass;

@Component
public class SkuTypeRefExport {
	
public void writeData(Sheet sheet,List<SkuTypeRefVM> lstData) {
		

		int rowCnt =0;
		int colCnt =0;
		try {

			List<String> hdr = getHdrFromClass(lstData.get(0).getClass());
			
			Row row = sheet.createRow(rowCnt++);
			for(String hname :  hdr) {
				
				Cell cell = row.createCell(colCnt++);
				cell.setCellValue(hname.toUpperCase());
			}
			
			for (SkuTypeRefVM obj : lstData) {
				
				row = sheet.createRow(rowCnt++);
				row.createCell(0).setCellValue(obj.getSku_type());
				row.createCell(1).setCellValue(obj.getSku_type_name());
				row.createCell(2).setCellValue(obj.getSku_type_desc());
				row.createCell(3).setCellValue(obj.getDefault_name_conv());
				row.createCell(4).setCellValue(obj.isHas_vendor_part_num() ?"Y":"N");
				row.createCell(5).setCellValue(obj.isHas_upc() ?"Y":"N");
				row.createCell(6).setCellValue(obj.getUpc_owner());
				row.createCell(7).setCellValue(obj.isActivatable() ?"Y":"N");
				row.createCell(8).setCellValue(obj.getError_status());
				row.createCell(9).setCellValue(replaceNull(obj.getError_message()));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


}
