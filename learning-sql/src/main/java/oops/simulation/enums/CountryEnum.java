/*
 *
 * Copyright (c) 2111-2115 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation.enums;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-24
 * Project        : desultory-essay
 * File Name      : CountryEnum.java
 */
public enum CountryEnum implements Probable<String>
{
    ARGENTINA("Argentina", new String[]{"Spanish"}, 1),

    AUSTRALIA("Australia", new String[]{"English"}, 3),

    BRAZIL("Brazil", new String[]{"Portuguese"}, 2),

    CANADA("Canada", new String[]{"English", "French"}, 6),

    CHINA("China", new String[]{"Chinese"}, 15),

    FRANCE("France", new String[]{"French", "Arabic"}, 4),

    GERMANY("Germany", new String[]{"German"}, 5),

    INDIA("India", new String[]{"Hindi", "English"}, 7),

    ITALY("Italy", new String[]{"Italian"}, 1),

    JAPAN("Japan", new String[]{"Japanese"}, 6),

    SOUTH_KOREA("South Korea", new String[]{"Korean", "Chinese"}, 3),

    MEXICO("Mexico", new String[]{"Spanish", "Portuguese"}, 1),

    RUSSIA("Russia", new String[]{"Russian"}, 3),

    SAUDI_ARABIA("Saudi Arabia", new String[]{"Arabic"}, 2),

    SOUTH_AFRICA("South Africa", new String[]{"English", "French"}, 1),

    TURKEY("Turkey", new String[]{"Turkish", "Arabic"}, 1),

    UNITED_KINGDOM ("United Kingdom", new String[]{"English"}, 9),

    UNITED_STATES("United States", new String[]{"English", "French", "Portuguese", "Spanish", "Chinese", "German", "Arabic", "Italian"}, 42),

    BELGIUM("Belgium", new String[]{"German", "French"}, 1),

    DENMARK("Denmark", new String[]{"Danish", "Swedish"}, 1),

    FINLAND("Finland", new String[]{"Danish", "Swedish"}, 1),

    NETHERLANDS("Netherlands", new String[]{"Dutch", "French"}, 1),

    POLAND("Poland", new String[]{"Polish", "Russian"}, 1),

    PORTUGAL("Portugal", new String[]{"Portuguese"}, 1),

    SPAIN("Spain", new String[]{"Spanish"}, 1),

    SWEDEN("Sweden", new String[]{"Swedish"}, 1),

    IRAN("Iran", new String[]{"Persian", "Arabic"}, 1),

    KAZAKHSTAN("Kazakhstan", new String[]{"Russian"}, 1),

    IRAQ("Iraq", new String[]{"Arabic"}, 1),

    VIETNAM("Vietnam", new String[]{"Vietnamese", "Chinese"}, 1),

    NORTH_KOREA("North Korea", new String[]{"Korean", "Chinese"}, 1),

    ISRAEL("Israel", new String[]{"Hebrew", "Arabic"}, 1),

    NIGERIA("Nigeria", new String[]{"English", "French"}, 1),

    EGYPT("Egypt", new String[]{"Arabic"}, 1),

    ALGERIA("Algeria", new String[]{"French"}, 1),

    KENYA("Kenya", new String[]{"English", "Arabic"}, 1),

    ETHIOPIA("Ethiopia", new String[]{"English", "Arabic"}, 1),

    CAMEROON("Cameroon", new String[]{"French", "Spanish"}, 1),

    COTE_D_IVOIRE("Cote DIvoire", new String[]{"French", "Spanish"}, 1),

    ;

    private String country;

    private String[] phylum;

    private int probability;

    CountryEnum(String country, String[] phylum, int probability)
    {
        this.country = country;
        this.phylum = phylum;
        this.probability = probability;
    }

    public String getCountry()
    {
        return country;
    }

    public String getValue()
    {
        return country;
    }

    public int getProbability()
    {
        return probability;
    }

    public String[] getPhylum()
    {
        return phylum;
    }

    public static CountryEnum get(String country)
    {
        CountryEnum countryEnum = null;

        if (country != null)
        {
            for (CountryEnum cEnum : values())
            {
                if (country.equals(cEnum.getCountry()))
                {
                    countryEnum = cEnum;
                    break;
                }
            }
        }

        return countryEnum;
    }
}
