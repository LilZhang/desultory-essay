# 一些分布式的概念

## 分布式的一些特点

- 节点分布
- 节点对等
- 数据可能被并发访问
- 缺乏全局时钟
- 节点随时存在故障


## 分布式的常见问题

- 通信异常
- 网络分区
    指集群中的节点之间因通信不畅, 最后演变成若干个子集群的情况(脑裂)
- 三态
    调用的返回除了 _成功/失败_ 以外, 还有超时
- 节点故障


## 事务的 ACID

- Atomic        原子性
- Consistency   一致性 (强一致性)
- Isolated      隔离性
    - Read uncommitted  未提交读
    - Read committed    提交读 (可解决 脏读)
    - repeatable read   可重复读 (可解决 脏读, 不可重复读)
    - serialization     串行化
- Durability    持久性


## CAP (Consistency, Availability, Partition tolerance)

- Consistency   一致性
- Availability  可用性
- Partition tolerance   分区容错性

- CAP 无法同时满足, 最多满足其中两项
- 一般来说 partition tolerance 必须具备, 剩下的则在 _一致性_ 和 _可用性_ 之前权衡


## BASE (Basically Available, Soft state, Eventually consistent)

1. 概念

- Basically Available   基本可用   (服务部分可用, 高并发时降级)
- Soft state            软状态     (允许存在中间状态)
- Eventually consistent 最终一致性

2. 最终一致性的五种变种

- Causal consistency    因果一致性
    
    A 写入某值后通知 B, B 再读取

- Read your write       读己之所写

    A 读取自己刚才写入的值

- Session consistency   会话一致性

    在同一个会话中实现 "读己之所写"

- Monotonic read consistency    单调读一致性

    一旦读取到某个值后, 后续对这个值的访问不应返回更旧的值

- Monotonic write consistency   单调写一致性

    来自同一个进程的写操作被顺序执行