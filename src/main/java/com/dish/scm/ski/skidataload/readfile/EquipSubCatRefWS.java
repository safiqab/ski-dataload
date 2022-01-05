package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.EquipSubCatRefVM;
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
public class EquipSubCatRefWS {
	
	@Value("{skip.header:Y")
	private String skip_header;

	public List<EquipSubCatRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<EquipSubCatRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{						
			if (!isRowEmpty(x)) {
			
				EquipSubCatRefVM obj = new EquipSubCatRefVM();
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 
						 obj.setEquip_sub_cat(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)));
						 obj.setEquip_sub_cat_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
						 obj.setEquip_sub_cat_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));					 
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
