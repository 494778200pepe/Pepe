package com.hopechart.sort;

/**
 * 冒泡排序
 * @author wang
 * @date 2018/1/30.
 * 描述：前一个比后一个大，就交换，循环n遍。每次确定一个最大值。
 * 时间复杂度：
 * 空间复杂度：
 * 稳定性：不稳定。左边的最大值会比右边的最大值，更先到达右端，相对顺序就变了。
 */

public class BubbleSort {

    public static void main(String[] args) {
        float f = 0.3f;
        double d = 0.3d;
        // 结果是 不相等
        if (f == d) {
            System.out.println("相等");
        } else {
            System.out.println("不相等");
        }
        int[] array = {1234, 99, 21, 4, 5, 15, 8, 21, 1, 54, -1, 0, -5, 43532, 0, -1, 327327, -1010, 2, 3, 5, 4, 3, 9, 78, 55, -999, 11, 0, 3, 4, 9, 0, 12, -9};
        bubbleSort(array);
    }


    public static void bubbleSort(int[] array) {
        if (null == array || array.length == 0) {
            return;
        }
        int j;
        int temp;
        boolean tag;
        p(array);
        for (int i = 0; i < array.length - 1; i++) {
            tag = true;
            for (j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    tag = false;
                }
            }
            if (tag) {
                break;
            }
        }
        p(array);
    }

    public static void p(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }

    /**
     * 叶工修改意见：
     * 1、for (int i = 0; i < array.length - 2; i++) 循环判断有问题，应该 i < array.length - 1;
     * 2、for (j = 0; j < array.length - 1 - i; j++) 之前应该加一个标志, 可以提前跳出循环。
     */
}

