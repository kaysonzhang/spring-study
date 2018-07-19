package com.kayson.api.Utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author by kayson
 * @data 2018/7/17 12:19
 * @description 密码验证，使用的是shiro的md5-slat生成密码
 */
public class PasswordUtil {

    //获得一个16位字节的盐值
    public static String getSalt(){
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        return secureRandom.nextBytes(8).toHex();
    }

    //生成密码
    public static String createPassword(String password, String slat){
        if(password.equals("") || slat.equals("")){
            return null;
        }

        String hashAlgorithmName = "MD5";//加密方式
        //Object crdentials = password;//密码原值

        ByteSource salt = ByteSource.Util.bytes(slat);//以账号作为盐值
        int hashIterations = 1024;//加密1024次
        SimpleHash hash = new SimpleHash(hashAlgorithmName, password, salt, hashIterations);
        return hash.toString();
    }

    //验证密码
    public static boolean verify(String password, String receivePassword, String slat) {
        String buildpw = PasswordUtil.createPassword(receivePassword, slat);
        if(password.equals(buildpw)){
            return true;
        }
        return false;

    }
}
