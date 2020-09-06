package com.github.asyu.herren.util;

public class NumberUtils {

    public static int toInt(String number) {
        return toInt(number, 0);
    }

    public static int toInt(String number, int defaultNumber) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultNumber;
        }
    }

    public static boolean isNumber(String number) {
        if(number == null || number.trim().length() == 0) {
            return false;
        }
        try {
            int parsed = Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
