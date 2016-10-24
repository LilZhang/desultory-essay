# 异常
- Error 和 RuntimeException 的子类为未检查异常，其余为检查异常
- 在下列示例中，OverflowException与 DivideByZeroException 为自定义的异常，检查异常
- ArithmeticException 属于未检查异常

1. 示例方法求余数源码
```
static int remainder(int dividend, int divisor)
	throws OverflowException, DivideByZeroException
{
	if ((dividend == Integer.MIN_VALUE) && 
		(divisor == -1))
	{
		throw new OverflowException();
	}
	
	try
	{
		return dividend % divisor;
	}
	catch(ArthmeticException e)
	{
		throw new DivideByZeroException();
	}
}
```

2. 示例方法求余数字节码(部分)
```
0	iload_0		// 将局部变量0 (dividend) 压入操作数栈

1	ldc #1		// #1: Integer.MIN_VALUE
			// 将常量池中的值压入操作数栈

3	if_icmpne 19	// 若 Integer.MIN_VALUE != dividend ，goto 19

6	iload_1		// 将局部变量0 (divisor) 压入操作数栈

7	iconst_m1	// 将 -1 压入操作数栈

8	if_icmpne 19	// 若 divisor != -1 ，goto 19


// src code throw: throw new OverflowException();

11	new #4		// #4: CONSTANT_Class: OverflowException

14	dup		// duplicate the reference of new OverflowException Object right now

15	invokespecial #10 // method OverflowException()

18	athrow

// src code throw end


// try
// return dividend % divisor;

19	iload_0		// 将局部变量0 (dividend) 压入操作数栈

20	iload_1		// 将局部变量0 (divisor) 压入操作数栈

21	irem		// dividend % divisor

22	ireturn		// return 

// catch

23	pop		// ?

24	new #2		// #2: CONSTANT_Class: DivideByZeroException

27	dup		// duplicate the reference

28	invokespecial #9 // method DivideByZeroException()

31	athrow
```

3. 该方法的异常表如下
Exception table for method remainder(int, int)
from	to	target	type
19	23	23	#3	// CONSTANT_Class: ArithmeticException
				// 若有多个 catch 则该表格有多行

from:	try 的开始行号
to:	try 的结尾行号(不含)
target:	如果捕获到异常的话，跳到哪儿(也就是PC寄存器下一步指向哪儿)
type:	异常的常量池入口 // CONSTANT_Class

4. 异常抛出
- 当异常在方法执行时被抛出时，jvm 会在该方法的异常表中_按顺序_检索
- 如果遇到第一个匹配项时，检查当前计数器是否在 from to 之间
- 若是，则下一步执行该 target 位置的指令(也就是PC寄存器下一步指向 target )

- 若翻了半天找不到异常表项
- jvm 从当前栈帧中弹出，到上一层调用它的上层方法，抛出同样的异常
- (在上一层栈帧中继续寻找 上层方法 异常表)
