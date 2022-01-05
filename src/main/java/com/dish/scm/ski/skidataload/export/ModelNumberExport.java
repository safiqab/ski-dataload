package com.dish.scm.ski.skidataload.export;

import com.dish.scm.ski.skidataload.model.file.ModelNumberVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;

@Component
public class ModelNumberExport {

    public void writeData(Sheet sheet,List<ModelNumberVM> lstData) {

        int rowCnt =0;
        int colCnt =0;
        try {


            Row row = sheet.createRow(rowCnt++);
            Cell cell = row.createCell(colCnt++);
            cell.setCellValue("MODEL_NUMBER");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MODEL_NAME");
            cell = row.createCell(colCnt++);
            cell.setCellValue("EQUIP_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MODEL_DESC");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MANF_CODE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("OS_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("ADD_LANG_SUPT_FLAG");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRODUCT_WEIGHT");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRODUCT_WEIGHT_UNIT_OF_MEAS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRODUCT_HEIGHT");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRODUCT_WIDTH");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRODUCT_LENGTH");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PRODUCT_SIZE_UNIT_OF_MEAS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("SIM_TYPE1");
            cell = row.createCell(colCnt++);
            cell.setCellValue("SIM_TYPE2");
            cell = row.createCell(colCnt++);
            cell.setCellValue("SIM_FORM1");
            cell = row.createCell(colCnt++);
            cell.setCellValue("SIM_FORM2");
            cell = row.createCell(colCnt++);
            cell.setCellValue("FCC_ID");
            cell = row.createCell(colCnt++);
            cell.setCellValue("SERIAL_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("BYOD");
            cell = row.createCell(colCnt++);
            cell.setCellValue("EQUIP_ID");
            cell = row.createCell(colCnt++);
            cell.setCellValue("EQUIP_SUB_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("EQUIP_SUB_CAT");
            cell = row.createCell(colCnt++);
            cell.setCellValue("STATUS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MESSAGE");

            for (ModelNumberVM obj : lstData) {
                colCnt = 0;
                if (!StringUtils.isEmpty(obj.getModel_number())) {
                    row = sheet.createRow(rowCnt++);
                    row.createCell(colCnt++).setCellValue(obj.getModel_number());
                    row.createCell(colCnt++).setCellValue(obj.getModel_name());
                    row.createCell(colCnt++).setCellValue(obj.getEquip_type());
                    row.createCell(colCnt++).setCellValue(obj.getModel_desc());
                    row.createCell(colCnt++).setCellValue(obj.getManf_code());
                    row.createCell(colCnt++).setCellValue(obj.getOs_type());
                    row.createCell(colCnt++).setCellValue(obj.isAdd_lang_supt_flag() ?"Y":"N");
                    row.createCell(colCnt++).setCellValue(obj.getProductDimensionVM().getProduct_weight());
                    row.createCell(colCnt++).setCellValue(obj.getProductDimensionVM().getProduct_weight_unit_of_meas());
                    row.createCell(colCnt++).setCellValue(obj.getProductDimensionVM().getProduct_height());
                    row.createCell(colCnt++).setCellValue(obj.getProductDimensionVM().getProduct_width());
                    row.createCell(colCnt++).setCellValue(obj.getProductDimensionVM().getProduct_length());
                    row.createCell(colCnt++).setCellValue(obj.getProductDimensionVM().getProduct_size_unit_of_meas());
                    row.createCell(colCnt++).setCellValue(obj.getFcc_id());
                    row.createCell(colCnt++).setCellValue(obj.getSerial_type());
                    row.createCell(colCnt++).setCellValue(obj.getByod());
                    row.createCell(colCnt++).setCellValue(obj.getEquip_id());
                    row.createCell(colCnt++).setCellValue(obj.getEquip_sub_type());
                    row.createCell(colCnt++).setCellValue(obj.getEquip_sub_cat());
                    row.createCell(colCnt++).setCellValue(obj.getError_status());
                    row.createCell(colCnt++).setCellValue(replaceNull(obj.getError_message()));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }




}
