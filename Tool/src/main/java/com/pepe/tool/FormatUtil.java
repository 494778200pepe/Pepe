package com.pepe.tool;

import java.text.DecimalFormat;

/**
 * DecimalFormat 类主要靠 # 和 0 两种占位符号来指定数字长度。
 * 0 表示如果位数不足则以 0 填充，# 表示只要有可能就把数字拉上这个位置，没有就不填充。
 * 上面的例子包含了差不多所有的基本用法，如果你想了解更多，请参考 DecimalFormat 类的文档。
 * Java DecimalFormat的主要功能及使用方法 - Copyにんじゃ兰染 - CSDN博客
 * http://blog.csdn.net/evangel_z/article/details/7624503
 * DecimalFormat (Java Platform SE 7 )
 * https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
 *
 * @author wang
 * @date 2017/10/23
 */

public class FormatUtil {

    /**
     * 转一位小数
     *
     * @param value 带转换的double值
     * @return 一位小数的字符串
     */
    public static String formatToOneDecimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(value);
    }

    /**
     * 转两位小数
     *
     * @param value 带转换的double值
     * @return 两位小数的字符串
     */
    public static String formatToTwoDecimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(value);
    }


    public static void main(String[] args) {
        //圆周率
        double pi = 3.1415927;
        // 取一位整数 //3
        System.out.println(new DecimalFormat("0").format(pi));
        //取一位整数和两位小数 //3.14
        System.out.println(new DecimalFormat("0.00").format(pi));
        //取两位整数和三位小数，整数不足部分以0填补。 // 03.142
        System.out.println(new DecimalFormat("00.000").format(pi));
        // 03.142
        System.out.println(new DecimalFormat("00.000000000").format(pi));
        //取所有整数部分 //3
        System.out.println(new DecimalFormat("#").format(pi));
        //以百分比方式计数，并取两位小数 //314.16%
        System.out.println(new DecimalFormat("#.##%").format(pi));
        //光速
        long c = 299792458;
        //显示为科学计数法，并取五位小数 //2.99792E8
        System.out.println(new DecimalFormat("#.#####E0").format(c));
        //显示为两位整数的科学计数法，并取四位小数 //29.9792E7
        System.out.println(new DecimalFormat("00.####E0").format(c));
        //每三位以逗号进行分隔。 //299,792,458
        System.out.println(new DecimalFormat(",###").format(c));
        //将格式嵌入文本
        System.out.println(new DecimalFormat("光速大小为每秒,###米。").format(c));
    }
}
