package oops.java8;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class App
{
    public static void main(String[] args)
    {
        ArrayList<MenuConditionMarkPoint> points = new ArrayList<>();
//        points.add(new MenuConditionMarkPoint("menu_1", 1L));
//        points.add(new MenuConditionMarkPoint("menu_2", 2L));
//        points.add(new MenuConditionMarkPoint("menu_3", 3L));
//        points.add(new MenuConditionMarkPoint("menu_4", 4L));
//        points.add(new MenuConditionMarkPoint("menu_5", 5L));
//        points.add(new MenuConditionMarkPoint("menu_6", 6L));
//        points.add(new MenuConditionMarkPoint("menu_7", 7L));
//        points.add(new MenuConditionMarkPoint("menu_8", 8L));
//        points.add(new MenuConditionMarkPoint("menu_8", 4L));
//        points.add(new MenuConditionMarkPoint("menu_8", 2L));

        /*Map<String, Long> menuCodeMarkPointMap = points.stream()
                .collect(Collectors.groupingBy((markPoint) -> markPoint.getMenuCode(),
                        Collectors.mapping((markPoint) -> markPoint.getTimestamp(),
                                Collectors.reducing(0L, (acc, ele) -> 0L == acc.longValue() ? acc + ele : acc))));*/

        Map<String, Long> menuCodeMarkPointMap = points.stream()
                .collect(Collectors.groupingBy((markPoint) -> markPoint.getMenuCode(),
                        Collectors.mapping((markPoint) -> markPoint.getTimestamp(),
                                Collectors.reducing(0L, (acc, ele) -> ele))));

        System.out.println(menuCodeMarkPointMap);
    }


    public static class MenuConditionMarkPoint
    {
        public String menuCode;

        public long timestamp;

        public MenuConditionMarkPoint(String menuCode, long timestamp)
        {
            this.menuCode = menuCode;
            this.timestamp = timestamp;
        }

        public String getMenuCode()
        {
            return menuCode;
        }

        public void setMenuCode(String menuCode)
        {
            this.menuCode = menuCode;
        }

        public long getTimestamp()
        {
            return timestamp;
        }

        public void setTimestamp(long timestamp)
        {
            this.timestamp = timestamp;
        }
    }
}
