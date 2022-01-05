package com.dish.scm.ski.skidataload.export;


import com.dish.scm.ski.skidataload.model.file.ProductSupplierVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;
import static com.dish.scm.ski.skidataload.util.DateUtil.getStringDate;

@Component
public class ProductSupplierExport {

	public void writeData(Sheet sheet,List<ProductSupplierVM> lstData) {

		int rowCnt =0;
		int colCnt =0;
		try {

			Row row = sheet.createRow(rowCnt++);
			Cell cell = row.createCell(0);
			cell.setCellValue("MODEL_NUMBER");
			cell = row.createCell(1);
			cell.setCellValue("SKU");
			cell = row.createCell(2);
			cell.setCellValue("EQUIP_TYPE");
			cell = row.createCell(3);
			cell.setCellValue("SUPPLIER_PART_NUMBER");
			cell = row.createCell(4);
			cell.setCellValue("SUPPLIER_CODE");
			cell = row.createCell(5);
			cell.setCellValue("SUPPLIER_ENABLE_DATE");
			cell = row.createCell(6);
			cell.setCellValue("SUPPLIER_DISABLE_DATE");
			cell = row.createCell(7);
			cell.setCellValue("DEFAULT_SUPPLIER_COST");
			cell = row.createCell(8);
			cell.setCellValue("STATUS");
			cell = row.createCell(9);
			cell.setCellValue("MESSAGE");

			for (ProductSupplierVM obj : lstData) {

				row = sheet.createRow(rowCnt++);
				row.createCell(0).setCellValue(obj.getModel_number());
				row.createCell(1).setCellValue(obj.getSku());
				row.createCell(2).setCellValue(obj.getSupplier_part_number());
				row.createCell(3).setCellValue(obj.getSupplier_code());
				row.createCell(4).setCellValue(StringUtils.isEmpty(obj.getSupplier_enable_date())?"":getStringDate(obj.getSupplier_enable_date()));
				row.createCell(5).setCellValue(StringUtils.isEmpty(obj.getSupplier_disable_date())?"":getStringDate(obj.getSupplier_disable_date()));
				row.createCell(6).setCellValue(obj.getDefault_supplier_cost());
				row.createCell(7).setCellValue(obj.getError_status());
				row.createCell(8).setCellValue(replaceNull(obj.getError_message()));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}



}
