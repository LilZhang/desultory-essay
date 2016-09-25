### 视图操作若干

1. 从表创建视图
```
CREATE (OR REPLACE) VIEW table_view
(
    view_a,
    view_b,
    view_c
)
AS
SELECT
    field_a,
    field_b,
    field_c
FROM table;
```

2. 从视图创建表
```
CREATE TABLE table_2
(
    table_2_a,
    table_2_b,
    table_2_c
)
AS
SELECT
    view_a,
    view_b,
    view_c
FROM view;
```

3. 视图内值的可更新条件(mysql)

- 没有使用聚合函数(max(), min(), avg())
- 没有使用 group by 或 having
- select 或 from 子句中不存在子查询，where 子句中不存在关联子查询
- 没有使用 union, union all 或者 distinct
- from 子句包括不止一个表或可更新视图
- 如果有不止一个表或视图，那么 from 子句只能用 inner join