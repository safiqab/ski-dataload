package com.dish.scm.ski.skidataload.export;


import com.dish.scm.ski.skidataload.model.file.LocationRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;
import static com.dish.scm.ski.skidataload.util.FileUtil.getHdrFromClass;

@Component
public class LocationRefExport {
	
public void writeData(Sheet sheet,List<LocationRefVM> lstData) {
		

		int rowCnt =0;
		int colCnt =0;
		try {

			List<String> hdr = getHdrFromClass(lstData.get(0).getClass());
			
			Row row = sheet.createRow(rowCnt++);
			for(String hname :  hdr) {
				
				Cell cell = row.createCell(colCnt++);
				cell.setCellValue(hname.toUpperCase());
			}
			
			for (LocationRefVM obj : lstData) {
				
				row = sheet.createRow(rowCnt++);
				row.createCell(0).setCellValue(obj.getLocation_id());
				row.createCell(1).setCellValue(obj.getLocation_name());
				row.createCell(2).setCellValue(obj.getLocation_desc());
				row.createCell(3).setCellValue(obj.getChannel_id());
				row.createCell(4).setCellValue(obj.getContact_first_name());
				row.createCell(5).setCellValue(obj.getContact_last_name());
				row.createCell(6).setCellValue(obj.getContact_phone_number());
				row.createCell(7).setCellValue(obj.getContact_fax_number());
				row.createCell(8).setCellValue(obj.getLocation_addr_line1());
				row.createCell(9).setCellValue(obj.getLocation_addr_line2());
				row.createCell(10).setCellValue(obj.getLocation_city());
				row.createCell(11).setCellValue(obj.getLocation_postal_code());
				row.createCell(12).setCellValue(obj.getLocation_country_code());
				row.createCell(13).setCellValue(obj.getLocation_state());
				row.createCell(14).setCellValue(obj.getShip_to_code());				
				row.createCell(15).setCellValue(obj.getError_status());
				row.createCell(16).setCellValue(replaceNull(obj.getError_message()));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


}
