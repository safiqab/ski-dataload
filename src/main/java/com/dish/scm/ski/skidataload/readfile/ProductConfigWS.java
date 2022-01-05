package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.ProductConfigVM;
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
public class ProductConfigWS {
	
	@Value("{skip.header:Y")
	private String skip_header;

	public List<ProductConfigVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<ProductConfigVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{						
			if (!isRowEmpty(x)) {
			
				ProductConfigVM obj = new ProductConfigVM();			
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 obj.setModel_number(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)));
						 obj.setSku(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
						 obj.setConfig_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));
						 obj.setConfig_value(dataFormatter.formatCellValue(nextCol.getRow().getCell(3)));
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
