package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.TransactionTypeRefVM;
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
public class TransactionTypeRefWS {

	@Value("{skip.header:Y")
	private String skip_header;

	public List<TransactionTypeRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<TransactionTypeRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->		
		{						
			if (!isRowEmpty(x)) {
				TransactionTypeRefVM obj = new TransactionTypeRefVM();
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 
						 obj.setTrans_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)));
						 obj.setTrans_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
						 obj.setTrans_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));
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
