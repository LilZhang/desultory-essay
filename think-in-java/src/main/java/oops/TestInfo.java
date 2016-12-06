/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Map;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-06
 * Project        : desultory-essay
 * File Name      : TestInfo.java
 */
public class TestInfo
{
    public static String encryptMD5(String key, String secret, Map<String, String> paramMap) {
        String plainText = getPlainText(key, secret, paramMap, false);
        String md5 = DigestUtils.md5Hex(plainText);
        StringBuilder md5Text = new StringBuilder(md5);


        String encrypt = md5Text.toString().toUpperCase();
        return encrypt;
    }

    public static String getPlainText(String key, String secret, Map<String, String> paramMap, boolean needTime) {
        if(!isBlank(key) && !isBlank(secret)) {
            StringBuilder plainText = new StringBuilder(key);
            buildParameters(plainText, paramMap);
            String time = null;
            if(needTime) {
                time = fetchTime();


                plainText.append(time);
            }

            plainText.append(secret);


            String encoded = plainText.toString();
            return encoded;
        } else {
            return null;
        }
    }

    private static void buildParameters(StringBuilder plainText, Map<String, String> paramMap) {
        if(paramMap != null && paramMap.size() > 0) {
            Map.Entry[] entryArray = (Map.Entry[])paramMap.entrySet().toArray(new Map.Entry[paramMap.size()]);
            Arrays.sort(entryArray, new Comparator()
            {
                public int compare(Object o1, Object o2) {
                    return ((String)((Map.Entry<String, String>) o1).getKey()).compareTo((String)((Map.Entry<String, String>) o2).getKey());
                }
            });
            Map.Entry[] arr$ = entryArray;
            int len$ = entryArray.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Map.Entry entry = arr$[i$];
                String value = (String)entry.getValue();
                if(value != null) {
                    plainText.append((String)entry.getKey()).append(value);
                }
            }
        }

    }

    private static String fetchTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(1);
        int month = calendar.get(2) + 1;
        int date = calendar.get(5);
        int hour = calendar.get(11);
        int minute = calendar.get(12);
        int second = calendar.get(13);
        return "" + year + '-' + month + '-' + date + '-' + hour + '-' + minute + '-' + second;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
}
