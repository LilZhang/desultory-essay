/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-24
 * Project        : desultory-essay
 * File Name      : AppXls.java
 */
public class AppXls
{
    private static final String IN_URL = "/home/lilzhang/Desktop/need_compact.xlsx";

    private static final String OUT_URL = "/home/lilzhang/Desktop/need_compact_compacted_20170224.xlsx";

    public static void main(String[] args)
    {
        InputStream in = null;
        OutputStream out = null;
        try
        {
            in = new FileInputStream(IN_URL);
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);
            Row row;
            Cell cell;
            for (int i = 0; i <= sheet.getLastRowNum(); i++)
            {
                row = sheet.getRow(i);
                if (row != null)
                {
                    cell = row.getCell(0);
                    if (cell != null)
                    {
                        String notSplited = cell.getStringCellValue();
                        String[] split = notSplited.split(" ");

                        for (int i1 = 0; i1 < split.length; i1++)
                        {
                            cell = row.createCell(i1 + 1);
                            String s = split[i1];
                            cell.setCellValue(s.trim());
                        }
                    }
                }
            }

            out = new FileOutputStream(OUT_URL);
            workbook.write(out);
            out.flush();
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

            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }
    }
}
