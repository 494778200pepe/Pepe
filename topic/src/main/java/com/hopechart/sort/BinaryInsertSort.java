package com.hopechart.sort;

/**
 * 折半插入排序
 * @author wang
 * @date 2018/1/28.
 */

public class BinaryInsertSort {
    public static void main(String[] args) {
        int[] array = {21, 4, 5, 15, 8, 21, 3, 4, 9, 0, 12};
        binaryInsertSort(array);
    }

    public static void binaryInsertSort(int[] array) {
        p(array);
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int low = 0;
            int high = i - 1;
            if(array[i] < array[i - 1]){
                while (low <= high) {
                    int mid = (low + high) / 2;
                    if (temp < array[mid]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            }
            for (int j = i; j >= low + 1; j--) {
                array[j] = array[j - 1];
            }
            array[low] = temp;
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
     * 1、插入排序应该先判断 array[i] < array[i-1] 才进入二分定位。
     */
}


