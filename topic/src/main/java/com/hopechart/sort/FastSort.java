package com.hopechart.sort;

/**
 * @author wang
 * @date 2018/3/7.
 * 描述：以第一个值 M 作为支点，并挖坑，从右找比 M 小的，填到左边去，并挖坑，然后从左找比 M 大的，填到右边去。如此循环，直到左边和右边碰头。
 * 时间复杂度：
 * 空间复杂度：
 * 稳定性：不稳定。
 */

public class FastSort {
    public static void main(String[] args) {
        int[] array = {1234, 99, 21, 4, 5, 15, 8, 21, 1, 54, -1, 0, -5, 11, 0, 3, 4, 9, 0, 12, -9};
        fastSort(array, 0, array.length - 1);
        p(array);
    }

    public static void fastSort(int[] array, int l, int r) {
        if (l < r) {
            int left = l, right = r;
            int mid = array[left];
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

