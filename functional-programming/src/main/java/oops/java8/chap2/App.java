package oops.java8.chap2;

import java.math.BigDecimal;
import java.util.function.*;

public class App
{
    // 一些 lambda 的接口

    public static final Predicate<String> PREDICATE = (str) -> str.length() > 0;

    public static final Function<String, Integer> FUNCTION = (str) -> Integer.parseInt(str);

    public static final Consumer<BigDecimal> CONSUMER = (bigDecimal) ->
    {
        // do something
        // and no need to return
    };

    public static final Supplier<Integer> SUPPLIER = () -> Integer.MIN_VALUE;

    public static final UnaryOperator<Integer> UNARY_OPERATOR = (param) -> param + 1;

    public static final BinaryOperator<Integer> BINARY_OPERATOR = (param1, param2) -> param1 + param2;


    public static final ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "init_value");
}
