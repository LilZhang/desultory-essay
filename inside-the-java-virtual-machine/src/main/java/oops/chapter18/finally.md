# finally

## preview
- finally 相当于一个“微型子例程”
- try 和 catch 字节码后面都有指向 finally 的指令

1. try{} catch{} finally{}
- 执行 try，抛出异常。
- 执行 catch
- 执行 finally // finally 将返回值存至`局部变量`而不是直接返回
- jvm 弹出栈帧，回到上层方法。(若有需要，异常被抛至上层方法)
// 若 try 与 finally 中都有 return
// try 的抛出异常在其 return 之前，所以 try 的 return 并没有被执行到
// 但是 finally 的 return 一定能被执行到

2. try{return;} finally{return;}
- 执行 try
- 执行 finally // finally 将返回值存至`局部变量`而不是直接返回
- jvm 弹出栈帧，回到上层方法。(若有需要，异常被抛至上层方法)

## finally 所属指令

- jsr 行号: 跳转至 finally
// try 中可以有 jsr to finally
// catch 中也可以有 jsr to finally
- ret 局部变量索引: 将 finally 的返回值存入局部变量指定的位置
// 为什么不直接 return 出来而是 ret 到局部变量里？
// 因为 finally 语句中还有return, break, continue, 以及抛异常
// 不一定能执行到 ret

// PS: 其实 finally 块中的 return 大致有以下步骤
// 1 ret 至 局部变量
// 2 iload_n 或者 xload_n 至操作数栈
// 3 ireturn 或 areturn

- 示例
```
try
{
bytecode
0 ... 17 (含)
}

finally
{

}
```

字节码中自动有如下异常表
from	to	target	type
0	18	18	`any`
