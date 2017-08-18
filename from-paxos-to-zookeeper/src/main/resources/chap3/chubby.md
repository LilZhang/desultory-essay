# Chubby: Google 的分布式锁服务

- 含有中心化节点的分布式锁服务 (master 节点)
- ZooKeeper 是其开源实现


## 概述

1. Chubby 树形数据结构

- 节点如 `/ls/foo/wombat/pouch` 所示
- 持久节点与临时节点


2. Chubby 节点元数据

- 实例编号
- 文件内容编号
- 锁编号
- ACL(访问控制列表)编号

- 64位单调递增


3. Chubby 节点事件通知

- 文件内容变更
- 节点删除
- 子节点更新/删除
- Master 服务器转移


4. Chubby 服务端与 Chubby 客户端缓存

- 客户端会缓存服务端的文件内容与元数据
- 缓存租期与服务端 Master 节点租期同步
- 缓存更新会话, 续租
- 客户端缓存与 Master 节点时钟不同步所导致的宽限期(危险期)存在
- 缓存应对 Master 更换进行平滑过渡


5. Chubby 服务端基本架构

- 容错日志系统(Fault-Tolerant Log): 最底层, 每个 value 都是一个 Paxos instance
- KV 型容错数据库(Fault-Tolerant DB): 封装了日志系统
- 分布式锁和小文件存储服务: 最上层, 对外提供服务

- value 可能是某个节点的数?

## Chubby 中的 Paxos (用于容错日志系统)

1. 选取一个副本节点作为 Paxos 算法的主节点

2. 主节点可以生成一个 prepare 请求, 其编号为 N, 供其他尚未达到一致性的 Paxos instance 使用
   原来由 proposer 自己生成 prepare 请求(prepare - promise), 改为使用 Master 生成的

3. prepose - accept 过程结束后, 可将自己需要 set 的值写入本地日志系统并广播 COMMIT 消息给其它副本节点
   若其它副本节点收到 COMMIT 后也更新本地日志系统
   若其它副本节点未收到 COMMIT, 可主动向集群中其它副本节点进行查询, 获取到的多数提案为 set 值
