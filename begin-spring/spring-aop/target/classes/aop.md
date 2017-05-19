# AOP 面向切面编程

## 概念点

1. JoinPoint(接合点): 某个方法中的某一个_点_，在这个点切入新的逻辑

2. Advice(通知): 所要在 JoinPoint 切入的_逻辑_

3. Pointcut(切入面): 一群接合点聚合成_面_
- 切入面可由一个或多个的_表达式_来描述
- 比如: `execution(public * *(..))` 表示所有公共方法
- 比如: `within(oops.util.MyClass+)` 表示 MyClass 类及其子类的所有方法
- 比如: `@annotation(oops.anno.MyAnno)` 表示所有被该注解类修饰的方法

4. Target(目标): JoinPoint(接合点) 所在的对象，也就是被切入的每一个_对象_

5. Weaving(编织): 将切入的逻辑与_目标_结合在一起的过程
- 编译时编织
- 加载时编织
- 运行时编织 (spring aop 使用 Proxy 模式以该方式实现 aop)

## spring aop 常用 JoinPoint (接合点)

- Before
- afterReturning
- afterThrowing
- after
- around


