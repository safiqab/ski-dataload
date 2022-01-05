package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.SalesChannelVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.*;
import static com.dish.scm.ski.skidataload.util.DateUtil.convertStringToDate;

@Component
public class SalesChannelWS {
	
	@Value("{skip.header:Y")
	private String skip_header;

	public List<SalesChannelVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<SalesChannelVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{						
			if (!isRowEmpty(x)) {
			
				SalesChannelVM obj = new SalesChannelVM();
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(0))) {
							 if (nextCol.getRow().getCell(0).getCellType().equals(CellType.NUMERIC)) {
								 obj.setModel_number(getScienceNot2String(nextCol.getRow().getCell(0).getNumericCellValue()));
							 }
							 else {
								 obj.setModel_number(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)).trim());
							 }
						 }
						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(1))) {
							 if (nextCol.getRow().getCell(1).getCellType().equals(CellType.NUMERIC)) {
								 obj.setSku(getScienceNot2String(nextCol.getRow().getCell(1).getNumericCellValue()));
							 }
							 else {
								 obj.setSku(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)).trim());
							 }
						 }

						 obj.setChannel_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)).trim());
						 obj.setBrand_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(3)).trim());
						 obj.setChannel_price(format2Decimal(dataFormatter.formatCellValue(nextCol.getRow().getCell(4))));
						 obj.setChannel_cost(format2Decimal(dataFormatter.formatCellValue(nextCol.getRow().getCell(5))));

						 try {		            		
			            		obj.setChannel_sales_start_date(convertStringToDate(dataFormatter.formatCellValue(nextCol.getRow().getCell(6))));
			            	} 
			            	catch (ParseException e) {
			            		obj.setError_status("Fail");
			            		obj.setError_message("Invalid Channel sales start date");
			            	}
						 
						 try {		            		
			            		obj.setChannel_sales_end_date(convertStringToDate(dataFormatter.formatCellValue(nextCol.getRow().getCell(7))));
			            	} 
			            	catch (ParseException e) {
			            		obj.setError_status("Fail");
			            		obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid Channel sales end date.":obj.getError_message()+"Invalid Channel sales end date.");
			            		
			            	}

						 break;
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
