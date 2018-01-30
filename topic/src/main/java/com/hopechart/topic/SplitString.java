package com.hopechart.topic;

import java.text.DecimalFormat;

/**
 * Created by wp on 2017/3/7.
 *
 * @version 2.0 2017/3/9
 */

public class SplitString {

    private static DecimalFormat df3 = new DecimalFormat("0.0");
    private static DecimalFormat df2 = new DecimalFormat("0.00");

    public static void main(String[] args) {
        p(df3.format(-1));
        p(df3.format(0.5));
        p(df3.format(0));
        p(df3.format(0.0));
        p(df3.format(1));
        p(df3.format(1.4));
        p(df3.format(1.44));
        p(df3.format(1.45));
        p(df3.format(1.5));
        p(df3.format(1.6));
        p(df3.format(500));
        p("--------------");
        p(df2.format(0.5));
        p(df2.format(0));
        p(df2.format(0.0));
        p(df2.format(1));
        p(df2.format(1.4));
        p(df2.format(1.44));
        p(df2.format(1.45));
        p(df2.format(1.5));
        p(df2.format(1.6));
        p(df2.format(500));

//        String str = "a,b,,c,d,,,1,2,3,4,5";
        //测试不同的字符串
//        String str = "aaa,bbb,,ccc,dd,,,11,22,333,444,55";
//        String str = ",aaaa,bb,,cc,dddd,,,111,22,3333,44,55,,";
        String str = " a b  c d   1 2 3 4 5";
//        char split = ',';
        char split = ' ';
//        p("str 为" + str + ", 分隔符为" + split + ", 则有:");
//        p("---------------------------------------------------");
//        p("from\tto\tindex\tresult");
//        p("---------------------------------------------------");
//        p("0\t20\t<0\t" + splitStr(str, split, -1, 0, 20));
//        p("0\t20\t0\t" + splitStr(str, split, 0, 0, 20));
//        p("0\t20\t2\t" + splitStr(str, split, 2, 0, 20));
//        p("0\t20\t4\t" + splitStr(str, split, 4, 0, 20));
//        p("0\t20\t8\t" + splitStr(str, split, 8, 0, 20));
//        p("0\t20\t11\t" + splitStr(str, split, 11, 0, 20));
//        p("0\t20\t>11\t" + splitStr(str, split, 12, 0, 20));
//        p("--------------------------------------");
//        p("3\t15\t0\t" + splitStr(str, split, 0, 3, 15));
//        p("3\t15\t2\t" + splitStr(str, split, 2, 3, 15));
//        p("3\t15\t11\t" + splitStr(str, split, 11, 3, 15));
//        p("--------------------------------------");
//        p("11\t25\t0\t" + splitStr(str, split, 0, 11, 25));
//        p("11\t25\t2\t" + splitStr(str, split, 2, 11, 25));
//        p("11\t25\t4\t" + splitStr(str, split, 4, 11, 25));
//        p("11\t25\t>4\t" + splitStr(str, split, 5, 11, 25));
//
//        p("---------------------------------------------------");
//        //测试不同的参数
//        p("-4\t20\t<-1\t" + splitStr(str, split, -1, -4, 20));
//        p("0\t45\t0\t" + splitStr(str, split, 0, 0, 45));
//        p("6\t2\t2\t" + splitStr(str, split, 2, 6, 2));
//        p("8\t3\t7\t" + splitStr(str, split, 7, 8, 3));
//        p("0\t0\t0\t" + splitStr(str, split, 0, 0, 0));
//        p("21\t22\t1\t" + splitStr(str, split, 1, 21, 22));
//        p("21\t22\t0\t" + splitStr(str, split, 0, 21, 22));

    }

    /**
     * 提取以分隔符 split 分隔的 index 项字符串, 其中:
     *
     * @param str   源字符串
     * @param split 分隔符
     * @param index 索引项, 从 0 开始
     * @param from  字符串 str 的开始索引, 从 0 开始
     * @param to    字符串 str 的结束索引, 不含此索引, 若 < 0 则到字符串末尾
     * @return null 提取 index 项失败, 可能未找到或参数不合法 (值) 返回 str 的 index 项字符串
     */
    public static String splitString(String str, char split, int index,
                                     int from, int to) {
        // 对参数的有效性进行判断
        if (str == null || str.length() == 0) {
            return null;
        }
        if (from < 0) {
            return null;
        }
        if (index < 0) {
            return null;
        }
        if (to < 0 || to > str.length()) {
            to = str.length();
        }
        if (from >= to || index >= to) {
            return null;
        }

        //保存split字符在str中position的数组
        int[] splitTagArray = new int[to - from];
        //用来标记splitTagArray里面存储split字符position的个数
        int tag = 0;
        for (int i = from; i < to; i++) {
            char c = str.charAt(i);
            if (c == split) {
                splitTagArray[tag] = i;
                tag++;
            }
        }
        //去除最后一次的tag++,如果str中不存在split，tag就会为0
        tag--;
        if (tag < 0) {
            return null;
        } else if (splitTagArray[tag] == to - 1) {
            //如果str最后一个字符是split，那么不用处理
        } else {
            //如果str最后一个字符不是split，那么tag要++,同时还有最后一个字符串
            tag++;
            splitTagArray[tag] = to;
        }
        if (index > tag) {
            return null;
        }
        //result为str中从splitTagArray[index-1]到splitTagArray[index]之间的字符串
        String result = null;
        if (index == 0) {
            //从from到splitTagArray[0]
            if (splitTagArray[0] == from) {
                //第一个字符就是split
                result = "";
            } else {
                result = str.substring(from, splitTagArray[0]);
            }

        } else {
            if (splitTagArray[index] - splitTagArray[index - 1] == 1) {
                //如果是两个split相连
                result = "";
            } else {
                result = str.substring(splitTagArray[index - 1] + 1, splitTagArray[index]);
            }
        }
        return result;
    }

    public static String splitStr(String str, char split, int index,
                                  int from, int to) {
        // 对参数的有效性进行判断
        if (str == null || str.length() == 0) {
            return null;
        }
        if (from < 0) {
            return null;
        }
        if (index < 0) {
            return null;
        }
        if (to < 0 || to > str.length()) {
            to = str.length();
        }
        if (from >= to || index >= to) {
            return null;
        }
        String result = null;
        int lastSplitPosition = 0;
        //用来标记split的个数
        int splitTag = 0;
        for (int i = from; i < to; i++) {
            char c = str.charAt(i);
            if (c == split) {
                if (index == splitTag) {
                    if (splitTag == 0) {
                        //从from到i
                        if (i == from) {
                            //第一个字符就是split
                            return "";
                        } else {
                            return str.substring(from, i);
                        }
                    } else {
                        if (i - lastSplitPosition == 1) {
                            //如果是两个split相连
                            return "";
                        } else {
                            return str.substring(lastSplitPosition + 1, i);
                        }
                    }
                }
                splitTag++;
                lastSplitPosition = i;
            }
        }
        //去除最后一次的tag++,如果str中不存在split，splitTag就会为0
        splitTag--;
//        p("splitTag:"+splitTag);
        //一个都没有
        if (splitTag <= 0 || index > splitTag + 1) {
            return null;
        }
        //如果最后splitTag后面还有字符
        if (index == splitTag + 1) {
            return str.substring(lastSplitPosition + 1, to);
        }
        return result;
    }

    private static void p(String str) {
        System.out.println(str);
    }

    /**
     * 第一次修改意见
     *  1. throw new IllegalArgumentException 若参数不合法应该返回 null 而不是异常的 bug;
     *  2. if (split == '\0') 想当然的 bug, 谁说分隔符不能 '\0'!
     *  3. if (to < from) 未处理 to < 0 的 bug;
     *  4. if (to == -1 || to > str.length()) 想当然的 bug, 谁说 to < 0 就是等于 -1!
     *  5. if ((i - 1) >= 0 && str.charAt(i - 1) != split) 明显没理解题意的 bug;
     *  6. 看来没明白 splitString 要实现的功能, 与要实现的目标偏差非常大!
     *  7. 评分: 15分(看在写了一大堆代码的份上)
     */

    /**
     * 第二次修改意见
     *  1. if (splitTagArray[tag] == to - 1) 存在 tag = -1 时越界的 bug;
     *  2. result = "空字符串" 想当然的 bug, 谁告诉你空串的返回值是 '空字符串'?
     *  3. int[] splitTagArray = new int[to - from] 额外的内存开销;
     *  4. 评分: 75分, 与第一版比进步很大, 虽然使用 splitTagArray 方式解决了问题, 但需要转换思路去优化代码。
     */

}
