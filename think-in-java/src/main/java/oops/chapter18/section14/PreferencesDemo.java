/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section14;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-16
 * Project        : desultory-essay
 * File Name      : PreferencesDemo.java
 */
public class PreferencesDemo
{
    public static void main(String[] args)
    {
        Preferences preferences = Preferences.userRoot().node("/oops/test");
//        preferences.put("location", "0z");
//        preferences.put("footwear", "lil");
//        preferences.putInt("companions", 4);
//        preferences.putBoolean("flag", true);

        int count = preferences.getInt("primer", 2);
        int count2 = preferences.getInt("companions", 2);

        System.out.println();

        try
        {
            preferences.removeNode();
        }
        catch (BackingStoreException e)
        {
            e.printStackTrace();
        }
    }
}
