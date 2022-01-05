package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.EquipIdRefVM;
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
public class EquipIDRefWS {

	@Value("{skip.header:Y")
	private String skip_header;

	public List<EquipIdRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<EquipIdRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{	
			if (!isRowEmpty(x)) {
			
				EquipIdRefVM obj = new EquipIdRefVM();
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 
						 obj.setEquip_id(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)));
						 obj.setEquip_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
						 obj.setEquip_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));					 
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
