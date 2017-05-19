/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-10
 * Project        : desultory-essay
 * File Name      : App.java
 */
public class App
{
    private static final String DESKTOP = "/home/lilzhang/Desktop/";

    private static final String ORIGIN_DATA = DESKTOP + "product_abb.xlsx";

    private static final String NEED_UPDATE = DESKTOP + "ABB-FA2017.xls";


    private static final String SQL = DESKTOP + "update_abb_listprice_20170216_3.sql";

    private static final String NOT_MATCH = DESKTOP + "abb_not_match_3.tsv";

    private static final String SQL_TEMP = "UPDATE product SET ListPrice = '%s' WHERE Id = %d AND BrandId = 9;";

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private static final DecimalFormat dfn = new DecimalFormat("#");

    // [^0-9a-zA-Z]

    private static final Map<String, String> notFiltedPCodeMap = new HashMap<String, String>();
    private static final Map<String, String> notFiltedSNMap = new HashMap<String, String>();

    public static void main(String[] args)
    {
        File sqlFile = new File(SQL);
        File notMatchFile = new File(NOT_MATCH);

        Map<String, Integer> originPcodePidMap = getOriginPcodePidMap();
        Map<String, Integer> originSNPidMap = getOriginSNPidMap();

        Map<String, String> updatePCodeLPriceMap = getUpdatePCodeLPriceMap();
        Map<String, String> updateSNLPriceMap = getUpdateSNLPriceMap();
        Map<String, String> updateSNPCodeMap = getUpdateSNPCodeMap();

        List<String> sqlList = new ArrayList<String>();
        List<String> notMatchList = new ArrayList<String>();

        for (Map.Entry<String, String> snEntry : updateSNLPriceMap.entrySet())
        {
            String serialNumber = snEntry.getKey();
            String listPrice = snEntry.getValue();

            Integer id = originSNPidMap.get(serialNumber);
            if (id != null)
            {
                sqlList.add(String.format(SQL_TEMP, listPrice, id.intValue()));
            }
            else
            {
                String productCode = updateSNPCodeMap.get(serialNumber);
                Integer id2 = originPcodePidMap.get(productCode);
                if (id2 != null)
                {
                    sqlList.add(String.format(SQL_TEMP, listPrice, id2.intValue()));
                }
                else
                {
                    String notFiltedPCode = notFiltedPCodeMap.get(productCode) == null ? "" : notFiltedPCodeMap.get(productCode);
                    String notFiltedSNumber = notFiltedSNMap.get(serialNumber) == null ? "" : notFiltedSNMap.get(serialNumber);
//                    String lprice = updatePCodeLPriceMap.get(productCode) == null ? "" : updatePCodeLPriceMap.get(productCode);
                    notMatchList.add(notFiltedPCode + "\t" + notFiltedSNumber + "\t" + listPrice + "\r");
                }
            }
        }

        System.out.println();

        try
        {
            FileUtils.writeLines(sqlFile, sqlList);
            FileUtils.writeLines(notMatchFile, notMatchList);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> getOriginPcodePidMap()
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        InputStream in = null;
        try
        {
            in = new FileInputStream(ORIGIN_DATA);
            Workbook origin = new XSSFWorkbook(in);
            Sheet sheet = origin.getSheetAt(0);

            Row row;
            Cell cell;

            for (int i = 0; i <= sheet.getLastRowNum(); i++)
            {
                Integer id = null;
                String productCode = null;
                row = sheet.getRow(i);
                cell = row.getCell(2);
                if (cell != null)
                {
                    id = ((int) cell.getNumericCellValue());
                }

                cell = row.getCell(0);
                if (cell != null)
                {
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            double numericCellValue = cell.getNumericCellValue();
                            productCode = dfn.format(numericCellValue);
                            break;
                        case Cell.CELL_TYPE_STRING:
                            productCode = cell.getStringCellValue().replaceAll("[^0-9a-zA-Z]", "");
                            break;
                    }
                }

                if (id != null && productCode != null)
                {
                    map.put(productCode, id);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }
        return map;
    }

    private static Map<String, Integer> getOriginSNPidMap()
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        InputStream in = null;
        try
        {
            in = new FileInputStream(ORIGIN_DATA);
            Workbook origin = new XSSFWorkbook(in);
            Sheet sheet = origin.getSheetAt(0);

            Row row;
            Cell cell;

            for (int i = 0; i <= sheet.getLastRowNum(); i++)
            {
                Integer id = null;
                String serialNumber = null;
                row = sheet.getRow(i);
                cell = row.getCell(2);
                if (cell != null)
                {
                    id = ((int) cell.getNumericCellValue());
                }

                cell = row.getCell(1);
                if (cell != null)
                {
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            double numericCellValue = cell.getNumericCellValue();
                            serialNumber = dfn.format(numericCellValue);
                            break;
                        case Cell.CELL_TYPE_STRING:
                            serialNumber = cell.getStringCellValue().replaceAll("[^0-9a-zA-Z]", "");
                            break;
                    }
                }

                if (id != null && serialNumber != null)
                {
                    map.put(serialNumber, id);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }
        return map;
    }

    private static Map<String, String> getUpdatePCodeLPriceMap()
    {
        Map<String, String> map = new LinkedHashMap<String, String>();
        InputStream in = null;
        try
        {
            in = new FileInputStream(NEED_UPDATE);
            Workbook origin = new HSSFWorkbook(in);
            Sheet sheet = origin.getSheetAt(0);

            Row row;
            Cell cell;

            for (int i = 1; i <= sheet.getLastRowNum(); i++)
            {
                String productCode = null;
                String listPrice = null;
                row = sheet.getRow(i);

                cell = row.getCell(0);
                if (cell != null)
                {
                    String notFiltedProductCode = "";
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_STRING:
                            notFiltedProductCode = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            notFiltedProductCode = dfn.format(cell.getNumericCellValue());
                            break;
                    }
                    productCode = notFiltedProductCode.replaceAll("[^0-9a-zA-Z]", "");

                    notFiltedPCodeMap.put(productCode, notFiltedProductCode);
                }

                cell = row.getCell(2);
                if (cell != null)
                {
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_STRING:
                            String stringCellValue = cell.getStringCellValue();
                            try
                            {
                                Double price = ((Double) df.parse(stringCellValue));
                            }
                            catch (ParseException e)
                            {
                                System.out.println("parse exception: " + i);
                                e.printStackTrace();
                            }
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            listPrice = df.format(cell.getNumericCellValue());
                            break;
                    }
                }

                if (listPrice != null && productCode != null)
                {
                    map.put(productCode, listPrice);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }
        return map;
    }

    private static Map<String, String> getUpdateSNLPriceMap()
    {
        Map<String, String> map = new LinkedHashMap<String, String>();
        InputStream in = null;
        try
        {
            in = new FileInputStream(NEED_UPDATE);
            Workbook origin = new HSSFWorkbook(in);
            Sheet sheet = origin.getSheetAt(0);

            Row row;
            Cell cell;

            for (int i = 1; i <= sheet.getLastRowNum(); i++)
            {
                String serialNumber = null;
                String listPrice = null;
                row = sheet.getRow(i);

                cell = row.getCell(1);
                if (cell != null)
                {
                    String notFiltedSerialNumber = "";
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_STRING:
                            notFiltedSerialNumber = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            notFiltedSerialNumber = dfn.format(cell.getNumericCellValue());
                            break;
                    }
                    serialNumber = notFiltedSerialNumber.replaceAll("[^0-9a-zA-Z]", "");

                    notFiltedSNMap.put(serialNumber, notFiltedSerialNumber);
                }

                cell = row.getCell(2);
                if (cell != null)
                {
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_STRING:
                            String stringCellValue = cell.getStringCellValue();
                            try
                            {
                                Double price = ((Double) df.parse(stringCellValue));
                            }
                            catch (ParseException e)
                            {
                                System.out.println("parse exception: " + i);
                                e.printStackTrace();
                            }
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            listPrice = df.format(cell.getNumericCellValue());
                            break;
                    }
                }

                if (listPrice != null && serialNumber != null)
                {
                    map.put(serialNumber, listPrice);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }
        return map;
    }

    private static Map<String, String> getUpdateSNPCodeMap()
    {
        Map<String, String> map = new LinkedHashMap<String, String>();
        InputStream in = null;
        try
        {
            in = new FileInputStream(NEED_UPDATE);
            Workbook origin = new HSSFWorkbook(in);
            Sheet sheet = origin.getSheetAt(0);

            Row row;
            Cell cell;

            for (int i = 1; i <= sheet.getLastRowNum(); i++)
            {
                String serialNumber = null;
                String productCode = null;
                row = sheet.getRow(i);

                cell = row.getCell(1);
                if (cell != null)
                {
                    String notFiltedSerialNumber = "";
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_STRING:
                            notFiltedSerialNumber = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            notFiltedSerialNumber = dfn.format(cell.getNumericCellValue());
                            break;
                    }
                    serialNumber = notFiltedSerialNumber.replaceAll("[^0-9a-zA-Z]", "");

                }

                cell = row.getCell(0);
                if (cell != null)
                {
                    String notFiltedProductCode = "";
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_STRING:
                            notFiltedProductCode = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            notFiltedProductCode = dfn.format(cell.getNumericCellValue());
                            break;
                    }
                    productCode = notFiltedProductCode.replaceAll("[^0-9a-zA-Z]", "");

                }

                if (productCode != null && serialNumber != null)
                {
                    map.put(serialNumber, productCode);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }
        return map;
    }
}