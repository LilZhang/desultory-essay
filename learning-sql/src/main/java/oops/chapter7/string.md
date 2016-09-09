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
