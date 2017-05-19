/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.webservice.demo1;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-12
 * Project        : desultory-essay
 * File Name      : BusinessImpl.java
 */

// 需要在客户端执行 wsimport
@WebService(name = "Business",
        serviceName = "BusinessService",
        targetNamespace = "http://webservice.oops/client")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class BusinessImpl implements Business
{

    public String echo(String value)
    {
        System.out.println("method echo() invoked");
        return "yo";
    }


    // 发布
    public static void main(String[] args)
    {
        Endpoint.publish("http://localhost:9527/BusinessService", new BusinessImpl());
        System.out.println("[BusinessService] publish succeed.");
    }
}
