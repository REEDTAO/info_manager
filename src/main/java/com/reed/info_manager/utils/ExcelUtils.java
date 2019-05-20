package com.reed.info_manager.utils;

import com.reed.info_manager.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }


    public static List<User> parseFileToUserList(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream in = new FileInputStream(file);
        Workbook workbook = getWorkbok(in,file);
        Sheet sheet = workbook.getSheetAt(0);

        List<User> list = new ArrayList<>();

        for (Row row :sheet){
            User user = new User();
            user.setId(((Number)getValue(row.getCell(0))).intValue());
            user.setName((String) getValue(row.getCell(1)));
            user.setSex((String) getValue(row.getCell(2)));
            user.setAddr((String) getValue(row.getCell(3)));
            user.setEmail((String) getValue(row.getCell(4)));
            user.setTel(transToString(getValue(row.getCell(5))));
            user.setPassword(transToString(getValue(row.getCell(6))));
            list.add(user);
        }
        in.close();
        return list;
    }

    private static Object getValue(Cell cell) {
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }
    private static String transToString(Object object){
        String str = object+"";
        return str.substring(0,str.indexOf('.'));
    }

}
