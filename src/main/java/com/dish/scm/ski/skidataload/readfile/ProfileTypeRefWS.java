package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.ProfileTypeRefVM;
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
public class ProfileTypeRefWS {

	@Value("{skip.header:Y")
	private String skip_header;

	public List<ProfileTypeRefVM> readSheet(Sheet worksheet) {

		DataFormatter dataFormatter = new DataFormatter();
		List<ProfileTypeRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{						
			if (!isRowEmpty(x)) {
		
				ProfileTypeRefVM obj = new ProfileTypeRefVM();			
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 
						 obj.setProfile_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)));
						 obj.setProfile_type_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
						 obj.setProfile_type_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));
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
