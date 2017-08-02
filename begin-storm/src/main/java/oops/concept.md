// page 58

# 关于 storm 的一些基础概念

## 元组 Tuple

- 消息传递的基本单元
- 由 Spout/Bout 发射(emit)
- 发射时需要声明 tuple 的字段(在 OutputFieldsDeclarer 中声明)
- 可以针对不同的 stream 声明不同的字段(OutputFieldsDeclarer 的 declareStream 方法)

- 元组树
- 生成时随机生成 64 位 ID

## 流 Stream

- 简言之, 一个从 Spout 起始的一个无界的元组序列
- 每个流有对应的 ID
- 流可分组(grouping)分发, 7 种分组方式

## 喷口 Spout

- 拓扑的流的来源
- 配合消息队列使用
- 接收, ack, fail
- 实现接口 IRichSpout


## 螺栓 Bolt

- 流的处理节点
- 需要应答 ack


## 拓扑 Topology

- Storm 中的一个实时应用程序
- 将 Topology 打成 jar 包提交给 storm 以运行
- 每个 Topology 可设置 worker 数量, 每个 worker 就是一个 JVM 进程
- Spout 和 Bolt 运行在 worker 之上, 每个 Spout/Bolt 线程称为 Task
- Storm 0.8 之后, Spout/Bolt 可能共用线程, 该线程被称为 Executor

## 节点

### 主控节点(master)
守护进程 Nimbus
分发任务

### 工作节点(worker)
- 工作进程 worker 属于一个特定的拓扑(Topology)
- 守护进程 Supervisor
- Nimbus 和 Supervisor 是快速失败和无状态的

工作节点 worker 死亡时, Supervisor 会尝试重启它
重启失败数次后, 由 Nimbus 将本属于该 worker 的任务分配给别的 worker

## 序列化

- 尽量给自定义类型配备自定义的序列化器
- 因为 Java 本身的序列化性能不高


## 元组的锚定 Anchoring

现有示例如下:
Spout 中发射出元组 TupleA
经 Bolt 处理后发射元组 TupleA1, TupleA2, TupleA3

可在发射元组 TupleA1, TupleA2, TupleA3 时传入 TupleA 锚定
相当于在元组树中加入新的元组关系
还可传入 tuple 集合复合锚定

```
outputCollector.emit(tupleA, new Values(tupleA1.val()))
```


## 拓扑的 Acker 任务

- 每个拓扑有一组 acker 任务, 用以跟踪元组
- 当 Spout 发送一个新的元组 tuple 时
Spout 将消息发送给 acker 告知该 spout 的任务 id 负责这个元组
- acker 以此作为确认时的依据
- 这个元组会在 acker 中存储一对 key-value : <该 spout 的任务 id, ack val>
- ack val 是这个元组树中的所有元组的 id 的 XOR(异或)
- ack val 为 0 表示该元组树已完成

- bolt 挂了: 元组树根节点 tuple 超时重发
- acker 挂了: acker 超时重发 ? (原话: 所有的 Spout 元组跟踪的 Acker 会超时并被重发)
- spout 挂了: 消息来源负责重放消息(来源可靠)

## 需要 ZooKeeper 集群


## Storm 与 Hadoop 概念对比
            Storm           Hadoop
系统角色     Nimbus          JobTracker
            Supervisor      TaskTracker
            Worker(工作进程) Child

应用名称     Topology        Job

组件接口     Spout/Bolt      Mapper/Reducer


# Storm 拓扑的并行度 (parallelism)

## 概况
```
|- Storm 集群
    |- 一至多个拓扑 (topology)
        |- 一至多个工作进程 (worker)
        
        |- 一至多个组件 (spout/bolt)
            |- 一至多个执行器 (executor): 即线程, parallelism_hint 可设置初始值, 运行中数量可变化
            |- 一至多个任务 (task): 组件的运行单元, 默认与执行器数量一致(每个执行器一个任务)
                                    若指定了任务数, 则该组件的任务会被平均分配到执行器中
```

现有 Topology 代码如下

```
    Config config = new Config();
    config.setNumWorkers(2);                                // worker: 2
    
    builder.setSpout("blue_spout", new BlueSpout(), 2);     // parallelism_hint(executor):2 , Task: 2 (default)
    
    builder.setBolt("green_bolt", new GreenBolt(), 2)       // parallelism_hint(executor):2 , Task: 4
            .setNumTasks(4)
            .shuffleGrouping("blue_spout");

    builder.setBolt("yellow_bolt", new YellowBolt(), 6)     // parallelism_hint(executor):6 , Task: 6 (default)
            .shuffleGrouping("green_bolt");
    
    StormSubmitter.submitTopology("my_topology",
            config,
            builder.createTopology());
```

数据流向图

```
                            ->  yellow_bolt
            ->  green_bolt  ->  yellow_bolt
blue_spout  ->  green_bolt  ->  yellow_bolt
blue_spout  ->  green_bolt  ->  yellow_bolt
            ->  green_bolt  ->  yellow_bolt
                            ->  yellow_bolt
```

`my_topology` 的概况如下:

- 工作进程(worker): 2 个
- 执行器(executor): 2 + 2 + 6 = 10 个
- 任务(task): 2 + 4 + 6 = 12 个

_executor(thread) 与 task 的数量平均分配至每个 worker_

_手动设置 task 数量的组件(spout/bolt), 其每个 executor 上执行的 task 数量等于对应的 task/parallelism_hint_

因此每个 worker 可获得 10/2 = 5 个 executor, 和 12/2 = 6 个 task

每个 executor 执行 1 个 task
执行 green_bolt 的每个 executor 执行两个 green_bolt task

可以使用命令对 topology 的 worker 数量, 组件的并行度进行动态调整

```
// my_topology 的 worker 数量调整到 5
// blue-spout 的并行度 调整到 3
$ storm rebalance my_topology -n 5 -e blue-spout=3
```