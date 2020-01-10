package com.example.core.utils;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * DES是一种对称加密算法，所谓对称加密算法:加密和解密使用相同的秘钥的算法
 * @author daniel
 * @date 2019-01-09
 */
@Component
@Slf4j
public final class DESUtil {

    private static Key key;
    //设置秘钥key
    private static String KEY_STR="myKey";
    private static String CHARSETNAME="UTF-8";
    private static String ALGORITHM="DES";

    static{
        try{
            //生成DES算法对象
            KeyGenerator generator=KeyGenerator.getInstance(ALGORITHM);
            //运用SHA1安全策略
            SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
            //设置上密钥种子
            secureRandom.setSeed(KEY_STR.getBytes());
            //初始化基于SHA1的算法对象
            generator.init(secureRandom);
            //生成密钥对象
            key=generator.generateKey();
            generator=null;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取加密的信息
     * @param str
     * @return
     */
    public String getEncryptString(String str){
        //基于BASE64编码，接收byte[]并转换成String
        BASE64Encoder base64Encoder=new BASE64Encoder();
        try {
            // 按UTF8编码
            byte[] bytes = str.getBytes(CHARSETNAME);
            // 获取加密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码信息
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] doFinal = cipher.doFinal(bytes);
            // byte[]to encode好的String并返回
            return base64Encoder.encode(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取解密之后的信息
     *
     * @param str
     * @return
     */
    public static String getDecryptString(String str) {
        // 基于BASE64编码，接收byte[]并转换成String
        BASE64Decoder base64decoder = new BASE64Decoder();
        try {
            // 将字符串decode成byte[]
            byte[] bytes = base64decoder.decodeBuffer(str);
            // 获取解密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化解密信息
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 解密
            byte[] doFinal = cipher.doFinal(bytes);
            // 返回解密之后的信息
            return new String(doFinal, CHARSETNAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        //加密
//        logger.info(getEncryptString("root"));//WnplV/ietfQ=
//        logger.info(getEncryptString("123456"));//QAHlVoUc49w=
//        //解密
//        logger.info(getDecryptString(getEncryptString("root")));//root
//        logger.info(getDecryptString(getEncryptString("123456")));//123456
    }

    /**
     * des加密解密工具
     *
     * @author lushusheng 2018-11-30
     */
//    @Slf4j
//    public class DES {
//        private static final String ALGORITHM = "DES/CBC/PKCS5Padding";
//        private static final byte[] IV_DEFAULT = "DIICHDES".getBytes();
//
//        private DES(){}
//        /**
//         * 加密
//         * @param key 加密密钥
//         * @param source 加密的内容
//         * @param output 加密的结果
//         * @return 加密后的长度
//         */
//        public static int encrypt(byte[] key, byte[] source, byte[] output){
//            return encrypt(key, IV_DEFAULT, source, 0, source.length, output, 0);
//        }
//
//        /**
//         * 加密
//         * @param key 密钥
//         * @param source 加密的内容
//         * @return 加密的结果缓存
//         */
//        public static byte[] encrypt(byte[] key, byte[] source){
//            return encrypt(key, IV_DEFAULT, source, 0, source.length);
//        }
//
//        /**
//         * 加密
//         * @param key 密钥
//         * @param iv 密钥补充
//         * @param source 加密的内容缓存
//         * @param offset 缓存偏移
//         * @param length 需要加密的长度，即从offset开始的length内容需要加密
//         * @return 加密的结果缓存
//         */
//        public static byte[] encrypt(byte[] key, byte[] iv, byte[] source, int offset, int length){
//            try {
//                Cipher cipher = Cipher.getInstance(ALGORITHM);
//                KeySpec keySpec = new DESKeySpec(key);
//                SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
//                cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
//                byte[] output = new byte[cipher.getOutputSize(length)];
//                cipher.doFinal(source, offset, length, output, 0);
//                return output;
//            }catch (Exception e){
//                log.error("des加密异常：{}", e.getMessage());
//            }
//            return new byte[0];
//        }
//
//        /**
//         * 加密
//         * @param key 密钥
//         * @param iv 密钥补充
//         * @param source 加密的内容缓存
//         * @param offset 缓存的偏移
//         * @param length 需要加密的长度，即从offset开始的length内容需要进行加密
//         * @param output 输出缓存
//         * @param offsetOutput 输出缓存的偏移
//         * @return 加密后的长度
//         */
//        public static int encrypt(byte[] key, byte[] iv, byte[] source, int offset, int length,
//                                  byte[] output, int offsetOutput){
//            try {
//                Cipher cipher = Cipher.getInstance(ALGORITHM);
//                KeySpec keySpec = new DESKeySpec(key);
//                SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
//                cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
//                return cipher.doFinal(source, offset, length, output, offsetOutput);
//            }catch (Exception e){
//                log.error("des加密异常：{}", e.getMessage());
//            }
//            return 0;
//        }
//
//        /**
//         * 解密
//         * @param key 解密密钥
//         * @param iv 密钥补充
//         * @param source 解密的内容缓存
//         * @param offset 缓存的偏移
//         * @param length 需要解密的长度，即从offset开始length长度的内容需要进行解密
//         * @param output 输出缓存
//         * @param offsetOutput 输出缓存的偏移
//         * @return 解密后的长度
//         */
//        public static int decrypt(byte[] key, byte[] iv, byte[] source, int offset, int length,
//                                  byte[] output, int offsetOutput){
//            try {
//                Cipher cipher = Cipher.getInstance(ALGORITHM);
//                KeySpec keySpec = new DESKeySpec(key);
//                SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
//                cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
//                return cipher.doFinal(source, offset, length, output, offsetOutput);
//            }catch (Exception e){
//                log.error("des加密异常：{}", e.getMessage());
//            }
//            return 0;
//        }
//
//        /**
//         * 解密
//         * @param key 密钥
//         * @param iv 补充密钥
//         * @param source 解密的内容
//         * @param offset 缓存的偏移
//         * @param length 解密的长度，即从offset开始的length长度的内容都需要解密
//         * @return 解密的结果
//         */
//        public static byte[] decrypt(byte[] key, byte[] iv, byte[] source, int offset, int length){
//            byte[] output = new byte[length];
//            if (decrypt(key, iv, source, offset, length, output, 0) == 0){
//                return new byte[0];
//            }
//            return output;
//        }
//
//        /**
//         * 解密
//         * @param key 密钥
//         * @param source 解密的内容
//         * @return 解密的结果
//         */
//        public static byte[] decrypt(byte[] key, byte[] source){
//            return decrypt(key, IV_DEFAULT, source, 0, source.length);
//        }
//
//        /**
//         * 解密
//         * @param key 解密密钥
//         * @param source 解密的内容
//         * @param output 解密的结果
//         * @return 解密结果的长度
//         */
//        public static int decrypt(byte[] key, byte[] source, byte[] output){
//            return decrypt(key, IV_DEFAULT, source, 0, source.length, output, 0);
//        }
//    }
}