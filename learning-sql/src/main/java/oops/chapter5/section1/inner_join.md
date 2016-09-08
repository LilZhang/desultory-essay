### JOIN (INNER JOIN)

- SQL

```
SELECT
    a.`value` AS a_value,
    b.`value` AS b_value
FROM
    inner_join_a a
(INNER) JOIN inner_join_b b
ON a.b_id = b.id;
```

若需JOIN的字段名相同(比如为field)，可以使用USING

```
SELECT
    a.`value` AS a_value,
    b.`value` AS b_value
FROM
    inner_join_a a
(INNER) JOIN inner_join_b b
USING (field);
```