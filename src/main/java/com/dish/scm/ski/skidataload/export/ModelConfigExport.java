package com.dish.scm.ski.skidataload.export;

import com.dish.scm.ski.skidataload.model.file.ModelConfigVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;

@Component
public class ModelConfigExport {
    public void writeData(Sheet sheet, List<ModelConfigVM> lstData) {

        int rowCnt =0;
        int colCnt =0;
        try {

            Row row = sheet.createRow(rowCnt++);

            Cell cell = row.createCell(colCnt++);
            cell.setCellValue("MODEL_NUMBER");
            cell = row.createCell(colCnt++);
            cell.setCellValue("EQUIP_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("CONFIG_TYPE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("CONFIG_VALUE");
            cell = row.createCell(colCnt++);
            cell.setCellValue("STATUS");
            cell = row.createCell(colCnt++);
            cell.setCellValue("MESSAGE");

            for (ModelConfigVM obj : lstData) {
                colCnt = 0;
                row = sheet.createRow(rowCnt++);
                row.createCell(colCnt++).setCellValue(obj.getModel_number());
                row.createCell(colCnt++).setCellValue(obj.getConfigType());
                row.createCell(colCnt++).setCellValue(obj.getConfigValue());
                row.createCell(colCnt++).setCellValue(obj.getError_status());
                row.createCell(colCnt++).setCellValue(replaceNull(obj.getError_message()));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
