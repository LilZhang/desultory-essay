## HashSet

1. 底层即 HashMap

2. 该 map 的 value 都是同一个 object 

3. 为什么要填充 object：
- 防止NPE
- add() 与 remove() 中返回 boolean 表示是否成功
- 可用 object 来判断是否成功
