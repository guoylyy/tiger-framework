package tiger.common.util;

import java.nio.ByteBuffer;

/**
 * 16进制转换工具
 * @author aoxiang.lax
 * @version $Id: HexUtils.java, v 0.1 2014年6月6日 下午5:37:27 aoxiang.lax Exp $
 */
public final class HexUtil {

    /**
     * 16进制用到的字符
     */
    private static final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
            'B', 'C', 'D', 'E', 'F'   };

    /** 构造函数 */
    private HexUtil() {
    }

    /**
     * 将指定的长整型数值的最低位字节转换为2个字符长度的16进制字符串(字母大写)
     *
     * @param l
     *            需要转换的长整型数值
     * @return 16进制字符串
     */
    public static String toHexString(long l) {
        return toHexString(l, 2);
    }

    /**
     * 将指定的长整型数值转换为16进制字符串(字母大写), 字符串长度由参数{@code hexLength}决定
     * <p>
     *
     * <pre>
     * hexLength &lt;= 0, 返回的16进制字符串中不包含全为0的高位值
     * hexLength &gt; 0, 返回指定字符长度的16进制字符串
     *   如果有效数值转换后的字符串长度小于指定长度，则自动在高位填充'0'字符
     *   如果有效数值转换后的字符串长度大于指定长度，则自动截断高位字符串
     * </pre>
     *
     * @param l
     *            需要转换的长整型数值
     * @param hexLength
     *            指定转换后的16进制字符串字符长度
     * @return 16进制字符串
     */
    public static String toHexString(long l, int hexLength) {
        char[] buf = new char[(hexLength <= 0 ? 16 : hexLength)]; // 对于长整型,
        // 最多会使用到16个字符
        int charPos = buf.length;
        int radix = 1 << 4;
        int mask = radix - 1;
        do {
            buf[--charPos] = digits[(int) l & mask];
            l >>>= 4;

            if (hexLength <= 0 && l == 0) {
                break;
            }

        } while (charPos > 0);

        return new String(buf, charPos, (buf.length - charPos));
    }

    /**
     * 将二进制字节数组转换为16进制表示的字符串(字母大写)
     *
     * @param b
     *            源字节数组
     * @param offset
     *            起始下标
     * @param length
     *            有效字节长度
     * @return 16进制表示的字符串
     */
    public static String toHexString(byte[] b, int offset, int length) {
        int l = length;
        char[] out = new char[l << 1];

        // two characters form the hex value.
        for (int i = offset, j = 0; i < offset + l; i++) {
            out[j++] = digits[(0xF0 & b[i]) >>> 4];
            out[j++] = digits[0x0F & b[i]];
        }

        return new String(out);
    }

    /**
     * 将二进制字节数组转换为16进制表示的字符串(字母大写)
     *
     * @param b
     *            源字节数组
     * @param offset
     *            起始下标
     * @param length
     *            有效字节长度
     * @param delimiter
     *            每个字节间(即每两个16进制字符间)的分隔符
     * @return 16进制表示的字符串
     */
    public static String toHexString(byte[] b, int offset, int length, String delimiter) {
        if (length == 0) {
            return "";
        }

        int l = length;
        StringBuilder sb = new StringBuilder(l << 1);

        // two characters form the hex value.
        for (int i = offset; i < offset + l; i++) {
            sb.append(digits[(0xF0 & b[i]) >>> 4]).append(digits[0x0F & b[i]]);
            if (delimiter != null) {
                sb.append(delimiter);
            }
        }

        if (delimiter != null) { // 去除末尾的分隔符
            sb.setLength(sb.length() - delimiter.length());
        }

        return sb.toString();
    }

    /**
     * 将二进制字节数组转换为16进制表示的字符串(字母大写)
     *
     * @param b
     *            源字节数组
     * @param delimiter
     *            每个字节间(即每两个16进制字符间)的分隔符
     * @return 16进制表示的字符串
     */
    public static String toHexString(byte[] b, String delimiter) {
        return toHexString(b, 0, b.length, delimiter);
    }

    /**
     * 将二进制字节数组转换为16进制表示的字符串(字母大写)
     *
     * @param b
     *            源字节数组
     * @return
     */
    public static String toHexString(byte... b) {
        return toHexString(b, 0, b.length, null);
    }

    /**
     * 将二进制字节块转换为16进制表示的字符串(字母大写)
     *
     * @param buf
     *            字节块
     * @return
     */
    public static String toHexString(ByteBuffer buf) {
        String str;
        if (buf.hasArray()) {
            // 直接利用底层数组
            str = toHexString(buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(),
                    null);
            buf.position(buf.limit());
        } else {
            // 需要数据复制
            byte[] bytes = new byte[buf.remaining()];
            buf.get(bytes);
            str = toHexString(bytes);
        }

        return str;
    }

    /**
     * 将二进制字节数组转换为16进制表示的字符串(字母大写)
     * <p>
     * 从数组下标0开始扫描, 略过值=0的元素，直到遇到第一个非0的元素开始转换到字符
     *
     *            源字节数组
     * @return 16进制表示的字符串
     */
    public static String toAbbrHexString(byte[] ba) {
        if (ba.length == 0) {
            return "";
        }

        int l = ba.length;
        StringBuilder sb = new StringBuilder(l << 1);

        // two characters form the hex value.
        for (int i = 0; i < l; i++) {
            if (ba[i] == 0 && sb.length() == 0) {
                continue;
            }

            sb.append(digits[(0xF0 & ba[i]) >>> 4]).append(digits[0x0F & ba[i]]);
        }

        return sb.toString();
    }

    /**
     * 将16进制的字符串解析为byte数组
     *
     * @param cs
     *            16进制的字符串
     * @return byte数组
     */
    public static byte[] parseHexString(CharSequence cs) {
        int len = cs.length();

        byte[] ba;
        int baLen = len >> 1;
        int i = 0, j = 0;
        if ((len & 0x1) == 0x1) {
            // 原始字符串长度为奇数
            ba = new byte[baLen + 1];

            int c = cs.charAt(i++);
            if (c >= 'a') {
                c = c - 'a' + 10;
            } else if (c >= 'A') {
                c = c - 'A' + 10;
            } else {
                c -= '0';
            }
            if (c < 0 || c > 15) {
                throw new IllegalArgumentException("illegal hex char position is: 0");
            }

            ba[j++] = (byte) c;
        } else {
            // 原始长度为偶数
            ba = new byte[baLen];
        }

        for (; i < len; i += 2, j++) {
            int c1 = cs.charAt(i);
            if (c1 >= 'a') {
                c1 = c1 - 'a' + 10;
            } else if (c1 >= 'A') {
                c1 = c1 - 'A' + 10;
            } else {
                c1 -= '0';
            }
            if (c1 < 0 || c1 > 15) {
                throw new IllegalArgumentException("illegal hex char position is:" + i);
            }

            int c2 = cs.charAt(i + 1);
            if (c2 >= 'a') {
                c2 = c2 - 'a' + 10;
            } else if (c2 >= 'A') {
                c2 = c2 - 'A' + 10;
            } else {
                c2 -= '0';
            }
            if (c2 < 0 || c2 > 15) {
                throw new IllegalArgumentException("illegal hex char position is:" + (i + 1));
            }

            ba[j] = (byte) (((c1 << 4) | c2) & 0xFF);
        }

        return ba;
    }
}