package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.LocationRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.isColumnEmpty;
import static com.dish.scm.ski.skidataload.util.DataUtil.isRowEmpty;

@Component
public class LocationRefWS {
	
	@Value("{skip.header:Y")
	private String skip_header;

	public List<LocationRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<LocationRefVM> lstData = new ArrayList<>();
		
		worksheet.iterator().forEachRemaining(x->
		{						
			if (!isRowEmpty(x)) {
			
			LocationRefVM obj = new LocationRefVM();
			if (skip_header.equals("N")) {
				boolean hasData = false;
				Iterator<Cell> colIterator = x.cellIterator();				
				 while (colIterator.hasNext()) {		
					 
					 hasData = true;
					 Cell nextCol = colIterator.next();
					 try {
						 obj.setLocation_id(isColumnEmpty(nextCol.getRow().getCell(0))?0:Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(0))));
					 }
					 catch(NumberFormatException ne) {
						 obj.setError_status("Fail");
						 obj.setError_message("Invalid Location Id. or null");
					 }
					 obj.setLocation_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
					 obj.setLocation_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));

					 try {						 
					 
		            		obj.setChannel_id(isColumnEmpty(nextCol.getRow().getCell(3))?0:Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(3))));
					 }
					 catch(NumberFormatException ne) {						 
						 obj.setError_status("Fail");
		            	 obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid channel Id.":obj.getError_message()+"Invalid channel Id.");
					 }
					 
					 					 
					 obj.setContact_first_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(4)));
					 obj.setContact_last_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(5)));
					 obj.setContact_phone_number(dataFormatter.formatCellValue(nextCol.getRow().getCell(6)));
					 obj.setContact_fax_number(dataFormatter.formatCellValue(nextCol.getRow().getCell(7)));
					 obj.setLocation_addr_line1(dataFormatter.formatCellValue(nextCol.getRow().getCell(8)));
					 obj.setLocation_addr_line2(dataFormatter.formatCellValue(nextCol.getRow().getCell(9)));
					 obj.setLocation_city(dataFormatter.formatCellValue(nextCol.getRow().getCell(10)));
					 try {
						 obj.setLocation_postal_code(isColumnEmpty(nextCol.getRow().getCell(11))?0:Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(11))));
					 }		            		
					 catch(NumberFormatException ne) {
		            		obj.setError_status("Fail");
		            		obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid postal code.":obj.getError_message()+"Invalid postal code.");
		            		
					 }
					 
					 try {
						 obj.setLocation_country_code(isColumnEmpty(nextCol.getRow().getCell(12))?0:Integer.parseInt(dataFormatter.formatCellValue(nextCol.getRow().getCell(12))));
					 }
					 catch(NumberFormatException ne) {
		            		obj.setError_status("Fail");
		            		obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid country code.":obj.getError_message()+"Invalid country code.");
					 }
					 
					 obj.setLocation_state(dataFormatter.formatCellValue(nextCol.getRow().getCell(13)));
					 obj.setShip_to_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(14)));
					 
				 }
				 if (hasData)
					 lstData.add(obj);				
			}
			skip_header = "N";	
			}
		});
		
		return lstData;
	}


}
