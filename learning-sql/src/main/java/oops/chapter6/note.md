### 集合操作符
- UNION (去除重复项)
- UNION ALL (保留重复项)
- INTERSECT (取交集) [未实现]
- except (取差集) [未实现]

### 交集

1. example 1
```
(SELECT 1, 'abc')
UNION
    (SELECT 2, 'ghj')
```

- RESULT

| 1     | abc   |
| ---   | ---   |
| 1     | abc   |
| 2     | ghj   |


2. example 2

| a.id  | a.b_id|
| ---   | ---   |
| 1     | 1     |
| 2     | 2     |
| 3     | 3     |
| 4     | 4     |

| b.id  | b.join|
| ---   | ---   |
| 1     | 1     |
| 2     | 2     |
| 3     | 3     |
| 4     | 1     |
| 5     | 2     |
| 6     | 3     |

```
(
    SELECT
        a.id,
        a.b_id
    FROM
        cross_join_a a
)
UNION
    (
        SELECT
            b.id,
            b.`join`
        FROM
            cross_join_b b
    )
```

- RESULT

| id    | b_id  |
| ---   | ---   |
| 1     | 1     |
| 2     | 2     |
| 3     | 3     |
| 4     | 4     |
| 4     | 1     |
| 5     | 2     |
| 6     | 3     |

- 列数需相同
- 对应的列类型需相同
- 结果列名随第一个查询子句，可被order by