/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-03
 * Project        : desultory-essay
 * File Name      : Utils.java
 */
public class Utils
{
    public static Map<String, String> getReqInfoMap(HttpServletRequest request)
    {
        Map<String, String> infoMap = new HashMap<String, String>();
        infoMap.put("request.getAuthType()", request.getAuthType());
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> parameterStrMap = new HashMap<String, String>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet())
        {
            parameterStrMap.put(entry.getKey(), Arrays.toString(entry.getValue()));
        }
        infoMap.put("request.getParameterMap()", parameterStrMap.toString());
        infoMap.put("request.getMethod()", request.getMethod());
        infoMap.put("request.getProtocol()", request.getProtocol());
        infoMap.put("request.getContentType()", request.getContentType());
        infoMap.put("request.getContextPath()", request.getContextPath());
        infoMap.put("request.getPathInfo()", request.getPathInfo());
        infoMap.put("request.getQueryString()", request.getQueryString());
        infoMap.put("request.getPathTranslated()", request.getPathTranslated());
        infoMap.put("request.getRemoteUser()", request.getRemoteUser());
        infoMap.put("request.getRequestURI()", request.getRequestURI());
        infoMap.put("request.getRequestedSessionId()", request.getRequestedSessionId());
        infoMap.put("request.getServletPath()", request.getServletPath());
        infoMap.put("request.getCharacterEncoding()", request.getCharacterEncoding());
        infoMap.put("request.getLocalAddr()", request.getLocalAddr());
        infoMap.put("request.getLocalName()", request.getLocalName());
        infoMap.put("request.getRemoteAddr()", request.getRemoteAddr());
        infoMap.put("request.getRemoteHost()", request.getRemoteHost());
        infoMap.put("request.getScheme()", request.getScheme());
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            String[] cookiestr = new String[cookies.length];
            for (int i = 0; i < cookies.length; i++)
            {
                Cookie cookie = cookies[i];
                if (cookie != null)
                {
                    cookiestr[i] = cookie.getName();
                }
            }
            infoMap.put("request.getCookies()", Arrays.toString(cookiestr));
        }
        Map<String, String> header = new HashMap<String, String>();
        Enumeration<String> headerNameEnum = request.getHeaderNames();
        while (headerNameEnum.hasMoreElements())
        {
            String s = headerNameEnum.nextElement();
            header.put(s, request.getHeader(s));
        }
        infoMap.put("request.getHeaderNames()", header.toString());
        return infoMap;
    }

    public static String randomString(int len)
    {
        char[] chars = new char[len];
        char base;
        for (int i = 0; i < chars.length; i++)
        {
            int upcase = ((int) (Math.random() * 2));
            if (upcase == 0)
            {
                base = 'a';
            }
            else
            {
                base = 'A';
            }
            chars[i] = ((char) (base + ((int) (Math.random() * 26))));
        }
        return new String(chars);
    }
}
