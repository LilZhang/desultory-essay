/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p4;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-02
 * Project        : desultory-essay
 * File Name      : ToastOMatic.java
 */
class Toast
{
    public enum Status
    {
        DRY,
        BUTTERED,
        JAMMED
    }

    private Status status = Status.DRY;

    private final int id;

    public Toast(int id)
    {
        this.id = id;
    }

    public void butter()
    {
        status = Status.BUTTERED;
    }

    public void jam()
    {
        status = Status.JAMMED;
    }

    public Status getStatus()
    {
        return status;
    }

    public int getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Toast{");
        sb.append("id=").append(id);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}

class Toaster
{

}

class Butterer
{

}

class Jammer
{

}

class Eater
{

}

public class ToastOMatic
{
}
