## tips of interrupt

- 所有可以抛出 InterruptedException 的都可以被中断

- IO无法中断，NIO可以

- 为了中断IO，可以釜底抽薪，关闭IO以响应中断

- NIO主动中断与被动中断(关闭NIO)时抛出的异常不一样

- 因synchronized被别人获取而造成的阻塞无法被中断

- 但Lock(被别人获取而造成的阻塞)可以被中断，甚至互斥的Lock锁也能被中断

- 如果代码中有无法中断的部分，可以使用 Thread.interrupted() 检查中断，同时在对应的 finally 块里做好对应的清理工作

