package com.hopechart.topic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 田富版
 * Created by tf on 2017/4/25.
 */

public class C24To32 {

    public static void main(String[] args) {
        Integer code = 99;
        try {
            p("null---" + C24ToU32(null, code) + ",code = " + code);
            p(" ---" + C24ToU32("", code) + ",code = " + code);
            p("*123---" + C24ToU32("*123", code) + ",code = " + code);
            p("11*123---" + C24ToU32("11*123", code) + ",code = " + code);
            p("-123*123---" + C24ToU32("-123*123", code) + ",code = " + code);
            p("XXXXXXXXXXXX---" + C24ToU32("XXXXXXXXXXXX", code) + ",code = " + code);

            p("------------------------------------------");
            p("0---" + C24ToU32("0", code) + ",code = " + code);
            p("T---" + C24ToU32("T", code) + ",code = " + code);
            p("10---" + C24ToU32("10", code) + ",code = " + code);
            p("1X---" + C24ToU32("1X", code) + ",code = " + code);
            p("40---" + C24ToU32("40", code) + ",code = " + code);
            p("Ky---" + C24ToU32("Ky", code) + ",code = " + code);
            p("Ky11---" + C24ToU32("Ky11", code) + ",code = " + code);
            p("ky11FH---" + C24ToU32("ky11FH", code) + ",code = " + code);

            p("------------------------------------------");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void p(String str) {
        System.out.println(str);
    }

    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'F', 'H', 'K', 'M', 'P', 'S', 'T', 'U', 'W', 'X', 'Y'};

    public static int C24ToU32(String str, Integer code) throws
            IllegalAccessException, NoSuchFieldException {
        int result = 0;
        int nCode = -1;
        if (null != str && str.length() > 0) {
            int i = 0, len = str.length();
            nCode = 0;
            long nRet = 0;
            while (i < len) {
                char c = str.charAt(i++);
                if ('a' <= c && c <= 'z') {
                    c = (char) ((int) c - ('a' - 'A'));
                }
                int low = 0;
                int high = digits.length - 1;
                boolean contains = false;
                while (low <= high) {
                    int mid = (low + high) >>> 1;
                    int midVal = digits[mid];
                    if (midVal < c) {
                        low = mid + 1;
                    } else if (midVal > c) {
                        high = mid - 1;
                    } else {
                        nRet *= 24;
                        nRet += mid;
                        contains = true;
                        break;
                    }
                }
                if (nRet > 0xFFFFFFFFL) {
                    nRet = 0;
                    nCode = -1;
                    break;
                }
                if (!contains) {
                    nCode = i;
                    break;
                }
            }
            result = (int) (nRet & 0xFFFFFFFFL);
        }
        if (null != code) {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(code, nCode);
        }
        return result;
    }
}
