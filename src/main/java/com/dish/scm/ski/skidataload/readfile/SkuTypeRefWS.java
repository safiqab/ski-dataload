package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.SkuTypeRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.isRowEmpty;

@Component
public class SkuTypeRefWS {
	
	@Value("{skip.header:Y")
	private String skip_header;

	public List<SkuTypeRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<SkuTypeRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{						
			if (!isRowEmpty(x)) {
			
				SkuTypeRefVM obj = new SkuTypeRefVM();
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 
						 obj.setSku_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)));
						 obj.setSku_type_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
						 obj.setSku_type_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));
						 obj.setDefault_name_conv(dataFormatter.formatCellValue(nextCol.getRow().getCell(3)));
						 obj.setHas_vendor_part_num(dataFormatter.formatCellValue(nextCol.getRow().getCell(4)).equalsIgnoreCase("y"));
						 obj.setHas_upc(dataFormatter.formatCellValue(nextCol.getRow().getCell(5)).equalsIgnoreCase("y"));
						 obj.setUpc_owner(dataFormatter.formatCellValue(nextCol.getRow().getCell(6)));
						 obj.setActivatable(dataFormatter.formatCellValue(nextCol.getRow().getCell(7)).equalsIgnoreCase("y"));
					 }
					 if (hasData) {
						 if (StringUtils.isEmpty(obj.isActivatable()))
							 obj.setActivatable(true);
						 if (StringUtils.isEmpty(obj.isHas_upc()))
							 obj.setActivatable(true);
						 if (StringUtils.isEmpty(obj.isHas_vendor_part_num()))
							 obj.setHas_vendor_part_num(true);
						 lstData.add(obj);
					 }						 				
				}
				skip_header = "N";
				
			}	
		});
		
		return lstData;
	}


}
