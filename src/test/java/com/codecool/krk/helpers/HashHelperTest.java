package com.codecool.krk.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashHelperTest {

    @Test
    public void createHashReturnsCorrectHash() {
        String result = HashHelper.createHash("test");
        assertEquals("10021145", result);
    }
}


//"test" 10021145
//"" 7
//"666" 262159

//        System.out.println(HashHelper.createHash("test"));
//                System.out.println(HashHelper.createHash(null));
