package com.dish.scm.ski.skidataload.readfile;


import com.dish.scm.ski.skidataload.model.file.ProductDefinitionVM;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductDefinitionWS {
	
	@Value("{skip.header:Y")
	private String skip_header;

	public List<ProductDefinitionVM> readSheet(Sheet worksheet) {
	
		DataFormatter dataFormatter = new DataFormatter();
		List<ProductDefinitionVM> lstData = new ArrayList<>();

		worksheet.iterator().forEachRemaining(x->
		{						
			if (!isRowEmpty(x)) {
				
				ProductDefinitionVM obj = new ProductDefinitionVM();
				if (skip_header.equals("N")) {
					boolean hasData = false;
					Iterator<Cell> colIterator = x.cellIterator();	
					 while (colIterator.hasNext()) {		
						 //int col = 0;
						 hasData = true;
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
								 obj.setSku(getScienceNot2String(nextCol.getRow().getCell(1).getNumericCellValue()));
							 }
							 else {
								 obj.setSku(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)).trim());
							 }
						 }
						 obj.setProduct_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)).trim());
						 obj.setProduct_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(3)).trim());
						 obj.setMarketing_product_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(4)));
						 obj.setSku_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(5)).trim());
						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(6))) {
							 if (nextCol.getRow().getCell(6).getCellType().equals(CellType.NUMERIC)) {
								 obj.setParent_sku(getScienceNot2String(nextCol.getRow().getCell(6).getNumericCellValue()));
							 }
							 else {
								 obj.setParent_sku(dataFormatter.formatCellValue(nextCol.getRow().getCell(6)).trim());
							 }
						 }
						 obj.setColor(dataFormatter.formatCellValue(nextCol.getRow().getCell(7)).trim());
						 obj.setColor_hex(dataFormatter.formatCellValue(nextCol.getRow().getCell(8)).trim());
						 obj.setMemory_capacity(dataFormatter.formatCellValue(nextCol.getRow().getCell(9)).trim());
						 obj.setMemory_unit_of_meas(dataFormatter.formatCellValue(nextCol.getRow().getCell(10)).trim());
						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(11))) {
							 if (nextCol.getRow().getCell(11).getCellType().equals(CellType.NUMERIC)) {
								 obj.setUpc(getScienceNot2String(nextCol.getRow().getCell(11).getNumericCellValue()));
							 }
							 else {
								 obj.setUpc(dataFormatter.formatCellValue(nextCol.getRow().getCell(11)).trim());
							 }
						 }
						 obj.setSim_type1(dataFormatter.formatCellValue(nextCol.getRow().getCell(12)));
						 obj.setSim_type2(dataFormatter.formatCellValue(nextCol.getRow().getCell(13)));
						 obj.setSim_form1(dataFormatter.formatCellValue(nextCol.getRow().getCell(14)));
						 obj.setSim_form2(dataFormatter.formatCellValue(nextCol.getRow().getCell(15)));
						 obj.setPre_inserted_sim(dataFormatter.formatCellValue(nextCol.getRow().getCell(16)).equalsIgnoreCase("y"));
						 obj.setConfig_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(17)));

						 obj.setAvailable_to_activate(dataFormatter.formatCellValue(nextCol.getRow().getCell(18)).equalsIgnoreCase("y"));
						 obj.setOpen_market(dataFormatter.formatCellValue(nextCol.getRow().getCell(19)).equalsIgnoreCase("y"));
						 obj.setOnline_visible(dataFormatter.formatCellValue(nextCol.getRow().getCell(20)).equalsIgnoreCase("y"));
						 obj.setRequired_return(dataFormatter.formatCellValue(nextCol.getRow().getCell(21)).equalsIgnoreCase("y"));
						 try {
							 obj.setPlanned_launch_date(convertStringToDate(dataFormatter.formatCellValue(nextCol.getRow().getCell(22))));
						 }
						 catch (ParseException e) {
							 obj.setError_status("Fail");
							 obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid planned launch date.":obj.getError_message()+"Invalid planned launch date.");
						 }

						 try {
							 obj.setDo_not_activate_date(convertStringToDate(dataFormatter.formatCellValue(nextCol.getRow().getCell(23))));
						 }
						 catch (ParseException e) {
							 obj.setError_status("Fail");
							 obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid Do not activate date.":obj.getError_message()+"Invalid Do not activate date.");
						 }
						 obj.setAvailable_to_order(dataFormatter.formatCellValue(nextCol.getRow().getCell(24)).equalsIgnoreCase("y"));
						 obj.setAvailable_to_agent(dataFormatter.formatCellValue(nextCol.getRow().getCell(25)).equalsIgnoreCase("y"));
						 obj.setRetail_available(dataFormatter.formatCellValue(nextCol.getRow().getCell(26)).equalsIgnoreCase("y"));
						 if (!StringUtils.isEmpty(nextCol.getRow().getCell(27))) {
							 if (nextCol.getRow().getCell(27).getCellType().equals(CellType.NUMERIC)) {
								 obj.setBom_sku(getScienceNot2String(nextCol.getRow().getCell(27).getNumericCellValue()));
							 }
							 else {
								 obj.setBom_sku(dataFormatter.formatCellValue(nextCol.getRow().getCell(27)).trim());
							 }
						 }
						 obj.setFinance_sku(dataFormatter.formatCellValue(nextCol.getRow().getCell(28)).trim());
						 obj.setRetail_inventoried(dataFormatter.formatCellValue(nextCol.getRow().getCell(29)).equalsIgnoreCase("y"));
						 obj.setConsigned(dataFormatter.formatCellValue(nextCol.getRow().getCell(30)).equalsIgnoreCase("y"));
						 obj.setAvailable_to_report(dataFormatter.formatCellValue(nextCol.getRow().getCell(31)).equalsIgnoreCase("y"));
						 obj.setProduct_owner_id(dataFormatter.formatCellValue(nextCol.getRow().getCell(32)).trim());
						 obj.setCountry_of_orgin(dataFormatter.formatCellValue(nextCol.getRow().getCell(33)).trim());
						 obj.setDefault_srp(dataFormatter.formatCellValue(nextCol.getRow().getCell(34)).trim());
						 obj.setProduct_value(dataFormatter.formatCellValue(nextCol.getRow().getCell(35)).trim());
						 obj.setAvailable_to_ship(dataFormatter.formatCellValue(nextCol.getRow().getCell(36)).equalsIgnoreCase("y"));
						 try {
							 obj.setProduct_units(isColumnEmpty(nextCol.getRow().getCell(37))?0:Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(37))));

						 }
						 catch(NumberFormatException ne) {
							 obj.setError_status("Fail");
							 obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid product units.":obj.getError_message()+"Invalid product units");

						 }
						 try {
							 obj.setPallet_product_qty(isColumnEmpty(nextCol.getRow().getCell(38))?0:Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(38))));

						 }
						 catch(NumberFormatException ne) {
							 obj.setError_status("Fail");
							 obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid pallet product quantity.":obj.getError_message()+"Invalid pallet product quantity.");

						 }
						 try {
							 obj.setPallet_layer_qty(isColumnEmpty(nextCol.getRow().getCell(39))?0:Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(39))));

						 }
						 catch(NumberFormatException ne) {
							 obj.setError_status("Fail");
							 obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid pallet layer quantity.":obj.getError_message()+"Invalid pallet layer quantity.");
						 }
						 try {
							 obj.setPallet_layer_master_ctn_qty(isColumnEmpty(nextCol.getRow().getCell(40))?0:Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(40))));
						 }
						 catch(NumberFormatException ne) {
							 obj.setError_status("Fail");
							 obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid pallet layer master quantity.":obj.getError_message()+"Invalid pallet layer master quantity.");

						 }
						 try {
							 obj.setPallet_layer_product_qty(isColumnEmpty(nextCol.getRow().getCell(41))?0:Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(41))));

						 }
						 catch(NumberFormatException ne) {
							 obj.setError_status("Fail");
							 obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid pallet layer product quantity.":obj.getError_message()+"Invalid pallet layer product quantity.");

						 }

						 try {
							 obj.setPallet_master_carton_qty(isColumnEmpty(nextCol.getRow().getCell(42))?0:Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(42))));

						 }
						 catch(NumberFormatException ne) {
							 obj.setError_status("Fail");
							 obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid pallet master carton quantity.":obj.getError_message()+"Invalid pallet master carton quantity.");

						 }
						 obj.setMaster_carton_length(dataFormatter.formatCellValue(nextCol.getRow().getCell(43)).trim());
						 obj.setMaster_carton_width(dataFormatter.formatCellValue(nextCol.getRow().getCell(44)).trim());
						 obj.setMaster_carton_height(dataFormatter.formatCellValue(nextCol.getRow().getCell(45)).trim());
						 obj.setMaster_carton_size_unit_of_meas(dataFormatter.formatCellValue(nextCol.getRow().getCell(46)).trim());
						 obj.setMaster_carton_weight(dataFormatter.formatCellValue(nextCol.getRow().getCell(47)).trim());
						 obj.setMaster_carton_weight_unit_of_meas(dataFormatter.formatCellValue(nextCol.getRow().getCell(48)).trim());
						 try {
							 obj.setMaster_carton_product_qty(isColumnEmpty(nextCol.getRow().getCell(49))?0:Long.parseLong(dataFormatter.formatCellValue(nextCol.getRow().getCell(49))));

						 }
						 catch(NumberFormatException ne) {
							 obj.setError_status("Fail");
							 obj.setError_message( StringUtils.isEmpty(obj.getError_message())? "Invalid master carton product quantity.":obj.getError_message()+"Invalid master carton product quantity.");
						 }
						 obj.setPackage_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(50)).trim());
						 obj.setPackage_length(dataFormatter.formatCellValue(nextCol.getRow().getCell(51)).trim());
						 obj.setPackage_width(dataFormatter.formatCellValue(nextCol.getRow().getCell(52)).trim());
						 obj.setPackage_height(dataFormatter.formatCellValue(nextCol.getRow().getCell(53)).trim());
						 obj.setPackage_size_unit_of_meas(dataFormatter.formatCellValue(nextCol.getRow().getCell(54)).trim());
						 obj.setPackage_weight(dataFormatter.formatCellValue(nextCol.getRow().getCell(55)).trim());
						 obj.setPackage_weight_unit_of_meas(dataFormatter.formatCellValue(nextCol.getRow().getCell(56)).trim());

						break;
					 }

					 if (hasData) {

						 if (StringUtils.isEmpty(obj.isPre_inserted_sim()))
							 obj.setPre_inserted_sim(false);
						 if (StringUtils.isEmpty(obj.isRequired_return()))
							 obj.setRequired_return(false);
						 if (StringUtils.isEmpty(obj.isConsigned()))
							 obj.setConsigned(false);
						 if (StringUtils.isEmpty(obj.isAvailable_to_agent()))
							 obj.setAvailable_to_agent(false);
						 if (StringUtils.isEmpty(obj.isAvailable_to_order()))
							 obj.setAvailable_to_order(false);
						 if (StringUtils.isEmpty(obj.isAvailable_to_ship()))
							 obj.setAvailable_to_ship(false);
						 if (StringUtils.isEmpty(obj.isAvailable_to_report()))
							 obj.setAvailable_to_report(false);
						 if (StringUtils.isEmpty(obj.isRetail_inventoried()))
							 obj.setRetail_inventoried(false);
						 if (StringUtils.isEmpty(obj.isRetail_available()))
							 obj.setRetail_available(false);
						 if (StringUtils.isEmpty(obj.isOnline_visible()))
							 obj.setOnline_visible(false);
						 if (StringUtils.isEmpty(obj.isAvailable_to_activate()))
							 obj.setAvailable_to_activate(false);
						 if (StringUtils.isEmpty(obj.isOpen_market()))
							 obj.setOpen_market(false);
						lstData.add(obj);										
					 }
				}
				skip_header = "N";
			}
			
		});
		return lstData;
	}


}
