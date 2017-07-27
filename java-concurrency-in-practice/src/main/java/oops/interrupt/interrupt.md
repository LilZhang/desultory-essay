# 一些关于中断的笔记

- 线程阻塞时中断
    抛出 InterruptedException

- 线程运行时中断
    仅修改该线程的中断状态 (interrupt status)
    代码中需要自行判断中断状态

- static interrupted() 和 isInterrupted() 的区别
    1. 都调用了 native isInterrupted(boolean clearStatus) 方法
    2. static interrupted() 会重置中断状态 
    3. isInterrupted() 仅检查中断状态, 不会重置