package com.smile.encryption;

/**
 * @author: ningqiang
 * @time: 16/12/4
 * @description: 凯撒加密
 * 凯撒密码作为一种最为古老的对称加密体制，在古罗马的时候都已经很流行，他的基本思想是：
 * 通过把字母移动一定的位数来实现加密和解密。明文中的所有字母都在字母表上向后（或向前）
 * 按照一个固定数目进行偏移后被替换成密文。例如，当偏移量是3的时候，所有的字母A将被替
 * 换成D，B变成E，以此类推X将变成A，Y变成B，Z变成C。由此可见，位数就是凯撒密码加密和
 * 解密的密钥。
 */

public class Caesar {
    private static final int KEY = 6;
    public static void main(String[] args) {
        String text = "love me love my dog ,爱屋及乌";

        System.out.println("原文：" + text);

        String encrypt = encrypt(text, KEY);

        System.out.println("加密：" + encrypt);

        String decrypt = decrypt(encrypt,KEY);

        System.out.println("解密：" + decrypt);


    }

    public static String encrypt(String input, int key) {
        if (input == null) {
            return null;
        }
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            //将字符转化为ascii码后，对字符进行移位，移位后再转化为字符，替换掉原数组的值
            charArray[i] = (char) (charArray[i] + key);

        }
        return new String(charArray);
    }
    public static String decrypt(String input, int key) {
        if (input == null) {
            return null;
        }
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {

            charArray[i] = (char) (charArray[i] - key);

        }
        return new String(charArray);
    }
}
