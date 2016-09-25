## 接口 Iterable

1. 实现了该接口的类的对象可在foreach语句中作为被循环对象


## 接口 Iterator

1. Iterator 取代了原有的 Enumeration 接口

2. 相对于 Enumeration 的优势
- Iterator 在迭代中可以删除元素
- 方法名更优雅

3. 如果迭代中已经没有元素了，再调用 next() 会抛出 NoSuchElementException

4. 每个元素只能被 remove() 一次，如果被 remove() 了多次会抛出 IllegalStateException


