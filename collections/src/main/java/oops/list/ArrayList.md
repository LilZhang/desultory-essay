## ArrayList
1. 初始大小为10， 1.5 倍 扩容

1. ArrayList 大体上近似于 Vector。(Vector 线程安全，ArrayList 线程不安全)

2. 底层为 array 。

3. 若要一次性批量添加大量元素时，可手动调用 ensureCapacity 扩容以提高性能。

4. 使 ArrayList 线程安全的方法：1. 操作时加锁；2. Collections.synchronizedList(new ArrayList(...));

5. 依靠 modCount 来判断是否被别的线程入侵的方式存在风险。

6. 在 1.7 中，构造器 ArrayList() 会将底层的 array 初始化成 (公共的) 空array，
    待第一次 add() 时初始化成 size 为 10 的 array。

7. System.arraycopy(): 如果 src 和 dest 为同一个 array，
    src 中需要复制的部分会先被复制到一个临时 array 上，
    然后再往 dest 上 copy

8. ArrayStoreException 会在什么情况下发生：
- src 或者 dest 有一方不为 array
- 两方为不同的基本类
- 一方为基本类一方为引用类

9. 部分拷贝？？
