package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.TacRangeVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.*;

@Component
@Slf4j
public class TacRangeWS {

	@Value("{skip.header:Y")
	private String skip_header;

	public List<TacRangeVM> readSheet(Sheet worksheet) {
		DataFormatter dataFormatter = new DataFormatter();
		List<TacRangeVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->		
		{						
			if (!isRowEmpty(x)) {
			
				TacRangeVM obj = new TacRangeVM();			
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {

						 Cell nextCol = colIterator.next();
						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(0))) {
							 if (nextCol.getRow().getCell(0).getCellType().equals(CellType.NUMERIC)) {
								 obj.setModel_number(getScienceNot2String(nextCol.getRow().getCell(0).getNumericCellValue()));
							 }
							 else {
								 obj.setModel_number(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)).trim());
							 }
							 hasData = true;
						 }
						 obj.setTacPrefix(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)).trim());
						 obj.setTacHighRange(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)).trim());

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
