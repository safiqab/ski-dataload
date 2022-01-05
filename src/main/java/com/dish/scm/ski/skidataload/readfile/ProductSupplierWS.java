package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.ProductSupplierVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.*;
import static com.dish.scm.ski.skidataload.util.DateUtil.convertStringToDate;

@Component
public class ProductSupplierWS {
	
	@Value("{skip.header:Y")
	private String skip_header;

	public List<ProductSupplierVM> readSheet(Sheet worksheet) {

		DataFormatter dataFormatter = new DataFormatter();
		List<ProductSupplierVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{						
			if (!isRowEmpty(x)) {
		
				ProductSupplierVM obj = new ProductSupplierVM();		
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 
						 hasData = true;
						 Cell nextCol = colIterator.next();
						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(0))) {
							 if (nextCol.getRow().getCell(0).getCellType().equals(CellType.NUMERIC)) {
								 obj.setModel_number(getScienceNot2String(nextCol.getRow().getCell(0).getNumericCellValue()));
							 }
							 else {
								 obj.setModel_number(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)).trim());
							 }
						 }
						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(1))) {
							 if (nextCol.getRow().getCell(1).getCellType().equals(CellType.NUMERIC)) {
								 obj.setSku(getScienceNot2String(nextCol.getRow().getCell(1).getNumericCellValue()));
							 }
							 else {
								 obj.setSku(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)).trim());
							 }
						 }
						 obj.setSupplier_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)).trim());

						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(3))) {
							 if (nextCol.getRow().getCell(3).getCellType().equals(CellType.NUMERIC)) {
								 obj.setSupplier_part_number(getScienceNot2String(nextCol.getRow().getCell(3).getNumericCellValue()));
							 }
							 else {
								 obj.setSupplier_part_number(dataFormatter.formatCellValue(nextCol.getRow().getCell(3)).trim());
							 }
						 }
						 obj.setDefault_supplier_cost(format2Decimal(dataFormatter.formatCellValue(nextCol.getRow().getCell(4))).trim());

						 try {
							 obj.setSupplier_enable_date(convertStringToDate(dataFormatter.formatCellValue(nextCol.getRow().getCell(5))));
						 }
						 catch (ParseException e) {
			            		obj.setError_status("Fail");
			            		obj.setError_message("Invalid Supplier enable date.");
						 }
						 
						 try {		            		
			            		obj.setSupplier_disable_date(convertStringToDate(dataFormatter.formatCellValue(nextCol.getRow().getCell(6))));
						 }
						 catch (ParseException e) {
			            		obj.setError_status("Fail");
			            		obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid supplier disable date.":obj.getError_message()+"Invalid supplier disable date.");
						 }

						 break;
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
