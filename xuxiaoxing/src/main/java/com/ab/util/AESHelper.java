package com.ab.util;

/**
 * 描述： AES算法加密，传输，解密过程示例(AES可以使用128、192、和256位密钥，并且用128位分组加密和解密数据)
 * 默认只能用16位密钥加密，但用security包下的java包换掉jre中对应的后，可任意密钥加解密。
 * 作者：徐小星 on 2016/10/8 0008 10:11
 * 邮箱：xx@yougudongli.com
 */

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES算法加密，传输，解密过程示例(AES可以使用128、192、和256位密钥，并且用128位分组加密和解密数据)
 * 默认只能用16位密钥加密，但用security包下的java包换掉jre中对应的后，可任意密钥加解密。
 *
 * @author steven-pan
 */
public class AESHelper {

    private static Cipher cipher = null; // 私鈅加密对象Cipher

    public static void main(String args[]) {
        System.out.println("AES加解密测试：");

        String password = "c8a9229820ffa315bc6a17a9e43d01a9";
        String content = "6222001521522152212";
        // 加密（传输)
        System.out.println("加密前：" + content);

        // 以HEX进行传输
        String encryptResult = encrypt(content, password);// data transfer as text
        System.out.println("encryptResult:" + encryptResult);

        // 解密
        String decryptResultb = decrypt(encryptResult, password);
        System.out.println("解密后：" + decryptResultb);
    }

    static {
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     *
     * @param message
     * @return
     */
    public static String encrypt(String message, String passWord) {
        // if (passWord == null) {
        // System.out.print("passWord为空null");
        // return null;
        // }
        // // 判断passWord是否为16位
        // if (passWord.length() != 16) {
        // System.out.print("Key长度不是16位");
        // return null;
        // }

        try {
            /* AES算法 */
            SecretKey secretKey = new SecretKeySpec(passWord.getBytes(), "AES");// 获得密钥
            /* 获得一个私鈅加密类Cipher，DESede-》AES算法，ECB是加密模式，PKCS5Padding是填充方式 */
            cipher.init(Cipher.ENCRYPT_MODE, secretKey); // 设置工作模式为加密模式，给出密钥
            byte[] resultBytes = cipher.doFinal(message.getBytes("UTF-8")); // 正式执行加密操作
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(resultBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptResult, String passWord) {
        byte[] messageBytes = org.apache.commons.codec.binary.Base64.decodeBase64(encryptResult.getBytes());
        String result = "";
        try {
            /* AES算法 */
            SecretKey secretKey = new SecretKeySpec(passWord.getBytes(), "AES");// 获得密钥
            cipher.init(Cipher.DECRYPT_MODE, secretKey); // 设置工作模式为解密模式，给出密钥
            byte[] resultBytes = cipher.doFinal(messageBytes);// 正式执行解密操作
            result = new String(resultBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 去掉加密字符串换行符
     *
     * @param str
     * @return
     */
    public static String filter(String str) {
        String output = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            if (asc != 10 && asc != 13) {
                sb.append(str.subSequence(i, i + 1));
            }
        }
        output = new String(sb);
        return output;
    }

}