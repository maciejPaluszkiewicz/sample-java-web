package com.codecool.krk.helpers;

import java.security.*;

public class HashHelper {

    public String createHash(String str) throws Exception{
        byte[] bytesOfMessage = str.getBytes("UTF-8");

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] theDigest = md.digest(bytesOfMessage);

        return new String(theDigest);
    }
}
