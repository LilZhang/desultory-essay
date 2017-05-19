/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import com.lilzh.webservice.client.Business;
import com.lilzh.webservice.client.BusinessService;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-12
 * Project        : desultory-essay
 * File Name      : App.java
 */
public class App
{
    public static void main(String[] args)
    {
        BusinessService businessService = new BusinessService();
        Business business = businessService.getBusinessPort();
        String echo = business.echo("felala");
        System.out.println(echo);
    }
}
