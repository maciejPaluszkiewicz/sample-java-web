package com.codecool.krk.helpers;

public class HashHelper {

    public static String createHash(String str){
        int hash = 7;
        for (int i = 0; i < str.length(); i++) {
            hash = hash*31 + str.charAt(i);
        }
        return Integer.toString(hash);
    }
}
