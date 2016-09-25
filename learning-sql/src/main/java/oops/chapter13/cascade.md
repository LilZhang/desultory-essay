### 外键级联约束

- 初始化
```
CREATE TABLE product_type
(
  id SMALLINT AUTO_INCREMENT,
  type_name VARCHAR(20), -- 被外键关联的字段必须类型相同(严格匹配)，而且没有默认值
  PRIMARY KEY (`id`),
  INDEX IX_TYPE_NAME(type_name) -- 被外键关联的字段必须为主键或者有索引
) Engine = innoDB;

CREATE TABLE product
(
  id SMALLINT AUTO_INCREMENT,
  product_type VARCHAR(20), -- 没有默认值
  `name` VARCHAR(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  CONSTRAINT FK_PRODUCT_TYPE FOREIGN KEY (product_type) REFERENCES product_type(type_name)
) Engine = innoDB;

INSERT INTO product_type (type_name) VALUES
('ACCOUNT'), ('INSURANCE'), ('LOAN');

INSERT INTO product (product_type, name) VALUES
('LOAN', 'Honda'), ('ACCOUNT', 'Yamasagi'),
('ACCOUNT', 'Vardy'), ('INSURANCE', 'Cazola'),
('ACCOUNT', 'Ali'), ('LOAN', 'Fabiano'),
('INSURANCE', 'Lil');
```

1. 尝试更新被外键指向的字段(product_type.type_name)
```
UPDATE product_type
SET type_name = 'XYZ'
WHERE
    id = 1
```

RESULT:
[Err] 1451 - Cannot delete or update a parent row:
a foreign key constraint fails (`test_join/product`, CONSTRAINT `FK_PRODUCT_TYPE` FOREIGN KEY (`product_type`) REFERENCES `product_type` (`type_name`))

2. 尝试更新引用外键的字段(product.product_type)
```
UPDATE product
SET product_type = 'XYZ'
WHERE
    id = 5;
```

RESULT:
[Err] 1452 - Cannot add or update a child row:
a foreign key constraint fails (`test_join/product`, CONSTRAINT `FK_PRODUCT_TYPE` FOREIGN KEY (`product_type`) REFERENCES `product_type` (`type_name`))

3. 更改 product 表的外键为 ON UPDATE CASCADE ON DELETE CASCADE
```
ALTER TABLE product
DROP FOREIGN KEY FK_PRODUCT_TYPE;

ALTER TABLE product
ADD
CONSTRAINT FK_PRODUCT_TYPE FOREIGN KEY (product_type)
REFERENCES product_type(type_name)
ON UPDATE CASCADE ON DELETE CASCADE;
```

4. 再对 product_type 表进行更新(或删除)
会发现，引用了 product_type 表的 product 也会有相应的变化

| ON UPDATE/DELETE  | DESC      |
| ---               | ---       |
| RESTRICT          | 同no action, 都是立即检查外键约束                                |
| NO ACTION         | 如果子表中有匹配的记录,则不允许对父表对应候选键进行update/delete操作   |
| CASCADE           | 在父表上update/delete记录时，同步update/delete掉子表的匹配记录      |
| SET NULL          | 在父表上update/delete记录时，将子表上匹配记录的列(非NULL)设为null    |