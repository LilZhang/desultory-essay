package oops;

import java.io.FileInputStream;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        FileChannel channel = null;
        try
        {
            channel = new FileInputStream("E:\\DESKTOP\\r.txt").getChannel();

            long tmpSize = 10, channelSize = channel.size();
            for (long pos = 0; pos < channelSize; pos += tmpSize)
            {
                if (pos + tmpSize > channelSize)
                {
                    tmpSize = channelSize - pos;
                }
                MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, pos, tmpSize);
                CharBuffer charBuffer = Charset.forName("UTF-8").decode(mappedByteBuffer);

                while (charBuffer.hasRemaining())
                {
                    System.out.print(charBuffer.get());
                }
                System.out.println();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (channel != null)
            {
                try
                {
                    channel.close();
                }
                catch (Exception e)
                {
                    // ignore
                }
            }
        }
    }

    private <T> List<T> list()
    {
        return Collections.emptyList();
    }

    public void m()
    {
        List<String> list = list();
        m2(this.<String>list());
    }

    private void m2(List<String> list)
    {

    }

    private <T, E> void f1(List<T> list, List<E> list2)
    {

    }
}
