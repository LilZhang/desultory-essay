# Salutation程序的连接

Salutation.java

```
package oops.demo.t1016;

public class Salutation
{
	private static final String hello = "Hello, world!";
	private static final String greeting = "Greetings, planet!";
	private static final String salutation = "Salutation, orb!";

	private static int choice = (int) (Math.random() * 2.99);

	public static void main(String[] args)
	{
		String s = hello;
		if (choice == 1)
		{
			s = greeting;
		}
		else if (choice == 2)
		{
			s = salutation;
		}
		
		System.out.println(s);
	}
}
```

Salutation.class
```
Classfile /home/lilzhang/dev/oops/demo/t1016/Salutation.class
  Last modified Oct 16, 2016; size 850 bytes
  MD5 checksum 4b4a55cc0a55f846184c68bb8318836e
  Compiled from "Salutation.java"
public class oops.demo.t1016.Salutation
  SourceFile: "Salutation.java"
  minor version: 0
  major version: 51
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #12.#31        //  java/lang/Object."<init>":()V
   #2 = String             #32            //  Hello, world!
   #3 = Fieldref           #11.#33        //  oops/demo/t1016/Salutation.choice:I
   #4 = String             #34            //  Greetings, planet!
   #5 = String             #35            //  Salutation, orb!
   #6 = Fieldref           #36.#37        //  java/lang/System.out:Ljava/io/PrintStream;
   #7 = Methodref          #38.#39        //  java/io/PrintStream.println:(Ljava/lang/String;)V
   #8 = Methodref          #40.#41        //  java/lang/Math.random:()D
   #9 = Double             2.99d
  #11 = Class              #42            //  oops/demo/t1016/Salutation
  #12 = Class              #43            //  java/lang/Object
  #13 = Utf8               hello
  #14 = Utf8               Ljava/lang/String;
  #15 = Utf8               ConstantValue
  #16 = Utf8               greeting
  #17 = Utf8               salutation
  #18 = Utf8               choice
  #19 = Utf8               I
  #20 = Utf8               <init>
  #21 = Utf8               ()V
  #22 = Utf8               Code
  #23 = Utf8               LineNumberTable
  #24 = Utf8               main
  #25 = Utf8               ([Ljava/lang/String;)V
  #26 = Utf8               StackMapTable
  #27 = Class              #44            //  java/lang/String
  #28 = Utf8               <clinit>
  #29 = Utf8               SourceFile
  #30 = Utf8               Salutation.java
  #31 = NameAndType        #20:#21        //  "<init>":()V
  #32 = Utf8               Hello, world!
  #33 = NameAndType        #18:#19        //  choice:I
  #34 = Utf8               Greetings, planet!
  #35 = Utf8               Salutation, orb!
  #36 = Class              #45            //  java/lang/System
  #37 = NameAndType        #46:#47        //  out:Ljava/io/PrintStream;
  #38 = Class              #48            //  java/io/PrintStream
  #39 = NameAndType        #49:#50        //  println:(Ljava/lang/String;)V
  #40 = Class              #51            //  java/lang/Math
  #41 = NameAndType        #52:#53        //  random:()D
  #42 = Utf8               oops/demo/t1016/Salutation
  #43 = Utf8               java/lang/Object
  #44 = Utf8               java/lang/String
  #45 = Utf8               java/lang/System
  #46 = Utf8               out
  #47 = Utf8               Ljava/io/PrintStream;
  #48 = Utf8               java/io/PrintStream
  #49 = Utf8               println
  #50 = Utf8               (Ljava/lang/String;)V
  #51 = Utf8               java/lang/Math
  #52 = Utf8               random
  #53 = Utf8               ()D
{
  public oops.demo.t1016.Salutation();
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0       
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return        
      LineNumberTable:
        line 3: 0

  public static void main(java.lang.String[]);
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: ldc           #2                  // String Hello, world!
         2: astore_1      
         3: getstatic     #3                  // Field choice:I
         6: iconst_1      
         7: if_icmpne     16
        10: ldc           #4                  // String Greetings, planet!
        12: astore_1      
        13: goto          26
        16: getstatic     #3                  // Field choice:I
        19: iconst_2      
        20: if_icmpne     26
        23: ldc           #5                  // String Salutation, orb!
        25: astore_1      
        26: getstatic     #6                  // Field java/lang/System.out:Ljava/io/PrintStream;
        29: aload_1       
        30: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        33: return        
      LineNumberTable:
        line 13: 0
        line 14: 3
        line 16: 10
        line 18: 16
        line 20: 23
        line 23: 26
        line 24: 33
      StackMapTable: number_of_entries = 2
           frame_type = 252 /* append */
             offset_delta = 16
        locals = [ class java/lang/String ]
           frame_type = 9 /* same */


  static {};
    flags: ACC_STATIC
    Code:
      stack=4, locals=0, args_size=0
         0: invokestatic  #8                  // Method java/lang/Math.random:()D
         3: ldc2_w        #9                  // double 2.99d
         6: dmul          
         7: d2i           
         8: putstatic     #3                  // Field choice:I
        11: return        
      LineNumberTable:
        line 9: 0
}

```

1. jvm 将 oops.demo.t1016.Salutation 作为参数传给 启动类装载器
2. 启动类装载器 获取得 类的二进制形式 // 并创建一个该类的 Class 实例
3. jvm 查看并解析 oops.demo.t1016.Salutation 的 super_class (CONSTANT_Class)，
这将导致其父类 java.lang.Object 被装载
4. 然后开始 “连接并初始化” 过程，顺序与装载相反，从 java.lang.Object 开始
5. Object “连接并初始化” 过程结束，开始连接 Salutation
6. 连接步骤一：验证。
- Salutation 二进制是否正确
- Salutation 是否正确的实现了Java的语义
- Salutation 的字节码是否会导致 jvm 崩溃
7. 连接步骤二：准备
- 给类变量 choice 分配内存空间
- 给类变量 choice 赋默认初始值0
- 另外三个字符串是编译时常量，在编译时已经被替代为 CONSTANT_String
8. 装载，验证，准备都完成时，就可以初始化了。
// 解析是可选的，而且时机不固定
// 比如早解析，迟解析
9. 执行 <clinit> 方法 // 在这之前执行的是 Object 的 <clinit>
<clinit> 方法包括
- 初始化类变量值
- static {}

Salutation 的 <clinit>
```
         0: invokestatic  #8                  // Method java/lang/Math.random:()D
         3: ldc2_w        #9                  // double 2.99d
         6: dmul          
         7: d2i           
         8: putstatic     #3                  // Field choice:I
        11: return        

```

10. invokestatic #8	CONSTANT_Methodref	java/lang/Math.random:()D
10.1 解析常量池 #8 CONSTANT_Methodref (若已解析则直接使用直接引用)
10.2 装载，连接并初始化 java.lang.Math (如已装载则无需再装载)
10.3.1 在常量池 #8 处放入一个指向 random() 方法的直接引用
10.3.2 将常量池 #8 这个入口标记为已解析
10.3.3 把 invokestatic 操作码替换成 invokestatic_quick
10.4 执行 incokestatic (或 invokestatic_quick), 
返回值会被压入main()的操作数栈中

11. ldc2_w #9		CONSTANT_Double		2.99d
11.1 解析常量池 #9 CONSTANT_Double  // 2.99d 直接解析 (若已解析则直接使用直接引用)
11.2 将常量池 #9 这个入口标记为已解析
11.3 把 ldc2_w 替换为 ldc2_w_quick
11.4 把 2.99 压入操作数栈

// 思考问题，为什么常量池中没有 #10
// 答：因为 Double 一般占两个字长
// 而常量池的单位长度一般为一个字长

12. dmul	// 弹出两个 double, 相乘，把结果压栈
13. d2i		// cast double to int

14. putstatic #3	CONSTANT_Fieldref	oops.demo.t1014.Salutation.choice:I
14.1 解析常量池 #3 CONSTANT_Fieldref (若已解析则直接使用直接引用)
14.2 在常量池 #3 处放入一个指向本类 choice 的直接引用
14.3 将常量池 #3 这个入口标记为已解析
14.4 将 putstatic 操作码替换成 putstatic_quick
14.5 将操作数栈里面的值(刚才被转成int的两个double的乘积)
弹出，放入类变量 choice

15. return	// 通知 jvm 方法结束
		// <clinit> 结束
		// oops.demo.t1016.Salutation 初始化完成

16. jvm 开始执行 main() 方法
main() 的字节码
```
         0: ldc           #2                  // String Hello, world!
         2: astore_1      
         3: getstatic     #3                  // Field choice:I
         6: iconst_1      
         7: if_icmpne     16
        10: ldc           #4                  // String Greetings, planet!
        12: astore_1      
        13: goto          26
        16: getstatic     #3                  // Field choice:I
        19: iconst_2      
        20: if_icmpne     26
        23: ldc           #5                  // String Salutation, orb!
        25: astore_1      
        26: getstatic     #6                  // Field java/lang/System.out:Ljava/io/PrintStream;
        29: aload_1       
        30: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        33: return        

```

17. ldc #2	CONSTANT_String		"Hello, world!"
// 解析该CONSTANT_String 入口是 ldc 指令的一部分
17.1 解析常量池 #2 CONSTANT_String (若已解析则直接使用字符串拘留表中引用)
17.2 解析过程中，创建一个新的字符串对象，并拘留(intern)它
17.3 在常量池 #2 入口放入对这个字符串对象的直接引用
17.4 将常量池 #2 标记为已解析
17.5 将 ldc 操作码替换为 ldc_quick 操作码
17.6 将字符串对象的引用(reference)压入操作数栈

18. astore_1	// 将操作数栈中刚才压入的引用弹出
		// 并存入该方法的第一个局部变量

19. getstatic #3 CONSTANT_Fieldref	// oops.demo.t1016.Salutation.choice:I
19.1 查看常量池入口 #3 发现已经被解析(至本类的choice字段)，可以使用直接引用
19.2 将 getstatic 替换为 getstatic_quick
19.3 使用常量池入口 #3 得到的直接引用获取到 choice 的值，并将其压入操作数栈

20. iconst_1	// 在操作数栈中压入 int 1
21. if_icmpne 16
21.1 弹出操作数栈中的两个数，相减。
21.2 若不为0(?)，更新PC寄存器的下一条指令(16)，跳转至指令16 (getstatic #3)
21.3 跳转至16

22. getstatic #3	// 同(19.)
23. iconst_2		// 将 int 2 压入操作数栈
24. if_icmpne 26	// 同(21.) 跳转至26
25. getstatic #6	CONSTANT_Fieldref	// java.lang.System.out::Ljava/io/PrintStream;
25.1 装载，连接并初始化 java.lang.System
25.2 把常量池 #6 放入对 java.lang.System的静态变量out的直接引用
25.3 把 getstatic 操作码替换为 getstatic_quick
25.4 将java.lang.System的out字段的直接引用(reference)压入操作数栈

26. aload_1	// 将局部变量1中的对象("Salutations, orb!")压入操作数栈
27. invokevirtual #7  CONSTANT_Methodref	// java/io/PrintStream.println:(Ljava/lang/String;)V
27.1 解析常量池 #7  CONSTANT_Methodref
27.2 装载，连接并初始化 java.io.PrintStream
27.3.1 在常量池 #7 处放入一个指向 println() 方法的直接引用
27.3.2 将常量池 #7 这个入口标记为已解析
27.3.3 把 invokevirtual 操作码替换成 invokevirtual_quick
27.4 执行 invokevirtual (或 invokevirtual_quick) 

28. return	// 方法结束
		// 没有其他非守护(daemon)线程了
		// jvm 退出

- 其中有一条指令 ldc #4 (// "greeting planet")没有执行到
- 所以也没有被解析过
- 所以，该字符串对象没有被jvm创建


## 总结
1. jvm 传入 main()所在类的全限定名给启动类装载器，装载该类
2. 装载该类 -> 该类的父类 (递归)
2.1 装载该类实现的接口
3. 连接并初始化，从Object一直至该类 
3.1 <clinit>,也就是 static {}
3.2 接口不被初始化，仅装载
4. 连接并初始化该类
5. 验证，准备过后即可初始化 // <clinit>
6. 初始化过程中字节码遇到尚未解析的常量池入口，解析之

6.1 若为该类的字段或方法
- 获取字段或方法的直接引用，放入该常量池入口
- 该常量池被标记为已解析，下次可直接使用直接引用
- 若已解析，则使用直接引用
- 字节码可被 _quick 优化 // 早期实现
- 继续执行字节码	// 对操作数栈的压入或出栈

6.2 若为其它类的字段或方法
- 先装载，连接并初始化那个类(仅当那个类未被装载的时候)
- 其它同 6.1

6.3 若常量池入口为 CONSTANT_String
- 创建一个 String 对象，并拘留(intern)它
- 若拘留表中已有该 String 对象，使用该拘留表中的引用
- 其它同 6.1










