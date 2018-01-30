package com.hopechart.topic;

import java.util.ArrayList;

/**
 * Created by wang on 2017/7/4.
 */

public class SubBCDInt {

    private static boolean printLog = true;

    /**
     * 两个大的整数相减之差
     *
     * @param num1 整数的BCD码数组1
     * @param num2 整数的BCD码数组2
     * @param dest 存放结果BCD码数组
     * @return -3,相减失败, 参数不合法;-2,相减失败, BCD码数组2存在不合法字符;-1,相减失败, BCD码数组1存在不合法字符; 0,相减失败, 结果BCD码串的数组太小;>0相减成功, 返回BCD码串的字节数
     */
    public static int SubBCDInt(final byte[] num1, final byte[] num2, byte[] dest) {
        int result = -3;
        //-3,相加失败, 参数不合法;
        if (num1 != null && num1.length > 0 && num2 != null && num2.length > 0 && dest != null) {
            //先判断符号是否相同，决定是加法还是减法
            //加法，取绝对值相加，最后添加正负号
            //减法，需要判断大小，决定结果是正是负

            //判断两个整数的符号,标记是否是负数
            int isNegative1 = 0, isNegative2 = 0; //0为正数，1为负数
            //取第一个字节，判断符号位
            byte symbolByte1 = num1[0],symbolByte2 = num2[0];
            if (symbolByte1 == (byte) 0xFF)  isNegative1 = 1;
            if (symbolByte2 == (byte) 0xFF) isNegative2 = 1;

            //判断是做加法还是减法，符号不同，则加法（去除符号位左加法），符号相同，则减法
            boolean addOrSub = isNegative1 != isNegative2;
            if (addOrSub) {
                result = dealAdd(num1, num2, isNegative1, dest);
            } else {
                result = dealSub(num1, num2, isNegative1, dest);
            }
        }
        return result;
    }

    /**
     d. if ((low | height) == 0) 判断效率不高, 应该 if (low || height);
     * @param num1
     * @param num2
     * @param isNegative1
     * @param dest
     * @return
     */
    private static int dealAdd(final byte[] num1, final byte[] num2, int isNegative1, byte[] dest) {
        int resultSymbol = isNegative1;//标记符号位,0表示正数，1表示负数
        int isNegative2 = isNegative1 ^ 1;
        int len1 = num1.length - isNegative1;
        int len2 = num2.length - isNegative2;
        int destLen = dest.length;
        int len = len1 >= len2 ? len1 : len2;
        byte b1, b2;
        int height, height1, height2, low, low1, low2;

        int result = 0;
        int correctResult = 0;

        //是否进位的标记
        int carry = 0;
        for (int i = 1; i <= len; i++) {
            result = i;
            height1 = low1 = height2 = low2 = 0;
            if (i <= len1) {
                b1 = num1[num1.length - i];
                height1 = (b1 >> 4) & 0x0F;//高四位值
                low1 = b1 & 0x0F;//低四位值
                if (height1 > 9 || low1 > 9) {
                    return -1;
                }
            }
            if (i <= len2) {
                b2 = num2[num2.length - i];
                height2 = (b2 >> 4) & 0x0F;
                low2 = b2 & 0x0F;
                if (height2 > 9 || low2 > 9) {
                    return -2;
                }
            }
            low = low1 + carry + low2;
            carry = 0;

            height = height1 + height2;
            if (low > 9) {
                low -= 10;
                height++;
            }
            if (height > 9) {
                height -= 10;
                carry = 1;
            }

            //第五步：存储
            if (i <= destLen) {
                //dest数组长度足够
                dest[destLen - i] = (byte) (low + (height << 4));
                //设置修正参数
                if ((low | height) == 0) {
                    correctResult++;
                } else {
                    correctResult = 0;
                }
            } else {
                //dest数组长度不足，判断是否为0
                if ((low | height) != 0) {
                    result = 0;
                    break;
                } else {
                    //两个都是0
                    correctResult++;
                }
            }
        }

        if (result > 0) {
            //修正result
            if (result == correctResult) {
                dest[0] = 0;
                result = 1;
            } else {
                result = result - correctResult;
                int correctDest = destLen - result - resultSymbol - carry;
                if (correctDest < 0) {
                    result = 0;
                } else {
                    result += resultSymbol;//加上符号位
                    if (resultSymbol == 1) {
                        dest[0] = (byte) 0xFF;
                    }
                    if (carry == 1) {
                        dest[resultSymbol] = (byte) 1;
                        result++;
                    }
                    for (int i = resultSymbol + carry; i < result; i++) {
                        dest[i] = dest[i + correctDest];
                    }
                }
            }
        }
        return result;
    }

    private static int dealSub(final byte[] num1, final byte[] num2, int isNegative1, byte[] dest) {
        int len1 = num1.length - isNegative1, len2 = num2.length - isNegative1;
        int len = len1 >= len2 ? len1 : len2;

        //如果是减法，需要用到tempDest
        byte[] tempDest = new byte[len + 1];//tempDest的有效长度并一定是最终的有效长度

        byte b1, b2;
        int height, height1, height2, low, low1, low2;

        int result = 0;
        int correctResult = 0;
        int carry = 0;
        for (int i = 1; i <= len; i++) {
            result = i;
            height1 = low1 = height2 = low2 = 0;
            if (i <= len1) {
                b1 = num1[num1.length - i];
                height1 = (b1 >> 4) & 0x0F;//高四位值
                low1 = (b1 & 0x0F);//低四位值
                if (height1 > 9 || low1 > 9) {
                    return -1;
                }
            }
            if (i <= len2) {
                b2 = num2[num2.length - i];
                height2 = (b2 >> 4) & 0x0F;
                low2 = (b2 & 0x0F);
                if (height2 > 9 || low2 > 9) {
                    return -2;
                }
            }

            low1 -= carry;
            low = low1 - low2;
            carry = 0;
            if (low1 < low2) {
                carry = 1;
                low += 10;
            }
            height1 -= carry;
            height = height1 - height2;
            carry = 0;
            if (height1 < height2) {
                carry = 1;
                height += 10;
            }

            //第五步：存储
            tempDest[tempDest.length - i] = (byte) (low + (height << 4));

            if ((low | height) == 0) {
                correctResult++;
            } else {
                correctResult = 0;
            }
        }
        if (result > 0) {
            //修正result
            if (result == correctResult) {
                dest[0] = 0;
                result = 1;
            } else {
                result = result - correctResult;
                if (carry == 1) {//计算完成，需要借位
                    p("计算完成，需要借位");
                    p("tempDest",tempDest);
                    result = reverseByte(tempDest, result);

                    if (dest.length < result) {
                        result = 0;
                    } else {
                        if (isNegative1 == 0) {//结果是负数
                            dest[0] = (byte) 0xFF;
                            for (int i = 1; i <= result; i++) {
                                dest[i] = tempDest[tempDest.length - result - 1 + i];
                            }
                            result++;
                        } else {
                            for (int i = 0; i < result; i++) {
                                dest[i] = tempDest[tempDest.length - result + i];
                            }
                        }
                    }
                } else {//计算完成，不需要借位
                    int correctDest = dest.length - result - isNegative1;
                    if (correctDest < 0) {
                        result = 0;
                    } else {
                        result += isNegative1;
                        if (isNegative1 == 1) {
                            dest[0] = (byte) 0xFF;
                        }
                        for (int i = isNegative1; i < result; i++) {
                            dest[i] = tempDest[i + tempDest.length - result];
                        }
                    }
                }
            }
        }
        return result;
    }

    public static int reverseByte(byte[] tempDest, int result) {
//        p("tempDest:", tempDest);
        int carry = 0;
        int trueLen = 0;
        tempDest[tempDest.length - 1]--;
        for (int i = tempDest.length - 1; i > tempDest.length - result - 1; i--) {
            int tempLow = tempDest[i] & 0x0F;
            int tempHigh = (tempDest[i] >> 4) & 0x0F;
            tempLow = reverseInt(tempLow);
            tempLow += carry;
            carry = 0;
            if (tempLow > 9) {
                tempLow -= 10;
                carry = 1;
            }

            tempHigh = reverseInt(tempHigh);
            tempHigh += carry;
            carry = 0;
            if (tempHigh > 9) {
                tempHigh -= 10;
                carry = 1;
            }
            tempDest[i] = (byte) (tempLow + (tempHigh << 4));
            if (tempDest[i] != 0) {
                trueLen = tempDest.length - i;
            }
        }
//        p("tempDest:", tempDest);
//        p("trueLen:" + trueLen);
        return trueLen;
    }

    public static int reverseInt(int lowOrHigh) {
        lowOrHigh = 9- lowOrHigh;
//        lowOrHigh = lowOrHigh + 6;
//        lowOrHigh = ~lowOrHigh;
//        lowOrHigh = lowOrHigh & 0x0F;
        return lowOrHigh;
    }


    public static void main(String[] args) {
        p("正 - 负 = 正");
        byte[] b1 = StrToByteArray("4444");
//        byte[] b1 = StrToByteArray("2222");
        p("b1", b1);
        byte[] b2 = StrToByteArray("FF8888");
//        byte[] b2 = StrToByteArray("FF11113333");
        p("b2", b2);
        byte[] dest1 = new byte[6];
        p("BCD码串的字节数1 : " + SubBCDInt(b1, b2, dest1));
        p("dest1", dest1);

        p("--------------------------1---------------------------");

        p("负 - 正 = 负");
        byte[] b3 = StrToByteArray("FF4444");
//        byte[] b3 = StrToByteArray("FF11113333");
        p("b3", b3);
        byte[] b4 = StrToByteArray("8888");
//        byte[] b4 = StrToByteArray("2222");
        p("b4", b4);
        byte[] dest2 = new byte[6];
        p("BCD码串的字节数2 : " + SubBCDInt(b3, b4, dest2));
        p("dest2", dest2);
        p("--------------------------2---------------------------");

        p("正（大） - 正（小） = 正");
        byte[] b5 = StrToByteArray("11113333");
        p("b5", b5);
        byte[] b6 = StrToByteArray("2222");
        p("b6", b6);
        byte[] dest3 = new byte[6];
        p("BCD码串的字节数3 : " + SubBCDInt(b5, b6, dest3));
        p("dest3", dest3);
        p("--------------------------3---------------------------");

        p("正（小） - 正（大） = 负");
        byte[] b7 = StrToByteArray("2222");
        p("b7", b7);
        byte[] b8 = StrToByteArray("11113333");
        p("b8", b8);
        byte[] dest4 = new byte[6];
        p("BCD码串的字节数4 : " + SubBCDInt(b7, b8, dest4));
        p("dest4", dest4);
        p("--------------------------4---------------------------");

        p("负（大） - 负（小） = 负");
        byte[] b9 = StrToByteArray("FF11113333");
        p("b9", b9);
        byte[] b10 = StrToByteArray("FF2222");
        p("b10", b10);
        byte[] dest5 = new byte[6];
        p("BCD码串的字节数5 : " + SubBCDInt(b9, b10, dest5));
        p("dest5", dest5);
        p("--------------------------5---------------------------");

        p("负（小） - 负（大） = 正");
        byte[] b11 = StrToByteArray("FF2222");
        p("b11", b11);
        byte[] b12 = StrToByteArray("FF11113333");
        p("b12", b12);
        byte[] dest6 = new byte[6];
        p("BCD码串的字节数6 : " + SubBCDInt(b11, b12, dest6));
        p("dest6", dest6);
        p("--------------------------6---------------------------");

        p("加零----   正 - 负 = 正");
        byte[] b13 = StrToByteArray("0004444");
        p("b13", b13);
        byte[] b14 = StrToByteArray("FF00008888");
        p("b14", b14);
        byte[] dest7 = new byte[6];
        p("BCD码串的字节数7 : " + SubBCDInt(b13, b14, dest7));
        p("dest7", dest7);

        p("--------------------------7---------------------------");

        p("加零----   负(大) - 负(小) = 负");
        byte[] b19 = StrToByteArray("FF00008888");
        p("b19", b19);
        byte[] b20 = StrToByteArray("FF0004444");
        p("b20", b20);
        byte[] dest10 = new byte[6];
        p("BCD码串的字节数10 : " + SubBCDInt(b19, b20, dest10));
        p("dest10", dest10);

        p("--------------------------10---------------------------");

        p("测试1");
        byte[] b15 = StrToByteArray("000012345678901234");
        p("b15", b15);
        byte[] b16 = StrToByteArray("0999999999999987654321");
        p("b16", b16);
        p("b15 - b16 = " + (12345678901234L - 99999999987654321L));
        byte[] dest8 = new byte[15];
        p("BCD码串的字节数7 : " + SubBCDInt(b15, b16, dest8));
        p("dest8", dest8);

        p("--------------------------8---------------------------");

        p("测试2");
        byte[] b17 = StrToByteArray("FF0012345678901234");
        p("b17", b17);
        byte[] b18 = StrToByteArray("ff0999999999999987654321");
        p("b18", b18);
        byte[] dest9 = new byte[15];
        p("BCD码串的字节数7 : " + SubBCDInt(b17, b18, dest9));
        p("dest9", dest9);

        p("--------------------------9---------------------------");

        p("测试 0 - 0");
        byte[] b21 = StrToByteArray("FF000000");
        p("b21", b21);
        byte[] b22 = StrToByteArray("00000");
        p("b22", b22);
        byte[] dest11 = new byte[15];
        p("BCD码串的字节数7 : " + SubBCDInt(b21, b22, dest11));
        p("dest11", dest11);

        p("--------------------------11---------------------------");

        p("测试 0 - 正数");
        byte[] b23 = StrToByteArray("FF00000");
        p("b23", b23);
        byte[] b24 = StrToByteArray("ff099999001023654012549999999987654321");
        p("b24", b24);
        byte[] dest12 = new byte[20];
        p("BCD码串的字节数7 : " + SubBCDInt(b23, b24, dest12));
        p("dest12", dest12);

        p("--------------------------12---------------------------");
    }

    /**
     * 字符串转 BCD 码串的byte数组
     *
     * @param str
     * @return
     */
    public static byte[] StrToByteArray(final String str) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //方法一：
//            char[] charArray = {c};
//            p(Integer.parseInt(new String(charArray)));
            //方法二：
//            p(Character.getNumericValue(c));
            arrayList.add(Character.getNumericValue(c));
        }
        byte[] num;
        if (arrayList.size() % 2 == 0) {
            num = new byte[arrayList.size() / 2];
            int isNegative = 0;
            int height = arrayList.get(0);
            int low = arrayList.get(1);
            if (height == 106 || height == 146 || low == 106 || low == 146) {
                height = 15;
                low = 15;
                isNegative = 1;
                num[0] = (byte) (height * 16 + low);
            }
            int i = isNegative;
            for (; i < num.length; i++) {
                num[i] = (byte) (arrayList.get(i * 2) * 16 + arrayList.get(i * 2 + 1));
            }
        } else {
            num = new byte[arrayList.size() / 2 + 1];

            int isNegative = 1;
            int height = arrayList.get(0);
            int low = arrayList.get(1);
            if (height == 15 || height == 15 || low == 15 || low == 15) {
                isNegative += 1;
                num[0] = (byte) (height * 16 + low);
                num[1] = (byte) (0 + arrayList.get(2));
            } else {
                num[0] = (byte) (0 + arrayList.get(0));
            }
            //从第一个开始，还是从第二个开始
            int i = isNegative;
            for (; i < num.length; i++) {
                num[i] = (byte) (arrayList.get(i * 2 - 1) * 16 + arrayList.get(i * 2));
            }
        }
        return num;
    }

    private static void p(int i) {
        if (printLog) {
            System.out.println(i);
        }
    }

    private static void p(String i) {
        if (printLog) {
            System.out.println(i);
        }
    }

    /**
     * 打印byte数组的高位和低位数字
     *
     * @param num
     */
    private static void p(String str, byte[] num) {
        p(str + ":");
        for (int i = 0; i < num.length; i++) {
            byte b = num[i];
            int height = (b >> 4) & 0x0F;
            int low = (b & 0x0F);
            if (height == 15 || low == 15) {
                System.out.print("FF");
            } else {
                System.out.print(height + "" + low);
            }
        }
        p("");
    }
    /**
     * 第一次修改意见
     *  1. SubBCDInt 函数超过 200 代码, 要学会代码分割和合并相同项;
     *  2. if (((symbolByte1 >> 4) & 0x0F) == 15 && (symbolByte1 & 0x0F) == 15) 这样效率不高, 需要转换思路, 只要 symbolByte1 == (byte)0xFF 即可;
     *  3. int len = len1 >= len2 ? len1 : len2 未处理 len1 或 len2 为 0 的 bug;
     *  4. byte[] tempDest = new byte[len + 1] 若是相加则不必分配数组, 论拆分函数的重要性;
     *  5. b1 = num1[len1 + isNegative1 - i] 取项效率不高, 可以换种思路去考虑;
     *  6. dest[0] = (byte) (15 * 16 + 15) 为什么不直接 (byte)0xFF;
     *  7. byte[] bb1 = new byte[result + 1] 一开始思路没有理清导致增加内存开销;
     *  8. result = SubBCDInt(bb1, tempDest, dest) 完全可以避免递归调用, 论算法的重要性;
     *  9. p("结果是负数") 调试时可以使用, 但提交时就必须去掉!
     *  10. 评分: 40分
     */

    /**
     * 第二次修改意见
     *  1. dealAdd 函数中问题如下:
     *      a. if (len1 - i > -1) 判断效率不高, 应该 if (i <= len1);
     *      b. if (len2 - i > -1) 判断效率不高, 应该 if (i <= len2);
     *      c. if (destLen - i > -1) 判断效率不高, 应该 if (i <= destLen);
     *      d. if ((low | height) == 0) 判断效率不高, 应该 if (low || height);
     *  2. dealSub 函数中问题如下:
     *      a. if (len1 - i > -1) 判断效率不高, 应该 if (i <= len1);
     *      b. if (len2 - i > -1) 判断效率不高, 应该 if (i <= len2);
     *      c. if ((low | height) == 0) 判断效率不高, 应该 if (low || height);
     *      d. result = reverseByte(tempDest, result) 效率太低, 应该转换思路, 否则效率无法提高;
     *  3. reverseByte 函数效率太低, 其实应该很简单, 只要 0 减去 Dest 值即可;
     *  4. reverseInt 函数效率太低, 直接 return 9 - lowOrHigh 即可;
     *  5. dealSub 函数应该换个思路去做, 否则就是在凑结果, 需要跳出现在的思路去考虑;
     *  6. 评分: 60分
     */

}
