package com.dish.scm.ski.skidataload.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataUtil {

    public static boolean isRowEmpty(Row row) {
        boolean isEmpty = true;
        DataFormatter dataFormatter = new DataFormatter();

        if (row != null) {
            for (Cell cell : row) {
                if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
                    isEmpty = false;
                    break;
                }
            }
        }

        return isEmpty;
    }

    public static boolean isColumnEmpty(Cell cell) {

        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell).trim().length() <= 0;
    }

    public static String getScienceNot2String(Double doubleValue) {
        try {
            BigDecimal bd = new BigDecimal(doubleValue.toString());
            long lonVal = bd.longValue();
            return Long.toString(lonVal).trim();
        }
        catch (Exception e) {
            return doubleValue.toString();
        }
    }

    public static long convertDouble2Long(Double doubleValue) {
        if (doubleValue>=0)
            return doubleValue.longValue();
        return 0;
    }

    public static String convertLong2String(Long value) {
        if (value>=0)
            return String.valueOf(value);
        return null;
    }

    public static String convertInt2String(int value) {
        if (value>=0)
            return String.valueOf(value);
        return null;
    }

    public static String convertBool2String(boolean value) {
        if (!StringUtils.isEmpty(value))
            return String.valueOf(value);
        return "false";
    }

    public static String format2Decimal(String value) {
        if (!StringUtils.isEmpty(value)) {
            value = value.replace(",","");
            value = value.replace("$","");
            int dts = value.contains(".") ? value.substring(value.indexOf(".")+1).length() : 0;
            String zeros = "";
            if (dts > 1) {
                for (int i = 0; i < dts; i++) {
                    zeros = zeros + "0";
                }
            }
            else
                zeros = "00";
            DecimalFormat df = new DecimalFormat("#########0." + zeros);
            try {
                return df.format(new Double(value));
            }
            catch (Exception e) {
                return value;
            }
        }
        return value;
    }

    public static String replaceNull(String data) {
        return StringUtils.isEmpty(data)?"":data.replace("null","");
    }

    public static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors)
    {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

        return t ->
        {
            final List<?> keys = Arrays.stream(keyExtractors)
                    .map(ke -> ke.apply(t))
                    .collect(Collectors.toList());

            return seen.putIfAbsent(keys, Boolean.TRUE) == null;
        };
    }

}
