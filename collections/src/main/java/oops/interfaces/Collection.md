## 接口 Collection

1. 没有直接的实现，一般实现 Set, List

2. 实现类一般有两个构造器
- 默认的
- Collection 类型的参数，用以复制别的集合

3. size() 的上限是 Integer 的上限：2 ^ 31 - 1

4. toArray() 其实是新建了一个array，顺序由 iterator() 决定

5. Object[] toArray() 方法与 <T> T[] toArray(T[] a)的不同
- 后者可以确定 array 的运行时类型
- 如果给定的 array a 大小合适，填充之并返回
- 如果给定的 array a 太小，返回一个更大的(同类型的)新的数组
- 如果给定的 array a 太大，空余部分会被填 null

6. remove(Object o) 如果要删的对象在集合中有多个与之 equal() 的对象会怎样？
- 仅删除第一个