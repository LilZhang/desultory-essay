## MySQL数据类型

### 字符型

单位: byte(字节)。某些charset可能一个字需要 2 byte。


| 类型        | 最大长度        | bit    |
| ---        | :---:          | ---:   |
| char       | 255            | 2 ^ 8  |
| varchar    | 65 536         | 2 ^ 16 |
| tinytext   | 255            | 2 ^ 8  |
| text       | 65 536         | 2 ^ 16 |
| mediumtext | 16 777 215     | 2 ^ 24 |
| longtext   | 4 294 967 295  | 2 ^ 32 |

text 数据尾部用空格填充。

### 整型
