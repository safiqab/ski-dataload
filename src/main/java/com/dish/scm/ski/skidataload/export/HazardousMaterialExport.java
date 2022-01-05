package com.dish.scm.ski.skidataload.export;

import com.dish.scm.ski.skidataload.model.file.HazardousMaterialVM;
import com.dish.scm.ski.skidataload.model.file.HazardousMaterialVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HazardousMaterialExport {
    public void writeData(Sheet sheet, List<HazardousMaterialVM> lstData) {

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
            cell.setCellValue("HAZMAT_COMMODITY_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("BATTERY_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("BATTERY_FORM");
            cell = row.createCell(colCnt++);
            cell.setCellValue("BATTERY_CELL_COUNT");
            cell = row.createCell(colCnt++);
            cell.setCellValue("BATTERY_PACKAGING");
            cell = row.createCell(colCnt++);
            cell.setCellValue("BATTERY_VOLTAGE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("HAZMAT_UN_NUMBER");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PI_NUMBER");
            cell = row.createCell(colCnt++);
            cell.setCellValue("LI_ION_WATT_HOURS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("TOTAL_BATTERY_KGS");

            for (HazardousMaterialVM obj : lstData) {
                colCnt =0;
                row = sheet.createRow(rowCnt++);
                row.createCell(colCnt++).setCellValue(obj.getModel_number());
                row.createCell(colCnt++).setCellValue(obj.getHazmat_commodity_type());
                row.createCell(colCnt++).setCellValue(obj.getBattery_type());
                row.createCell(colCnt++).setCellValue(obj.getBattery_form());
                row.createCell(colCnt++).setCellValue(obj.getBattery_cell_count());
                row.createCell(colCnt++).setCellValue(obj.getBattery_packaging());
                row.createCell(colCnt++).setCellValue(obj.getBattery_voltage());
                row.createCell(colCnt++).setCellValue(obj.getHazmat_un_number());
                row.createCell(colCnt++).setCellValue(obj.getPi_number());
                row.createCell(colCnt++).setCellValue(obj.getLi_ion_watt_hours());
                row.createCell(colCnt++).setCellValue(obj.getTotal_battery_kgs());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
