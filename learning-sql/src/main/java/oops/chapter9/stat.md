### 数据统计

1. 创建统计标准

```
SELECT 'small' title, 0 floor, 1000 ceil
UNION ALL
SELECT 'medium' title, 1000 floor, 10000 ceil
UNION ALL
SELECT 'high' title, 10000 floor, 100000 ceil
UNION ALL
SELECT 'very high' title, 100000 floor, 1000000 ceil;
```

| title     | floor | ceil   |
| ---       | ---   | ---    |
| small     | 0     | 1000   |
| medium    | 1000  | 10000  |
| high      | 10000 | 100000 |
| very high | 100000| 1000000|

2. do it
```
SELECT
    stat.title AS title,
    COUNT(pop.PaymentAmount) AS cnt
FROM
(
    SELECT 'small' title, 0 floor, 1000 ceil
    UNION ALL
    SELECT 'medium' title, 1000 floor, 10000 ceil
    UNION ALL
    SELECT 'high' title, 10000 floor, 100000 ceil
    UNION ALL
    SELECT 'very high' title, 100000 floor, 1000000 ceil
) stat
INNER JOIN
    pay_order_payment pop
ON

    -- 这一句非常关键
    pop.PaymentAmount >= stat.floor AND pop.PaymentAmount < stat.ceil
    -- 至今仍觉酷炫

GROUP BY
    stat.title;
```

| title     | cnt   |
| ---       | ---   |
| small     | xxx   |
| medium    | xxxx  |
| high      | xxx   |
| very high | xx    |