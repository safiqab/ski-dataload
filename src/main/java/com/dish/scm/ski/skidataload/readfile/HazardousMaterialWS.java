package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.model.file.HazardousMaterialVM;
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
public class HazardousMaterialWS {
    @Value("{skip.header:Y")
    private String skip_header;

    public List<HazardousMaterialVM> readSheet(Sheet worksheet) {

        DataFormatter dataFormatter = new DataFormatter();
        List<HazardousMaterialVM> lstData = new ArrayList<>();
        worksheet.iterator().forEachRemaining(x->
        {
            if (!isRowEmpty(x)) {

                HazardousMaterialVM obj = new HazardousMaterialVM();
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

                        obj.setHts_code_us(dataFormatter.formatCellValue(nextCol.getRow().getCell(1)).trim());
                        obj.setEccn_code(dataFormatter.formatCellValue(nextCol.getRow().getCell(2)).trim());
                        obj.setHazmat_commodity_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(3)));
                        obj.setBattery_type(dataFormatter.formatCellValue(nextCol.getRow().getCell(4)));
                        obj.setBattery_form(dataFormatter.formatCellValue(nextCol.getRow().getCell(5)));
                        if (!StringUtils.isEmpty(nextCol.getRow().getCell(6))) {
                            if (nextCol.getRow().getCell(6).getCellType().equals(CellType.NUMERIC)) {
                                obj.setBattery_cell_count(convertDouble2Long(nextCol.getRow().getCell(6).getNumericCellValue()));
                            }
                            else {
                                obj.setBattery_cell_count(Integer.parseInt(dataFormatter.formatCellValue(nextCol.getRow().getCell(6)).trim()));
                            }
                        }

                        obj.setBattery_packaging(dataFormatter.formatCellValue(nextCol.getRow().getCell(7)));
                        if (!StringUtils.isEmpty(nextCol.getRow().getCell(8))) {
                            if (nextCol.getRow().getCell(8).getCellType().equals(CellType.NUMERIC)) {
                                obj.setBattery_voltage(convertDouble2Long(nextCol.getRow().getCell(8).getNumericCellValue()));
                            }
                            else {
                                obj.setBattery_voltage(Integer.parseInt(dataFormatter.formatCellValue(nextCol.getRow().getCell(8)).trim()));
                            }
                        }

                        if (!StringUtils.isEmpty(nextCol.getRow().getCell(9))) {
                            if (nextCol.getRow().getCell(9).getCellType().equals(CellType.NUMERIC)) {
                                obj.setHazmat_un_number(convertDouble2Long(nextCol.getRow().getCell(9).getNumericCellValue()));
                            }
                            else {
                                obj.setHazmat_un_number(Integer.parseInt(dataFormatter.formatCellValue(nextCol.getRow().getCell(9)).trim()));
                            }
                        }

                        if (!StringUtils.isEmpty(nextCol.getRow().getCell(10))) {
                            if (nextCol.getRow().getCell(10).getCellType().equals(CellType.NUMERIC)) {
                                obj.setPi_number(convertDouble2Long(nextCol.getRow().getCell(10).getNumericCellValue()));
                            }
                            else {
                                obj.setPi_number(Integer.parseInt(dataFormatter.formatCellValue(nextCol.getRow().getCell(10)).trim()));
                            }
                        }

                        if (!StringUtils.isEmpty(nextCol.getRow().getCell(11))) {
                            if (nextCol.getRow().getCell(11).getCellType().equals(CellType.NUMERIC)) {
                                obj.setLi_ion_watt_hours(convertDouble2Long(nextCol.getRow().getCell(11).getNumericCellValue()));
                            }
                            else {
                                obj.setLi_ion_watt_hours(Integer.parseInt(dataFormatter.formatCellValue(nextCol.getRow().getCell(11)).trim()));
                            }
                        }

                        if (!StringUtils.isEmpty(nextCol.getRow().getCell(12))) {
                            if (nextCol.getRow().getCell(12).getCellType().equals(CellType.NUMERIC)) {
                                obj.setTotal_battery_kgs(convertDouble2Long(nextCol.getRow().getCell(12).getNumericCellValue()));
                            }
                            else {
                                obj.setTotal_battery_kgs(Integer.parseInt(dataFormatter.formatCellValue(nextCol.getRow().getCell(12)).trim()));
                            }
                        }

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
