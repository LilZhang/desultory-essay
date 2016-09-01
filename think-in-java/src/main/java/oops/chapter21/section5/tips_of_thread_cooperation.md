## tips of thread cooperation

- wait(), notify() 和 notifyAll() 需要对锁进行操作，所以必须在 synchronized 方法或代码块中调用。在此之外的调用会产生IllegalMonitorStateException异常。

- wait() 会释放锁，sleep() 和 yield() 不会释放锁。

- synchronized(aObject): aObject的两个方法
 `synchronized hangUp() { wait(); }` 和 `synchronized prod() { notifyAll(); }`是写在一起的。