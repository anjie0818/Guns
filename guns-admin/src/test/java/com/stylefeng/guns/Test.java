package com.stylefeng.guns;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
    //    str.substring(str.length()-4,str.length);

        String old="1234567890abcd";
        String star=old.substring(0,5);
        String end=old.substring(old.length()-4,old.length());
        Map<String,String> map=new HashMap<>();
        map.put("anjie","n");
        map.put("anjie2","m");
        System.out.println(map);


    }
}
