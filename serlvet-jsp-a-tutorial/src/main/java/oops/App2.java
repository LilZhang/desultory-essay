package oops;

import java.text.DecimalFormat;

public class App2
{
    public static void main(String[] args)
    {
        DecimalFormat df = new DecimalFormat("####0.####");
        double x = Math.pow(2d, 26d) - 1;
        System.out.println(df.format(x));
    }
}
