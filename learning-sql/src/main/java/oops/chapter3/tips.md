### 视图(view)

相当于某个查询的 situation。

```
CREATE VIEW user_of_xiao
AS
SELECT
    ua.Username AS`name`,
    ua.AuthorizationVerifyStatus as au_status,
    ua.ProfileType as activated_num
FROM
    user_account ua
WHERE
    ua.SaleName = 'zuoqing.xiao'
```

这个 situation 还能被用来继续查询。

```
SELECT
    uox.`name` AS nm,
    uox.au_status AS authorized
FROM
    user_of_xiao AS uox
```

### order by

根据某字段后四位排序。

```
SELECT
    pop.Id AS id,
    pop.OrderId AS order_id
FROM
    pay_order_payment pop
ORDER BY
    RIGHT(pop.ExtOrderId, 4);
```

根据 BuyerId, SellerId (所在的列序号)排序。

```
SELECT
    pop.Id AS id,
    pop.OrderId AS order_id,
    pop.BuyerId AS buyer_id,
    pop.SellerId AS seller_id,
    pop.Channel AS channel
FROM
    pay_order_payment pop
ORDER BY
    3,
    4;
```