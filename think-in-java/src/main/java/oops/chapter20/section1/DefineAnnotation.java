/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter20.section1;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-17
 * Project        : desultory-essay
 * File Name      : DefineAnnotation.java
 *
 * 从有道云笔记拷贝
 */
public class DefineAnnotation
{
}

/**
 * 四大元注解：
 * @Target： Annotation所修饰的对象范围
 * @Retention: 描述注解的生命周期（即：被描述的注解在什么范围内有效）
 * @Documented: 可否被文档化
 * @Inherited: 被该注解修饰的类的子类将继承该注解
 *
 * @author Lil ZHANG
 *
 */

/* ElementType.
    CONSTRUCTOR:用于描述构造器
    FIELD:用于描述域
    LOCAL_VARIABLE:用于描述局部变量
    METHOD:用于描述方法
    PACKAGE:用于描述包
    PARAMETER:用于描述参数
    TYPE:用于描述类、接口(包括注解类型) 或enum声明

    若省略@Target，则表明应用于所有ElementType
    */
@Target(ElementType.TYPE)
/*RetentionPolicy.
    SOURCE:在源文件中有效（即源文件保留）
    CLASS:在class文件中有效（即class保留）
    RUNTIME:在运行时有效（即运行时保留）*/
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface TypeAnnotation
{

    /*注解参数的可支持数据类型：

        1.所有基本数据类型（int,float,boolean,byte,double,char,long,short)
        2.String类型
        3.Class类型
        4.enum类型
        5.Annotation类型
        6.以上所有类型的数组

        7.默认值不能为 null
        8.要么(为属性)指定默认值，要么在使用中必须设定值
        */

    String value() default "";

    boolean isMy() default false;

    enum Color {BULE, RED, GREEN};

    Color color() default Color.BULE;

    String name() default "";
}

/* ============= FieldAnnotation.java 如下 ============= */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldAnnotation
{

    String attr() default "";

}

/* ============= MethodAnnotation.java 如下 ============= */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation
{

    String attr() default "";

}

/* ============= 关于如何使用Annotation并如何获取其中配置 ============= */

@TypeAnnotation(name="Sharon", isMy=true, color= TypeAnnotation.Color.GREEN, value="sharonValue")
class ExampleEntity
{

    @FieldAnnotation(attr="sharonField")
    private String entityName;

    public String getEntityName()
    {
        return entityName;
    }

    public void setEntityName(String entityName)
    {
        this.entityName = entityName;
    }

    @MethodAnnotation(attr="sharonMethod")
    public void entityMethod()
    {

    }

    public static void main(String[] args)
    {
        Class<ExampleEntity> c = ExampleEntity.class;

        if (c.isAnnotationPresent(TypeAnnotation.class))
        {
            System.out.println(
                    c.getAnnotation(TypeAnnotation.class).name() + ", " +
                            c.getAnnotation(TypeAnnotation.class).value()
            );
        }

        for (Field f : c.getDeclaredFields())
        {
            if (f.isAnnotationPresent(FieldAnnotation.class))
            {
                System.out.println(
                        f.getAnnotation(FieldAnnotation.class).attr()
                );
            }
        }

        for (Method m : c.getDeclaredMethods())
        {
            if (m.isAnnotationPresent(MethodAnnotation.class))
            {
                System.out.println(
                        m.getAnnotation(MethodAnnotation.class).attr()
                );
            }
        }
    }
}
