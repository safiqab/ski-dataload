package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.SimConfigVM;
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

import static com.dish.scm.ski.skidataload.util.DataUtil.*;

@Component
public class SimConfigWS {
    @Value("{skip.header:Y")
    private String skip_header;

    public List<SimConfigVM> readSheet(Sheet worksheet) {

        DataFormatter dataFormatter = new DataFormatter();
        List<SimConfigVM> lstData = new ArrayList<>();
        worksheet.iterator().forEachRemaining(x->
        {
            if (!isRowEmpty(x)) {

                SimConfigVM obj = new SimConfigVM();
                if (skip_header.equals("N")) {
                    boolean hasData = false;
                    Iterator<Cell> colIterator = x.cellIterator();
                    while (colIterator.hasNext()) {

                        hasData = true;
                        Cell nextCol = colIterator.next();
                        if (!StringUtils.isEmpty(nextCol.getRow().getCell(0))) {
                            if (nextCol.getRow().getCell(0).getCellType().equals(CellType.NUMERIC)) {
                                obj.setModel_number(getScienceNot2String(nextCol.getRow().getCell(0).getNumericCellValue()));
                            }
                            else {
                                obj.setModel_number(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)).trim());
                            }
                        }
                        if (!StringUtils.isEmpty(nextCol.getRow().getCell(1))) {
                            if (nextCol.getRow().getCell(1).getCellType().equals(CellType.NUMERIC)) {
                                obj.setSku(getScienceNot2String(nextCol.getRow().getCell(1).getNumericCellValue()));
                            }
                            else {
                                obj.setSku(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)).trim());
                            }
                        }

                        obj.setSupplier_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)).trim());
                        obj.setConfig_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(3)).trim());
                        obj.setConfig_value(format2Decimal(dataFormatter.formatCellValue(nextCol.getRow().getCell(4))));

                        break;
                    }
                    if (hasData)
                        lstData.add(obj);
                }
                skip_header = "N";

            }

        });

        return lstData;
    }
}
