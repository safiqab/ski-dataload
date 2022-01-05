package com.dish.scm.ski.skidataload.export;

import com.dish.scm.ski.skidataload.model.file.ProductDefinitionVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;
import static com.dish.scm.ski.skidataload.util.DateUtil.getStringDate;

@Component
public class ProductDefinitionExport {

    public void writeData(Sheet sheet,List<ProductDefinitionVM> lstData) {

        int rowCnt =0;
        int colCnt =0;

        try {

            Row row = sheet.createRow(rowCnt++);

            Cell cell = row.createCell(colCnt++);
            cell.setCellValue("MODEL_NUMBER");
            cell = row.createCell(colCnt++);
            cell.setCellValue("SKU");
            cell = row.createCell(colCnt++);
            cell.setCellValue("EQUIP_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("SKU_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRODUCT_NAME");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRODUCT_DESC");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MARKETING_PRODUCT_NAME");
            cell = row.createCell(colCnt++);
            cell.setCellValue("COLOR");
            cell = row.createCell(colCnt++);
            cell.setCellValue("COLOR_HEX");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MEMORY_CAPACITY");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MEMORY_UNIT_OF_MEAS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("UPC");
            cell = row.createCell(colCnt++);
            cell.setCellValue("CONFIG_CODE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("DEFAULT_VARIANT");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRODUCT_OWNER_ID");
            cell = row.createCell(colCnt++);
            cell.setCellValue("DEFAULT_SRP");
            cell = row.createCell(colCnt++);
            cell.setCellValue("COUNTRY_OF_ORIGIN");
            cell = row.createCell(colCnt++);
            cell.setCellValue("COMPONENT_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRODUCT_VALUE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("DEV_SKU");
            cell = row.createCell(colCnt++);
            cell.setCellValue("COMP_SKU");
            cell = row.createCell(colCnt++);
            cell.setCellValue("BOM_SKU");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PACKAGE_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PACKAGE_WEIGHT");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PACKAGE_WEIGHT_UNIT_OF_MEAS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PACKAGE_HEIGHT");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PACKAGE_WIDTH");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PACKAGE_LENGTH");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PACKAGE_SIZE_UNIT_OF_MEAS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MASTER_CARTON_PRODUCT_QTY");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MASTER_CARTON_WEIGHT");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MASTER_CARTON_WEIGHT_UNIT_OF_MEAS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MASTER_CARTON_HEIGHT");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MASTER_CARTON_WIDTH");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MASTER_CARTON_LENGTH");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MASTER_CARTON_SIZE_UNIT_OF_MEAS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PALLET_LAYER_MASTER_CTN_QTY");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PALLET_LAYER_QTY");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PALLET_PRODUCT_QTY");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PALLET_MASTER_CARTON_QTY");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PALLET_LAYER_PRODUCT_QTY");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRODUCT_UNITS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PLANNED_LAUNCH_DATE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("DO_NOT_ACTIVATE_DATE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRE_INSERTED_SIM");
            cell = row.createCell(colCnt++);
            cell.setCellValue("AVAILABLE_TO_REPORT");
            cell = row.createCell(colCnt++);
            cell.setCellValue("CONSIGNED");
            cell = row.createCell(colCnt++);
            cell.setCellValue("AVAILABLE_TO_AGENT");
            cell = row.createCell(colCnt++);
            cell.setCellValue("AVAILABLE_TO_ORDER");
            cell = row.createCell(colCnt++);
            cell.setCellValue("AVAILABLE_TO_SHIP");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PHYSICAL_INVENTORY");
            cell = row.createCell(colCnt++);
            cell.setCellValue("REQUIRED_RETURN");
            cell = row.createCell(colCnt++);
            cell.setCellValue("RETAIL_INVENTORIED");
            cell = row.createCell(colCnt++);
            cell.setCellValue("RETAIL_AVAILABLE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("ONLINE_VISIBLE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("FINANCE_SKU");
            cell = row.createCell(colCnt++);
            cell.setCellValue("HAZMAT_REQUIRED ");
            cell = row.createCell(colCnt++);
            cell.setCellValue("HTS_CODE_US");
            cell = row.createCell(colCnt++);
            cell.setCellValue("ECCN_CODE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("STATUS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MESSAGE");

            for (ProductDefinitionVM obj : lstData) {
                colCnt =0;
                row = sheet.createRow(rowCnt++);
                /*
                row.createCell(colCnt++).setCellValue(obj.getModel_number());
                row.createCell(colCnt++).setCellValue(obj.getSku());
                row.createCell(colCnt++).setCellValue(obj.getSku_type());
                row.createCell(colCnt++).setCellValue(obj.getProduct_name());
                row.createCell(colCnt++).setCellValue(obj.getProduct_desc());
                row.createCell(colCnt++).setCellValue(obj.getMarketing_product_name());
                row.createCell(colCnt++).setCellValue(obj.getColor());
                row.createCell(colCnt++).setCellValue(obj.getColor_hex());
                row.createCell(colCnt++).setCellValue(obj.getMemory_capacity());
                row.createCell(colCnt++).setCellValue(obj.getMemory_unit_of_meas());
                row.createCell(colCnt++).setCellValue(obj.getUpc());
                row.createCell(colCnt++).setCellValue(obj.getConfig_code());
                row.createCell(colCnt++).setCellValue(obj.getDefault_variant());
                row.createCell(colCnt++).setCellValue(obj.getProduct_owner_id());
                row.createCell(colCnt++).setCellValue(obj.getDefault_srp());
                row.createCell(colCnt++).setCellValue(obj.getCountry_of_orgin());
                row.createCell(colCnt++).setCellValue(obj.getComponent_type());
                row.createCell(colCnt++).setCellValue(obj.getProduct_value());
                row.createCell(colCnt++).setCellValue(obj.getDev_sku());
                row.createCell(colCnt++).setCellValue(obj.getComp_sku());
                row.createCell(colCnt++).setCellValue(obj.getBom_sku());
                row.createCell(colCnt++).setCellValue(obj.getPackage_type());
                row.createCell(colCnt++).setCellValue(obj.getPackage_weight());
                row.createCell(colCnt++).setCellValue(obj.getPackage_weight_unit_of_meas());
                row.createCell(colCnt++).setCellValue(obj.getPackage_height());
                row.createCell(colCnt++).setCellValue(obj.getPackage_width());
                row.createCell(colCnt++).setCellValue(obj.getPackage_length());
                row.createCell(colCnt++).setCellValue(obj.getPackage_size_unit_of_meas());
                row.createCell(colCnt++).setCellValue(obj.getMaster_carton_product_qty());
                row.createCell(colCnt++).setCellValue(obj.getMaster_carton_weight());
                row.createCell(colCnt++).setCellValue(obj.getMaster_carton_weight_unit_of_meas());
                row.createCell(colCnt++).setCellValue(obj.getMaster_carton_height());
                row.createCell(colCnt++).setCellValue(obj.getMaster_carton_width());
                row.createCell(colCnt++).setCellValue(obj.getMaster_carton_length());
                row.createCell(colCnt++).setCellValue(obj.getMaster_carton_size_unit_of_meas());
                row.createCell(colCnt++).setCellValue(obj.getPallet_layer_master_ctn_qty());
                row.createCell(colCnt++).setCellValue(obj.getPallet_layer_qty());

                row.createCell(colCnt++).setCellValue(obj.getPallet_product_qty());
                row.createCell(colCnt++).setCellValue(obj.getPallet_master_carton_qty());
                row.createCell(colCnt++).setCellValue(obj.getPallet_layer_product_qty());
                row.createCell(colCnt++).setCellValue(obj.getProduct_units());
                row.createCell(colCnt++).setCellValue(StringUtils.isEmpty(obj.getPlanned_launch_date())?"":getStringDate(obj.getPlanned_launch_date()) );
                row.createCell(colCnt++).setCellValue(StringUtils.isEmpty(obj.getDo_not_activate_date())?"":getStringDate(obj.getDo_not_activate_date()) );
                row.createCell(colCnt++).setCellValue(obj.isPre_inserted_sim() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.isAvailable_to_report() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.isConsigned() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.isAvailable_to_agent() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.isAvailable_to_order() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.isAvailable_to_ship() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.isPhysical_inventory() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.isRequired_return() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.isRetail_inventoried() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.isRetail_available() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.isOnline_visible() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.getFinance_sku());
                row.createCell(colCnt++).setCellValue(obj.isHazmat_required() ?"Y":"N");
                row.createCell(colCnt++).setCellValue(obj.getHts_code_us());
                row.createCell(colCnt++).setCellValue(obj.getEccn_code());
                row.createCell(colCnt++).setCellValue(obj.getError_status());
                row.createCell(colCnt++).setCellValue(replaceNull(obj.getError_message()));
                */
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}
