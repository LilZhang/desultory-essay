package oops.calculator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-10
 * Project        : desultory-essay
 * File Name      : CalculatorJS.java
 */
public class CalculatorJS
{
    private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");

    public static void main(String[] args)
    {
        System.out.println(calculate("(1 + 2) * 3 - 4 / 2"));   // 7.0
        System.out.println(calculate("1 / 0"));                 // Infinity
        System.out.println(calculate("0 / 0"));                 // NaN
        System.out.println(calculate("fxxk"));                  // java.lang.IllegalArgumentException: illegal expr
    }

    public static double calculate(String expr) throws IllegalArgumentException
    {
//        ScriptEngineManager sem = new ScriptEngineManager();
//        ScriptEngine engine = sem.getEngineByName("javascript");
        String temp = "var result = calculate(); function calculate(){ return %s; }";

        doValid(expr);

        try
        {
            engine.eval(String.format(temp, expr));
        }
        catch (ScriptException e)
        {
            throw new IllegalArgumentException("illegal expr", e);
        }

        Double result = (Double) engine.get("result");
        return result.doubleValue();
    }

    private static void doValid(String expr)
    {
        // TODO
    }
}
