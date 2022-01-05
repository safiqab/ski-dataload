package com.dish.scm.ski.skidataload.export;

import com.dish.scm.ski.skidataload.model.file.SkuPartOwnerVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;

@Component
public class SkuPartOwnerExport {
    public void writeData(Sheet sheet, List<SkuPartOwnerVM> lstData) {

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
            cell.setCellValue("PART_OWNER");
            cell = row.createCell(colCnt++);
            cell.setCellValue("PART_NUMBER");
            cell = row.createCell(colCnt++);
            cell.setCellValue("STATUS");
            cell = row.createCell(colCnt);
            cell.setCellValue("MESSAGE");

            for (SkuPartOwnerVM obj : lstData) {

                row = sheet.createRow(rowCnt++);
                row.createCell(0).setCellValue(obj.getModel_number());
                row.createCell(1).setCellValue(obj.getSku());
                row.createCell(2).setCellValue(obj.getPart_owner());
                row.createCell(3).setCellValue(obj.getPart_number());
                row.createCell(4).setCellValue(obj.getError_status());
                row.createCell(5).setCellValue(replaceNull(obj.getError_message()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
