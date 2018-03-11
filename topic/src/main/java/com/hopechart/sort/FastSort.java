package com.hopechart.sort;

/**
 * @author wang
 * @date 2018/3/7.
 */

public class FastSort {
    public static void main(String[] args) {
        int[] array = {1234, 99, 21, 4, 5, 15, 8, 21, 1, 54, -1, 0, -5, 11, 0, 3, 4, 9, 0, 12, -9};
        fastSort(array, 0, array.length - 1);
        p(array);
    }

    public static void fastSort(int[] array, int l, int r) {
        if (l < r) {
            int mid = array[l];
            int left = l, right = r;
            while (left < right) {
                while (left < right && array[right] >= array[left]) {
                    right--;
                }
                if (left < right) {
                    array[left] = array[right];
                }
                while (left < right && array[left] <= mid) {
                    left++;
                }
                if (left < right) {
                    array[right] = array[left];
                }
            }
            array[left] = mid;
            fastSort(array, l, left - 1);
            fastSort(array, left + 1, r);
        }
    }

    public static void p(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }
}

