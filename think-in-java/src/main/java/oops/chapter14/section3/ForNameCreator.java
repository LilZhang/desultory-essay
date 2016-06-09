/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter14.section3;

import oops.chapter14.section3.domain.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-06-07
 * Project        : desultory-essay
 * File Name      : ForNameCreator.java
 */
public class ForNameCreator extends PetCreator
{
    private static final List<Class<? extends Pet>> types = loader();

    private static String[] typeNames =
            {
                    "oops.chapter14.section3.domain.Mutt",
                    "oops.chapter14.section3.domain.Pug",
                    "oops.chapter14.section3.domain.EgyptianMau",
                    "oops.chapter14.section3.domain.Manx",
                    "oops.chapter14.section3.domain.Mutt",
                    "oops.chapter14.section3.domain.Mutt",
                    "oops.chapter14.section3.domain.Mutt",
                    "oops.chapter14.section3.domain.Mutt"
            };

    @Override
    public List<Class<? extends Pet>> types()
    {
        return types;
    }

    private static List<Class<? extends Pet>> loader()
    {
        List<Class<? extends Pet>> result = new ArrayList<Class<? extends Pet>>();

        try
        {
            for (String name : typeNames)
            {
                result.add((Class<? extends Pet>) Class.forName(name));
            }
            return result;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
