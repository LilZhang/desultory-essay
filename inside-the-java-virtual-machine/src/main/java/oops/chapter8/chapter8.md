# 连接模型

## 动态连接和解析

- 每个 .class 有一个常量池
- 每个被 jvm 装载的 .class 有一个运行时常量池，映射到 .class 的常量池
- 指令可以指向常量池入口。每一个常量池入口只被解析一次，
解析过后其他指向该常量池入口的指令都可以使用第一次解析之后的直接引用结果

解析策略：
- 早解析：在main()方法被调用前完成完全连接
- 迟解析：在访问每个符号引用的最后一刻去解析

## 解析和动态扩展
- 动态装载类(eg. JDBC Driver)

关于 Class Class.forName(String className)
- Class.forName 的重载方法：
Class.forName(String className, boolean initialize, ClassLoader loader)
className: 类名。
initialize: true返回之前连接并初始化，false只装载。
loader: 类装载器，null为启动类装载器。

关于 ClassLoader 的 Class loadClass()	// 装载并返回 Class 对象
- 重载方法：
Class loadClass(String className, boolean resolve)
className: 类名。
resolve: true返回之前连接，false只装载。

若resolve为false，该类可能没有被连接，也可能被连接了。
。。。。。。。。。。该方法过时(Deprecated)了
。。。。。。。。。。不早说


loadClass() 相比 forName():
1. 特定的装载类的方法，比如字节码从网络下载，从数据库读取，加密解密
2. 不同的ClassLoader提供了不同的namespace，可提供安全防护(保护域)

假设 Cat 被 ClassLoader cl1 装载
当jvm解析至Cat中指向Mouse类的引用时
- 先检查 Mouse 类是否在 cl1 的 namespace 中
- 如果没有，cl1 装载 Mouse 类 (其他 namespace 的Mouse类不可见)

## 类装载器与其父类装载器
现有类装载器如下：

BOOTSTRAP
  |
  v
Grandma
  |
  v
Mom
  |
  v
Cindy

调用 ClassLoader Cindy 去装载一个类，Cindy 会先委派父类去装载，以此类推

BOOTSTRAP	(定义类装载器，初始类装载器)
  ^ | class
  | V
Grandma		(初始类装载器)
  ^ |
  | V
Mom		(初始类装载器)
  ^ |
  | V
Cindy		(初始类装载器)

若 BOOTSTRAP 能装载或已经装载了该类，原路返回 Class 实例

BOOTSTRAP
  
  
Grandma		(定义类装载器，初始类装载器)
  ^ | class
  | V
Mom		(初始类装载器)
  ^ |
  | V
Cindy		(初始类装载器)

每个类装载器 会维护一张表，表中是每个 类装载器 以及 将该类装载器作为初始类装载器的类 的映射	// 这些表组成namespace
(以下简称类装载器及其类表)

## 常量池解析

### 解析 CONSTANT_Class
- eg. this_class 指向 CONSTANT_Class

#### 指向数组类
- eg. [I (基本类型数组), [Ljava/lang/String; (引用类型数组) // 有对应的 class 对象
1. 若为引用数组：eg. [Ljava.lang.Integer;
- 检查当前类装载器是否为该数组类的初始类装载器。若是，则使用同样的类。
- 若不是，则用当前装载器装载元素类 (此例中是 java/lang/Integer)
- 确定维数
- 创建该数组类的Class实例，将```当前类装载器```定义为该数组类的定义类装载器

2. 若为基本类型数组： eg. [I
- 检查当前类装载器是否为该数组类的初始类装载器。若是，则使用同样的类。
- 若不是，则立即创建关于 I 的新数组类，并确定维度
- 创建该数组类的Class实例，将```启动类装载器```定义为该数组类的定义类装载器


#### 指向非数组类与接口
snapshot:
1a. 装载类型或父类(装载)
1b. 检查访问权限(验证)

2. 连接和初始化新解析的类型及其父类
2a. 验证类型
2b. 准备类型
2c. 解析类型(可选)
2d. 初始化类型

===================================

装载：
1a. 类型被装载
- 检查该类型是否被装载入当前namespace	// 检查类装载器及其类表
- 若已装载，则使用已装载的类(方法区的类数据 & 堆中的该类的Class对象)
- 若未装载，则使用当前类装载器来装载类

- 如果当前类装载器为启动类装载器，则以默认方式装载
- 如果为自定义类装载器，则调用该类的 loadClass() 方法
- 装载可向上委派
- 装载时可调用别的类装载器(包括启动类装载器)，甚至形成调用链
1. 调用别的自定义类装载器：调用其 loadClass(String className)	// 内部一般调用defineClass()
2. 调用启动类装载器：调用其 findSystemClass(String className)

- 调用链上的类装载器都是这个类的初始类装载器
- 只有返回这个 Class 对象的类装载器为定义类装载器

- 装载后检查字节码，查看父类是否被装载。若没有被装载则装载父类，一直到Object
// 父类在该类的 super_class 中，该入口也是 CONSTANT_Class 。。。于是又开始了(递归)

- 从Object返回途中，再依次检查每个父类是否实现接口。若有，则```装载```该接口
- 若接口有父接口，```装载```其父接口，以此类推
// 接口在该类的 interfaces 中，该入口也是 CONSTANT_Class 。。。于是又开始了(递归)

可能会出现的错误(Error)
1. NoClassDefFoundError	// 找不到该二进制数据
2. ClassNotFountError
3. ClassFormatError
4. UnsupportedClassVersionError
5. ClassCircularityError	// 把自己当自己的父类，把自己当自己的接口
6. IncompatibleClassError	// 父类是个接口，父接口是个类

1b. 检查访问类型的权限 (校验)
- 如果没有权限，抛出错误

可能会出现的错误(Error)
1. IllegalAccessError

===================================

2. 连接和初始化新解析的类型及其父类 // 时机由早解析或迟解析决定
- (之前的)装载
类 -> 父类(接口) -> Object(父接口)

- 初始化
父类 -> 类	// 与装载相反 // 接口除外，接口只是被装载

2a. 校验类型
- 思考以下场景：
某个类中有变量`(static) ClassA classA = new ClassB();`
则在这个过程中 ClassA 类与 ClassB 类都会被装载，并且验证 ClassB 类是否是 ClassA 类的子类

可能会出现的错误(Error)
1. VerifyError

2b. 准备类型
- 分配内存(类变量与方法表)

2c. 解析类型(可选)
- 重复本文中的步骤(对被指向的CONSTANT_Class的类的解析，也就是递归解析，[装载，连接(验证，准备，解析)])

2d. 初始化类型
- 初始化类型按照 父类 -> 子类的顺序执行 <clinit>

可能会出现的错误(Error)
1. ExceptionInInitializerError
2. OutOfMemoryError

### 解析 CONSTANT_Fieldref
1. 解析对应的 CONSTANT_Class
2. 对于 CONSTANT_Fieldref 的 CONSTANT_NameAndType 的解析
- 在已解析的被引用的 CONSTANT_Class 中 查找 对应名字与类型的 字段
- 若有，则查找成功并作为结果，标记为已解析，并这个常量池入口存入这个字段的直接引用
- 若无，查找 在已解析的被引用的 CONSTANT_Class 的```接口(与之父接口)```
- 若无，查找 在已解析的被引用的 CONSTANT_Class 的```父类(递归至所有父类)```
- 再没有，查找失败。			Error: NoSuchFieldError
- 若找到，但是权限不对，查找失败。	Error: IllegalAccessError

### 解析 CONSTANT_Methodref
1. 解析对应的 CONSTANT_Class
- 若 CONSTANT_Class 指向一个接口，则抛出异常 Error: IncompatibleClassChangeError

2. 对于 CONSTANT_Methodref 的 CONSTANT_NameAndType 的解析
- 在已解析的被引用的 CONSTANT_Class 中 查找 对应名字与类型的 方法
- 若有，则查找成功并作为结果，标记为已解析，并这个常量池入口存入这个方法的直接引用
- 若无，查找 在已解析的被引用的 CONSTANT_Class 的```父类(递归至所有父类)```
- 若无，查找 在已解析的被引用的 CONSTANT_Class 的```接口(与之父接口)```	// 顺序与 CONSTANT_Fieldref 略有不同
- 再没有，查找失败。			Error: NoSuchMethodError
- 若找到，但是方法是抽象方法，查找失败。	Error: AbstractMethodError
- 若找到，但是权限不对，查找失败。	Error: IllegalAccessError

### 解析 CONSTANT_InterfaceMethodref
1. 解析对应的 CONSTANT_Class
- 若 CONSTANT_Class 指向一个类，则抛出异常 Error: IncompatibleClassChangeError

2. 对于 CONSTANT_Methodref 的 CONSTANT_NameAndType 的解析
- 在已解析的被引用的 CONSTANT_Class 中 查找 对应名字与类型的 方法
- 若有，则查找成功并作为结果，标记为已解析，并这个常量池入口存入这个方法的直接引用
- 若无，查找 在已解析的被引用的 CONSTANT_Class 的```接口(与之父接口)```	// 顺序与 CONSTANT_Fieldref 略有不同
- 再没有，查找失败。			Error: NoSuchMethodError
- 无需检查权限，接口中的方法都是 PUBLIC 的。

### 解析 CONSTANT_String
- 其中的 string_index 指向 CONSTANT_Utf8

// 每个jvm会维护一张表(string_intern表)
// 所有出现过的string字面量，相同的Unicode字面量在表中有且只有一个
// 所以 "abc" == "abc" 为 true

### 解析 其它类型
- CONSTANT_Integer
- CONSTANT_Float
- CONSTANT_Long
- CONSTANT_Double

直接使用值

### 装载约束

？？？？？

### 编译时常量解析
- static final
- 限 基本类型 & java.lang.String

- 编译时常量支持 switch

- 对于编译时常量的条件编译：
```
static final flag = true;

if (flag)	  // flag 被直接替换为 true // 不过字节码中没有 boolean // 非 0 的 int 即为 true
{
  // do something // 会被编译为字节码
}
else
{
  // do something 2 // 不会被编译为字节码，因为不可到达
}
```

### 直接引用

1. 指向类
- 指向类型，类变量，类方法的直接引用为本地指针

2. 指向对象
对象的直接引用一般为偏移量(offset)

现有：
interface Friendly 
{
	void sayHello();
	void sayGoodBye();
}

class Dog
{
	public int wagCount = ... ;

	public void sayHello(){...}
	public String toString(){...}
}

class CockerSpaniel extends Dog implements Friendly
{
	public int woofCount = ... ;
	public int wimperCount = ... ;

	public void sayHello(){...}	// override
	public void sayGoodBye(){...}	// override
}

class Cat implements Friendly
{
	public void eat(){...}
	public void sayHello(){...}	// override
	public void sayGoodBye(){...}	// override
	protected void finalize(){...}
}

Dog 实例的布置
0	ptr to method area
1	wagCount

CockerSpaniel 实例的布置
0	ptr to method area
1	wagCount	// 不论在 Dog 还是在 CockerSpaniel (包括其他Dog子类)中
			// wagCount总被解析为 offset 1
2	woofCount
3	wimperCount

Cat 实例的布置
0	ptr to method area

// 以下方法表只是某一种实现方式
// 静态方法不包含在其中
// 构造器也不在其中

偏移量	方法表			方法区中的类数据
Dog
0	ptr to clone()		Object
1	ptr to equals(Object)	Object
2	ptr to finalize()	Object
3	ptr to getClass()	Object
4	ptr to hashCode()	Object
5	ptr to notify()		Object
6	ptr to notifyAll()	Object
7	ptr to toString()	Dog	// to Dog
8	ptr to wait()		Object
9	ptr to wait(long)	Object
10	ptr to wait(long, int)	Object
11	ptr to sayHello()	Dog	// to Dog

CockerSpaniel
0	ptr to clone()		Object
1	ptr to equals(Object)	Object
2	ptr to finalize()	Object
3	ptr to getClass()	Object
4	ptr to hashCode()	Object
5	ptr to notify()		Object
6	ptr to notifyAll()	Object
7	ptr to toString()	Dog	// to Dog
8	ptr to wait()		Object
9	ptr to wait(long)	Object
10	ptr to wait(long, int)	Object
11	ptr to sayHello()	CockerSpaniel	// to CockerSpaniel
						// Dog 及其子类的该方法
						// offset 都是 11
12	ptr to satGoodbye()	CockerSpaniel	// to CockerSpaniel

Cat
0	ptr to clone()		Object
1	ptr to equals(Object)	Object
2	ptr to finalize()	Cat	// to Cat
3	ptr to getClass()	Object
4	ptr to hashCode()	Object
5	ptr to notify()		Object
6	ptr to notifyAll()	Object
7	ptr to toString()	Object
8	ptr to wait()		Object
9	ptr to wait(long)	Object
10	ptr to wait(long, int)	Object
11	ptr to eat()		Cat	// 与Dog，CockerSpanel的
					// offset 开始不同
12	ptr to sayHello()	Cat
13	ptr to sayGoodbye()	Cat

- 虽然Cat 和 Dog， CockerSpaniel 都实现了 Friendly 接口
- 但是部分方法的偏移量不同

思考以下情况

```
Dog dog = new CockerSpaniel();
Friendly fr = (Friendly) dog;
fr.sayHello();		// pos 1

Friendly fr2 = new Cat();
fr2.sayGoodbye();	// pos 2
```

pos 1 & pos 2 处的方法指向实际上是 CONSTANT_InterfaceMethodref

- 调用 CONSTANT_InterfaceMethodref 会搜索对象的类(的方法表)来调用方法
- 这种调用接口引用实例的方法
- 比直接调用类的实例的方法要慢很多

### _quick
- 替换字节码
- ldc -> ldc_quick





























