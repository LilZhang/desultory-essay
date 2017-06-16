## 概念科普
源码目录如下 (Maven, Gradle):

```
|- src
    |- main
        |- java
        |   |- com
        |       |- lilzh
        |           |- path
        |               |- demo
        |               |   |- A.java
        |               |- B.java
        |- resources
            |- com
                |- lilzh
                    |- path
                        |- demo
                        |   |- a.properties
                        |- b.properties
```

编译后目录应如下:

```
|- com
    |- lilzh
        |- path
            |- demo
            |   |- A.class
            |   |- a.properties
            |- B.class
            |- b.properties
```

1. 绝对路径示例

- 类: `com.lilzh.path.demo.A`
- 资源: `/com/lilzh/path/demo/a.properties`  (注意要以 `/` 开头)

2. 相对路径示例

- 从 A.class 去往 b.properties: `../b.properties`   (不需要以 `/` 开头)
- 从 A.class 去往 a.properties: `a.properties`
- 从 B.class 去往 a.properties: `demo/a.properties`


## 方法 Class.getResourceAsStream()
- 一般接受 _相对路径_ 作为参数
```
A.class.getResourceAsStream("../b.properties");
A.class.getResourceAsStream("a.properties");
B.class.getResourceAsStream("demo/a.properties");
```

## 方法 Class.getClassLoader().getResourceAsStream()
- 一般接受 _绝对路径_ 作为参数
- 路径之前的`/`需要另行移除
```
getClass().getClassLoader().getResourceAsStream("com/lilzh/path/demo/a.properties");
getClass().getClassLoader().getResourceAsStream("com/lilzh/path/b.properties");
```


_注: Class.getResourceAsStream() 方法最终调用的依然是 Class.getClassLoader().getResourceAsStream() 方法, 只是在其之上处理了路径而已_