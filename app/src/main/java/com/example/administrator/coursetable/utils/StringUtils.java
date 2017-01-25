package com.example.administrator.coursetable.utils;

/**
 * Created by ShaoQuanwei on 2017/1/25.
 */

public class StringUtils {


    public static boolean isEmpty(String str)
    {
        if(str == null || str.length() < 1)
        {
            return true;
        }

        return false;
    }


}
