## java 集合框架
[原文](http://docs.oracle.com/javase/7/docs/technotes/guides/collections/overview.html)

### 组成

1. Collection 接口(Collection interfaces)

2. 常用实现(General-purpose implementations)

3. 遗留实现(Legacy implementations): 指的是`Vector`和`Hashtable`

4. 特殊用途实现(Special-purpose implementations)

5. 并发实现(Concurrent implementations)

6. 包装实现(Wrapper implementations): Collections.synchronizedList() ?

7. 便捷实现(Convenience implementations): 微实现

8. 抽象实现(Abstract implementations): 一般为具象实现的父类

9. 算法(Algorithms)

10. 基础设施(Infrastructure)

11. 工具类?(Array Utilities)


### 集合框架接口

- 一般分为 java.util.Collection 和 java.util.Map


### 集合可以怎样分类

1. 可修改的 vs 不可修改的 (unmodifiable vs modifiable)

2. 不变的 vs 可变的 (immutable vs mutable) : 并没有看懂

3. 可扩容的 vs 不可扩容的 (variable-size vs fixed-size)

4. 随机 vs 连续 (random access vs sequential access)

### 常用实现表(实现方式 + 接口)

| Interface | Hash Table | Resizable Table | Balanced Tree | Linked List | Hash Table + Linked List |
| ---       | ---        | ---             | ---           | ---         | ---                      |
| Set       | HashSet    |                 | TreeSet       |             | LinkedHashSet            |
| List      |            | ArrayList       |               | LinkedList  |                          |
| Deque     |            | ArrayDeque      |               | LinkedList  |                          |
| Map       | HashMap    |                 | TreeMap       |             | LinkedHashMap            |

> All of the new implementations have fail-fast iterators

### RandomAccess vs sequential access 遍历方式

在以下代码段，RandomAccess表现更好
```
    for (int i=0, n=list.size(); i < n; i++)
    {
        list.get(i);
    }
```

在以下代码段，sequential access 表现更好
```
    for (Iterator i=list.iterator(); i.hasNext(); )
    {
        i.next();
    }
```