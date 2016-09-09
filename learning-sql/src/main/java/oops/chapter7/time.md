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


##### STR_TO_DATE('DEC-21-1980', '%b-%d-%Y'): 格式化日期

| 简写 | 含义                               |
| --- | ---                                |
| %a  | 星期简写 (Sun, Mon, ...)            |
| %b  | 月简写 (Jan, Feb, ...)              |
| %c  | 月数字 (0..12)                    |
| %d  | 日对于月的次序 (00..31)              |
| %f  | 毫秒数(000000..999999)             |
| %H  | 小时(24H) (00..23)                 |
| %h  | 小时(12H) (01..12)                 |
| %i  | 分钟 (00..59)                      |
| %j  | 日对于年的次序(001..366)             |
| %M  | 月全称 (January, February, ...)    |
| %m  | 月数字                             |
| %p  | AM 或 PM                           |
| %s  | 秒 (00..59)                        |
| %W  | 星期全称 (Sunday..Saturday)         |
| %w  | 日对于星期的次序 (0=周日, 6=周六)      |
| %Y  | 年(4位数字)                         |


### 操作时间数据


##### CURRENT_DATE(): 当前日期
##### CURRENT_TIME(): 当前时间
##### CURRENT_TIMESTAMP(): 当前时间戳


##### DATE_ADD(date,INTERVAL expr unit): 为指定日期增加时间间隔
- 例: DATE_ADD('2016-09-09',INTERVAL 5 DAY) = '2016-09-14'
- 例: DATE_ADD('2016-09-09 02:00:00',INTERVAL 25 MINUTE) = '2016-09-09 02:25:00'

| unit          | description                       |
| ---           | ---                               |
| SECOND        | count of second                   |
| MINUTE        | count of minute                   |
| HOUR          | count of hour                     |
| DAY           | count of day                      |
| MONTH         | count of month                    |
| YEAR          | count of year                     |
| MINUTE_SECOND | 'min_count:sec_count'             |
| HOUR_SECOND   | 'hour_count:min_count:sec_count'  |
| YEAR_MONTH    | 'year_count-month_count'          |

- DATE_ADD('2016-09-09 02:00:00',INTERVAL '3:66:78' HOUR_SECOND) = '2016-09-09 06:07:18'


##### LAST_DAY(date): 返回某个日期对应的月末日
- 例: LAST_DAY('2016-09-02') = '2016-09-30'

##### CONVERT_TZ(dt,from_tz,to_tz): 将一个时区的 datetime 转换到另一个时区
- 例: CONVERT_TZ('2016-09-09 10:02:00','US/Eastern','UTC')

##### DAYNAME(date): 返回某个日期对应是星期几
- 例: DAYNAME('2016-09-09') = "Friday"

##### EXTRACT(unit FROM date): 从某个日期中抽出某个部分的值(见unit表)
- 例: EXTRACT(YEAR FROM CURRENT_DATE()) = 2016

##### DATEDIFF(expr1,expr2): 计算出两个时间的间隔天数
- 例: DATEDIFF('2016-09-01','2016-06-01') = 92