### JOIN (CROSS JOIN)

- TABLE cross_join_a

| id    | value |
| ---   | ---   |
| 1     | 1     |
| 2     | 2     |
| 3     | 3     |

- TABLE cross_join_b

| id    | value |
| ---   | ---   |
| 1     | a     |
| 2     | b     |
| 3     | c     |

- SQL
```
SELECT
    a.`value` AS a_value,
    b.`value` AS b_value
FROM
    cross_join_a a
JOIN cross_join_b b
```

- RESULT

| a_value   | b_value   |
| ---       | ---       |
| 1         | a         |
| 2         | a         |
| 3         | a         |
| 1         | b         |
| 2         | b         |
| 3         | b         |
| 1         | c         |
| 2         | c         |
| 3         | c         |

JOIN (CROSS JOIN) 未指定两个表如何连接。
所以结果产生笛卡儿积（3(a) × 3(b) = 9 个置换）。