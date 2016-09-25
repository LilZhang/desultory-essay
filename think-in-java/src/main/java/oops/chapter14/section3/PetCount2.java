/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter14.section3;

import oops.chapter14.section3.domain.*;

import java.util.HashMap;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-09
 * Project        : desultory-essay
 * File Name      : PetCount.java
 */
public class PetCount2
{
    public static void countPets(PetCreator creator)
    {
        PetCounter petCounter = new PetCounter();

        List<Class<? extends Pet>> types = creator.types();

        for (Pet pet : creator.createArray(20))
        {
            System.out.println(pet.getClass().getSimpleName());

            for (Class<? extends Pet> type : types)
            {
                if (type.isInstance(pet))
                {
                    petCounter.count(type);
                }
            }

        }

        System.out.println(petCounter);
    }

    static class PetCounter extends HashMap<Class<? extends Pet>, Integer>
    {
        public void count(Class<? extends Pet> type)
        {
            Integer quantity = get(type);
            if (quantity == null)
            {
                put(type, 1);
            }
            else
            {
                put(type, ++quantity);
            }
        }
    }

    public static void main(String[] args)
    {
        countPets(new ForNameCreator());
    }
}
