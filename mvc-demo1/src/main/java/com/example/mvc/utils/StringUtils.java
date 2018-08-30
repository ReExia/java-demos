package com.example.mvc.utils;

public class StringUtils {

    /**
     * 截取前缀
     * @param strValue
     * @param prefix
     * @return
     */
    public static String trimByPrefix(String strValue ,String prefix){
        if (strValue.startsWith("redirect:")) {
            String newString = strValue.replace(prefix, "");
            int index = newString.lastIndexOf("/");
            String start = newString.substring(0, index);
            String end = newString.substring(index + 1);

            return start + "?param=" + end;
        } else {
            String newString = strValue.replace(prefix, "");
            return newString;
        }
    }

}
