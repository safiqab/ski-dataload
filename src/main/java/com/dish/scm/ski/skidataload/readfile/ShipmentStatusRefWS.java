package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.ShipmentStatusRefVM;
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
public class ShipmentStatusRefWS {

	@Value("{skip.header:Y")
	private String skip_header;

	public List<ShipmentStatusRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<ShipmentStatusRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{
			if (!isRowEmpty(x)) {
				ShipmentStatusRefVM obj = new ShipmentStatusRefVM();
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 
						 obj.setShipment_status(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)));
						 obj.setShipment_status_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));					 					 
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
