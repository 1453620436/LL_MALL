package com.mall.admin.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 *
 * 加密工具类
 * Created by macro on 2019/8/23.
 */
public class EncryptUtil {
    private  static  final Log LOG = LogFactory.getLog(EncryptUtil.class);

    /**
     * 私有构造
     */
    private EncryptUtil(){}

    /**
     * 进行md5加密
     * @param str
     * @return
     */
    public static String encodeMD5String(String str){
        return encode(str,"MD5");
    }

    /**
     * RSA获取私钥公钥
     * @param str
     * @return
     */
    public static HashMap<String,String> encodeRSAString(String str){
        HashMap<String,String> hashMap = new HashMap<>();
        KeyPair keyPair = genKeyPair(1024,"RSA");
        String privateKey = new String(Base64.encodeBase64String(keyPair.getPrivate().getEncoded()));//获取私钥
        String publicKey = new String(Base64.encodeBase64String(keyPair.getPublic().getEncoded()));////获取公钥
        hashMap.put("privateKey",privateKey);
        hashMap.put("publicKey",publicKey);
        return hashMap;
    }

    private static String encode(String str, String method){
        MessageDigest messageDigest = null;
        // 单线程用StringBuilder，速度快 多线程用stringbuffer，安全
        StringBuilder stringBuilder = new StringBuilder();
        try {
            // 获得MD5摘要算法的 MessageDigest对象
            messageDigest = MessageDigest.getInstance(method);
            // 使用指定的字节更新摘要
            messageDigest.update(str.getBytes());
            // 获得密文
            byte [] md = messageDigest.digest();
            for (int i=0;i<md.length;i++){
                int tmp = md[i];
                if (tmp < 0) {
                    tmp += 256;
                }
                if (tmp < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(tmp));
            }
        }catch (NoSuchAlgorithmException e) {
            LOG.error(e);
        }
        return stringBuilder.toString();
    }

    /**
     * 生成秘钥
     * @param keyLength
     * @return
     * @throws Exception
     */
    private static KeyPair genKeyPair(int keyLength,String method){
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(method);
            keyPairGenerator.initialize(keyLength);
        }catch (NoSuchAlgorithmException e) {
            LOG.error(e);
        }
        return keyPairGenerator.generateKeyPair();
    }
}
