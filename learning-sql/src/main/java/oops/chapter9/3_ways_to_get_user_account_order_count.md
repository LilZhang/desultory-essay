### 实践: 获取购买次数为5至10次的用户

- 方法一: JOIN
```
SELECT
    ua.Id AS userId,
    COUNT(1) AS cnt
FROM
    user_account ua
JOIN pay_order po ON po.BuyerId = ua.Id
GROUP BY
    ua.Id
HAVING
    COUNT(1) BETWEEN 5 AND 10
ORDER BY
    ua.Id;
```
(18ms)

- 方法二: 在 WHERE 子句中子查询
- 但是如果需要获取具体 COUNT 值时捉急
```
SELECT
    ua.Id AS userId
    -- ,(
    --     SELECT
    --         COUNT(1)
    --     FROM
    --         pay_order po
    --     WHERE
    --         po.buyerId = ua.Id
    -- ) AS cnt
FROM
    user_account ua
WHERE
    (
        SELECT
            COUNT(1)
        FROM
            pay_order po
        WHERE
            po.buyerId = ua.Id
    ) BETWEEN 5 AND 10
ORDER BY
    ua.Id;
```
(80ms)


- 方案三: 在 JOIN 中建立 UID-CNT 临时表
```
SELECT
    ua.Id AS userId,
    po_cnt.cnt AS cnt
FROM
    user_account ua
JOIN (
    SELECT
        po.BuyerId AS userId,
        COUNT(1) AS cnt
    FROM
        pay_order po
    -- not need here
    -- WHERE
    --    po.buyerId = ua.Id
    GROUP BY
        po.BuyerId
) po_cnt ON ua.Id = po_cnt.userId
WHERE
    po_cnt.cnt BETWEEN 5 AND 10
ORDER BY
    ua.Id;
```
(16ms)