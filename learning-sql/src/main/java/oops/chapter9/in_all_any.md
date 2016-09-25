### NOT IN & != ALL

NOT IN 写法
```
SELECT
    id
FROM
    cross_join_a
WHERE
    id NOT IN (
        SELECT
            a.id
        FROM
            cross_join_a a
        WHERE
            a.id = 2
        OR a.id = 3    -- 2, 3
    )
```

!= ALL 写法
```
SELECT
    id
FROM
    cross_join_a
WHERE
    id != ALL (
        SELECT
            a.id
        FROM
            cross_join_a a
        WHERE
            a.id = 2
        OR a.id = 3    -- 2, 3
    )
```

- NOT IN 和 != ALL 几乎可以等同
- IN 后面可以直接接集合如(2, 3)
- != ALL 不行
- NOT IN 和 != ALL 后面请确保 不出现 NULL , 不然可能EMPTY SET
- 其实所有要比较的地方都确保不要和 NULL 比较，会出现意想不到的后果

### < ALL 或者 > ALL

```
SELECT
    id
FROM
    cross_join_a
WHERE
    id < ALL (
        SELECT
            a.id
        FROM
            cross_join_a a
        WHERE
            a.id = 2
        OR a.id = 3     -- 2, 3
    )
```

RESULT : 1

### IN & = ANY

### < ANY 或者 > ANY

类似