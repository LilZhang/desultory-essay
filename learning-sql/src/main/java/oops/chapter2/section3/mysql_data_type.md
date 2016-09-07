## MySQL数据类型


| Tables      | Are           | Cool  |
| --- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |

### 字符型

单位: byte(字节)。某些charset可能一个字需要 2 byte。



- char(255): [2 ^ 8]
- varchar(65 535): [2 ^ 16]
- tinytext(255): [2 ^ 8]
- text(65 535): [2 ^ 16]
- mediumtext(16 777 215): [2 ^ 24]
- longtext(4 294 967 295): [2 ^ 32]

text 数据尾部用空格填充。

### 整型
