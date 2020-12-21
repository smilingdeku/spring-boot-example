package org.example.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.StringUtils;

/**
 * @author walle@eva
 * @version V1.0
 * @since 2020-12-21 11:21
 */

public class Md5Util {

    private static MessageDigest digest = null;

    static {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsae) {
            //
        }
    }

    /**
     * 将数据转成md5字符串
     *
     * @param data 数据
     * @return String
     */
    public static String encode(String data) {
        return encode(data, false);
    }

    /**
     * 将数据转成md5字符串
     *
     * @param data 数据
     * @return String
     */
    public static String encode(String data, boolean isUpperCase) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        digest.update(data.getBytes());
        String value = toHex(digest.digest());
        return isUpperCase ? value.toUpperCase() : value;
    }

    /**
     * 转换成16进制数据
     *
     * @param hash arr
     * @return String
     */
    private static String toHex(byte[] hash) {
        StringBuilder buf = new StringBuilder(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if (((int) hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) hash[i] & 0xff, 16));
        }
        return buf.toString();
    }
}
