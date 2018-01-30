package com.hopechart.sort;

/**
 * 选择排序
 * @author wang
 * @date 2018/1/28.
 */

public class SelectSort {

    public static void main(String[] args) {
        int[] array = {21, 4, 5, 15, 8, 21, 3, 4, 9, 0, 12};
        selectSort(array);
    }

    public static void selectSort(int[] array) {
        if (null == array || array.length == 0) {
            return;
        }
        int k;
        int j;
        p(array);
        for (int i = 0; i < array.length; i++) {
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

}
