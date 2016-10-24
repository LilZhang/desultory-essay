# 方法调用与返回

## 方法调用
- invokevirtual: 调用实例方法	// 动态(迟)绑定
- invokestatic: 调用类方法	// 静态(早)绑定

// 先解析 对应 CONSTANT_Methodref (装载，连接以及初始化)
// 调用时将形参所需值压入操作数栈中(实例方法还需要将 objectref 压入)
// jvm 为该方法建立一个新的栈帧，包括：
- 局部变量空间	// 可在编译时得出大小	// 实例方法的 局部变量0 是 this
- 操作数栈	// 可在编译时得出大小
- 帧信息区

// 再将该新建的栈帧压入当前线程的 java 栈
// 本地方法除外。当进入本地方法栈时，java失效。本地方法返回时java栈重新被启用。

## 特殊的
1. invokespecial: 
- 调用 `<init>`
- 调用私有非静态方法
- 调用 super 方法

2. invokeinterface:
- 调用接口方法

- invokespecial 和 invokevirtual 的区别
invokespecial 根据引用的类型选择方法(静态绑定)
invokevirtual 根据对象的类来选择方法(动态绑定) 
// 比如调用一个对象的方法
// 方法实际上在父类，则运行父类的代码

```
public class App 
{
    public static void main( String[] args )
    {
        SubClass subClass = new SubClass();
        subClass.method(); 	// invokevirtual: 动态绑定
				// 绑定到父类的方法
    }

    private static class SuperClass
    {
        public void method()
        {
            invoke(); // invokespecial: 静态绑定
        }

        private void invoke()
        {
            System.out.println("super class invoke()");
        }
    }

    private static class SubClass extends SuperClass
    {
        void invoke()
        {
            System.out.println("sub class invoke()");
        }
    }
}
```

1. 静态绑定
- invokestatic(公开或私有的静态方法)
- invokespecial(`<init>`, private, super.*)

2. 动态绑定
- invokevirtual(对于类)
- invokeinterface(对于接口)
