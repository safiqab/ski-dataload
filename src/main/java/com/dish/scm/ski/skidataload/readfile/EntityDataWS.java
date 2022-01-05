package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.ModelNumVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class EntityDataWS {

    private final ModelNameWorkflowWS modelNameWorkflowWS;


    @Autowired
    public EntityDataWS(ModelNameWorkflowWS modelNameWorkflowWS) {
        this.modelNameWorkflowWS = modelNameWorkflowWS;

    }

    public List<ModelNumVM> readData(String fileName, String tabname) {

        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        List<ModelNumVM> lstData = new ArrayList<>();
        try {

            fis = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(fis);

            for (Sheet sheet : workbook) {

                if (sheet.getSheetName().equalsIgnoreCase(tabname.toLowerCase())) {
                    lstData.addAll(modelNameWorkflowWS.readSheet(sheet));
                }
            }
            workbook.close();
            fis.close();

        }
        catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        finally {
            if (workbook != null)
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
        }
        return lstData;
    }

}
