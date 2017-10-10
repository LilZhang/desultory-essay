package oops.basicGuavaUtilities;

import com.google.common.base.Joiner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseJoiner
{
    private static final List<String> STR_LIST = Arrays.asList("str1", "str2", null, "str3", null, "str4");

    public static void main(String[] args)
    {
        String joinStr1 = Joiner.on("~")    // 创建一个 Joiner 对象并配置规则
//                .skipNulls()
                .useForNull("[null]")       // 配置的规则
                .join(STR_LIST);

        // str1~str2~[null]~str3~[null]~str4
        System.out.println(joinStr1);


        Joiner joiner2 = Joiner.on("-").useForNull("[null]");
        StringBuilder sb = new StringBuilder();
        joiner2.appendTo(sb, "sb1", null, "sb2");   // 可以用来对 StringBuilder 进行操作 (Appendable)

        // sb1-[null]-sb2
        System.out.println(sb.toString());


        try (FileWriter fileWriter = new FileWriter("D:\\test.txt", true))
        {
            // 可以用来对 FileWriter 进行操作 (Appendable)
            joiner2.appendTo(fileWriter, "file_writer_1\n", "file_writer_2\n", null, "file_writer_3\n");
            fileWriter.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // 输出流在 try() 中自动释放


        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put(null, "value_NULL");
        map.put("key3", "value3");

        String strMap = Joiner.on(", ")
                .useForNull("[null]")
                .withKeyValueSeparator("=")
                .join(map);

        // key1=value1, [null]=value_NULL, key2=value2, key3=value3
        System.out.println(strMap);
    }
}
