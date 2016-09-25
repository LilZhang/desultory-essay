/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter14.section3;

import oops.chapter14.section3.domain.*;

import java.util.HashMap;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-09
 * Project        : desultory-essay
 * File Name      : PetCount.java
 */
public class PetCount
{
    public static void countPets(PetCreator creator)
    {
        PetCounter petCounter = new PetCounter();

        for (Pet pet : creator.createArray(20))
        {
            System.out.println(pet.getClass().getSimpleName());

            if (pet instanceof Pet)
            {
                petCounter.count("Pet");
            }
            if (pet instanceof Dog)
            {
                petCounter.count("Dog");
            }
            if (pet instanceof Mutt)
            {
                petCounter.count("Mutt");
            }
            if (pet instanceof Pug)
            {
                petCounter.count("Pug");
            }
            if (pet instanceof Cat)
            {
                petCounter.count("Cat");
            }
            if (pet instanceof EgyptianMau)
            {
                petCounter.count("EgyptianMau");
            }
            if (pet instanceof Manx)
            {
                petCounter.count("Manx");
            }
            if (pet instanceof Cymric)
            {
                petCounter.count("Cymric");
            }
            if (pet instanceof Rodent)
            {
                petCounter.count("Rodent");
            }
            if (pet instanceof Rat)
            {
                petCounter.count("Rat");
            }
            if (pet instanceof Mouse)
            {
                petCounter.count("Mouse");
            }
            if (pet instanceof Hamster)
            {
                petCounter.count("Hamster");
            }

        }

        System.out.println(petCounter);
    }

    static class PetCounter extends HashMap<String, Integer>
    {
        public void count(String type)
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
