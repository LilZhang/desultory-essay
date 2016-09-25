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