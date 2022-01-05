package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.ColorRefVM;
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
public class ColorRefWS {
	
	@Value("{skip.header:Y")
	private String skip_header;

	public List<ColorRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<ColorRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{						
			if (!isRowEmpty(x)) {
				ColorRefVM obj = new ColorRefVM();
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 
						 obj.setColor(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)).trim());
						 obj.setColor_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)).trim());
						 obj.setColor_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)).trim());
						 obj.setHex_color(dataFormatter.formatCellValue(nextCol.getRow().getCell(3)).trim());
						 obj.setFull_name_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(4)).trim());
						 obj.setFour_digit_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(5)).trim());
						 obj.setThree_digit_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(6)).trim());
						 obj.setTwo_digit_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(7)).trim());
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
