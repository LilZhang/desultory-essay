/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : TestModel.java
 */
public class TestModel
{
    private int id;

    private String content;

    public TestModel()
    {
    }

    public TestModel(int id)
    {
        this.id = id;
    }

    public TestModel(int id, String content)
    {
        this.id = id;
        this.content = content;
    }

    public TestModel(TestModel model)
    {
        this.id = model.getId();
        this.content = model.getContent();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("TestModel{");
        sb.append("id=").append(id);
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void xmlInit()
    {
        System.out.println("该 bean 由 xml 方式初始化");
    }

    public void xmlDestory()
    {
        System.out.println("该 bean 由 xml 方式销毁");
    }

    @PostConstruct
    public void init()
    {
        System.out.println("该 bean 由 注解 方式初始化");
    }

    @PreDestroy
    public void destory()
    {
        System.out.println("该 bean 由 注解 方式销毁");
    }
}
