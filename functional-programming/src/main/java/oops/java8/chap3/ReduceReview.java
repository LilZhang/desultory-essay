package oops.java8.chap3;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ReduceReview
{
    // reduce 可以从一组值中生成一个值

    private static int reduceAdd()
    {
        List<Integer> list = Collections.emptyList();
        return list.stream().reduce(
                0,  // 累加器基准值
                (arg1, arg2) -> arg1 + arg2 // 参数间执行何种方式
        );
    }

    private static MergeObject reduceMerge()
    {
        MergeObject[] mergeObjects = new MergeObject[]
                {
                    new MergeObject("sdfg", 5274),
                    new MergeObject("lkgh", 986),
                    new MergeObject("zcsa", 8532),
                    new MergeObject("khig", 2386)
                };

        return Stream.of(mergeObjects)
                .reduce(new MergeObject("origin", 11), (mgObj1, mgObj2) -> mgObj1.merge(mgObj2));
    }

    /*object before merge: MergeObject{content='origin', value=11}
    param before merge: MergeObject{content='sdfg', value=5274}
    object after merge: MergeObject{content='origin -> sdfg', value=5285}
    here we go

    object before merge: MergeObject{content='origin -> sdfg', value=5285}
    param before merge: MergeObject{content='lkgh', value=986}
    object after merge: MergeObject{content='origin -> sdfg -> lkgh', value=6271}
    here we go

    object before merge: MergeObject{content='origin -> sdfg -> lkgh', value=6271}
    param before merge: MergeObject{content='zcsa', value=8532}
    object after merge: MergeObject{content='origin -> sdfg -> lkgh -> zcsa', value=14803}
    here we go

    object before merge: MergeObject{content='origin -> sdfg -> lkgh -> zcsa', value=14803}
    param before merge: MergeObject{content='khig', value=2386}
    object after merge: MergeObject{content='origin -> sdfg -> lkgh -> zcsa -> khig', value=17189}
    here we go*/

    public static void main(String[] args)
    {
        reduceMerge();
    }

    private static class MergeObject
    {
        private String content;

        private int value;

        public MergeObject(String content, int value)
        {
            this.content = content;
            this.value = value;
        }

        public String getContent()
        {
            return content;
        }

        public void setContent(String content)
        {
            this.content = content;
        }

        public int getValue()
        {
            return value;
        }

        public void setValue(int value)
        {
            this.value = value;
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("MergeObject{");
            sb.append("content='").append(content).append('\'');
            sb.append(", value=").append(value);
            sb.append('}');
            return sb.toString();
        }

        public MergeObject merge(MergeObject mergeObject)
        {
            if (mergeObject != null)
            {
                System.out.println("object before merge: " + this);
                System.out.println("param before merge: " + mergeObject);

                StringBuilder sb = new StringBuilder(getContent());
                sb.append(" -> ").append(mergeObject.getContent());
                this.setContent(sb.toString());
                this.setValue(getValue() + mergeObject.getValue());

                System.out.println("object after merge: " + this);
                System.out.println("here we go");
                System.out.println();

                return this;
            }
            return null;
        }
    }
}
