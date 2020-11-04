package com.lyt.hutoolTest;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * @ClassName HutoolTest
 * @Description 使用Hutool实现AES、DES加密解密
 * @Author liyintao
 * @Date 2020/11/4 13:40
 */
public class HutoolTest {

    public static void main(String[] args) {
        String content = "test中文";
        System.out.println(aes(content));
    }

    /**
     * AES加密解密
     * @return
     */
    public static String aes(String content) {
        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        //构建
        AES aes = SecureUtil.aes(key);
        //加密
        byte[] encrypt = aes.encrypt(content);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);
        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        //解密为原字符串
        String decryptStr = aes.decryptStr(encryptHex);
        return decryptStr;
    }

    /**
     * DES加密解密
     * @return
     */
    public static String des(String content) {
        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        //构建
        AES aes = SecureUtil.aes(key);
        //加密
        byte[] encrypt = aes.encrypt(content);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);
        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        //解密为原字符串
        String decryptStr = aes.decryptStr(encryptHex);
        return decryptStr;
    }

    /**
     * 对称加密对象的使用
     * @param content
     * @return
     */
    public static String symmetricTest(String content) {

        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();

        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        //加密
        byte[] encrypt = aes.encrypt(content);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);

        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex);
        return decryptStr;
    }
}
