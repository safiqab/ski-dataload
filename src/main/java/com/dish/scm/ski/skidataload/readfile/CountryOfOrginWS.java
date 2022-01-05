package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.CountryOfOrginRefVM;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.dish.scm.ski.skidataload.util.DataUtil.isRowEmpty;

@Component
public class CountryOfOrginWS {
    @Value("{skip.header:Y")
    private String skip_header;

    public List<CountryOfOrginRefVM> readSheet(Sheet worksheet) {

        DataFormatter dataFormatter = new DataFormatter();
        List<CountryOfOrginRefVM> lstData = new ArrayList<>();
        worksheet.iterator().forEachRemaining(x->
        {
            if (!isRowEmpty(x)) {

                CountryOfOrginRefVM obj = new CountryOfOrginRefVM();
                if (skip_header.equals("N")) {
                    boolean hasData = false;
                    Iterator<Cell> colIterator = x.cellIterator();
                    while (colIterator.hasNext()) {

                        hasData = true;
                        Cell nextCol = colIterator.next();

                        obj.setCountry_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(0)));
                        obj.setCountry_name(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)));
                        obj.setCountry_desc(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)));
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
