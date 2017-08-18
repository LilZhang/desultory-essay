# paxos 算法

## 角色

1. proposer (提案提交者): 可以提交若干个提案(Mn, Vn)给 acceptor, 待 acceptor 批准
2. acceptor (提案批准者): 批准提案
3. learner (提案获取者): 获取已经批准提案

提案以(M0编号, V0值)格式存在
每一个节点可以同时作为多个角色存在

## 算法

1. proposer 生成提案

- proposer 生成一个新提案 (Mn, Vn), 向某个 acceptor 集合(超过半数) 发送请求 (prepare请求)
- 同时 proposer 要求该 acceptor 集合中的 acceptor 做出如下回应:

    - 承诺不再批准任何编号小于 Mn 的提案
    - 若有 acceptor 已经批准过任何提案, 返回已批准的编号小于 Mn 的最大编号的提案 value 给 proposer

- proposer 收集齐 acceptor 的响应 (超过 acceptor 总数的半数即可)
- proposer 找出最大的 value 作为 Vnx, 向某个 acceptor 集合(可以和之前不同) 发送批准请求 (accept请求)
- 提案为(Mn, Vnx)


2. acceptor 批准提案

- 容错容灾: acceptor 可以忽略任何请求
- acceptor 可以收到 proposer 的两种请求 (prepare请求/accept请求)

    - 可以在任意时刻响应一个 prepare 请求
    - 在不违背现有承诺的前提下, 任意响应 accept 请求

- 因为 acceptor 不会批准一个比已批准的提案编号更小的提案
- 所以实质上可以忽略所有的 _比已批准的提案编号更小的提案_ 的 prepare 和 accept 请求
- acceptor 可以记住已批准的提案最大编号和已响应 prepare 请求的最大编号



3. learner 获取提案 value

- 只要获取集合中的多数 value 即可




## 算法证明

- todo