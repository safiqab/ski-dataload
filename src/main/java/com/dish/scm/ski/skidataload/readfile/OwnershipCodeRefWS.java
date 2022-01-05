package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.OwnershipCodeRefVM;
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
public class OwnershipCodeRefWS {

	@Value("{skip.header:Y")
	private String skip_header;

	public List<OwnershipCodeRefVM> readSheet(Sheet worksheet) {

		DataFormatter dataFormatter = new DataFormatter();
		List<OwnershipCodeRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->		
		{						
			if (!isRowEmpty(x)) {
			
				OwnershipCodeRefVM obj = new OwnershipCodeRefVM();
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 
						 obj.setOwnership_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)));
						 obj.setOwnership_code_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
						 obj.setOwnership_code_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));
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
