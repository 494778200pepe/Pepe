package com.hopechart.topic;

import java.util.ArrayList;

/**
 * Created by wang on 2017/6/2.
 */

public class AddBCDInt {

    /**
     * 两个大的正整数相加之和
     *
     * @param num1 整数的BCD码数组1
     * @param num2 整数的BCD码数组2
     * @param dest 存放结果BCD码数组
     * @return -3,相加失败, 参数不合法;-2,相加失败, BCD码数组2存在不合法字符;-1,相加失败, BCD码数组1存在不合法字符;0,相加失败, 结果BCD码串的数组太小; >0,相加成功, 返回BCD码串的字节数
     */
    public static int AddBCDInt(final byte[] num1, final byte[] num2, byte[] dest) {
        int result = -3;
        int correctResult = 0;
        //-3,相加失败, 参数不合法;
        if (num1 != null && num2 != null && dest != null) {
            //两个数组的长度
            int len1 = num1.length;
            int len2 = num2.length;
            int destLen = dest.length;

            int len = len1 >= len2 ? len1 : len2;

            byte b1, b2;
            //高四位值
            int height, height1, height2;
            //低四位值
            int low, low1, low2;

            //不同byte间是否进位的标记
            int carry = 0;

            for (int i = 1; i <= len; i++) {
                result = i;
                if (len1 - i > -1) {
                    b1 = num1[len1 - i];
                    //高四位值
                    height1 = (b1 >> 4) & 0x0F;
                    //低四位值
                    low1 = (b1 & 0x0F);
                    if (height1 > 9 || low1 > 9) {
                        result = -1;
                        break;
                    }
                } else {
                    height1 = 0;
                    low1 = 0;
                }
                if (len2 - i > -1) {
                    b2 = num2[len2 - i];
                    height2 = (b2 >> 4) & 0x0F;
                    low2 = (b2 & 0x0F);
                    if (height2 > 9 || low2 > 9) {
                        result = -2;
                        break;
                    }
                } else {
                    height2 = 0;
                    low2 = 0;
                }

//            p("b1: " + height1 + low1 + " b2: " + height2 + low2);

                low = low1 + low2 + carry;
                //取消byte间进位的标记
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
//            p("height: " + height + " low: " + low);

                if (destLen - i > -1) {
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
                    result = -3;
                } else {
                    result = result - correctResult;
                    int correctDest = destLen - result;
                    for (int i = 0; i < result; i++) {
                        dest[i] = dest[i + correctDest];
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

//        byte[] b1 = StrToByteArray("18234");
        byte[] b1 = StrToByteArray("000012345678901234");
//        byte[] b1 = new byte[]{};
//        byte[] b1 = StrToByteArray("12345678");
        p("b1", b1);
//        byte[] b2 = StrToByteArray("4455");
        byte[] b2 = StrToByteArray("0599999999999987654321");
//        byte[] b2 = StrToByteArray("0012341234a0");
        p("b2", b2);
        byte[] dest = new byte[20];
        p("BCD码串的字节数1 : " + AddBCDInt(b1, b2, dest));
        p("dest", dest);
        byte[] b3 = new byte[]{};
        byte[] b4 = new byte[]{};
        p("BCD码串的字节数3 : " + AddBCDInt(b3, b4, dest));
        p("dest", dest);
        byte[] b5 = StrToByteArray("000011234");
        byte[] b6 = StrToByteArray("00001234");
        p("BCD码串的字节数5 : " + AddBCDInt(b5, b6, dest));
        p("dest", dest);

        byte[] b7 = StrToByteArray("0000000");
        byte[] b8 = StrToByteArray("00000000000");
        p("BCD码串的字节数7 : " + AddBCDInt(b7, b8, dest));
        p("dest", dest);

        byte[] dest2 = new byte[3];
        byte[] b9 = StrToByteArray("000011234");
        byte[] b10 = StrToByteArray("00001234");
        p("BCD码串的字节数9 :");
        p("BCD码串的字节数9 : " + AddBCDInt(b9, b10, dest2));
        p("dest2", dest2);

        byte[] dest3 = new byte[2];
        byte[] b11 = StrToByteArray("000011234");
        byte[] b12 = StrToByteArray("00001234");
        p("BCD码串的字节数9 :");
        p("BCD码串的字节数9 : " + AddBCDInt(b11, b12, dest3));
        p("dest3", dest3);
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
            for (int i = 0; i < num.length; i++) {
                num[i] = (byte) (arrayList.get(i * 2) * 16 + arrayList.get(i * 2 + 1));
            }
        } else {
            num = new byte[arrayList.size() / 2 + 1];
            num[0] = (byte) (0 + arrayList.get(0));
            for (int i = 1; i < num.length; i++) {
                num[i] = (byte) (arrayList.get(i * 2 - 1) * 16 + arrayList.get(i * 2));
            }
        }
        return num;
    }

    private static void p(int i) {
        System.out.println(i);
    }

    private static void p(String i) {
        System.out.println(i);
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
            System.out.print(height + "" + low);
        }
        p("");
    }

    /**
     * 第一次修改意见
     *  1. len = destLen >= len ? destLen : len 思维定势, '?' 用得很顺手会阻止自己的思路, 其实直接: if (destLen >= len) len = destLen;
     *  2. for (int i = 1; i < len + 1; i++) 循环判断效率不高, 改为: i <= len;
     *  3. height1 = ((b1 & 0xf0) >> 4) 应该改为: height1 = (b1 >> 4) & 0x0F;
     *  4. if (height1 > 9 || height1 < 0 || low1 > 9 || low1 < 0) 判断需要优化, 改为:if (height1 > 9 || low1 > 9) 即可;
     *  5. low = low1 + low2 + (carry ? 1 : 0) 为什么不把 carry 改为 int 类型? 这样就不必判断直接相加即可;
     *  6. if (low == 0 && height == 0) 可以为 if ((low | height) == 0);
     *  7. if (low != 0 || height != 0) 时未处理 else correctResult++ 的 bug;
     *  8. result = result - correctResult 当 num1 和 num2 都为 0 时返回值为 0 的 bug;
     *  9. return result; 相加结果未移位的 bug(我只想说明在函数返回之前都没有把结果项移到开始位置，即：“返回结果的 BCD 码串第一个字节不能为 0”。);
     *  10. 评分: 40分
     */

    /**
     * 第二次修改意见
     *  1. if (low > 9) { low -= 10; height = height1 + height2 + 1;} else {..} 代码可以优化, 先 height = height1 + height2, 若 low > 9 则 height++ 即可;
     *  2. if (result == correctResult) result = -3; 返回值错误的 bug, 若相等则表示两个值都为 0;
     *  3. BCD 字节值相加效率不高, 需要换种思路去解决;
     *  4. 评分: 85分
     */

    int AddWithoutArithmetic(int num1, int num2)
    {
        if(num2 == 0)
            return num1;
        int sum = num1 ^ num2;   //异或运算
        int carry = (num1 & num2) << 1;     //对0加0、0加1、1加0而言，都不会产生进位，只有1加1时，会向前产生一个进位。因此两个数先做位与运算，然后再向左移动一位。
        return AddWithoutArithmetic(sum, carry);
    }


}
