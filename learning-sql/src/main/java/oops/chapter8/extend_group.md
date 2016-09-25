### 利用表达式分组

- 在updateTime中抽取月份进行分组统计
```
SELECT
    EXTRACT(MONTH FROM po.UpdateTime) MONTH,
    COUNT(1) AS cnt
FROM
    pay_order po
GROUP BY
    EXTRACT(MONTH FROM po.UpdateTime)
```

| month | cnt   |
| ---   | ---   |
| 1     | 313   |
| 2     | 37    |
| 3     | 35    |

### 对多列的分组
```
SELECT
    field_a,
    field_b,
    SUM(field_c)
FROM
    table
GROUP BY
    field_a,
    field_b;
```

- field_a, field_b 需同时出现在 SELECT 与 GROUP BY 中

- 例:
```
SELECT
    po.SellerId,
    po.BuyerId,
    SUM(po.PaymentAmount)
FROM
    pay_order po
GROUP BY
    po.SellerId,
    po.BuyerId;
```
- 在 GROUP BY 中出现的 po.SellerId 和 po.BuyerId，
- 也需要在 SELECT 中出现。

| SellerId  | BuyerId   | SUM(po.PaymentAmount)|
| ---       | ---       | ---       |
| 1         | 35        | 27.54     |
| 3         | 10000     | 36.75     |
| 9         | 1         | 44.73     |
| 9         | 10        | 12.8      |
| 9         | 24        | 25.62     |
| 10        | 33        | 31.4      |
| 10        | 94        | 24.9      |
| 10        | 102       | 52        |



### 合计数(WITH ROLLUP)
```
SELECT
    po.SellerId,
    po.BuyerId,
    SUM(po.PaymentAmount)
FROM
    pay_order po
GROUP BY
    po.SellerId,
    po.BuyerId WITH ROLLUP;
```

| SellerId  | BuyerId   | SUM(po.PaymentAmount)|
| ---       | ---       | ---       |
| 1         | 35        | 27.54     |
| 1         |(NULL)TOTAL| 27.54     |
| 3         | 10000     | 36.75     |
| 3         |(NULL)TOTAL| 36.75     |
| 9         | 1         | 44.73     |
| 9         | 10        | 12.8      |
| 9         | 24        | 25.62     |
| 9         |(NULL)TOTAL| 83.15     |
| 10        | 33        | 31.4      |
| 10        | 94        | 24.9      |
| 10        | 102       | 52        |
| 10        |(NULL)TOTAL| 108.3     |

类似的还有 WITH CUBE, MySQL 没有实现。