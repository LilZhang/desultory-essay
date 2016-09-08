### 使用字符串数据

##### 可参考书籍: _SQL in a Nutshell_

##### 转义单引号“ ' ”: \\'

##### QUOTE(str): 用单引号包裹字符串，并转义其中的单引号

##### CHAR(): 用ASCII码构建特殊字符
- 例: CHAR(128)

##### ASCII(): CHAR() 的逆运算，已知特殊字符求ASCII码
- 例: ASCII(Ö)

### 操作字符串数据

##### LENGTH(str): 返回字符串的字符数
- 例: LENGTH('balab') = 5

##### POSITION(substr IN str): 搜索子字符串，返回子字符串在字符串中的位置，若找不到返回0。
- 例: POSITION('ba' IN 'bayoooyo') = 1
- 例: POSITION('yooo' IN 'bayoooyo') = 3

##### LOCATE(substr,str,pos): 与 POSITION() 类似，最后一个形参可以指定起始搜索位置。
- 例: LOCATE('yo','yolalalayoss',4) = 9
- 例: LOCATE('yo','yolalalayoss',1) = 1

##### CONCAT(str1,str2,...): 连接字符串(参数接受数字和日期等)
- 例: CONCAT(111,222,sss) = "111222sss"

##### INSERT(str,pos,len,newstr): 修改字符串。
str: 原始字符串
pos: 字符串操作的开始位置
len: 需要替换的字符数
newstr: 替换字符串

- 例: INSERT('aaabbbxxddd',7,2,'ccc') = "aaabbbcccddd"
- 例: INSERT('xxxxzzzz',5,0,'yyyy') = "xxxxyyyyzzzz"

##### SUBSTRING(str,pos,len): 获取子字符串
- 例: SUBSTRING('xxxxyyyyzzzz',5,4) = "yyyy"

### 算数

| function  | description       |
| ---       | ---               |
| Acos(x)   | arc cosine of x   |
| Asin(x)   | arc sine of x     |
| Atan(x)   | arc tangent of x  |
| Cos(x)    | cosine of x       |
| Cot(x)    | cotangent of x    |
| Exp(x)    | e ^ x             |
| Ln(x)     | natural log of x  |
| Sin(x)    | sine of x         |
| Sqrt(x)   | square root of x  |
| Tan(x)    | tangent of x      |
| MOD(x,y)  | x % y             |
| POW(x,y)  | x ^ y             |
| ABS(x)    | abs(x)            |
| SIGN(x)   | x<0:-1 x=0:0 x>0:1|

### 控制数字精度

| 函数名         | 描述        | 例子                        | 例子2                 |
| ---           | ---        | ---                        | ---                  |
| CEIL()        | 向上取整     | CEIL(72.001) = 73          |                      |
| FLOOR()       | 向下取整     | FLOOR(72.999) = 72         |                      |
| ROUND()       | 四舍五入     | ROUND(72.499) = 72         | ROUND(72.5) = 73     |
| TRUNCATE()    | 保留指定小数位| TRUNCATE(72.499,2) = 72.49 | TRUNCATE(17,-1) = 10 |

### 使用时间数据

##### 查看时区: SELECT @@global.time_zone, @@session.time_zone;
##### 设置时区: SET time_zone = 'Europe/Zurich';

| 组件   | 定义           |范围                 |
| ---   | ---           | ---                |
| yyyy  | year          | 1000 ~ 9999        |
| MM    | month         | 01(Jan) ~ 12(Dec)  |
| dd    | day           | 01 ~ 31            |
| HH    | hour          | 00 ~ 23            |
| HHH   | hour(past)    | -838 ~ 838         |
| mm    | minute        | 00 ~ 59            |
| ss    | second        | 00 ~ 59            |

| 类型       | 默认格式               |
| ---       | ---                   |
| DATE      | yyyy-MM-dd            |
| DATETIME  | yyyy-MM-dd HH:mm:ss   |
| TIMESTAMP | yyyy-MM-dd HH:mm:ss   |
| TIME      | HHH:mm:ss             |

##### CAST(expr AS type): 显式的字符串转日期(不仅是日期)
- 例: CAST('2016-02-04 12:13:14' AS DATETIME)
- 例: CAST('2016-02-04' AS DATE)