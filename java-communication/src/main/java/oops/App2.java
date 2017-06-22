package oops;

import java.util.Date;

public class App2
{
    public static void main(String[] args)
    {
        /*String str1 = "123456789";
        String str2 = "1234";
        String str3 = null;
        System.out.println(getIndustryIncode(str1));
        System.out.println(getIndustryIncode(str2));
        System.out.println(getIndustryIncode(str3));*/

        System.out.println(new Date().getTime());
    }

    private static String getIndustryIncode(String incode)
    {
        String result = incode;
        if (incode != null && incode.trim().length() >= 6)
        {
            result = incode.substring(0, 6);
        }
        return result;
    }
}
