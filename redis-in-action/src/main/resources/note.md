# redis 持久化

1. RDB 快照

- 备份数据
- 有时间间隔
- 最后一次备份以后的数据可能会丢失


2. AOF 操作记录

- 记录写操作
- 文件可能非常大
- AOF 文件可以被重写


# redis 发布与订阅

- PUBLISH
- SUBSCRIBE


# redis 事务

1. 乐观加锁

2. 涉及的命令

- WATCH
- MULTI
- EXEC
- UNWATCH
- DISCARD

- MULTI 相当于 START TRANSACTION

MULTI 命令来源于 redis 会将之后的命令放入一个队列之中, 等 EXEC 时执行

- EXEC 相当于 COMMIT

```bash
WATCH key_1
MULTI

# do something

EXEC    # 若 key_1 已被更换/删除, 执行失败
```


# redis non-transactional pipeline (管道)

1. 非事务型流水线

- 一次发送多条命令, 减少网络 IO
- batch 操作

```bash
$(echo -en "PING\r\n SET runoobkey redis\r\nGET runoobkey\r\nINCR visitor\r\nINCR visitor\r\nINCR visitor\r\n"; sleep 10) | nc localhost 6379

+PONG
+OK
redis
:1
:2
:3
```

# redis 主从

1. 从服务器

- 连接主服务器, 发送 SYNC 明令
- 对于这段时间如何响应客户端命令请求:
    1. 使用旧数据
    2. 返回错误 (以上两者选其一由配置决定)
- 接收到主服务器的数据快照
- 丢弃旧数据, 用快照恢复数据
- 接收主服务器缓存写命令, 执行

2. 主服务器

- 等待接收 SYNC 命令
- 执行 BGSAVE 命令, fork 出一个子进程保存当前的数据快照
- 将备份数据时执行的写命令存入缓冲区
- 数据备份执行完毕, 向从服务器发送数据文件
- 数据文件发送完毕, 发送缓冲区的写命令至从服务器以执行
- 缓冲区命令发送完毕, 接下来每一个写命令, 都会发送给从服务器


# redis sentinel


# 用 redis 实现分布式锁

1. 要求

- 加锁
- 释放锁
- 锁超时

2. 原理 
    
    1. WATCH + MULTI + EXEC
    2. SETNX (set if not exist) + (WATCH) DEL (EXEC)

执行成功则获取到锁, 失败则没有获取到


# redis 底层数据结构

1. list: 底层一般为 doubly linked list
2. hash: 底层一般为 hash table
3. sorted set: 底层一般为 hash table + skip list

4. 以上三种底层结构都可以被压缩为 ziplist