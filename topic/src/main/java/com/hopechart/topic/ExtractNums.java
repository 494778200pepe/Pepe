package com.hopechart.topic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by wang on 2017/5/9.
 */

public class ExtractNums {

    /**
     * 从字符串中提取数字串并排序
     *
     * @param str  字符串
     * @param dest 返回数字串
     * @return -1,提取失败, 参数不合法;0,提取失败, 无数字串;>0,提取成功, 返回数字串的项数
     */
    public static int ExtractNums(String str, StringBuffer dest) {
        int result = -1;
        if (null != str && str.length() > 0 && null != dest) {
            result = 0;
            TreeMap<Integer, ArrayList<String>> map = new TreeMap<>();
            String number;
            //每个数字串中无效0的长度
            int tag = -1;
            //截取数字串的起始点和结尾点
            int start = -1, end;
            int strLength = str.length();
            //记录有效长度
            int length;
            for (int i = 0; i < strLength; ) {
                char c = str.charAt(i);
                i++;
                if ('0' > c || c > '9' || i == strLength) {
                    if (start > -1) {
                        end = i == strLength ? i : (i - 1);
                        number = str.substring(start, end);
                        length = tag == -1 ? 0 : (end - tag);
                        //保存
                        if (map.containsKey(length)) {
                            map.get(length).add(number);
                        } else {
                            ArrayList<String> tempList = new ArrayList<>();
                            tempList.add(number);
                            map.put(length, tempList);
                        }
                        //重置
                        tag = -1;
                        start = -1;
                    }
                } else {
                    if (start == -1) {
                        start = i - 1;
                    }
                    //非0数字
                    if ('0' != c && tag == -1) {
                        tag = i - 1;
                    }
                }
            }
            if (!map.isEmpty()) {
                dest.setLength(0);
                Iterator<Integer> it = map.keySet().iterator();
                while (it.hasNext()) {
                    ArrayList<String> list = map.get(it.next());
                    if (list.size() > 1) {
                        Collections.sort(list, new Comparator<String>() {
                            @Override
                            public int compare(String str1,
                                               String str2) {
                                int len1 = str1.length();
                                int len2 = str2.length();
                                int activeLen = len1 <= len2 ? len1 : len2;
                                char c1, c2;
                                do {
                                    c1 = str1.charAt(len1 - activeLen);
                                    c2 = str2.charAt(len2 - activeLen);
                                    if (c1 > c2) {
                                        return 1;
                                    } else if (c1 < c2) {
                                        return -1;
                                    } else {
                                        if (activeLen > 1) {
                                            activeLen--;
                                        } else {
                                            return 0;
                                        }
                                    }
                                } while (true);
                            }
                        });
                    }
                    for (String s : list) {
                        result++;
                        dest.append(s);
                        dest.append(' ');
                    }
                }
                dest.deleteCharAt(dest.length() - 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer();
        str.append("+++++++++++++");
        p(ExtractNums("fs fd00010sdf,fs13,1sf0,14, f fdgdfs11**,gf010,gfhf0011,013,hgf15,00010,12ds000fsf*ds1100dsf00--00000", str));
        p(str.toString() + ",length = " + str.length());
        p("-----------");
        p(ExtractNums("444,0212,2222", str));
        p(str.toString() + ",length = " + str.length());
        p("-----------");
        str.append("+++++++++++++");
        p(ExtractNums(";1024;00256abcd51e002f;9842ghi298", str));
        p(str.toString() + ",length = " + str.length());
        p("-----------");
        str.append("+++++++++++++");
        p(ExtractNums("$$$$987654321012345##000000012345678", str));
        p(str.toString() + ",length = " + str.length());
        p("-----------");
        str.append("+++++++++++++");
        p(ExtractNums("$$$$222222222222222222222222#$222222222222222222222223##00000999999999999999999999999999999990012345678*****111111111111189898989898989898989898989898989898989111111111111111111111111111111111111111111115+111111111111189898989898989898989898989898989898989111111111111111111111111111111111111111111113-111111111111189898989898989898989898989898989898989111111111111111111111111111111111111111111112", str));
        p(str.toString() + ",length = " + str.length());
        p("-----------");
        str.setLength(0);
        str.append("-+++++++++++++-");
        p(ExtractNums("as%%#this is error, other reason", str));
        p(str.toString() + ",length = " + str.length());
        p("-----------");
    }

    private static void p(int i) {
        System.out.println(i);
    }

    private static void p(String i) {
        System.out.println(i);
    }

//                try {
//                //获取String类中的value字段
//                Field valueFieldOfString = String.class.getDeclaredField("value");
//                //改变value属性的访问权限
//                valueFieldOfString.setAccessible(true);
//                //获取s对象上的value属性的值
//                char[] value = (char[]) valueFieldOfString.get(dest);
//                if (dest.length() <= destStringBuffer.length()) {
//                    for (int i = 0; i < dest.length(); i++) {
//                        value[i] = destStringBuffer.toString().charAt(i);
//                    }
//                } else {
//                    for (int i = 0; i < destStringBuffer.length(); i++) {
//                        value[i] = destStringBuffer.toString().charAt(i);
//                    }
//                    for (int i = destStringBuffer.length(); i < dest.length(); i++) {
//                        value[i] = '\0';
//                    }
//                }
//            } catch (Exception e) {
//                p("error : " + e.getMessage());
//            }

    /**
     *  1. p("单个字符串：" + number + "    有效长度为：" + length); 调试时可以使用,但提交时就必须去掉!
     *  2. sb.append(c) 效率不高, 可以记住数字串的 from 和 to, 然后使用 substring 更好;
     *  3. compare(String str1, String str2) 效率不高, activeLen 没必要计算, 只要取长度小的为比较即可, 前导 '0' 不影响结果;
     *  4. dest.append(' '); dest.append(s); 此二行代码互换位置效率更高, 因为删除末尾字符效率为 O(1), 而删除第一个字符的效率为 O(length);
     *  5. 评分: 70分
     */

    /**
     * 1. 已按要求修改完成, 抽空可以换种思路考虑问题, 能否不使用 map 来实现?
     * 2. 评分: 90分
     */

}
