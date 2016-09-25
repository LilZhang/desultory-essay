### 动态SQL

- simple
```
-- 设置语句
SET @qry = '
SELECT * FROM cross_join_b b
WHERE b.id = ?';

-- 显示语句
SELECT @qry;

-- prepare statement
PREPARE dysql FROM @qry;

-- comdition
SET @bid = 3;

-- execute
EXECUTE dysql USING @bid;


DEALLOCATE PREPARE dysql;
```

- 设置语句的另一种方法
```
SELECT
    CONCAT('SELECT ',GROUP_CONCAT(DISTINCT COLUMN_NAME),' FROM ', TABLE_NAME, ' WHERE id = ?;')
    INTO @qury
FROM
    information_schema.`COLUMNS` col
WHERE
    col.TABLE_SCHEMA = 'test_join'
AND col.TABLE_NAME = 'cross_join_b';

SELECT @qury;
```