/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section11;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-07-19
 * Project        : desultory-essay
 * File Name      : HijackedInterface.java
 */
public class HijackedInterface
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        // cat.compareTo();
        // 形参依然为 Pet
    }
}

class Pet implements Comparable<Pet>
{
    public int compareTo(Pet o)
    {
        return 0;
    }
}

// 接口已被父类实现，<Pet> 不能被 <Cat> 替代
// 但是可以再次实现 Comparable<Pet>
class Cat extends Pet /*implements Comparable<Cat>*/
{

}
