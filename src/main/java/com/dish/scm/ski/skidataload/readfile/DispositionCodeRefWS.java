package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.DispositionCodeRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class DispositionCodeRefWS {

	@Value("{skip.header:Y")
	private String skip_header;

	public List<DispositionCodeRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<DispositionCodeRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{						
			DispositionCodeRefVM obj = new DispositionCodeRefVM();
			if (skip_header.equals("N")) {
				boolean hasData = false;
				Iterator<Cell> colIterator = x.cellIterator();				
				 while (colIterator.hasNext()) {		
					 
					 hasData = true;
					 Cell nextCol = colIterator.next();
					 try {
		            		obj.setDisposition_code(Integer.parseInt(dataFormatter.formatCellValue(nextCol.getRow().getCell(0))));
					 }
					 catch(NumberFormatException ne) {
		            		obj.setError_status("Fail");
		            		obj.setError_message("Invalid Disposition code or null");
					 }					 
					 obj.setDisposition_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
					 obj.setDisposition_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));					 
				 }
				 if (hasData)
					 lstData.add(obj);				
			}			
			skip_header = "N";	
		});
		
		return lstData;
	}
}
