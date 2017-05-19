/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.annotation;

import oops.methodForTx.UserDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-13
 * Project        : desultory-essay
 * File Name      : UserDemoManager.java
 */
@Service
public class UserDemoManager
{
    @Autowired
    private UserDemoService userDemoService;

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean transferAge(int fromId, int toId, int amount)
    {
        if (!this.userDemoService.decreaseAge(fromId, amount) ||
                !this.userDemoService.increaseAge(toId, amount))
        {
            throw new RuntimeException();
        }
        return true;
    }
}
