一些已有的 Collector 例子
- Collectors.mapping
- Collectors.groupingBy
- Collectors.joining

1. 解析 Collector 接口

```
public interface Collector<T, A, R>
{
    Supplier<A> supplier();
    BiConsumer<A, T> accumulator();
    BinaryOperator<A> combiner();
    Function<A, R> finisher();
}
```

泛型参数:
- T: 输入类型(对合并字符串来说就是 String)
- A: 处理时的容器类型(可能是 StringBuilder 之类), 容器可能有多个实例
- R: 最后的输出结果

- Supplier<A> supplier():         // 容器工厂类
- BiConsumer<A, T> accumulator(): // 输入类型如何进入容器
- BinaryOperator<A> combiner():   // 两个容器如何合并为一个
- Function<A, R> finisher():      // 容器如何转换为结果返回

2. 解析 Stream 中的 reduce

```
public interface Stream<T> extends BaseStream<T, Stream<T>>
{
    ...

    <U> U reduce(U identity,
                     BiFunction<U, ? super T, U> accumulator,
                     BinaryOperator<U> combiner);

    ...
}
```

泛型参数:
- T: 输入类型
- U: reduce 之后的返回类型

- U identity:                                   // 初始值
- BiFunction<U, ? super T, U> accumulator:      // 结合已有结果, 合并下一个输入, 返回新的结果(有点类似于容器)
- BinaryOperator<U> combiner:                   // 两个(容器)之间如何合并成一个

3. 合并字符串

- 参见 oops.java8.chap3.mergestr.MergeString