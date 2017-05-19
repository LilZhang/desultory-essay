/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.methodForTx;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-13
 * Project        : desultory-essay
 * File Name      : TransferService.java
 */
public interface UserDemoService
{
    boolean increaseAge(int id, int amount);

    boolean decreaseAge(int id, int amount);

    int insert(UserDemo userDemo);

    UserDemo loadById(int id);
}
