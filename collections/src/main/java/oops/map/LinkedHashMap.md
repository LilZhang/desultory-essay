## LinkedHashMap

1. 拓展(继承)自 HashMap

2. 除了哈希/单向链表以外，Entry 中多了 before & after 字段，作为双向链表

3. 增加 boolean accessOrder 字段(默认 false)
- false: 迭代时按照插入顺序(insertion order)
- true: 迭代时按照读取顺序(access order)，最近被读取的排最后(用于LRU缓存)
- LRU：Least Recently Used
