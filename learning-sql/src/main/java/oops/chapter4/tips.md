### NOT

```
SELECT
    pop.Id AS id,
    pop.OrderId AS order_id,
    pop.BuyerId AS buyer_id,
    pop.SellerId AS seller_id,
    pop.Channel AS channel
FROM
    pay_order_payment pop
WHERE
    NOT pop.SellerId = 1;    -- NOT 相当于 !
```

### BETWEEN

```
SELECT
    pop.Id AS id,
    pop.OrderId AS order_id,
    pop.BuyerId AS buyer_id,
    pop.SellerId AS seller_id,
    pop.Channel AS channel
FROM
    pay_order_payment pop
WHERE
    pop.UpdateTime
    BETWEEN '2015-12-01 00:00:00'
    AND '2015-12-12 00:00:00';

    -- 等价于 pop.UpdateTime >= '2015-12-01 00:00:00'
    -- AND pop.UpdateTime <= '2015-12-12 00:00:00'
```

### 通配符

| 通配符 | 匹配                      |
| ---   | ---                      |
| _     | 单个字符 (heat 匹配 h_at)  |
| %     | 任意个字符 (Fatty 匹配 Fa%) |

### 正则表达式

```
SELECT
    *
FROM
    user_account ua
WHERE
    ua.Username REGEXP '[ku]'
```