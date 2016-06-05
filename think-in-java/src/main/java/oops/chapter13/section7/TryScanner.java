/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter13.section7;

import java.util.Scanner;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-05
 * Project        : desultory-essay
 * File Name      : TryScanner.java
 */
public class TryScanner
{
    public static void main(String[] args)
    {
        String content = "text line\n" +
                "1 3.03 3\n" +
                "end line\n";
        Scanner scanner = new Scanner(content);
        System.out.println(scanner.nextLine());
        System.out.println(scanner.nextInt());
        System.out.println(scanner.nextDouble());
        System.out.println(scanner.nextInt());
        System.out.println(scanner.next());

        System.out.println();

        String content1 = content;

        scanner = new Scanner(content1);
        while (scanner.hasNext())
        {
            System.out.println(scanner.next());
        }

        System.out.println();

        content = "text line\n" +
                "end line\n";
        scanner = new Scanner(content);
        System.out.println(scanner.nextLine());
        System.out.println(scanner.nextLine());

        System.out.println();

        content = "1,2,3,4,5";
        scanner = new Scanner(content);
        scanner.useDelimiter("\\s*,\\s*");  // 逗号分隔符
        while (scanner.hasNext())
        {
            System.out.println(scanner.next());
        }
    }
}
