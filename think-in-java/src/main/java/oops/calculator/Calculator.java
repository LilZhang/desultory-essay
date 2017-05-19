package oops.calculator;

import javax.tools.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Calculator
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("正常计算： " + Integer.toString((1 + 2) * 3 - 4 / 2));
        System.out.println(calculate("(1 + 2) * 3 - 4 / 2"));
    }

    private static double calculate(String expr) throws Exception
    {
        String packName = Calculator.class.getPackage().getName();
        String className = "CalculatorMain";
        String methodName = "calculate";
        String source = "package " + packName + "; public class " + className
                + " { public static double " + methodName + "() { return " + expr
                + "; } }";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        StringSourceJavaObject sourceObject = new StringSourceJavaObject(className, source);

        ClassLoader loader = Calculator.class.getClassLoader();
        String flag = "-d";
        String outDir = loader.getResource("").getPath();
        Iterable<String> stringdir = Arrays.asList(flag, outDir);
        Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(sourceObject);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, stringdir, null, fileObjects);
        boolean result = task.call();
        if (result) {
            System.out.println("编译成功");
            loader.getResource("");
            try {
                Class<?> clazz = loader.loadClass(packName + "." + className);
                Method method = clazz.getMethod(methodName, new Class<?>[] {});
                Object value = method.invoke(null, new Object[] {});
                return (Double) value;
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new Exception("错误的表达式。 ");
        }
    }

    static class StringSourceJavaObject extends SimpleJavaFileObject
    {
        private String content = null;

        public StringSourceJavaObject(String name, String content) throws URISyntaxException
        {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException
        {
            return content;
        }
    }
}