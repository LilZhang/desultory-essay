package oops;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

public class Util
{
    private static final String HOST = "127.0.0.1";   //Redis服务器IP

    private static final int PORT = 6379;   //Redis的端口号

    private static final String RAW_KEY = "test:8m:raw";

    private static final String COMPRESS_KEY = "test:8m:compress";

    private static final int BOUND = 67108863;  // 2 ^ 26 - 1

//    private static final int BOUND = ((int) (Math.pow(2d, 26d) - 1));

    private static final byte[] EMPTY_BYTES = new byte[0];

    private static final Set<Integer> SET = IntStream
            .rangeClosed(0, BOUND)
            .filter((i) -> Math.random() < 0.001)
            .boxed()
            .collect(Collectors.toSet());

    public static void main(String[] args)
    {
        System.out.println("SET size: " + SET.size());

        Jedis jedis = new Jedis(HOST, PORT);
        checkResult(jedis);
//        rawPut(jedis);
//        compressPut(jedis);


        // 单线程
        // 多线程
    }

    public static void checkResult(Jedis jedis)
    {
        byte[] rawBytes = jedis.get(RAW_KEY.getBytes());
        BitSet rawBitSet = BitSet.valueOf(rawBytes);

        byte[] originComBytes = jedis.get(COMPRESS_KEY.getBytes());
        BitSet comBitSet = inflater(originComBytes);

        List<Integer> notEqual = new ArrayList<>();
        if (rawBitSet.size() == comBitSet.size())
        {
            int size = rawBitSet.size();
            for (int i = 0; i < size; i++)
            {
                if (rawBitSet.get(i) != comBitSet.get(i))
                {
                    notEqual.add(i);
                }
            }
        }

        System.out.println();

        // raw -> com
        int rawNotInCom = 0;

        // com -> raw
        int comNotInRaw = 0;
    }

    public static void rawPut(Jedis jedis)
    {
        System.out.println("raw start.");
        long start = System.currentTimeMillis();
        SET.forEach((i) -> jedis.setbit(RAW_KEY, i, true));
        long end = System.currentTimeMillis();
        System.out.println("raw put: " + (end - start));
    }

    public static void compressPut(Jedis jedis)
    {
        System.out.println("compress start.");
        long start = System.currentTimeMillis();

        // TODO: 2017/5/18 todo
//        SET.forEach((i) -> casPut(jedis, COMPRESS_KEY, i));

        int i = 0;
        long loopStart = start;
        for (Integer n : SET)
        {
            if (i % 100 == 0)
            {
                long l = System.currentTimeMillis();
                System.out.println(i + " : " + (l - loopStart));
                loopStart = l;
            }

            casPut(jedis, COMPRESS_KEY, n);
            i++;
        }

        long end = System.currentTimeMillis();
        System.out.println("compress put: " + (end - start));
    }








    public static void casPut(Jedis jedis, String key, int offset)
    {
        boolean failed;
        do
        {
            jedis.watch(key);
            byte[] bytes = jedis.get(key.getBytes());

            if (bytes == null)
            {
                bytes = EMPTY_BYTES;
            }

            BitSet bitSet = inflater(bytes);
            bitSet.set(offset);
            bytes = deflater(bitSet);

            Transaction tx = jedis.multi();
            tx.set(key.getBytes(), bytes);
            failed = tx.exec() == null;
        }
        while (failed);
    }

    // 解压缩
    public static BitSet inflater(byte[] bytes)
    {
        ByteArrayOutputStream baos = null;
        InflaterOutputStream ios = null;
        BitSet result = null;

        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            baos = new ByteArrayOutputStream();
            ios = new InflaterOutputStream(baos, new Inflater(true));

            byte[] b = new byte[1024];
            int len;
            while ((len = bais.read(b)) != -1)
            {
                ios.write(b, 0, len);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // bais.close();
            if (ios != null)
            {
                try
                {
                    ios.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }

        if (baos != null)
        {
            result = BitSet.valueOf(baos.toByteArray());
        }
        return result;
    }

    // 压缩
    public static byte[] deflater(BitSet bitSet)
    {
        byte[] bytes = bitSet.toByteArray();
        ByteArrayOutputStream baos = null;
        DeflaterOutputStream dos = null;
        byte[] result = null;

        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            baos = new ByteArrayOutputStream();
            dos = new DeflaterOutputStream(baos, new Deflater(1, true));   // COMPRESSION LEVEL 1

            byte[] b = new byte[1024];
            int len;
            while ((len = bais.read(b)) != -1)
            {
                dos.write(b, 0, len);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // bais.close();
            // baos.close();
            if (dos != null)
            {
                try
                {
                    dos.flush();
                    dos.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }

        if (baos != null)
        {
            result = baos.toByteArray();
        }
        return result;
    }
}
