package com.dish.scm.ski.skidataload.readfile;


import com.dish.scm.ski.skidataload.model.file.ChannelRefVM;
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
public class ChannelRefWS {

	@Value("{skip.header:Y")
	private String skip_header;

	public List<ChannelRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<ChannelRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{
			if (!isRowEmpty(x)) {
				ChannelRefVM obj = new ChannelRefVM();			
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 
						 obj.setChannel_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)).trim());
						 obj.setChannel_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
						 obj.setChannel_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));					 
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
