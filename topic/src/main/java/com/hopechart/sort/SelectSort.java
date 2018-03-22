package com.hopechart.sort;

/**
 * 选择排序
 * @author wang
 * @date 2018/1/28.
 * 描述：找最小值。从左边开始找，每次都标记最小的，然后互换。
 * 时间复杂度：
 * 空间复杂度：
 * 稳定性：不稳定。左边的最大值，可能因为与最小值互换，而变到最右边。
 */

public class SelectSort {

    public static void main(String[] args) {
        int[] array = {1234, 99, 21, 4, 5, 15, 8, 21, 1, 54, -1, 0, -5, 43532, 0, -1, 327327, -1010, 2, 3, 5, 4, 3, 9, 78, 55, -999, 11, 0, 3, 4, 9, 0, 12, -9};
        selectSort(array);
    }

    public static void selectSort(int[] array) {
        if (null == array || array.length == 0) {
            return;
        }
        int k;
        int j;
        p(array);
        for (int i = 0; i < array.length - 1; i++) {
            k = i;
            for (j = i + 1; j < array.length; j++) {
                if (array[k] > array[j]) {
                    k = j;
                }
            }
            if (k != i) {
                int temp = array[i];
                array[i] = array[k];
                array[k] = temp;
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
     * 1、选择排序中 for (int i = 0; i < array.length; i++) 判断条件应该为 i < array.length - 1;
     */
}

