# 关于 ZooKeeper 的一些笔记

ZAB: Zookeeper Atomic Broadcast

## ZAB 协议的两种模式以及其运作

ZAB 将变更以事务 propesal 的形式广播出去

- 消息广播

    1. leader 将客户端的事务请求作为 _事务 Proposal_ 发送给所有 follower (Proposal)
    leader 会为每个 follower 单独分配一个 Proposal 队列
    2. 若超过半数 follower 正确返回, leader 会再向所有的 follower 分发 Commit (Commit)

- 崩溃恢复

    1. 选举新 leader (fast leader election)
    
    2. 若有新的 follower 加入, 该节点先进入崩溃恢复模式
        然后比对自身与 leader 的 ZXID 比对, leader 将该 follower 未收到的 proposal 发送给 follower 执行
        该 follower 执行完后, 进入消息广播模式

- P1, P2, C1, C2, P3
- 类似二阶段提交

运作


## ZAB 下的节点框架

- leader
    只有 leader 才能处理事务请求
    当 leader 崩溃或失联时, 选举出新 leader, 进入崩溃恢复状态
    
- follower
- observer


## 节点的类型, 包含哪些数据

- 永久节点
- 永久顺序节点
- 临时节点
- 临时顺序节点

## ZAB 如何执行一个分布式事务, 其 ZXID 的分布组成

事务编号 ZXID 

- 低 32 位, 自增, 事务流水号
- 高 32 位, 自增, leader 周期号

## 新选出的 leader 如何通过 ZXID 识别需要被跳过的事务 proposal


## ZooKeeper 集群新添加一个节点的过程(数据同步)



## ZooKeeper 如何进行 leader 的选举


## 注册 Watcher


## 现有的 ZooKeeper 客户端: ZkClient 和 Curator

1. 节点操作: 增删改

2. 监听节点改动回调

3. Master 节点选举 160

多台机器同时向 ZooKeeper 某个集群申请创建一个节点
创建成功的机器即为 Master

4. 分布式锁 162

5. 分布式计数器: DistributedAtomicInteger