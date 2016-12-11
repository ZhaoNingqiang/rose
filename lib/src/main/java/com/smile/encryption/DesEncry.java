package com.smile.encryption;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @author: ningqiang
 * @time: 16/12/11
 * @description:
 */

public class DesEncry {
    private static final String KEY = "12345678";
    public static void main(String[] args) throws Exception {
        //原文
        String input = "jacklove";
        String desEncrypt = desEncrypt(input, KEY);
        System.out.println("加密："+ desEncrypt);
        String desDecrypt = desDecrypt(desEncrypt, KEY);
        System.out.println("解密：     "+desDecrypt);
//
//        //初始化秘钥工厂
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//        //秘钥规格
//        KeySpec keySpec = new DESKeySpec(KEY.getBytes());
//        //创建秘钥
//        SecretKey secretKey = keyFactory.generateSecret(keySpec);
//
//        //getInstance(transformation 转换模式);
//        //Cipher 密码器
//        Cipher cipher = Cipher.getInstance("DES");
//
//        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
//
//        byte[] enResult = cipher.doFinal(input.getBytes());
//        System.out.println("加密 = "+new String(enResult));
//
//        cipher.init(Cipher.DECRYPT_MODE,secretKey);
//        byte[] deResult = cipher.doFinal(enResult);
//        System.out.println("解密 = "+new String(deResult));

    }

    public static String desEncrypt(String input,String KEY) throws Exception {
        //初始化秘钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        //秘钥规格
        KeySpec keySpec = new DESKeySpec(KEY.getBytes());
        //创建秘钥
        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        //getInstance(transformation 转换模式);
        //Cipher 密码器
        Cipher cipher = Cipher.getInstance("DES");

        cipher.init(Cipher.ENCRYPT_MODE,secretKey);

        byte[] enResult = cipher.doFinal(input.getBytes());

        return Base64.encode(enResult);


    }
    public static String desDecrypt(String input,String KEY) throws Exception {
        //初始化秘钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        //秘钥规格
        KeySpec keySpec = new DESKeySpec(KEY.getBytes());
        //创建秘钥
        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        //getInstance(transformation 转换模式);
        //Cipher 密码器
        Cipher cipher = Cipher.getInstance("DES");

        cipher.init(Cipher.DECRYPT_MODE,secretKey);

        byte[] bytes = cipher.doFinal(Base64.decode(input));

        String encrypt = new String(bytes);

        return encrypt;

    }


}
