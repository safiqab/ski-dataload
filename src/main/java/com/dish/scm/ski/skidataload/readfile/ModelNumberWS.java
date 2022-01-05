package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.ModelNumberVM;
import com.dish.scm.ski.skidataload.model.file.ProductDimensionVM;
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

import static com.dish.scm.ski.skidataload.util.DataUtil.getScienceNot2String;
import static com.dish.scm.ski.skidataload.util.DataUtil.isRowEmpty;

@Component
public class ModelNumberWS {
	
	@Value("{skip.header:Y")
	private String skip_header;

	public List<ModelNumberVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<ModelNumberVM> lstData = new ArrayList<>();
		worksheet.iterator().forEachRemaining(x->
		{	
			if (!isRowEmpty(x)) {
			
				ModelNumberVM obj = new ModelNumberVM();
				ProductDimensionVM pdim = null;
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();				
					 while (colIterator.hasNext()) {		
						 int col = 0;

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

						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(1))) {
							 if (nextCol.getRow().getCell(1).getCellType().equals(CellType.NUMERIC)) {
								 obj.setModel_name(getScienceNot2String(nextCol.getRow().getCell(1).getNumericCellValue()));
							 }
							 else {
								 obj.setModel_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)).trim());
							 }
						 }
						 obj.setModel_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)).trim());
						 obj.setManf_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(3)).trim());
						 obj.setOs_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(4)).trim());
						 obj.setSerial_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(5)).trim());
						 obj.setAdd_lang_supt_flag(dataFormatter.formatCellValue(nextCol.getRow().getCell(6)).equalsIgnoreCase("y"));
						 obj.setFcc_id(dataFormatter.formatCellValue(nextCol.getRow().getCell(7)));
						 obj.setByod(dataFormatter.formatCellValue(nextCol.getRow().getCell(8)).trim());
						 obj.setEquip_id(dataFormatter.formatCellValue(nextCol.getRow().getCell(9)).trim());
						 obj.setEquip_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(10)).trim());
						 obj.setEquip_sub_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(11)).trim());
						 obj.setEquip_sub_cat(dataFormatter.formatCellValue(nextCol.getRow().getCell(12)).trim());
						 obj.setHazmat_required(dataFormatter.formatCellValue(nextCol.getRow().getCell(13)).equalsIgnoreCase("y"));

						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(14)) ||
							 !StringUtils.isEmpty(nextCol.getRow().getCell(15)) ||
							 !StringUtils.isEmpty(nextCol.getRow().getCell(16)) ||
							 !StringUtils.isEmpty(nextCol.getRow().getCell(17)) ||
							 !StringUtils.isEmpty(nextCol.getRow().getCell(18)) ||
							 !StringUtils.isEmpty(nextCol.getRow().getCell(19)) ) {
							obj.setProductDimensionVM(
								ProductDimensionVM.builder().product_weight(dataFormatter.formatCellValue(nextCol.getRow().getCell(14)).trim())
								.product_weight_unit_of_meas(dataFormatter.formatCellValue(nextCol.getRow().getCell(15)).trim())
								.product_height(dataFormatter.formatCellValue(nextCol.getRow().getCell(16)).trim())
								.product_width(dataFormatter.formatCellValue(nextCol.getRow().getCell(17)).trim())
								.product_length(dataFormatter.formatCellValue(nextCol.getRow().getCell(18)).trim())
								.product_size_unit_of_meas(dataFormatter.formatCellValue(nextCol.getRow().getCell(19)).trim())
								.build()
							);
						 }
						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(20))) {
							obj.setNetworkCode(dataFormatter.formatCellValue(nextCol.getRow().getCell(20)).trim().split(","));
						 }

						 break;
					 }
					 if (hasData) {						 
						 if (StringUtils.isEmpty(obj.isAdd_lang_supt_flag()))
							 obj.setAdd_lang_supt_flag(false);

						 if (StringUtils.isEmpty(obj.isHazmat_required()))
							 obj.setHazmat_required(false);
						 lstData.add(obj);
					 }					 				
				}			
				skip_header = "N";
			}
				
		});
		
		return lstData;
	}

}
