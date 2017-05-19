package oops;

import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.codec.binary.Hex;
import redis.clients.jedis.Jedis;

import java.util.BitSet;

public class App2
{
    private static final String HOST = "127.0.0.1";   //Redis服务器IP

    private static final int PORT = 6379;   //Redis的端口号

    private static final String RAW_KEY = "test:raw";

    private static final String BITSET_KEY = "test:bitset";

    public static void main(String[] args)
    {
        int[] ints = new int[]{2, 7, 9, 15};

        Jedis jedis = new Jedis(HOST, PORT);
        /*jedis.setbit(RAW_KEY, 2, true);
        jedis.setbit(RAW_KEY, 7, true);
        jedis.setbit(RAW_KEY, 9, true);
        jedis.setbit(RAW_KEY, 15, true);*/

        byte[] bytes = jedis.get(RAW_KEY.getBytes());
        System.out.println(Hex.encodeHexString(bytes));
        System.out.println(BinaryCodec.toAsciiString(bytes));

        System.out.println();

        BitSet bitSet = new BitSet();
        bitSet.set(2);
        bitSet.set(7);
        bitSet.set(9);
        bitSet.set(15);

        byte[] bytes1 = bitSet.toByteArray();
        System.out.println(Hex.encodeHexString(bytes1));
        System.out.println(BinaryCodec.toAsciiString(bytes1));
    }
}
