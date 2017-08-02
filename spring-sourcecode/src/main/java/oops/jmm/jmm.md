# 关于 JMM 内存模型

JVM 需要维护内部线程类似顺序化语义 (within-thread as-if-serial semantics)

即: 程序执行的最终结果等同于它在严格的顺序化中执行的结果



- 存在各级重排序(以优化性能为目的)

1. 字节码级别重排序
2. 指令级别重排序

_重排序可以使 双重锁检查(DCL) 失效_
可以加 volatile 修复(JDK 1.5+)

- 内存屏障(memory barriers)

一些特殊指令(SaveSave, SaveLoad)
平台相关

- happens-before
各种原则
volatile
内部用内存屏障实现

JMM 的最终效果: 决定了什么时候写入一个变量会对其他线程可见