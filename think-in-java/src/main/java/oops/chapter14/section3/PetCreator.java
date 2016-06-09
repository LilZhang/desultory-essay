/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter14.section3;

import oops.chapter14.section3.domain.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-06-07
 * Project        : desultory-essay
 * File Name      : PetCreator.java
 */
public  abstract class PetCreator
{
    private Random rand = new Random(47);

    public abstract List<Class<? extends Pet>> types();

    public Pet randomPet()
    {
        int n = rand.nextInt(types().size());

        try
        {
            return types().get(n).newInstance();
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
        catch (InstantiationException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Pet[] createArray(int size)
    {
        Pet[] result = new Pet[size];

        for (int i = 0; i < size; i++)
        {
            result[i] = randomPet();
        }

        return result;
    }

    public ArrayList<Pet> arrayList(int size)
    {
        ArrayList<Pet> result = new ArrayList<Pet>(size);
        Collections.addAll(result, createArray(size));

        return result;
    }
}
