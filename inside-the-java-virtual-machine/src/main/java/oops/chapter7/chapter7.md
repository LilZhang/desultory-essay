# 类的生命周期
## snapshot
1. 装载

2. 连接
2.1 验证
2.2 准备
2.3 解析(可选)

3. 初始化

4. 对象实例化

5. 垃圾收集

6. 对象终结

// 生命周期是交错的

## 装载
- 由 ClassLoader 读取 .class 文件(启动 or 自定义)
- Class<?> defineClass(String name, byte[] classData, int off, int len)
1. 通过完全限定名产生二进制流
2. 解析二进制流并存入方法区 (.class 的常量池到该类的运行时常量池)
3. 在堆中创建一个该类的 java.lang.Class 实例

- 装载时的验证
1. 格式
2. 装载该类的时候需确保父类已被装载 // ?

## 连接
- 把 .class 文件中的数据合并入 jvm
- 三个子步骤

// protected final void resolveClass(Class<?> c)

### 验证
- 检查 .class 中的数据(以下简称数据)是否格式正确等

装载时的验证阶段：
1. 装载时的检查格式等属于验证
2. 装载时检查类的父类是否被装载属于验证

正式的验证阶段：
3. 检查final的类不能拥有子类
4. 检查final的方法不能被Override
5. 确保类与超类之间没有不兼容的方法
6. 检查所有常量池入口一致(eg. CONSTANT_String 的 string_index 必须指向 CONSTANT_Utf8)
7. 检查常量池中的所有特殊字符串(类名，字段名， 方法名， 字段描述符， 方法描述符)是否符合格式
8. 检查字节码的完整性

解析时的验证阶段：
9. 解析时的符号引用的验证(指向是否存在，是否有权限)属于验证

### 准备
- 分配内存
- 赋默认初始值(类似 char 为 '\u0000')
- 给方法表分配内存


### 解析(可选)
- 把常量池中的符号引用变成直接引用(offset maybe)	// 又叫常量池解析 // 检查存在性及权限
- 可被推迟
- 可在初始化后进行 (能行么)

## 初始化
- 给static变量赋正确的初始值
- 执行static{}
- 上述两个行为会被编译器收集到 <clinit> 里面
- 相当于类初始化时会执行 <clinit>
- 若多个线程要初始化同一个类，仅允许一个线程来执行初始化方法

关于 <clinit>
1. 类的 <clinit> 与其父类的 <clinit> 并不是调用与被调用的关系。是后执行与先执行的关系。
2. 如果类没有静态变量也没有静态初始化块，也就没有 <clinit>
3. 如果 类中只有 static final (静态最终变量，这玩意儿直接被当成常量替换于字节码中)，也没有 <clinit>

- 初始化的时机被严格定义(首次主动使用时初始化)
主动使用：
1. 创建某个类的实例
2. 调用某个类的静态方法
3. 使用某个类的静态字段
4. 反射调用某个类
5. 初始化某个类
6. main()那个类

另：
1. 某个类被初始化时，其父类已被初始化，其实现的接口已被装载
2. 某个接口被初始化，其父接口一般不被初始化

被动使用(不会被初始化)
1. 引用了该类的父类或者接口的字段，该类不会被初始化
2. 引用了该类的static final变量


# 对象的生命周期
- 显式实例化一个对象的途径
1. new
2. Class或者java.lang.reflect.Constructor对象的newInstance()
3. clone()
4. java.io.ObjectInputStream的getObject()	// 反序列化

- 可能被隐式实例化的对象
1. args中的String对象
2. Class对象
3. .class 中的 CONSTANT_String : 在class被装载的时候，新的String对象会在队中被创建
4. 字符串拼接

## 分配内存并初始化
- 类中和父类中的变量都会被分配内存
- 指向方法区的指针
- 赋默认初始值
- 赋初始值
1. clone(): 拷贝原值到新对象
2. ObjectInputStream.readObject(): 从输入流中读入
3. 其他：<init>

关于 <init>
- <init> 中的代码大致可分为三种：
1. 类或者父类的 <init>
2. 非 static 变量值的初始化
3. {} 初始化代码块

- <init> 可以分为两种：
1. 默认以 super() 开始
- 父类的 <init> (Object 没有父类)
- 非 static 变量值的初始化
- {} 初始化代码块

2. 以 this() 开始
- 类的 <init>
- {} 初始化代码块

## 垃圾收集与对象终结
- finalize(): GC前执行一次


# 类的卸载
- 当程序不再引用某类型，则该类可被卸载(垃圾回收)
- 启动类装载器(BOOTSTRAP)装载的类永远可被触及，所以永远不会被卸载
- 若某个类的 class 对象无法被 GC 触及，则该类是不可触及的 (被 GC 触及是什么情况？)

- 某个类会被卸载的例子
```
MyClassLoader myClassLoader = new MyClassLoader();
Class<?> clazz = myClassLoader.loadClass("some.other.Classes");
some.other.Classes classesObj = (some.other.Classes) clazz.newInstance();

myClassLoader = null;
classesObj = null;
clazz = null;
```

然后 some.other.Classes 这个类因为无法被触及，于是被卸载了。














