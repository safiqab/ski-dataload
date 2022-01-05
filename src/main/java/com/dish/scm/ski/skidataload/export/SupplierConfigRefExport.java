package com.dish.scm.ski.skidataload.export;

import com.dish.scm.ski.skidataload.model.file.SupplierConfigRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;

@Component
public class SupplierConfigRefExport {

    public void writeData(Sheet sheet, List<SupplierConfigRefVM> lstData) {

        int rowCnt =0;
        int colCnt =0;
        try {

            Row row = sheet.createRow(rowCnt++);

            Cell cell = row.createCell(colCnt++);
            cell.setCellValue("SUPPLIER_CODE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("CONFIG_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("CONFIG_VALUE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("STATUS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MESSAGE");

            for (SupplierConfigRefVM obj : lstData) {

                row = sheet.createRow(rowCnt++);
                row.createCell(0).setCellValue(obj.getSupplier_code());
                row.createCell(1).setCellValue(obj.getConfig_type());
                row.createCell(2).setCellValue(obj.getConfig_value());
                row.createCell(3).setCellValue(obj.getError_status());
                row.createCell(4).setCellValue(replaceNull(obj.getError_message()));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
