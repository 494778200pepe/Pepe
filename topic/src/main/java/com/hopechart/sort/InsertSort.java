package com.hopechart.sort;

/**
 * 插入排序
 * @author wang
 * @date 2018/1/30.
 */

public class InsertSort {
    public static void main(String[] args) {
        int[] array = {21, 4, 5, 15, 8, 21, 3, 4, 9, 0, 12};
        insertSort(array);
    }

    public static void insertSort(int[] array) {
        if (null == array || array.length == 0) {
            return;
        }
        int j;
        int target;
        p(array);
        for (int i = 1; i < array.length; i++) {
            j = i;
            target = array[i];
            while (j > 0 && target < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = target;
        }
        p(array);
    }

    public static void p(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }
}
