package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.FeatureTypeRefVM;
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
public class FeatureTypeRefWS {
	
	@Value("{skip.header:Y")
	private String skip_header;

	public List<FeatureTypeRefVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<FeatureTypeRefVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{						
			if (!isRowEmpty(x)) {
				FeatureTypeRefVM ftr = new FeatureTypeRefVM();			
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 
						 ftr.setFeature_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)));
						 ftr.setFeature_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
						 ftr.setFeature_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));					 
					 }
					 if (hasData)
						 lstData.add(ftr);				
				}
				skip_header = "N";	
			}	
		});
		
		return lstData;
	}
	
}
