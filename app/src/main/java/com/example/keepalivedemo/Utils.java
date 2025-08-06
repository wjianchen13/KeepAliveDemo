package com.example.keepalivedemo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static void log(String str) {
        System.out.println("===============> " + str);
    }

    public static String getCurrentData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
