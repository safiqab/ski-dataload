package com.dish.scm.ski.skidataload.export;

import com.dish.scm.ski.skidataload.model.file.ModelNumVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.replaceNull;


@Component
@Slf4j
public class ModelNameExport {
    public void writeData(Sheet sheet, List<ModelNumVM> lstData) {

        int rowCnt =0;
        int colCnt =0;
        try {
            Row row = sheet.createRow(rowCnt++);
            Cell cell = row.createCell(colCnt++);
            cell.setCellValue("MODEL NUMBER");
            cell.setCellValue("STATUS");
            cell.setCellValue("MESSAGE");


            for (ModelNumVM obj : lstData) {

                row = sheet.createRow(rowCnt++);
                row.createCell(0).setCellValue(obj.getModelNumber());
                row.createCell(1).setCellValue(obj.getError_status());
                row.createCell(2).setCellValue(replaceNull(obj.getError_message()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
