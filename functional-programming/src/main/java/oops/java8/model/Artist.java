package oops.java8.model;

import java.util.List;

public class Artist
{
    private String name;

    private List<String> members;

    private String origin;

    public Artist()
    {
    }

    public Artist(String name, List<String> members, String origin)
    {
        this.name = name;
        this.members = members;
        this.origin = origin;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<String> getMembers()
    {
        return members;
    }

    public void setMembers(List<String> members)
    {
        this.members = members;
    }

    public String getOrigin()
    {
        return origin;
    }

    public void setOrigin(String origin)
    {
        this.origin = origin;
    }
}
