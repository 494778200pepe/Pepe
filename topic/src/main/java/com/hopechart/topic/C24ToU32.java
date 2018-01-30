package com.hopechart.topic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Created by wang on 2017/4/19.
 */

public class C24ToU32 {

    private static char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'F', 'H', 'K', 'M', 'P', 'S', 'T', 'U', 'W', 'X', 'Y'};

    /**
     * 二十四进制编码串转换为32位无符号整数
     *
     * @param str  二十四进制编码串
     * @param code 转换返回码, 若 code == null 则忽略返回码
     * @return 转换后的返回值, 转换是否成功从 code 得到。
     */
    public static int C24ToU32(String str, Integer code) throws NoSuchFieldException, IllegalAccessException {

        long result = 0;
        int resultCode = -1;
        if (null != str && str.length() > 0) {
            int i = 0;
            int len = str.length();
            int pos;
            long tempResult;
            char c;
            resultCode = 0;
            do {
                c = str.charAt(i++);
                if ('a' <= c && c <= 'z') {
                    c -= 'a' - 'A';
                }
                pos = Arrays.binarySearch(chars, c);
                if (pos >= 0) {
                    tempResult = result * 24 + pos;
                    if (tempResult > 0xFFFFFFFFL) {
                        resultCode = i;
                        break;
                    } else {
                        result = tempResult;
                    }
                } else {
                    resultCode = i;
                    break;
                }
            } while (i < len);
        }

        if (null != code) {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(code, resultCode);
        }
        return (int) result;
    }

    public static void main(String[] args) {
        Integer code = 99;
        try {
//            p("0:" + (0 >>> 1));
//            p("1:" + (1 >>> 1));
//            p("10:" + (10 >>> 1));
//            p("11:" + (11 >>> 1));
//            p("-1:" + (-1 >>> 1));
//            p("-2:" + (-2 >>> 1));
//            p("-2147483648:" + (-2147483648 >>> 1));
//            p("Arrays.binarySearch-A:" + Arrays.binarySearch(chars, 'A'));
//            p("Arrays.binarySearch-2:" + Arrays.binarySearch(chars, '2'));
//            p("Arrays.binarySearch-F:" + Arrays.binarySearch(chars, 'F'));
//            p("Arrays.binarySearch-*:" + Arrays.binarySearch(chars, '*'));
//            p("Arrays.binarySearch-+:" + Arrays.binarySearch(chars, '+'));
//            p("Arrays.binarySearch-%:" + Arrays.binarySearch(chars, '&'));
            p("null---" + C24ToU32(null, code) + ",code = " + code);
            p(" ---" + C24ToU32("", code) + ",code = " + code);
            p("*123---" + C24ToU32("*123", code) + ",code = " + code);
            p("11*123---" + C24ToU32("11*123", code) + ",code = " + code);
            p("-123*123---" + C24ToU32("-123*123", code) + ",code = " + code);
            p("XXXXXX---" + C24ToU32("XXXXXX", code) + ",code = " + code);
            p("XXXXXXX---" + C24ToU32("XXXXXXX", code) + ",code = " + code);
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

    /**
     * 第一次修改意见
     *  1. str = str.toUpperCase() 改变了 str 对象值的 bug, 而且效率不高;
     *  2. String array = "0123456789ABCFHKMPSTUWXY" 不应该定义为局部对象;
     *  3. if (i != 0) 判断错误而且此判断意义不大, 是不是 if (resultCode == 0)?
     *  4. str = str.substring(0, i) 效率不高;
     *  5. long charValue = array.indexOf(String.valueOf(str.charAt(k))) 中array.indexof 效率不高, 能否转变思路, 如: 二分定位?
     *  6. if (tempValue > 0xFFFFFFFFL) {tempValue = 0; resultCode = -1;} 返回值及返回码错误的 bug;
     *  7. 评分: 45分
     */

    /**
     * 第二次修改意见
     *  1. c = (char) (c - ('a' - 'A')) 可以写成: c -= 'a' - 'A';
     *  2. mid = (low + high) / 2; 可以写成: mid = (low + high) >>> 1;
     *  3. else if (chars[mid] == c) {..} 此判断是画蛇添足, 直接 else 即可;
     *  4. if (i == 0) resultCode = 1; else resultCode = i + 1; 要学会合并逻辑, 有没有发现判断是多余的? 直接 resultCode = i + 1 即可;
     *  5. if (result > 0xFFFFFFFFL) {..} 应该放在 else if (chars[mid] == c) 里面, 且不必重复计算, 可以使用临时变量来解决;
     *  6. 数组的二分查找使用 Arrays.binarySearch(chars, c) 返回的索引再处理更好;
     *  7. 评分: 80分
     */

    /**
     * 第三次修改意见
     *  1. 可以把小写字母也放在数组中, 这样可以减少 if ('a' <= c && c <= 'z') 判断;
     *  2. resultCode = 0; result = tempResult; 应该把 resultCode = 0 放在 do..while 之前即可;
     *  3. 评分: 90分
     */

}
