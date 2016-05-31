/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter12.section4;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-30
 * Project        : desultory-essay
 * File Name      : LoggingExceptions.java
 */
public class LoggingExceptions
{
    public static void main(String[] args)
    {
        try
        {
            throw new LoggingException();
        }
        catch (LoggingException e)
        {
            System.err.println("caught" + e);
        }
    }
}

class LoggingException extends Exception
{
    private static Logger logger = Logger.getLogger("LoggingException");

    public LoggingException()
    {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}
