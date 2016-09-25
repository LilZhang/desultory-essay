/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section7;

import java.lang.reflect.TypeVariable;
import java.util.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-13
 * Project        : desultory-essay
 * File Name      : ErasedTypeEquivalence.java
 */
public class ErasedTypeEquivalence
{
    static class Frob
    {

    }

    static class Fnorkle
    {

    }

    static class Quark<Q>
    {

    }

    static class Particle<POSITION, MOMENTUM>
    {

    }

    public static void main(String[] args)
    {
        List<Frob> frobList = new ArrayList<Frob>();

        Map<Frob, Fnorkle> frobFnorkleMap = new HashMap<Frob, Fnorkle>();

        Quark<Fnorkle> fnorkleQuark = new Quark<Fnorkle>();

        Particle<Long, Double> particle = new Particle<Long, Double>();

        printTypeParameters(frobList);          // [E]
        printTypeParameters(frobFnorkleMap);    // [K, V]
        printTypeParameters(fnorkleQuark);      // [Q]
        printTypeParameters(particle);          // [POSITION, MOMENTUM]
    }

    private static <T> void printTypeParameters(T t)
    {
        TypeVariable<? extends Class<?>>[] typeParameters = t.getClass().getTypeParameters();
        System.out.println(Arrays.toString(typeParameters));
    }
}
