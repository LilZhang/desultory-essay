package oops;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AppRegex {

    private static final String PATH = "/Users/lili/lili/dev/projects/test_java_cmd/test_regex.txt";

    private static final String REGEX = "[a-z]+_[a-zA-Z0-9]+@[a-zA-Z0-9_-]+\\.(com|net)";

    public static void main(String[] args) {

        try {
            String yo = FileUtils.readFileToString(new File(PATH));

            Pattern pattern = Pattern.compile(REGEX);

            Matcher matcher = pattern.matcher(yo);

            while (matcher.find()) {

                System.out.println("matcher.start(): " + matcher.start());
                System.out.println("matcher.end(): " + matcher.end());
                System.out.println("matcher.group(): " + matcher.group());
                System.out.println("matcher.hitEnd(): " + matcher.hitEnd());

                System.out.println();

            }


            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
