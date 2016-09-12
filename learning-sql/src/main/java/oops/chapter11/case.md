### CASE

1. 根据条件返回字符串结果
```
SELECT
    a.id AS id,
    CASE
    WHEN a.id BETWEEN 1 AND 2 THEN '1~2'
    WHEN a.id BETWEEN 3 AND 4 THEN '3~4'
    ELSE 'unknown'
    END cases
FROM
    cross_join_a a
```

2. 也可以使用简单CASE
```
SELECT
    a.id AS id,
    CASE a.id
    WHEN 1 THEN '1~2'
    WHEN 2 THEN '1~2'
    WHEN 3 THEN '3~4'
    WHEN 4 THEN '3~4'
    ELSE 'unknown'
    END cases
FROM
    cross_join_a a
```

3. 根据条件执行不同的子查询
```
SELECT
        a.id AS id,
        CASE
        WHEN a.id BETWEEN 1 AND 2 THEN 
        (
        SELECT COUNT(b.id) FROM cross_join_b b WHERE b.a_id = a.id
        )
        WHEN a.id BETWEEN 3 AND 4 THEN
        (
        SELECT COUNT(b.id) + 100 FROM cross_join_b b WHERE b.a_id = a.id
        ) 
        ELSE 'unknown'
        END cases
FROM
        cross_join_a a
```

4. 横向统计每个月的订单数
```
SELECT
    SUM(CASE EXTRACT(MONTH FROM po.AddTime) WHEN 8 THEN 1 ELSE 0 END) AS aug,
    SUM(CASE EXTRACT(MONTH FROM po.AddTime) WHEN 9 THEN 1 ELSE 0 END) AS sept,
    SUM(CASE EXTRACT(MONTH FROM po.AddTime) WHEN 10 THEN 1 ELSE 0 END) AS oct,
    SUM(CASE EXTRACT(MONTH FROM po.AddTime) WHEN 11 THEN 1 ELSE 0 END) AS nov,
    SUM(CASE EXTRACT(MONTH FROM po.AddTime) WHEN 12 THEN 1 ELSE 0 END) AS `dec`,
    SUM(CASE EXTRACT(MONTH FROM po.AddTime) WHEN 1 THEN 1 ELSE 0 END) AS jan,
    SUM(CASE EXTRACT(MONTH FROM po.AddTime) WHEN 2 THEN 1 ELSE 0 END) AS feb,
    SUM(CASE EXTRACT(MONTH FROM po.AddTime) WHEN 3 THEN 1 ELSE 0 END) AS mar
FROM
    pay_order po;
```

RESULT:

| aug| sept| oct| nov| dec| jan| feb| mar|
| ---| --- | ---| ---| ---| ---| ---| ---|
| xx | xx  | xx | xx | xx | xx | xx | xx |

话说纵向统计好像更省事，GROUP BY + COUNT 就行

5. 判断是否存在
```
SELECT
    ua.Id as userId,
    CASE
    WHEN EXISTS (
        SELECT 1 FROM user_enterprise ue WHERE ue.userId = ua.Id
        )
    THEN 'Y' ELSE 'N'
    END AS is_enterprise
FROM
    user_account ua
```

6. 手动过滤 NULL
```
SELECT
    CASE
    WHEN wn.`value` IS NULL THEN
        0
    ELSE
        wn.`value`
    END AS not_null_value
FROM
    with_null wn
```