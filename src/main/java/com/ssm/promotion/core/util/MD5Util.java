package com.ssm.promotion.core.util;

import java.security.MessageDigest;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5Util {

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


    private static final char HEXIDGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    public static String getMD5Str(String str) {
        if (str == null) {
            return null;
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        // 32位加密，从第0位到31位
        // return md5StrBuff.substring(8, 24).toString().toUpperCase();
        return md5StrBuff.substring(0, 32).toString().toUpperCase();
    }

    /**
     * 获取10位随机数
     *
     * @return
     * @author: xiaozhenhua
     * @data:2014-4-9 下午2:45:51
     */
    public static String getRandom() {
        Random r = new Random();
        String s = "";
        for (int i = 0; i < 6; i++) {
            s += r.nextInt(10);
        }
        return s;
    }

    /**
     * 获取文件的MD5值
     *
     * @param file
     * @return
     * @author: xiaozhenhua
     * @data:2014-4-9 下午2:47:32
     */
    public static String getMD5(File file) {
        String fileMd5Value = "";
        if (file.isFile()) {
            FileInputStream fis = null;
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                fis = new FileInputStream(file);
                byte[] buffer = new byte[2048];
                int length = -1;
                while ((length = fis.read(buffer)) != -1) {
                    md.update(buffer, 0, length);
                }
                byte[] b = md.digest();
                fileMd5Value = byteToHexString(b);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return fileMd5Value;
    }

    private static String byteToHexString(byte[] temp) {
        char str[] = new char[16 * 2];
        int k = 0;
        for (int i = 0; i < 16; i++) {
            byte byte0 = temp[i];
            str[k++] = HEXIDGITS[byte0 >>> 4 & 0xf];
            str[k++] = HEXIDGITS[byte0 & 0xf];
        }
        String s = new String(str);
        return s;
    }

}
