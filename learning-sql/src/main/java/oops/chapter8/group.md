### 聚集函数

- 可在 GROUP BY 和 HAVING 中使用的函数

| function  |
| ---       |
| MAX()     |
| MIN()     |
| AVG()     |
| SUM()     |
| COUNT()   |




- 坑爹的 NULL

### 无空值情况

TABLE without_null

| id    | value(not null)|
| ---   | ---           |
| 1     | 1             |
| 2     | 3             |
| 3     | 5             |
| 4     | 0             |

- 聚合函数
```
SELECT
    MAX(`value`),
    MIN(`value`),
    AVG(`value`),
    SUM(`value`),
    COUNT(`value`),
    COUNT(*),
    COUNT(1)
FROM
    without_null
```

- RESULT

| MAX(value)| MIN(value)| AVG(value)| SUM(value)| COUNT(value)  | COUNT(*)  | COUNT(1)  |
| ---       | ---       | ---       | ---       | ---           | ---       | ---       |
| 5         | 0         | 2.25      | 9         | 4             | 4         | 4         |

- 条件查询
```
SELECT
    *
FROM
    without_null
WHERE
    `value` != 2
```
- RESULT

| id    | value |
| ---   | ---   |
| 1     | 1     |
| 2     | 3     |
| 3     | 5     |
| 4     | 0     |



### 有空值情况

TABLE with_null

| id    | value(nullable)|
| ---   | ---           |
| 1     | 1             |
| 2     | 3             |
| 3     | 5             |
| 4     | (NULL)        |

- 聚合函数
```
SELECT
    MAX(`value`),
    MIN(`value`),
    AVG(`value`),
    SUM(`value`),
    COUNT(`value`),
    COUNT(*),
    COUNT(1)
FROM
    with_null
```

- RESULT

| MAX(value)| MIN(value)| AVG(value)| SUM(value)| COUNT(value)  | COUNT(*)  | COUNT(1)  |
| ---       | ---       | ---       | ---       | ---           | ---       | ---       |
| 5         | 1         | 3         | 9         | 3             | 4         | 4         |

- 条件查询
```
SELECT
    *
FROM
    with_null
WHERE
    `value` != 2
```
- RESULT

| id    | value |
| ---   | ---   |
| 1     | 1     |
| 2     | 3     |
| 3     | 5     |




### 空表情况

TABLE all_null

| id    | value         |
| ---   | ---           |
| (NULL)| (NULL)        |

- 聚合函数
```
SELECT
    MAX(`value`),
    MIN(`value`),
    AVG(`value`),
    SUM(`value`),
    COUNT(`value`),
    COUNT(*),
    COUNT(1)
FROM
    all_null
```

- RESULT

| MAX(value)| MIN(value)| AVG(value)| SUM(value)| COUNT(value)  | COUNT(*)  | COUNT(1)  |
| ---       | ---       | ---       | ---       | ---           | ---       | ---       |
| (NULL)    | (NULL)    | (NULL)    | (NULL)    | 0             | 0         | 0         |

### 总结

2. 关于 NULL 的查询
- 若 WHERE 字句中含有类似 field != 1条件时，NULL 值会被忽视
- 若不想 NULL 被排除，可以加上 AND field IS NOT NULL

3. 聚合函数
- MAX(field), MIN(field), AVG(field), SUM(field) 会忽视 NULL 值
- 若 MAX(field), MIN(field), AVG(field), SUM(field) 所统计的值都为 NULL ，则返回 NULL

4. COUNT() 与 MAX(), MIN(), AVG(), SUM() 区别
- COUNT(NULL) = 0
- MAX(NULL), MIN(NULL), AVG(NULL), SUM(NULL) = NULL

- 若 field 为 NULL ，COUNT(field) = 0
- 但是除了 field 以外的别的字段不一定为 NULL, 所以 COUNT(*) 和 COUNT(1) 不一定为 0