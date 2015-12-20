package tiger.common.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 字节相关工具
 *
 * @author aoxiang.lax
 */
public final class ByteUtil {
    /** 长整型数值的字节个数 */
    public static final int        LONG_BYTE_COUNT   = 8;

    /**
     * The empty byte array: <code>new byte[0]</code>.
     */
    public static final byte[]     EMPTY             = new byte[0];

    /** 空白的ByteBuffer常量 */
    public static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.wrap(EMPTY).asReadOnlyBuffer();

    /** The Constant BYTE_ZERO. 转换为Boolean时代表FALSE */
    public static final byte BYTE_ZERO = (byte) 0;

    /** The Constant BYTE_ONE. 转换为Boolean时代表TRUE */
    public static final byte BYTE_ONE = (byte) 1;

    /** 构造函数 */
    private ByteUtil() {
    }

    /**
     * 将字节数组(长度上限为8)转换为长整型数值
     * <p>
     *
     * <pre>
     * asc = true: 数组中0序号元素对应数值的最高位字节, 越低位的字节存放的序号越大
     * asc = false: 数组中0序号元素对应数值的最低位字节, 越低位的字节存放的序号越小
     * </pre>
     *
     * @param buf
     *            字节数组
     * @param offset
     *            需要转换的有效字节起始偏移量
     * @param length
     *            需要转换的字节长度
     * @param asc
     *            标记数值字节存放顺序
     * @return 长整型数值
     */
    public static long bytesToLong(byte[] buf, int offset, int length, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (length > 8) {
            throw new IllegalArgumentException("byte array size > 8 !");
        }

        long l = 0;
        if (asc) {
            for (int i = offset; i < offset + length; i++) {
                l <<= 8;
                l |= ((long) buf[i] & 0xFF);
            }
        } else {
            for (int i = offset + length - 1; i >= 0; i--) {
                l <<= 8;
                l |= ((long) buf[i] & 0xFF);
            }
        }

        return l;
    }

    /**
     * 将字节数组(长度限定为8)转换为长整型数值
     * <p>
     *
     * <pre>
     * asc = true: 数组中0序号元素对应数值的最高位字节, 越低位的字节存放的序号越大
     * asc = false: 数组中0序号元素对应数值的最低位字节, 越低位的字节存放的序号越小
     * </pre>
     *
     * @param buf
     *            字节数组
     * @param asc
     *            标记数值字节存放顺序
     * @return 长整型数值
     */
    public static long bytesToLong(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }

        return bytesToLong(buf, 0, buf.length, asc);
    }

    /**
     * 将字节数组(长度限定为4)转换为整型数值
     * <p>
     *
     * <pre>
     * asc = true: 数组中0序号元素对应数值的最高位字节, 越低位的字节存放的序号越大
     * asc = false: 数组中0序号元素对应数值的最低位字节, 越低位的字节存放的序号越小
     * </pre>
     *
     * @param buf
     *            字节数组
     * @param asc
     *            标记数值字节存放顺序
     * @return 整型数值
     */
    public static int bytesToInt(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 4) {
            throw new IllegalArgumentException("byte array size > 4 !");
        }

        return (int) bytesToLong(buf, 0, buf.length, asc);
    }

    /**
     * 将字节数组(长度限定为2)转换为短整型数值
     * <p>
     *
     * <pre>
     * asc = true: 数组中0序号元素对应数值的最高位字节, 越低位的字节存放的序号越大
     * asc = false: 数组中0序号元素对应数值的最低位字节, 越低位的字节存放的序号越小
     * </pre>
     *
     * @param buf
     *            字节数组
     * @param asc
     *            标记数值字节存放顺序
     * @return 短整型数值
     */
    public static short bytesToShort(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 2) {
            throw new IllegalArgumentException("byte array size > 2 !");
        }

        return (short) bytesToLong(buf, 0, buf.length, asc);
    }

    /**
     * 将一串字节数组导出成16进制字符串描述, 格式为:
     *
     * <pre>
     * name [16进制字节,16进制字节 ... ]
     * </pre>
     *
     * @param name
     *            字符串描述前缀
     * @param bytes
     *            需要导出的字节数组串
     * @return 字符串描述
     */
    public static String dumpToHexs(String name, byte[]... bytes) {
        StringBuilder result = new StringBuilder(name).append(" [");
        boolean appended = false;

        for (byte[] ba : bytes) {
            if (appended) {
                result.append(",");
            }

            result.append(HexUtil.toHexString(ba, 0, ba.length, ","));
            appended = true;
        }

        result.append("]");
        return result.toString();
    }

    /**
     * 将一串字节数组导出成10进制字符串描述, 格式为:
     *
     * <pre>
     * name [10进制字节,10进制字节 ... ]
     * </pre>
     *
     * @param name
     *            字符串描述前缀
     * @param bytes
     *            需要导出的字节数组串
     * @return 字符串描述
     */
    public static String dumpToDecs(String name, byte[]... bytes) {
        StringBuilder result = new StringBuilder(name).append(" [");
        int preLength = result.length();

        for (byte[] ba : bytes) {
            for (byte b : ba) {
                result.append(b).append(",");
            }
        }

        if (preLength != result.length()) { // 去除末尾的','字符
            result.setLength(result.length() - 1);
        }

        result.append("]");
        return result.toString();
    }

    /**
     * 合并一串字节数组, 返回一个包含原来所有数据的字节数组
     *
     * @param bytes
     *            需要合并的字节数组串
     * @return 合并后的字节数组
     */
    public static byte[] merge(byte[]... bytes) {
        byte[] result = null;
        int length = 0;
        for (byte[] t : bytes) {
            length += t.length;
        }
        result = new byte[length];

        int index = 0;
        for (byte[] t : bytes) {
            System.arraycopy(t, 0, result, index, t.length);
            index += t.length;
        }
        return result;
    }

    /**
     * 将boolean转换为byte
     *
     * @param b
     *          boolean， 为true时转换为1，fasle时转换为0
     * @return
     *          byte
     */
    public static byte booleanToByte(boolean b) {
        return b ? BYTE_ONE : BYTE_ZERO;
    }

    /**
     * 将短整型转换为字节数组(长度为2)
     * <p>
     *
     * <pre>
     * asc = true: 数值最低位字节存放在结果数组中最大序号处, 越高位的字节存放的序号越小
     * asc = false: 数值最低位字节存放在结果数组中0序号处, 越高位的字节存放的序号越大
     * </pre>
     *
     * @param s
     *            短整型数组
     * @param asc
     *            标记数值字节存放顺序
     * @return 字节数组
     */
    public static byte[] shortToBytes(short s, boolean asc) {
        return (getAsBytes(s, asc, 2));
    }

    /**
     * 将整型转换为字节数组(长度为4)
     * <p>
     * 数值最低位字节存放在结果数组中最大序号处, 越高位的字节存放的序号越小
     *
     * @param i
     *            整型数值
     * @return 字节数组
     */
    public static byte[] intToBytes(int i) {
        return (getAsBytes(i, true, 4));
    }

    /**
     * 将整型转换为字节数组(长度为4)
     * <p>
     *
     * <pre>
     * asc = true: 数值最低位字节存放在结果数组中最大序号处, 越高位的字节存放的序号越小
     * asc = false: 数值最低位字节存放在结果数组中0序号处, 越高位的字节存放的序号越大
     * </pre>
     *
     * @param i
     *            整型数值
     * @param asc
     *            标记数值字节存放顺序
     * @return 字节数组
     */
    public static byte[] intToBytes(int i, boolean asc) {
        return (getAsBytes(i, asc, 4));
    }

    /**
     * 将整型转换为字节数组, 数组长度由参数{@code length}指定
     * <p>
     *
     * <pre>
     * asc = true: 数值最低位字节存放在结果数组中最大序号处, 越高位的字节存放的序号越小
     * asc = false: 数值最低位字节存放在结果数组中0序号处, 越高位的字节存放的序号越大
     * </pre>
     *
     * @param i
     *            整型数值
     * @param asc
     *            标记数值字节存放顺序
     * @param length
     *            字节数组长度
     * @return 字节数组
     */
    public static byte[] intToBytes(int i, boolean asc, int length) {
        return (getAsBytes(i, asc, length));
    }

    /**
     * 将长整型转换为字节数组(长度为8)
     * <p>
     * 数值最低位字节存放在结果数组中最大序号处, 越高位的字节存放的序号越小
     *
     * @param l
     *            长整型
     * @return 字节数组
     */
    public static byte[] longToBytes(long l) {
        return (getAsBytes(l, true, 8));
    }

    /**
     * 将长整型转换为字节数组(长度为8)
     * <p>
     *
     * <pre>
     * asc = true: 数值最低位字节存放在结果数组中最大序号处, 越高位的字节存放的序号越小
     * asc = false: 数值最低位字节存放在结果数组中0序号处, 越高位的字节存放的序号越大
     * </pre>
     *
     * @param l
     *            长整型
     * @param asc
     *            标记数值字节存放顺序
     * @return 字节数组
     */
    public static byte[] longToBytes(long l, boolean asc) {
        return (getAsBytes(l, asc, 8));
    }

    /**
     * 将长整型转换为字节数组, 数组长度由参数{@code length}指定
     * <p>
     *
     * <pre>
     * asc = true: 数值最低位字节存放在结果数组中最大序号处, 越高位的字节存放的序号越小
     * asc = false: 数值最低位字节存放在结果数组中0序号处, 越高位的字节存放的序号越大
     * </pre>
     *
     * @param l
     *            长整型
     * @param asc
     *            标记数值字节存放顺序
     * @param length
     *            字节数组长度
     * @return 字节数组
     */
    public static byte[] getAsBytes(long l, boolean asc, int length) {
        byte[] ba = new byte[length];
        long mask = (1 << 8) - 1;

        if (asc) {
            int index = ba.length;
            do {
                ba[--index] = (byte) (l & mask);
                l >>>= 8;
            } while (l != 0 && index > 0);
        } else {
            int index = 0;
            do {
                ba[index++] = (byte) (l & mask);
                l >>>= 8;
            } while (l != 0 && index < length);
        }

        return ba;
    }

    /**
     * 将长整型数值转化成固定长度为8的字节数组(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param l
     *            数组
     * @return 字节数组
     */
    public static byte[] getAsBytes(long l) {
        byte[] b = new byte[8];
        b[0] = (byte) ((l >> 56) & 0xFF);
        b[1] = (byte) ((l >> 48) & 0xFF);
        b[2] = (byte) ((l >> 40) & 0xFF);
        b[3] = (byte) ((l >> 32) & 0xFF);
        b[4] = (byte) ((l >> 24) & 0xFF);
        b[5] = (byte) ((l >> 16) & 0xFF);
        b[6] = (byte) ((l >> 8) & 0xFF);
        b[7] = (byte) ((l >> 0) & 0xFF);
        return b;
    }

    /**
     * 将整型数值转化成固定长度为4的字节数组(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param i
     *            数值
     * @return 字节数组
     */
    public static byte[] getAsBytes(int i) {
        byte[] b = new byte[4];
        b[0] = (byte) ((i >> 24) & 0xFF);
        b[1] = (byte) ((i >> 16) & 0xFF);
        b[2] = (byte) ((i >> 8) & 0xFF);
        b[3] = (byte) ((i >> 0) & 0xFF);
        return b;
    }

    /**
     * 将短整型数组转化成固定长度为2的字节数组(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param s
     *            数值
     * @return 字节数组
     */
    public static byte[] getAsBytes(short s) {
        byte[] b = new byte[2];
        b[0] = (byte) ((s >> 8) & 0xFF);
        b[1] = (byte) ((s >> 0) & 0xFF);
        return b;
    }

    /**
     * 将单字节转化成固定长度为1的字节数组(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param b
     *            数值
     * @return 字节数组
     */
    public static byte[] getAsBytes(byte b) {
        byte[] buf = new byte[1];
        buf[0] = b;
        return buf;
    }

    /**
     * 将boolean数值转化成固定长度为1的字节数组(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param b
     *            数值
     * @return 字节数组
     */
    public static byte[] getAsBytes(boolean b) {
        byte[] buf = new byte[1];
        buf[0] = (b ? (byte) 1 : (byte) 0);
        return buf;
    }

    /**
     * 将指定字符串转换到字节数组, 支持str参数为null
     * <p>
     * 先尝试使用UTF-8字符集, 如果该字符集不存在则使用默认字符集
     *
     * @param str
     *            需要转换的字符串
     * @return 字节数组
     */
    public static byte[] getAsBytes(String str) {
        if (str == null) {
            return new byte[] { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };
        }

        byte[] buf;

        try {
            buf = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            buf = str.getBytes();
        }

        ByteBuffer bb = ByteBuffer.wrap(new byte[4 + buf.length]);
        bb.putInt(buf.length);
        bb.put(buf);

        return bb.array();
    }

    /**
     * 将指定长整型数组转换到字节数组, 支持la参数为null
     *
     * @param la
     *            长整型数组
     * @return 字节数组
     */
    public static byte[] getAsBytes(long[] la) {
        if (la == null) { // 长度-1 标记数组为null
            return new byte[] { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };
        }
        if (la.length == 0) { // 数组长度为 0
            return new byte[] { 0, 0, 0, 0 };
        }

        ByteBuffer bb = ByteBuffer.wrap(new byte[4 + (la.length << 3)]);
        bb.putInt(la.length);
        for (long l : la) {
            bb.putLong(l);
        }

        return bb.array();
    }

    /**
     * 将指定长整型列表转换到字节数组, 支持ll参数为null
     * <p>
     * 注意: List中不允许出现null元素
     *
     * @param ll
     *            长整型列表
     * @return 字节数组
     */
    public static byte[] getAsBytes(List<Long> ll) {
        if (ll == null) { // 长度-1 标记数组为null
            return new byte[] { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };
        }
        if (ll.size() == 0) { // 数组长度为 0
            return new byte[] { 0, 0, 0, 0 };
        }

        ByteBuffer bb = ByteBuffer.wrap(new byte[4 + (ll.size() << 3)]);
        bb.putInt(ll.size());
        for (long l : ll) {
            bb.putLong(l);
        }

        return bb.array();
    }

    /**
     * 将固定8字节长度的字节数组转化为长整型数值(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param b
     *            字节数组
     * @return 数值
     */
    public static long toLong(byte[] b) {
        return ((((long) b[7]) & 0xFF) | ((((long) b[6]) & 0xFF) << 8)
                | ((((long) b[5]) & 0xFF) << 16) | ((((long) b[4]) & 0xFF) << 24)
                | ((((long) b[3]) & 0xFF) << 32) | ((((long) b[2]) & 0xFF) << 40)
                | ((((long) b[1]) & 0xFF) << 48) | ((((long) b[0]) & 0xFF) << 56));
    }

    /**
     * 将固定8字节长度的字节数组转化为长整型数值(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param b
     *            字节数组
     * @param offset
     *            有效字节起始偏移量
     * @return 数值
     */
    public static long toLong(byte[] b, int offset) {
        return ((((long) b[offset + 7]) & 0xFF) | ((((long) b[offset + 6]) & 0xFF) << 8)
                | ((((long) b[offset + 5]) & 0xFF) << 16) | ((((long) b[offset + 4]) & 0xFF) << 24)
                | ((((long) b[offset + 3]) & 0xFF) << 32) | ((((long) b[offset + 2]) & 0xFF) << 40)
                | ((((long) b[offset + 1]) & 0xFF) << 48) | ((((long) b[offset]) & 0xFF) << 56));
    }

    /**
     * 将固定4字节长度的字节数组转化为整型数值(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param b
     *            字节数组
     * @return 数值
     */
    public static int toInt(byte[] b) {
        return (((((int) b[3]) & 0xFF)) | ((((int) b[2]) & 0xFF) << 8)
                | ((((int) b[1]) & 0xFF) << 16) | ((((int) b[0]) & 0xFF) << 24));
    }

    /**
     * 将固定4字节长度的字节数组转化为整型数值(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param b
     *            字节数组
     * @param offset
     *            有效字节起始偏移量
     * @return 数值
     */
    public static int toInt(byte[] b, int offset) {
        return (((((int) b[3 + offset]) & 0xFF)) | ((((int) b[2 + offset]) & 0xFF) << 8)
                | ((((int) b[1 + offset]) & 0xFF) << 16) | ((((int) b[offset]) & 0xFF) << 24));
    }

    /**
     * 将固定2字节长度的字节数组转化为短整型数值(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param b
     *            字节数组
     * @return 数值
     */
    public static short toShort(byte[] b) {
        return (short) ((((short) b[1]) & 0xFF) | ((((short) b[0]) & 0xFF) << 8));
    }

    /**
     * 将固定2字节长度的字节数组转化为短整型数值(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param b
     *            字节数组
     * @param offset
     *            有效字节起始偏移量
     * @return 数值
     */
    public static short toShort(byte[] b, int offset) {
        return (short) ((((short) b[offset + 1]) & 0xFF) | ((((short) b[offset]) & 0xFF) << 8));
    }

    /**
     * 将固定1字节长度的字节数组转化为boolean数值(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param b
     *            字节数组
     * @return 数值
     */
    public static boolean toBoolean(byte[] b) {
        return (b[0] == 1);
    }

    /**
     * To boolean.
     *
     * @param b the b
     * @return true, if successful
     */
    public static boolean toBoolean(byte b){
        return (b==1);
    }

    /**
     * 将固定1字节长度的字节数组转化为boolean数值(Big Endian)
     * <p>
     * 效率优先, 可用于序列化和反序列化
     *
     * @param b
     *            字节数组
     * @param offset
     *            有效字节起始偏移量
     * @return 数值
     */
    public static boolean toBoolean(byte[] b, int offset) {
        return (b[offset] == 1);
    }

    /**
     * 将字节数组转换成字符串; 支持null值的字符串返回
     * <p>
     * 先尝试使用UTF-8字符集, 如果该字符集不存在则使用默认字符集
     *
     * @param b
     *            字节数组
     * @return 字符串
     */
    public static String toString(byte[] b) {
        int length = toInt(b);
        if (length == -1) {
            return null;
        }
        if (length == 0) {
            return "";
        }

        try {
            return (new String(b, 4, b.length - 4, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            return (new String(b, 4, b.length - 4));
        }
    }

    /**
     * 将字节数组转换成长整型数组; 支持null的数组返回
     *
     * @param b
     *            字节数组
     * @return 长整型数组
     */
    public static long[] toLongArray(byte[] b) {
        ByteBuffer bb = ByteBuffer.wrap(b);

        int length = bb.getInt();
        if (length == -1) {
            return null;
        }

        long[] la = new long[length];
        for (int i = 0; i < length; i++) {
            la[i] = bb.getLong();
        }

        return la;
    }

    /**
     * 将字节数组转换成长整型列表; 支持null的列表返回
     *
     * @param b
     *            字节数组
     * @return 长整型List
     */
    public static List<Long> toLongArrayList(byte[] b) {
        ByteBuffer bb = ByteBuffer.wrap(b);

        int length = bb.getInt();
        if (length == -1) {
            return null;
        }

        ArrayList<Long> al = new ArrayList<Long>(length);
        for (int i = 0; i < length; i++) {
            al.add(bb.getLong());
        }

        return al;
    }

    /**
     * 将两个字节数组按顺序依次执行'位或'操作
     * <p>
     * 两个字节数组长度必须一致
     *
     * @param src
     *            源字节数组, 执行'位或'之后的值将保存到该数组内
     * @param other
     *            另一个字节数组
     * @param offset
     *            起始偏移量
     */
    public static void or(byte[] src, byte[] other, int offset) {
        if (src.length != other.length) {
            throw new IllegalArgumentException("the bytes length is different");
        }

        int index = src.length;
        while (--index >= 0) {
            src[index] |= other[index];
        }
    }

    /**
     * 将两个字节数组按顺序依次执行'位或'操作
     * <p>
     * 两个字节数组长度必须一致
     *
     * @param src
     *            源字节数组, 执行'位或'之后的值将保存到该数组内
     * @param srcOffset
     *            源字节数组中的起始偏移量
     * @param other
     *            另一个字节数组
     * @param otherOffset
     *            另一个字节数组的起始偏移量
     * @param length
     *            需要执行操作的字节长度
     */
    public static void or(byte[] src, int srcOffset, byte[] other, int otherOffset, int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length");
        }
        if (srcOffset < 0 || srcOffset + length > src.length) {
            throw new IndexOutOfBoundsException("src bytes");
        }
        if (otherOffset < 0 || otherOffset + length > other.length) {
            throw new IndexOutOfBoundsException("other bytes");
        }

        for (int i = srcOffset, j = otherOffset, end = srcOffset + length; i < end; i++, j++) {
            src[i] |= other[j];
        }
    }

}