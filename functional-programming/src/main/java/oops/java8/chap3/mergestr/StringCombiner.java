package oops.java8.chap3.mergestr;

public class StringCombiner
{
    private String delimiter;

    private String prefix;

    private String suffix;

    private StringBuilder stringBuilder;

    public StringCombiner()
    {
        this.stringBuilder = new StringBuilder();
    }

    public StringCombiner(String delimiter, String prefix, String suffix)
    {
        this();
        this.delimiter = delimiter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public StringCombiner append(String str)
    {
        if (this.stringBuilder.length() > 0)
        {
            this.stringBuilder.append(delimiter);
        }
        this.stringBuilder.append(str);
        return this;
    }

    public StringCombiner append(StringCombiner sc)
    {
        this.stringBuilder.append(delimiter).append(sc.stringBuilder);
        return this;
    }

    @Override
    public String toString()
    {
        return this.stringBuilder.insert(0, prefix)
                .append(suffix)
                .toString();
    }

    public String getDelimiter()
    {
        return delimiter;
    }

    public void setDelimiter(String delimiter)
    {
        this.delimiter = delimiter;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
    }

    public StringBuilder getStringBuilder()
    {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder)
    {
        this.stringBuilder = stringBuilder;
    }
}
