package com.hopechart.sort;

/**
 * 冒泡排序
 * @author wang
 * @date 2018/1/30.
 */

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {21, 4, 5, 15, 8, 21, 3, 4, 9, 0, 12};
        bubbleSort(array);
    }


    public static void bubbleSort(int[] array) {
        if (null == array || array.length == 0) {
            return;
        }
        int j;
        int temp;
        p(array);
        for (int i = 0; i < array.length - 1; i++) {
            for (j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
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

