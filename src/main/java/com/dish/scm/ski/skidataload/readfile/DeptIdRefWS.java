package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.DeptIdRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.isRowEmpty;

@Component
public class DeptIdRefWS {
	
	@Value("{skip.header:Y")
	private String skip_header;

	public List<DeptIdRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<DeptIdRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{						
			if (!isRowEmpty(x)) {
			
				DeptIdRefVM obj = new DeptIdRefVM();		
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 
						 try {
							 obj.setDept_id(Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(0))));
						 }
						 catch(NumberFormatException ne) {
			            		obj.setError_status("Fail");
			            		obj.setError_message("Invalid Dept Id. or null");
						 }						 
						 obj.setDept_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
						 obj.setDept_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));					 
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
