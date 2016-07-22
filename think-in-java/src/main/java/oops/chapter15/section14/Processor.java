/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section14;

import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-22
 * Project        : desultory-essay
 * File Name      : Processor.java
 */
public interface Processor<T, E extends Exception>
{
    void processor(List<T> list) throws E;
}

class ProcessorImpl implements Processor<Integer, IllegalAccessException>
{
    public void processor(List<Integer> list) throws IllegalAccessException
    {

    }
}
