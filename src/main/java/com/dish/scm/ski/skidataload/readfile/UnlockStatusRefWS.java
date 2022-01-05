package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.UnlockStatusRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.isColumnEmpty;
import static com.dish.scm.ski.skidataload.util.DataUtil.isRowEmpty;

@Component
public class UnlockStatusRefWS {

	@Value("{skip.header:Y")
	private String skip_header;

	public List<UnlockStatusRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<UnlockStatusRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->		
		{						
			if (!isRowEmpty(x)) {
				UnlockStatusRefVM obj = new UnlockStatusRefVM();
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 try {
							 obj.setUnlock_status_code(isColumnEmpty(nextCol.getRow().getCell(0))?0:Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(0))));
			            	}
			            	catch(NumberFormatException ne) {
			            		obj.setError_status("Fail");
			            		obj.setError_message("Invalid Unlock status code");
			            	}
						 obj.setUnlock_status_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
						 obj.setUnlock_status_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));					 					 
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
