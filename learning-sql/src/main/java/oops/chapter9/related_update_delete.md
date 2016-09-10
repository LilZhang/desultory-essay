### 关联子查询操作数据

1. UPDATE

```
UPDATE
    table_a a
SET
    a.value =
    (
        SELECT
            b.value
        FROM
            table_b b
        WHERE
            b.id = a.id
    )
WHERE EXISTS        -- 防止 NULL 被写入
(
    SELECT
        1
    FROM
        table_b b
    WHERE
        b.id = a.id
);
```

2. DELETE

```
DELETE FROM
    table_a a
WHERE NOT EXITS
(
    SELECT
        1
    FROM
        table_b b
    WHERE
        b.id = a.id
);
```