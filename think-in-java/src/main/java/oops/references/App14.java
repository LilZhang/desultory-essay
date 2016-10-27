/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-26
 * Project        : desultory-essay
 * File Name      : App14.java
 */
public class App14
{
    public static void main(String[] args)
    {
        SortedMap<String, Charset> charsetSortedMap = Charset.availableCharsets();
        for (Map.Entry<String, Charset> entry : charsetSortedMap.entrySet())
        {
            String key = entry.getKey();
            Charset charset = entry.getValue();
            Set<String> aliases = charset.aliases();
            if (aliases != null && aliases.size() > 0)
            {
                StringBuilder sb = new StringBuilder();
                for (String aliase : aliases)
                {
                    sb.append(aliase).append(", ");
                }

                System.out.format("%s : %s\n", key, sb.toString());
            }
        }
    }
}
