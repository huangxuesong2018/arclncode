package javax.core.common.utils;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-07
 */
public class StringUtils {
    /**
     * bytes to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytes2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int i = b & 0xFF;
            if (i <= 0xF) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

    /**
     * hex string to bytes
     * @return
     */
    public static byte[] hex2Bytes(String input) {
        byte[] res = new byte[input.length() / 2];
        char[] chs = input.toCharArray();
        for(int i = 0,c = 0; i < chs.length; i += 2,c ++){
            res[c] = (byte) (Integer.parseInt(new String(chs,i,2), 16));
        }
        return res;
    }
}
