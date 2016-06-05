/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter13.section6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-05
 * Project        : desultory-essay
 * File Name      : TestRegularExpressions.java
 */
public class TestRegularExpressions
{
    public static void main(String[] args)
    {
        String txt = "abcabcdefabc";

        String[] expressions = {"abc", "(abc)", "(abc){2,}"};

        System.out.println("input : " + txt);
        for (String expression : expressions)
        {
            System.out.println("regular expression : " + expression);

            Pattern p = Pattern.compile(expression);
            Matcher matcher = p.matcher(txt);

            while (matcher.find())
            {
                System.out.println("Match \" " +
                        matcher.group() + " at position " +
                        matcher.start() + " - " +
                        matcher.end() + "  \"");
            }
        }

        /**
         * output:
         *   input : abcabcdefabc
             regular expression : abc
             Match " abc at position 0 - 3  "
             Match " abc at position 3 - 6  "
             Match " abc at position 9 - 12  "
             regular expression : (abc)
             Match " abc at position 0 - 3  "
             Match " abc at position 3 - 6  "
             Match " abc at position 9 - 12  "
             regular expression : (abc){2,}
             Match " abcabc at position 0 - 6  "
         */
    }
}
