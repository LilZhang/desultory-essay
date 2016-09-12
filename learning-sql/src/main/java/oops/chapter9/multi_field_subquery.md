### 多列子查询

- 例子:

```
SELECT
    *
FROM
    table t
WHERE
    (t.field_1, t.field_2)
IN      -- 也可以用 = , !=
(
    SELECT
        it.field_1, it.field_2
    FROM
        in_table it
);
```

等价于

```
SELECT
    *
FROM
    table t
WHERE
    t.field_1
IN
(
    SELECT
        it.field_1
    FROM
        in_table it
)
AND
    t.field_2
IN
(
    SELECT
        it.field_2
    FROM
        in_table it
);
```