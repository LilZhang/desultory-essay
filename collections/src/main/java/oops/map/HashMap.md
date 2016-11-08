## HashMap

1. HashMap 是以 数组 + 链表 (数组的每个元素内有一个链表) 作为底层的容器

2. 底层数组 (table) 的大小为 capacity (默认 16)，Map 中 Entry 的数量为 size ，切勿混淆
	table 的 最大数量应该是 2 ^ 30

3. 参数 loadFactor (负载因子) 表述该 Map 的负载程度，若负载因子较大则负载程度较高(table 的每个元素的链表较多)
	若负载因子较小则负载程度较低(table 的每个元素的链表较少)

4. 默认的 loadFactor 的值为 0.75

5. 当前的 负载因子 如何计算： size / capacity

5. threshold (阈值) 如何计算： capacity * loadFactor ，默认 0.75 capacity

6. 当 size 到达阈值时，table 扩容。以 2倍 扩容。
	扩容 -> table.length 改变 -> hashseed 改变 -> hash 改变 -> 生成下标算法改变 -> 在 new table 的位置改变 -> 移动到新位置

7. hash 是经过处理的 hashcode

8. 为什么以 2倍 扩容底层数组：
- 为了 在用 hash 算取 table 下标值时更加方便
- 一般来说，用 hash % table.length(取模) 此类运算来获取下标。
- 但是 %(取模)运算 太耗时耗资源。
- 当 table.length 为 2 ^ n (n 为正整数) 时
- hash % table.length 等价于 
- hash & (table.length - 1)

9. HashMap 接受 null 值作为 key，该 null 值所在的 Entry 会被放在 table[0] (一般在代码中特殊处理)

0. hashseed & hash & rehash & resize


