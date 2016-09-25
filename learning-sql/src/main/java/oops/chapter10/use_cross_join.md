### 利用 CROSS JOIN 求笛卡尔积获取一年内的所有日期


```
SELECT
    DATE_ADD('2016-01-01',
    INTERVAL (ones.num + tens.num + hundreds.num) DAY) AS `date`
FROM
(
    SELECT 0 num UNION ALL
    SELECT 1 num UNION ALL
    SELECT 2 num UNION ALL
    SELECT 3 num UNION ALL
    SELECT 4 num UNION ALL
    SELECT 5 num UNION ALL
    SELECT 6 num UNION ALL
    SELECT 7 num UNION ALL
    SELECT 8 num UNION ALL
    SELECT 9 num
) ones

CROSS JOIN

(
    SELECT 0 num UNION ALL
    SELECT 10 num UNION ALL
    SELECT 20 num UNION ALL
    SELECT 30 num UNION ALL
    SELECT 40 num UNION ALL
    SELECT 50 num UNION ALL
    SELECT 60 num UNION ALL
    SELECT 70 num UNION ALL
    SELECT 80 num UNION ALL
    SELECT 90 num
) tens

CROSS JOIN

(
    SELECT 0 num UNION ALL
    SELECT 100 num UNION ALL
    SELECT 200 num UNION ALL
    SELECT 300 num
) hundreds

WHERE
    DATE_ADD('2016-01-01',INTERVAL (ones.num + tens.num + hundreds.num) DAY)
     < DATE_ADD('2016-01-01',INTERVAL 1 YEAR)
ORDER BY 1;
```