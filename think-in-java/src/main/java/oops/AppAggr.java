package oops;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class AppAggr
{
    public static void main(String[] args)
    {
        try
        {
            String in = "1000001206";
            String inCn = "兴业银行";
            List<String> jsonList = FileUtils.readLines(new File("D:\\clip\\rep\\1000001206.txt"));
            for (int i = 0; i < jsonList.size(); i++)
            {
                String json = jsonList.get(i);
                Map<String, Object> map = JSON.parseObject(json, Map.class);

                // ((List<Map<String, Object>>) map.get("words")).stream().map((wm) -> wm.get("weight")).collect(Collectors.toList())
                List<Map<String, Object>> words = (List<Map<String, Object>>) map.get("words");
//            Collections.sort(words, (m1, m2) -> ((BigDecimal) m1.get("weight")).compareTo(((BigDecimal) m2.get("weight"))));
                Collections.sort(words, Comparator.comparing((m) -> ((BigDecimal) m.get("weight")).negate()));

                Iterator<String> iterator = words.stream().map((m) -> ((String) m.get("incode"))).collect(Collectors.toList()).iterator();
                int j = 0;
                boolean match = false;
                while (iterator.hasNext())
                {
                    String incode = iterator.next();
                    j += 1;

//                System.out.println(incode != null ? incode : "null");

                    if (incode != null &&
                            (incode.equalsIgnoreCase(in) || incode.startsWith(in)))
                    {
                        match = true;
                        break;
                    }
                }

                if (match)
                {
                    System.out.printf("%d.\t分词: %s(%s)\t资讯infocode: %s\t切词时间: %s\t切词总数: %d\t切词排名: %d.\n",
                            i + 1, inCn, in, map.get("infoCode"), map.get("mongoInsertDateTime"), words.size(), j);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void s()
    {

    }
}
