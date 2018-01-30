package com.hopechart.topic;

/**
 * Created by wang on 2017/3/28.
 */

public class U32ToC24 {
    public static void main(String[] args) {
        //int的范围：“-2147483648”到“2147483647

        p("23/24:" + (23 / 24));
        p("0的24值:\t" + U32ToC24(0));
        p("还原0:\t" + C24ToU32(U32ToC24(0)));
        p("19的24值:\t" + U32ToC24(19));
        p("还原19:\t" + C24ToU32(U32ToC24(19)));
        p("24的24值:\t" + U32ToC24(24));
        p("还原24:\t" + C24ToU32(U32ToC24(24)));
        p("46的24值:\t" + U32ToC24(46));
        p("还原46:\t" + C24ToU32(U32ToC24(46)));
        p("96的24值:\t" + U32ToC24(96));
        p("还原96:\t" + C24ToU32(U32ToC24(96)));
        p("383的24值:\t" + U32ToC24(383));
        p("还原383:\t" + C24ToU32(U32ToC24(383)));
        p("220633的24值:\t" + U32ToC24(220633));
        p("还原220633:\t" + C24ToU32(U32ToC24(220633)));
        p("127084934的24值:\t" + U32ToC24(127084934));
        p("还原127084934:\t" + C24ToU32(U32ToC24(127084934)));
        p("2147483647:\t" + U32ToC24(2147483647));
        p("还原2147483647:\t" + C24ToU32(U32ToC24(2147483647)));

        p("---------------------负数测试-------------------------------------");

        p("-1的24值:\t" + U32ToC24(-1));
        p("还原-1:\t" + C24ToU32(U32ToC24(-1)));
        p("-2548的24值:\t" + U32ToC24(-2548));
        p("还原-2548:\t" + C24ToU32(U32ToC24(-2548)));
        p("-2147483647的24值:\t" + U32ToC24(-2147483647));
        p("还原-2147483647:\t" + C24ToU32(U32ToC24(-2147483647)));
        p("-2147483648的24值:\t" + U32ToC24(-2147483648));
        p("还原-2147483648:\t" + C24ToU32(U32ToC24(-2147483648)));

    }

    /**
     * 32位无符号整数转换为二十四进制编码串
     * 注: 二十四进制编码串为无符号值，而在 java 中因 int 为有符号整数，故需要考虑负数时的特殊处理。
     *
     * @param value
     * @return
     */
    public static String U32ToC24(int value) {
        StringBuffer result = new StringBuffer();
        String array = "0123456789ABCFHKMPSTUWXY";
        long tempValue;

        //方法一：（）
        //获取value的二进制字符串
//        String binaryStr = Integer.toBinaryString(value);
        //将二进制字符串转换成long值，去掉正负号
//        tempValue = Long.valueOf(binaryStr, 2);
        //方法二：
        tempValue = value & 0xFFFFFFFFL;

        do {
            result.append(array.charAt((int) (tempValue % 24)));
            tempValue = tempValue / 24;
        } while (tempValue != 0);

        return result.reverse().toString();
    }

    /**
     * 还原方法
     *
     * @param str
     * @return
     */
    public static int C24ToU32(String str) {
        String array = "0123456789ABCFHKMPSTUWXY";
        long tempValue = 0;
        int mul = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            Long charValue = (long) (array.indexOf(String.valueOf(str.charAt(i))));
            for (int j = 0; j < mul; j++) {
                charValue *= 24;
            }
            tempValue += charValue;
            mul++;
        }
        int result = new Long(tempValue).intValue();
        return result;
    }

    private static void p(String str) {
        System.out.println(str);
    }


    /**
     * 第一次修改意见
     *  1. resulut = new StringBuffer() 命名拼写有误, resulut 应该为 result;
     *  2. String binaryStr = Integer.toBinaryString(value) 转换成二进制字符串的操作效率太低, 转换成 long 非常简单: tempValue = value & 0x0FFFFFFFFL;
     *  3. resulut.append(array.charAt(Integer.parseInt(String.valueOf(tempValue % 24))))太绕了, 直接 append(array.charAt((int)(tempValue % 24))) 就可以了;
     *  4. while (tempValue / 24.0 > 0) 判断有问题, 直接 while (tempValue != 0) 即可;
     *  5. 转换算法没啥问题, 只是基本功太差, 能够直接解决的问题非要绕一大圈子!
     *  6. 评分: 60分
     */


}
