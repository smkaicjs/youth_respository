package com.shimk.Txgc.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TextUtils {

    public static String decodeMD5(String str){
        try {
            byte[] hex = MessageDigest.getInstance("MD5").digest(str.getBytes());
            String string="";
            for (byte b:hex){
                string = string+String.format("%02X", new Integer(b & 0xFF));
            }
            return string;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
