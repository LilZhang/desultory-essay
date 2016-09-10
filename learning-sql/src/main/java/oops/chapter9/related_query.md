### 关联子查询

| won.id | won.value |   | wn.id | wn.value |
| ---    | ---       |---| ---   |---       |
| 1      | 1         |   | 1     | 1        |
| 2      | 3         |   | 2     | 3        |
| 3      | 5         |   | 3     | 5        |
| 4      | 0         |   | 4     | (NULL)   |
| 5      | 5         |   | 5     | 7        |
| 6      | 6         |   | 6     | 0        |
| 7      | 7         |   | 7     | 0        |
| 8      | 8         |   | 8     | 8        |
| 9      | 9         |   | 9     | 0        |
| 10     | 10        |   | 10    | 10       |
| 11     | 11        |   | 11    | 0        |
| 12     | 12        |   | 12    | 0        |
| 13     | 13        |   | 13    | 13       |
| 14     | 14        |   | 14    | 0        |
|        |           |   | 15    | 0        |

1. 关联子查询
```
SELECT
    *
FROM
    without_null won
WHERE
    (
        SELECT
            wn.`value` AS wnval
        FROM
            with_null wn
        WHERE
            wn.id = won.id
    ) = won.id;
```

2. 关联子查询(EXISTS)
```
SELECT
    *
FROM
    without_null won
WHERE
    (NOT) EXISTS (
        SELECT
            1
        FROM
            with_null wn
        WHERE
            wn.`value` = won.id
            AND wn.id  = won.id
    )
```

- RESULT

| id | value |
| ---| ---   |
| 1  | 1     |
| 8  | 8     |
| 10 | 10    |
| 13 | 13    |


### 另一个关联子查询

TABLE cross_join_a

| id | value|
| ---| ---  |
| 1  | a    |
| 2  | b    |
| 3  | c    |
| 4  | d    |

TABLE cross_join_b

| id| a_id|
|---| --- |
| 1 | 1   |
| 2 | 1   |
| 3 | 2   |
| 4 | 2   |
| 5 | 2   |
| 6 | 3   |
| 7 | 3   |
| 8 | 3   |
| 9 | 3   |
| 10| 4   |
| 11| 4   |
| 12| 4   |
| 13| 4   |
| 14| 4   |

```
SELECT
    *
FROM
    cross_join_a a
WHERE
    (
        SELECT
            COUNT(*)
        FROM
            cross_join_b b
        WHERE
            b.a_id = a.id
    )
    BETWEEN 3 AND 5;
```

- RESULT

| id | value|
| ---| ---  |
| 2  | b    |
| 3  | c    |
| 4  | d    |

- 好像用连接更好一点