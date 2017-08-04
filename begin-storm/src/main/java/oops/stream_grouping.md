# 流分组

## 随机分组 Shuffle Grouping

随机的发送到指定 Bolt 的某一个 task 上


## 字段分组 Fields Grouping

根据某个字段发送数据
若按照字段 user_id, 相同 user_id 的元组被发送至指定 Bolt 的同一个 task 上
最简单实现方式, 按照该 Bolt 的 task 数量哈希取余


## 广播分组 All Grouping

发送到指定 Bolt 的所有 task 上
慎用


## 全局分组 Global Grouping

发送到指定 Bolt 的同一个 task 上
ID 最小的那个


## 无分组 None Grouping

效果和随机分组(Shuffle Grouping)一样
不同的是
若使用无分组, 发射 Bolt 和接收 Bolt 会在同一个 Executor 中执行


## 直接分组 Direct Grouping

由元组的生产者决定元组的目标 Bolt 的 task
限制
1. 流必须为直接流 (Direct Stream)
2. 发射时使用 emitDirect 方法


## 本地或者随机分组 Local or Shuffle Grouping

优先分配给同一个 worker 的目标 Bolt 的 task
其余同随机分组(Shuffle grouping)

## 自定义分组 Custom Grouping

需实现 CustomStreamGrouping 接口