/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9.section3;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-25
 * Project        : desultory-essay
 * File Name      : AlternativeClasses.java
 */
public class AlternativeClasses
{

}

// 1. 一个 .java 中只允许一个public class存在
// 2. 在类中，其余class或interface的权限只能为包可见，不可为private，protected
// 3. 编译时生成另外的 .class 文件，不带前缀
class AnotherClass
{

}

// 同 AnotherClass 注释
// 接口中的成员(字段，方法)默认为public，不可为private，protected
interface AnotherInterface
{
    // 自动为 static + final
    String NAME = "name";

    // 自动为 public
    void f();

}

class AnotherInterfaceImpl implements AnotherInterface
{
    // 只能public
    public void f()
    {

    }
}
